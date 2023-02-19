//Kamil Ciaglo

//zadanie 1
//na deklaracji

//zadanie 2
//zrobione tylko w OCamlu

//zadanie 3
def sumProd (xs:List[Int]) =
  xs match
  {
    case Nil => (0,1)
    case _ => (xs foldLeft (0,1)) ((k:(Int,Int),x:Int) => (x+k._1,x*k._2))
  }

//testy
sumProd(List(1,1,1,1,1)) == (5,1)
sumProd(List(1,2,3,4)) == (10,24)
sumProd(List(0,1,2,3)) == (6,0)
sumProd(List(1,2,3,-1)) == (5,-6)
sumProd(List()) == (0,1)

//zadanie 4
//na deklaracji

//zadanie 5
//nie zrobione
