//Kamil Ciaglo

//zadanie 1 - nie zrobione

//zadanie 2
//a)
def swap(tab: Array[Int], i:Int, j:Int) =
  val aux = tab(i)
  tab(i) = tab(j)
  tab(j) = aux
//b)
def choose_pivot(tab:Array[Int] ,m:Int ,n:Int) =
  tab((m+n)/2)

def partition(tab:Array[Int], l:Int, r:Int) =
  var i = l
  var j = r
  val pivot = choose_pivot(tab, l, r)
  while i <= j do
    while tab(i) < pivot do
      i = i + 1
    while pivot < tab(j) do
      j = j - 1
    if i <= j then
      swap(tab, i, j)
      i = i + 1
      j = j - 1
  (i,j)
//c)
def quick(tab: Array[Int], l: Int, r: Int):Unit =
  if l < r then
    val (i, j) = partition(tab, l, r)
    if (j - l) < (r - i) then
      quick(tab, l, j)
      quick(tab, i, r)
    else
      quick(tab, i, r)
      quick(tab, l, j)
  else
    ()
//d)
def quicksort(tab:Array[Int]) =
  quick(tab, 0, tab.length - 1)

//testy
val testTab1 = Array(3,7,4,6,2,1,5,0)
val testTab2 = Array(0,1,2,3,4,5,6,7)
val testTab3 = Array(7,6,5,4,3,2,1,0)
val testTab4 = Array[Int]()

quicksort(testTab1)
quicksort(testTab2)
quicksort(testTab3)
quicksort(testTab4)

testTab1
testTab2
testTab3
testTab4
