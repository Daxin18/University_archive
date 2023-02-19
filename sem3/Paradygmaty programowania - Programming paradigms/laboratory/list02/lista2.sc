//Kamil Ciaglo
import scala.annotation.tailrec

//zadanie 1
def find[A] (xs: List[A]):Function[A,Boolean] = (x:A) =>
    xs match
    {
      case Nil => false
      case hx::tx => hx == x || find(tx)(x)
    }

def find123 = find(List(1,2,3))
find123(1) == true
find123(2) == true
find123(3) == true
find123(4) == false

def findABCD = find(List('A','B','C','D'))
findABCD('B') == true
findABCD("B") == false
findABCD(66) == true //koercja typow

def findString = find(List("ala","ma","kota"))
findString("ala") == true
findString(1) == false
findString("ola") == false

val findEmpty = find(List())
findEmpty(1)

//zadanie 2
def split2Rec[A](xs: List[A]):(List[A],List[A]) =
{
  def split2Rec1(xs:List[A],k:(List[A],List[A])):(List[A],List[A]) =
  {
    if xs == Nil then k
    else
    xs match
    {
      case hx::tx => split2Rec2(tx,(hx::k._1,k._2))
      case _ => k
    }
  }
  def split2Rec2(xs:List[A],k:(List[A],List[A])):(List[A],List[A]) =
  {
    if xs == Nil then k
    else
    xs match
    {
      case hx::tx => split2Rec1(tx,(k._1,hx::k._2))
      case _ => k
    }
  }
  split2Rec1(xs,(List(),List()))
}

def split2Tail[A](xs:List[A]):(List[A],List[A]) =
{
  @tailrec
  def split2Tail1(xs:List[A], k:(List[A],List[A]), n:Int):(List[A],List[A]) =
  {
    (xs, n) match {
      case (Nil, _) => k
      case (hx :: tx, 1) => split2Tail1(tx,(hx::k._1,k._2),2)
      case (hx :: tx, 2) => split2Tail1(tx,(k._1,hx::k._2),1)
      case _ => ???
    }
  }
  split2Tail1(xs,(List(),List()),1)
}


split2Tail(List('a','b','a','b','a')) == (List('a','a','a'),List('b','b'))
split2Rec(List('a','b','a','b','a')) == (List('a','a','a'),List('b','b'))
split2Tail(List()) == (Nil,Nil)
split2Rec(List()) == (Nil, Nil)
split2Tail(List(1,2,3,4,5,6,7,8,9,10,11,12,13,14)) == (List(13,11,9,7,5,3,1),List(14,12,10,8,6,4,2))
split2Rec(List(1,2,3,4,5,6,7,8,9,10,11,12,13,14)) == (List(13,11,9,7,5,3,1),List(14,12,10,8,6,4,2))
