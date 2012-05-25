module ArrayDistanceTests

open NUnit.Framework
open ArrayDistance
open Swensen.Unquote.Assertions

[<Test>]
let ``simple example`` () = 
  calculateDL ("ac".ToCharArray()) ("abc".ToCharArray()) =? 1
  calculateDL ("ab".ToCharArray()) ("abcd".ToCharArray()) =? 2
  calculateDL ("ac".ToCharArray()) ("cab".ToCharArray()) =? 2

[<Test>]
let ``more involved example`` () = 
  calculateDL ("Åäö".ToCharArray()) ("4ad 3".ToCharArray()) =? 5

[<TestFixture>]
module StringDistanceTests =

  open StringDistance

  [<Test>]
  let ``edit distance for capitalized characters`` () =
    calculateEDL "åäÖ" "ö" =? 2 // not 3, which would be case sensitive