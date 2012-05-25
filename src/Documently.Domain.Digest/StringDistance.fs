module StringDistance

open ArrayDistance

/// Calculates the edit string distance
let calculateEDL (s1 : string) (s2 : string) =
  calculateDL (s1.ToLowerInvariant().ToCharArray()) (s2.ToLowerInvariant().ToCharArray())