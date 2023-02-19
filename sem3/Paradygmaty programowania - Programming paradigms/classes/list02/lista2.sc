//Kamil Ciaglo

//zadanie 1
//na deklaracji

//zadanie 2
def fib(n: Int): Int =
  if n<0 then throw new Exception(s"ujemny argument: $n")
  else if n==0 then 0
  else if n==1 then 1
  else fib(n-1) + fib(n-2)

def fibTail(n:Int): Int =
{
  if n < 0 then throw new Exception(s"ujemny argument: $n")
  else
  {
    def fibTailA(x: Int, n1: Int, n2: Int): Int =
    {
      if x == 1 then n2
      else fibTailA(x - 1, n2, n1 + n2)
    }
    if n == 0 then 0
    else if n == 1 then 1
    else fibTailA(n, 0, 1)
  }
}

//testy
//specjalnie najpierw ustawilem fibTail, ktore dziala szybciej
//widac wtedy przerwe miedzy prawie natychmiastowym fibTail
//a dosc wolnym fib
fibTail(42) == 267914296
fib(42) == 267914296

//ponizsze testy pokazuja, ze trzymajac sie specyfikacji
//nie obliczymy tak duzych liczb, zastosowanie BigInt
//mogloby tu pomoc
//ponadto widac tu wyraznie, ze metoda fib nie daje rady
//nadazyc, podczas gdy fibTail liczy szybko wynik
//zakomentuje testy dla szybszych wywolan kolejnych zadan
//gdyz czekanie na fib(80) zajmuje strasznie duzo czasu
//fibTail(80)
//fib(80)

//testy sprawdzajace ujemne wartosci
//fibTail(-3) == -1
//fib(-3) == -1


//zadanie 3
val dok = 1.0E-15 //dokladnosc przyblizenia (dla latwej modyfikacji kodu)
def root3(a:Double):Double =
{
  def root3A(a:Double, accum:Double): Double =
  {
    if math.abs(math.pow(accum,3) - a) <= dok * math.abs(a)
      then accum
    else root3A(a, accum+(a/math.pow(accum,2) - accum)/3)
  }
  if a>1 then root3A(a, a/3)
  else root3A(a, a)
}

val root3F = root3 _

//testy
math.abs(math.pow(3,1.0/3.0) - root3(3)) <= dok
math.abs(math.pow(3,1.0/3.0) - root3F(3)) <= dok
math.abs(math.pow(27,1.0/3.0) - root3(27)) <= dok
math.abs(math.pow(27,1.0/3.0) - root3F(27)) <= dok
math.abs(math.pow(125,1.0/3.0) - root3(125)) <= dok
math.abs(math.pow(125,1.0/3.0) - root3F(125)) <= dok

//1.ponizsze testy zwracaja false, mimo ze wynik jest poprawny
//ciekaw jestem, co moze to powodowac
//2.jesli zakomentujemy "<=dok", wychodzi w wyniku NaN, nie jestem pewien czemu
//3.problem lezy w fukcji math.pow(-64,1.0/3.0), najwyrazniej dla liczb ujemnych
//zwraca ona Nan... zakomentuje testy, zeby byly "archiwalne" i zapisze
//je z oczekiwanym wynikiem (-4.0) zamiast math.pow....
//math.abs(math.pow(-64,1.0/3.0) - root3(-64)) <= dok
//math.abs(math.pow(-64,1.0/3.0) - root3F(-64)) <= dok
math.abs(-4.0 - root3(-64)) <=dok
math.abs(-4.0 - root3F(-64)) <=dok

//zadanie 4
//zmienna x nazywam w zaleznosci od podpunktu [a) xa, b) xb1, xb2]
//aby uniknac ostrzerzen o ponownym definiowaniu zmiennej o tej
//samej nazwie
//a)
val List(_,_,xa,_,_) = List(-2,-1,0,1,2)
//b)
val List((_,_),(xb1,_)) = List((1,2),(0,1))
val List(_,(xb2,_)) = List((1,2),(0,1))

//zadanie 5
def initSegment[A](xs: List[A], ys: List[A]): Boolean =
{
  if xs == Nil then true
  else if ys == Nil then false
  else
  {
    (xs, ys) match
      case (hx::tx, hy::ty) =>
        if hx == hy then initSegment(tx, ty)
        else false
      case _ => false //linia dopisana tylko, zeby nie bylo ostrzezenia
      //"match may not be exhaustive."
  }
}

//testy
initSegment(List(), List(1,2,3)) == true
initSegment(List(1,2,3),List(1,2,3)) == true
initSegment(List(1,2,3),List(1,2,3,4,5)) == true
initSegment(List(1,2,4),List(1,2,3,4,5)) == false
initSegment(List(1,2,3,4,5),List(1,2,3)) == false
//koercja int -> char (lub char -> int ?) sprawia, ze
//test dziala i zwraca true
initSegment(List(32,97),List(' ','a','A')) == true

//zadanie 6
//a)
def replaceNth[A](xs:List[A], n:Int, x:A):List[A] =
{
  if n < 0 then throw new Exception(s"ujemny argument: $n")
  else if xs == Nil then Nil
  else
  {
    val hx::tx = xs
    if n==0 then x :: tx
    else hx::replaceNth(tx, n-1, x)
  }
}

//testy
replaceNth(List('o','l','a','m','a','k','o','t','a'),1,'s') == List('o','s','a','m','a','k','o','t','a')
replaceNth(List(1,2,3,4,5,6,6,8,9), 6, 7) == List(1,2,3,4,5,6,7,8,9)
replaceNth(List(0,1,2),5,8) == List(0,1,2)
//replaceNth(List(0,2),-1,'a')
replaceNth(List(0,2),1,'a') == List(0,97)

//b)
//osobno - nie zrobione

