//Kamil Ciaglo

//zadanie 1
def revNComp[A](f: Function[A, A], n: Int): A => List[A] = x =>
  {
    def tab[A](xs:List[A], n:Int, f:Function[A,A], x:A):List[A] =
    {
      if n<1 then xs
      else xs match
      {
        case Nil => tab (List(x), n-1, f, x)
        case h::t => tab ((f(h))::xs, n-1, f, x)
      }
    }
    tab(Nil, n, f, x)
  }

revNComp[Int](x=>(-x),5)(1)
revNComp[Int](x=>(x*x),5)(2)
revNComp[Int](x=>(x+2),10)(0)
revNComp[Int](x=>x, -10)(5)
revNComp[Double](x=>x, 0)(5)
revNComp[Double](x=>(x/2),5)(10)

//zadanie 2
//zalozenie - n jest poprawne
def area (a:Double, b:Double, f:Function[Double, Double], n:Int):Double =
  {
    val dist = ((b-a)/n)
    val xs = List.range(0,n)
    ((xs map (x=>dist*f(a+(x*dist)))) foldLeft 0.0) ((sum, x) => sum + x)
  }
area(0,1, (x=>x), 10000)
area(1,3, (x=>x*x), 10000)
area(-1,1, (x=>x), 2)
area(-2,2, (x=>x*x), 10000)
