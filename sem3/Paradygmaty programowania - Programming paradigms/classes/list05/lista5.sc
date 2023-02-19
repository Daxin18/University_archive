//Kamil Ciaglo

//zadanie 1
def lrepeat[A](k: Int)(lxs: LazyList[A]): LazyList[A] =
  def lrepeatIter(n: Int)(llist: LazyList[A]):LazyList[A] =
    (n,llist) match{
      case (_,LazyList()) => LazyList()
      case (0, _) => lrepeatIter(k)(llist.tail)
      case(_,_) => llist.head#::lrepeatIter(n-1)(llist)
    }
  lrepeatIter(k)(lxs)

//testy
lrepeat(3)(LazyList('a','b','c','d')).toList == List('a', 'a', 'a', 'b', 'b', 'b', 'c', 'c', 'c', 'd', 'd', 'd')
lrepeat(3)(LazyList.from(1)).take(15).toList == List(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5)
lrepeat(3)(LazyList()).take(15).toList == List()
lrepeat(5)(LazyList.from(9)).take(6).toList == List(9,9,9,9,9,10)
lrepeat(0)(LazyList(1,2,3)).toList == List();
lrepeat(-1)(LazyList.from(-1)).take(5).toList == List(-1,-1,-1,-1,-1)

//zadanie 2
def lfib =
  def lfibIter(first: Int)(second: Int):LazyList[Int]=
    first#::(lfibIter(second)(first+second))
  lfibIter(0)(1)

//testy
lfib.take(10).toList == List(0,1,1,2,3,5,8,13,21,34)

//zadanie 3 - nie zrobione


