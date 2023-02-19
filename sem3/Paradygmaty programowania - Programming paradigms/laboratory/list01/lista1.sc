//Kamil Ciaglo

//zadanie 1
def reverse4[A,B,C,D](x:(A,B,C,D)) = (x._4, x._3, x._2, x._1)

reverse4 ((1, 2<4, 0, 2.0)) == (2.0, 0, true, 1)
reverse4 ((1, 2.5, 'a', "Ala")) == ("Ala", 'a', 2.5, 1)
//reverse4 ((1)) //<-- blad, bo podana krotka jest za mala
//reverse4((1,2,3,4,5)) //<-- blad, bo podana krotka jest za duza

//zadanie 2
def substitute[A](x:(List[A],A,A)):List[A] =
  if x._1 == Nil then Nil
  else if x._1.head == x._2 then x._3 :: substitute(x._1.tail, x._2, x._3)
  else x._1.head :: substitute(x._1.tail, x._2, x._3)

substitute(List(1,2,3,4,5),2,3) == List(1,3,3,4,5)
substitute(List(1,2,3,4,5),0,3) == List(1,2,3,4,5)
substitute(List(1,2,3,4,5),3,Nil)// == List(1,2,Nil,4,5)
substitute(List(1,2,3,4,5),Nil,3) == List(1,2,3,4,5)
substitute(List(1,2,3,4,5),'a',3) == List(1,2,3,4,5)

//zadanie 3
def insert[A](x:(List[A],A,Int)): List[A] =
  if x._3 <= 0 then x._2 ::x._1 //jesli mamy wstawiac, badz dostalismy ujemna liczbe
  else if x._1 == Nil && x._3 >= 0 then List(x._2) //jesli nie wstawilismy elementu
  else if x._1 == Nil then Nil  //jesli go wstawilismy
  else x._1.head :: insert(x._1.tail, x._2, x._3-1) //jesli musimy isc dalej

insert(List(1,2,4,5),3,2) == List(1,2,3,4,5)
insert(List(1,2,3,4),5,8) == List(1,2,3,4,5)
insert(List(1,2,3,4),5,-1) == List(5,1,2,3,4)

