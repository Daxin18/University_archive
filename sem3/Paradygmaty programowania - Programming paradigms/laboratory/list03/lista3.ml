(*Kamil Ciaglo*)

(*zadanie 1*)
let revNComp f n = fun x ->
  let rec tab xs n =
    if n<1 then xs (*zwracamy xs, bo na starcie jest pusty, potem zawiera wynikowa tablice*)
    else match xs with
           [] -> tab [x] (n-1)
         | h::t -> tab (f h::xs) (n-1)    
  in
  tab [] n;;

revNComp (fun x->(-x)) 5 1;;
revNComp (fun x->x*x) 5 2;;
revNComp (fun x->x+2) 10 0;;
revNComp (fun x->x) (-10) 5;;
revNComp (fun x->x) (0) 5;;
revNComp (fun x->x/.2.) 5 10.;;

                                
(*zadanie 2*)
(*zalozenie - n jest poprawne*)
let area (a,b) f n =
  let dist = (b-.a)/.float_of_int n
  in
  let rec tab i xs =
    if i<float_of_int n then i::(tab (i+.1.) xs)
    else xs
  in
  let partialArea x =
    dist*.(f (a+.(x*.dist)))
  in
  List.fold_left (+.) 0.(List.map partialArea (tab 0. []))
;;

area (0.,1.) (fun x->x) 10000;;
area (1.,3.) (fun x->x*.x) 10000;;
area (-1.,1.) (fun x->x) 2;; (*malo podzialow dla zademonstrowania dzialania funkcji*)
area (-2.,2.) (fun x->x*.x) 10000;;

