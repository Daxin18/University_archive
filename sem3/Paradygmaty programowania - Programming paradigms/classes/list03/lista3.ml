(*Kamil Ciaglo*)

(*zadanie 1*)
(*na deklaracji*)

(*zadanie 2*)
(*N - wersja bez lukru syntaktycznego*)
(*a)*)
let curry3 f x y z = f (x, y, z);;
let curry3N = function f -> function x -> function y -> function z -> f (x, y, z);;
(*b)*)
let uncurry3 f (x, y, z) = f x y z;;
let uncurry3N = function f -> function (x, y, z) -> f x y z;;

(*testy*)
let f1 (x, y, z) = x + y + z;;
let f2 x y z = x + y + z;;

curry3 f1 1 2 3 = 6;;
curry3 (uncurry3 f2) 1 2 3 = 6;;
(*curry3 f1 (1,2,3) = 6;;*)
uncurry3 f2 (1,2,3) = 6;;
uncurry3 (curry3 f1) (1,2,3) = 6;;
(*uncurry3 f2 1 2 3 = 6;;*)

(*zadanie 3*)
let sumProd xs =
  match xs with
    [] -> (0,1)
  | _ -> List.fold_left (fun (a,b)x->(x+a,x*b)) (0,1) xs
;;

(*testy*)
sumProd [1;1;1;1;1] = (5,1);;
sumProd [1;2;3;4] = (10,24);;
sumProd [0;1;2;3] = (6,0);;
sumProd [1;2;3;-1] = (5,-6);;
sumProd [] = (0,1);;

(*zadanie 4*)
(*na deklaracji*)

(*zadanie 5*)
(*nie zrobione*)
  
            

