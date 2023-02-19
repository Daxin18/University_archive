//Kamil Ciaglo

//zadanie 2
//a) funkcyjnie

//b) imperatywnie
def modifiedPascallI(n: Int):Array[Int] =
  var prev = new Array[Int](n+1)
  val res = new Array[Int](n+1)
  var m = 0
  var i = 0
  while m <= n do
    prev(m) = 1
    res(m) = 1
    if m != n then
      while i<m do
        if m%2 == 1 then
          res(i+1) = prev(i) + prev(i+1)
        else
          res(i+1) = prev(i) - prev(i+1)
        i = i + 1
    prev = res.clone()
    i = 0
    m = m + 1
  res

modifiedPascallI(0)
modifiedPascallI(1)
modifiedPascallI(2)
modifiedPascallI(3)
modifiedPascallI(4)
modifiedPascallI(5)
modifiedPascallI(6)
