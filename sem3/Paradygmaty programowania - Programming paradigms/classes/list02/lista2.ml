(*Kamil Ciaglo*)

(*zadanie 1*)
(*na deklaracji*)

(*zadanie 2*)
let rec fib(n) =
  if n<0 then raise(Failure "ujemny argument")
  else if n==0 then 0
  else if n==1 then 1
  else fib(n-1) + fib(n-2);;


let rec fibTailA(x, n1, n2) =
     if x==1 then n2
     else fibTailA(x-1, n2, n1+n2);;

let fibTail(n) =
  if n<0 then raise(Failure "ujemny argument")
  else if n==0 then 0
  else if n==1 then 1
  else fibTailA(n, 0, 1);;
  
(*testy*)
(*roznica w czasie wykonywania funkcji w OCamlu jest jeszcze bardziej zauwazalna*)
fibTail(42) = 267914296;;
fib(42) = 267914296;;

(*fibTail(-3) = -1;;*)
(*fib(-3) = -1;;*)
             
(*zadanie 3*)
let dok = 1.0e-15;;
let rec root3A(a, accum) =
  if abs_float((accum*.accum*.accum) -. a) <= dok*.abs_float(a) then accum
  else root3A(a, accum+.(a/.(accum*.accum) -. accum)/.3.);;
let root3(a) =
  if a>1. then root3A(a, a/.3.)
  else root3A(a, a);;

(*testy*)
abs_float(3.**(1./.3.) -. root3(3.)) <=dok;;
abs_float(27.**(1./.3.) -. root3(27.)) <=dok;;
abs_float(125.**(1./.3.) -. root3(125.)) <=dok;;
(*ponownie potegowanie ujemnych daje nan*)
(*abs_float(-64.**(1./.3.) -. root3(-64.)) <=dok;;*)
abs_float(-4. -. root3(-64.)) <= dok;;

(*zadanie 4*)
(*a)*)
let [_;_;x;_;_] = [-2;-1;0;1;2];;
(*b)*)
let [(_,_);(x,_)] = [(1,2);(0,1)];;
let [_;(x,_)] = [(1,2);(0,1)];;

(*zadanie 5*)
let rec initSegment(xs, ys) =
  if xs = [] then true
  else if ys = [] then false
  else
  (
    match (xs, ys) with
    (hx::tx,hy::ty) -> if hx == hy then initSegment(tx, ty) else false
    |_ -> false (*linia dopisana zeby nie bylo ostrzezenia
    "this pattern-matching is not exhaustive"*)
  );;

(*testy*)
initSegment([],[1;2;3]) = true;;
initSegment([1;2;3],[1;2;3]) = true;;
initSegment([1;2;3],[1;2;3;4;5]) = true;;           
initSegment([1;2;4],[1;2;3;4;5]) = false;;
initSegment([1;2;3;4;5],[1;2;3]) = false;;
(*roznica miedzy Scala a OCamlem - ponizszy test wyrzuca blad, brak automatycznej
koercji typow*)
(*initSegment([32;97],[' ';'a';'A']) == true;;*)

(*zadanie 6*)
(*a)*)
let rec replaceNth(xs, n, x) =
  if n<0 then raise (Failure "ujemny argument")
  else if xs = [] then []
  else
  (
    match xs with
    hx::tx -> if n==0 then x::tx else hx::replaceNth(tx, n-1, x)
    |_ -> [] (*linia dopisana zeby nie bylo ostrzezenia
    "this pattern-matching is not exhaustive"*)
  );;

(*testy*)
replaceNth(['o';'l';'a';'m';'a';'k';'o';'t';'a'],1,'s')=['o';'s';'a';'m';'a';'k';'o';'t';'a'];;
replaceNth([1;2;3;4;5;6;6;8;9],6,7) = [1;2;3;4;5;6;7;8;9];;
replaceNth([0;1;2],5,8) = [0;1;2];;
(*replaceNth([0;2],-1,8);;*)
(*replaceNth([0;2],1,'a');;*)

(*b)*)
(*osobno - nie zrobione*)











                 
