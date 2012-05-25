module MetadataTests

open NUnit.Framework
open Swensen.Unquote.Assertions

type DigestCommands =
  | Create of Doc list
and Doc = string

type DigestEvents =
  | CreatedDigest
  
type State = 
  { Originals : Document list
  ; Features : FeatureVector }
  static member Empty = { Originals = [] ; Features = Map.empty }
and Document = string
and FeatureVector = Map<string, string>

type Digest(state) =

  member x.Handle = function
    | Create [] -> failwith "cannot handle command with no documents"
    | Create text -> 
      // let state' = add_data somedata
      x, [CreatedDigest (* state' *)]

  member x.Apply = function
    | CreatedDigest (* state' *) -> x 
      // TODO: 
      // Digest(state')

  static member New () = Digest(State.Empty)

[<Test>]
let ``apply create command`` () =
  let d = Digest.New()
  let d', evts = d.Handle <| Create(["Hello World"; "Goodbye world"])
  evts =? [CreatedDigest]

