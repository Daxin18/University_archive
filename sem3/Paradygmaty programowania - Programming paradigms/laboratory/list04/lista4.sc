//Kamil Ciaglo

//zadanie 1
sealed trait tree3[+A]
  case object Empty extends tree3[Nothing]
  case class Node[+A](elem: A, left: tree3[A], middle: tree3[A], right: tree3[A]) extends tree3[A]

def mapTree3[A](f:A => A, t: tree3[A]): tree3[A] =
  t match {
    case Empty => Empty
    case Node(e:A, l:tree3[A], m:tree3[A], r:tree3[A]) => Node (f(e), mapTree3(f,l), mapTree3(f,m), mapTree3(f,r))
  }

val t1 = Node(1,
              Node(2,
                Empty,
                Empty,
                Node(5,
                  Empty,
                  Empty,
                  Empty)),
              Node(3,
                Empty,
                Empty,
                Empty),
              Node(4,
                Node(6,
                  Empty,
                  Empty,
                  Empty),
                Empty,
                Empty))

mapTree3[Int](x => x+1, t1)
mapTree3[Int](x => x*x, t1)

//zadanie 2
sealed trait word
  case class Word(a:Char,b:String)

sealed trait sentence
  case class Affirmative(a:Word, b:List[Word]) extends sentence
  case class Question(a:Word, b:List[Word]) extends sentence
  case class Exclamation(a:Word, b:List[Word]) extends sentence
sealed trait text
  class Text(a:sentence, b:List[sentence])

val text1 = Text(Affirmative(Word('t',"o"), List(Word('c',"hyba"), Word('d',"ziala"))),
                List(Question(Word('t',"ak"),List()),
                Exclamation(Word('t',"ak"),List(Word('t',"o"),Word('d',"ziala")))))

