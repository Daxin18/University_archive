// Kamil Ciaglo

//testy wyrzucajace bledy sa zakomentowane oraz
//nie maja w sobie porownan do oczekiwanego wyniku, bo
//oczekiwany byl blad... w OCamlu wyglada to tak samo

//zadanie 1
def flatten1[A](xss: List[List[A]]): List[A] =
  if xss==Nil then Nil
  else xss.head ::: flatten1(xss.tail)

//przyklad z listy
flatten1(List(List(5,6),List(1,2,3))) == List(5,6,1,2,3)
//reszta testow sprawdzajaca, czy dodanie pustych list wplywa
//na oczekiwany wynik
flatten1(List(List(5,6),List(1,2,3), List())) == List(5,6,1,2,3)
flatten1(List(List(5,6), List(),List(1,2,3))) == List(5,6,1,2,3)
flatten1(List()) == Nil
flatten1(List(List(), List())) == Nil
//flatten1(List(5,6,7,8,9))

//zadanie 2
def count[A] (x: A, xs: List[A]): Int =
  if xs!= Nil then
    (
      if xs.head == x then count(x, xs.tail) + 1
      else count(x, xs.tail)
    )
  else 0

//przyklad z listy
count('a',List('a','l','a')) == 2
//reszta testow sprawdzajaca, czy funkcja dziala poprawnie
count(' ',List('a','l','a')) == 0
count('a',List('A','L','A')) == 0
count('a',List('a','n','a','n','a','s')) == 3
count(97,List('a','n','a','n','a','s')) == 3
count('a',List()) == 0
//count(List(), 'a')
count(List(), List(List(),List(1,2,3))) == 1

//zadanie 3
def replicate[A] (x: A, n: Int): List[A] =
  if n>0 then (List(x) ::: replicate(x, (n-1)))
  else Nil

//przyklad z listy
replicate("la",3) == List("la","la","la")
//reszta testow sprawdzajaca, czy funkcja dziala poprawnie
replicate('a',0) == List()
replicate('A', 4) == List('A','A','A','A')
replicate("Podanie ujemnej liczby skutkuje pusta lista", -5) == List()
replicate("Sprawdzam tylko, czy pusta lista i Nil to to samo", -5) == Nil
replicate(Nil,5) == List(Nil,Nil,Nil,Nil,Nil)
replicate(Nil,5) == List(List(),Nil,Nil,List(),Nil)
replicate('A',-5) == List()
//Podanie chara zamiast inta (liczby powtorzen obiektu)
//skutkuje powtorzeniem obiektu tyle razy, jaka liczba
//odpowiada danemu charowi w kodzie ASCII (spacja - 32)
//poczatkowo spodziewalem sie w tym tescie bledu
replicate('A',' ') == List('A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A','A')
//po tym tescie sprawdzilem tez inne typy, jednak one wyrzucaja juz blad
//replicate('A',"string")
//replicate('A',true) == List('A')
//replicate('A',Nil)
//replicate('A',2.0) == List('A','A')

//zadanie 4
def sqrtList(xs: List[Int]): List[Int] =
  if xs==Nil then Nil
  else List(xs.head * xs.head) ::: sqrtList(xs.tail)
val sqrtListF = sqrtList _

//przyklad z listy
sqrtList(List(1,2,3,-4)) == List(1,4,9,16)
sqrtListF(List(1,2,3,-4)) == List(1,4,9,16)
//reszta testow sprawdzajaca, czy funkcja dziala poprawnie
sqrtList(List(-1,0,3)) == List(1,0,9)
sqrtListF(List(-1,0,3)) == List(1,0,9)
sqrtList(List()) == List()
sqrtListF(List()) == List()
sqrtList(List(' ')) == List(1024)
sqrtListF(List(' ')) == List(1024)
//sqrtList(8)
//sqrtListF(8)

//zadanie 5
def palindrome[A] (xs:List[A]): Boolean =
    if xs.reverse == xs then true
    else false

//przyklad z listy
palindrome(List('a','l','a')) == true
//reszta testow
palindrome(List('k','a','j','a','k')) == true
palindrome(List('r','o','w','e','r')) == false
palindrome(List(1,2,3,3,2,1)) == true
palindrome(List()) == true
//palindrome("kajak")

//zadanie 6
def listLength[A] (xs:List[A]): Int =
    if xs == Nil then 0
    else listLength(xs.tail) + 1

//przyklad z listy
//*nie ma*
//reszta testow
listLength(List()) == 0
listLength(List(1,2,3,4,5)) == 5
listLength(List('a','l','a')) == 3
listLength(List('a','n','a','n','a','s')) == 6
listLength(List(List(1,2,3),List(2,3,4),List(3,4,5))) == 3
//listLength(8)
//listLength("String")

//zadanie 7
//na deklaracji - nie zrobione







