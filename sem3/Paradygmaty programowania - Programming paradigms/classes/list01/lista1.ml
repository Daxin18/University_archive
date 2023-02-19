(*Kamil Ciaglo*)

(*zadanie 1*)
let rec flatten1(xss: 'a list list) : 'a list =
  if xss = [] then []
  else List.hd xss @ flatten1(List.tl xss);;

(*przyklad z listy*)
flatten1[[5;6];[1;2;3]] = [5;6;1;2;3];;
(*reszta testow sprawdzajaca funkcje*)
flatten1[[5;6];[1;2;3];[]] = [5;6;1;2;3];;
flatten1[[5;6];[];[1;2;3]] = [5;6;1;2;3];;
flatten1[] = [];;
flatten1[[];[]] = [];;
(*flatten1[5;6;7;8;9];;*)

(*zadanie 2*)
let rec count (x, xs) = 
       if xs!= [] then
             (
               if List.hd xs = x then count(x,List.tl xs) +1
               else count(x,List.tl xs)
             )
       else 0;;

(*przyklad z listy*)
count('a',['a';'l';'a']) = 2;;
(*reszta testow*)
count(' ',['a';'l';'a']) = 0;;
count('a',['A';'L';'A']) = 0;;
count('a',['a';'n';'a';'n';'a';'s']) = 3;;
(*count(97,['a';'n';'a';'n';'a';'s']) = 3;;*)
count('a',[]) = 0;;
(*count([],'a');;*)
count([],[[];[1;2;3]]) = 1;;

(*zadanie 3*)
let rec replicate (x, n) =
  if n>0 then x :: replicate(x,n-1)
  else [];;
                      
(*przyklad z listy*)                    
replicate("la",3) = ["la";"la";"la"];;
(*reszta testow*)
replicate('a',0) = [];;
replicate('A',4) = ['A';'A';'A';'A'];;
replicate("Podanine ujemnej liczby skutkuje pusta tablica",-2) = [];;
replicate([],5) = [[];[];[];[];[]];;
replicate('A',-5) = [];;
(*ponizszy test pokazuje roznice miedzy Scala a OCamlem, tutaj char nie zostanie
zinterpretowany jako int na podstawie kodu ASCII i wyrzuci blad*)
(*replicate('A',' ');;*)
(*Reszta testow, jak w scali - wyrzuca blad*)
(*replicate('A',"string");;
replicate('A',true) == List('A');;
replicate('A',Nil);;
replicate('A',2.0) == List('A','A');;*)

(*zadanie 4*)
let rec sqrtList(xs) = 
  if xs=[] then []
  else (List.hd xs * List.hd xs) :: sqrtList(List.tl xs);;

(*przyklad z listy*)
sqrtList([1;2;3;-4]) = [1;4;9;16];;
(*reszta testow*)
sqrtList([-1;0;3]) = [1;0;9];;
sqrtList([]) = [];;
(*sqrtList([' ']) == sqrtList(1024);;*)
(*sqrtList(8);;*)

(*zadanie 5*)
let rec palindrome(xs) =
  if List.rev xs = xs then true
  else false;;

(*przyklad z listy*)
palindrome(['a';'l';'a']) = true;;
(*reszta testow*)
palindrome(['k';'a';'j';'a';'k']) = true;;
palindrome(['r';'o';'w';'e';'r']) = false;;
palindrome([]) = true;;
(*palindrome("kajak");;*)

(*zadanie 6*)
let rec listLength(xs) =
  if xs = [] then 0
  else listLength(List.tl xs) + 1;;

(*przykladu z listy nie ma, wiec od razu daje swoje przyklady*)
listLength([]) = 0;;
listLength([1;2;3;4;5]) = 5;;
listLength(['a';'l';'a']) = 3;;
listLength(['a';'n';'a';'n';'a';'s']) = 6;;
listLength([[1;2;3];[2;3;4];[3;4;5]]) == 3;;
(*listLength(8);;*)
(*listLength("string");;*)

(*zadanie 7*)
(*na deklaracji - nie zrobione*)




