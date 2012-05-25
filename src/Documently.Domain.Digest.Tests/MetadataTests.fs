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
    | Create text ->
      { 
        Originals = text
      ; Features = Map.empty (* TODO extract feature vector *) 
      }
      , [CreatedDigest]

  static member New () = Digest(State.Empty)

[<Test>]
let ``apply create command`` () =
  let d = Digest.New()
  let d', evts = d.Handle <| Create(["Hello World"; "Goodbye world"])
  evts =? [CreatedDigest]

