(*Kamil Ciaglo*)

(*zadanie 1*)
(*a)*)
module StackMachine =
  struct
    type stackMachine = {mutable l: float list}
    exception Empty of string
    exception DivBy0 of string
    exception NotEnoughArguments of string

    type instruction = Rst | LoadF of float | LoadI of int | Cpy | Add | Sub | Mul | Div
                                  
    let init() = {l = []}
    let result(sm) =
      match sm.l with
        h::_ -> h
      | [] -> raise (Empty "module StackMachine: result")
    let handle (sm) = function
        Rst -> sm.l <- []
      | LoadF(f) -> sm.l <- f::sm.l
      | LoadI(i) -> sm.l <- (float_of_int i)::sm.l
      | Cpy -> sm.l <- (match sm.l with
                          h::_ -> h::sm.l
                        | [] -> raise (Empty "module StackMachine: handle Cpy"))
      | Add -> sm.l <- (match sm.l with
                        e1::e2::t -> (e1+.e2)::t
                      | _ -> raise (NotEnoughArguments "module StackMachine: handle Add"))
      | Sub -> sm.l <- (match sm.l with
                        e1::e2::t -> (e1-.e2)::t
                      | _ -> raise (NotEnoughArguments "module StackMachine: handle Sub"))
      | Mul -> sm.l <- (match sm.l with
                        e1::e2::t -> (e1*.e2)::t
                      | _ -> raise (NotEnoughArguments "module StackMachine: handle Mul"))
      | Div -> sm.l <- (match sm.l with
                        e::0.::_ -> raise (DivBy0 "module StackMachine: handle Div")
                      | e1::e2::t -> (e1/.e2)::t            
                      | _ -> raise (NotEnoughArguments "module StackMachine: handle Div"))
    let rec execute sm il =
      match il with
        [] -> ()
      | h::t -> begin handle sm h;
                      execute sm t
                end
      
  end;;

(*b)*)
module type COPROCESSOR =
  sig
    type stackMachine
    exception Empty of string
    exception DivBy0 of string
    exception NotEnoughArguments of string

    type instruction =
        Rst
      | LoadF of float
      | LoadI of int
      | Cpy
      | Add
      | Sub
      | Mul
      | Div
       
    val init: unit -> stackMachine
    val result: stackMachine -> float
    val handle: stackMachine -> instruction -> unit      
    val execute: stackMachine -> instruction list -> unit
  end;;

(*c)*)
module Coprocessor = (StackMachine : COPROCESSOR) ;;

(*testy dla StackMachine*)
let s = StackMachine.init();;
let rst = StackMachine.Rst;;
let loadF = function x -> StackMachine.LoadF(x);;
let loadI = function x -> StackMachine.LoadI(x);;
let cpy = StackMachine.Cpy;;
let add = StackMachine.Add;;
let sub = StackMachine.Sub;;
let mul = StackMachine.Mul;;
let div = StackMachine.Div;;

let test = [rst; loadF(1.); loadI(2); cpy; add; sub; cpy; mul];;
let testDiv0 = [rst; loadI(0); loadI(1); div];;
StackMachine.execute(s)(test);;
StackMachine.result(s);;
StackMachine.execute(s)(testDiv0);;
StackMachine.result(s);;

(*testy dla Coprocessor*)
let cs = Coprocessor.init();;
let crst = Coprocessor.Rst;;
let cloadF = function x -> Coprocessor.LoadF(x);;
let cloadI = function x -> Coprocessor.LoadI(x);;
let ccpy = Coprocessor.Cpy;;
let cadd = Coprocessor.Add;;
let csub = Coprocessor.Sub;;
let cmul = Coprocessor.Mul;;
let cdiv = Coprocessor.Div;;

let ctest = [crst; cloadF(1.); cloadI(2); ccpy; cadd; csub; ccpy; cmul];;
let ctestDiv0 = [crst; cloadI(0); cloadI(1); cdiv];;
let ctestNEA = [crst; cloadI(0); cadd];;
let ctestEmpty = [crst];;
Coprocessor.execute(cs)(ctest);;
Coprocessor.result(cs);;
Coprocessor.execute(cs)(ctestDiv0);;
Coprocessor.execute(cs)(ctestNEA);;
Coprocessor.execute(cs)(ctestEmpty);;
Coprocessor.result(cs);;
