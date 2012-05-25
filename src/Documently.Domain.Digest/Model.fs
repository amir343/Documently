module Model

type Actor<'a> = MailboxProcessor<'a>

type SupMsg = WaitForDone of AsyncReplyChannel<string>
type ProgramState = RunNumber of int * Actor<WorkerMsg> option
and WorkerMsg = Begin of Id * AsyncReplyChannel<string>
and Id = int

let startComputation () = Actor.Start(fun inbox ->
  async { 
    let! Begin(id, chan) = inbox.Receive()
    printfn "Running Computation"
    do! Async.Sleep(20) // zZz
    chan.Reply(sprintf "'%i is done!'" id) })

let sup () = Actor.Start(fun inbox ->
  let rec loop state =
    async {
      match state with
      | RunNumber(_, None) -> return! loop <| RunNumber(1, Some(startComputation ()))
      | RunNumber(run, Some(active)) ->
        let! completed = active.PostAndAsyncReply(fun chan -> Begin(run, chan))
        printfn "sup observed: %s" completed
        let active' = Some(startComputation ())
        if run <> 5 then return! loop <| RunNumber(run + 1, active')
        else return! isDone () }
  and isDone () =
    async {
      let! WaitForDone(chan) = inbox.Receive()
      return chan.Reply("all done") }

  loop <| RunNumber(0, None))

//[<EntryPoint>]
let main args =
  printfn "%s" <| (sup ()).PostAndReply(fun chan -> WaitForDone(chan))
  0
