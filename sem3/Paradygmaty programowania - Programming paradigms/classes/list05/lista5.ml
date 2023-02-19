(*Kamil Ciaglo*)

(*definicja list leniwych*)
type 'a llist = LNil | LCons of 'a * 'a llist Lazy.t;;

let lhd = function
  LNil -> failwith "lhd"
  | LCons (x, _) -> x
;;

let ltl = function
  LNil -> failwith "ltl"
  | LCons (_, lazy t) -> t
;;

let rec lfrom k = LCons (k, lazy (lfrom (k+1)));;

let rec ltake = function
    (0, _) -> []
  | (_, LNil) -> []
  | (n, LCons(x,lazy xs)) -> x::ltake(n-1,xs)
;;

let rec toLazyList xs =
  match xs with 
    [] -> LNil
  | h::t -> LCons(h, lazy(toLazyList t))
;;

(*zadanie 1*)
let lrepeat k lxs =
  let rec lrepeatIter (n, llist) =
    match (n, llist) with
      (_,LNil) -> LNil
    | (0,_) -> lrepeatIter (k, (ltl llist))
    | (_,_) -> LCons(lhd llist, lazy(lrepeatIter((n-1), llist)))
  in
  lrepeatIter(k, lxs)
;;

(*testy*)    
ltake(12,lrepeat 3 (toLazyList['a';'b';'c';'d'])) = ['a';'a';'a';'b';'b';'b';'c';'c';'c';'d';'d';'d'];;
ltake(15, lrepeat 3 (lfrom 1)) = [1;1;1;2;2;2;3;3;3;4;4;4;5;5;5];;
ltake(15, lrepeat 3 (toLazyList[])) = [];;
ltake(6, lrepeat 5 (lfrom 9)) = [9;9;9;9;9;10];;
ltake(5, lrepeat 0 (toLazyList[1;2;3])) = [];;
(*lrepeat (-int) bedzie w nieskonczonosc powtarzal glowe listy*)
ltake(5, lrepeat (-1) (lfrom (-1))) = [-1;-1;-1;-1;-1];;

(*zadanie 2*)
let lfib =
  let rec lfibIter first second =
    LCons(first, lazy(lfibIter second (first + second)))
  in
  lfibIter 0 1
;;

(*test, ten sam co na wykladzie*)
ltake(10, lfib) = [0;1;1;2;3;5;8;13;21;34];;

(*zadanie 3 - nie zrobione*)

