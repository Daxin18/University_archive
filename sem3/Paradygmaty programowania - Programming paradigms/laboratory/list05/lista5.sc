//Kamil Ciaglo

//zadanie 2
def sublistsL[A](ll:LazyList[A]):LazyList[List[A]] =
{
  def sublistLIter(ll:LazyList[A], fl:LazyList[List[A]]):LazyList[List[A]] =
  {
    ll match
    {
      case h#::t => List(h)#::sublistLIter(t, fl).map(x=>h::x)
      case _ => fl
    }
  }
  sublistLIter(ll, LazyList())
}

def sublistsL[A](ll:LazyList[A]):LazyList[List[A]] =
{
  ll match {
    case h #:: t => List(h) #:: sublistsL(t).map(x => h :: x)
  }
}

//testy
val test1 = LazyList.from(1)
sublistsL(test1).take(3)
sublistsL(test1).take(3).toList
sublistsL(LazyList()).take(3).toList

