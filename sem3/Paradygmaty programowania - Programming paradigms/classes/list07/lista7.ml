(*Kamil Ciaglo*)

(*zadanie 1*)
module type QUEUE_FUN =
  sig
    type 'a t
    exception Empty of string
    val empty: unit -> 'a t
    val enqueue: 'a * 'a t -> 'a t
    val dequeue: 'a t -> 'a t 
    val first: 'a t -> 'a
    val isEmpty: 'a t -> bool
  end;;

(*a)*)
module Queue_list:QUEUE_FUN =
  struct
    type 'a t = 'a list
    exception Empty of string
    let empty() = []
    let enqueue(x,q) = q @ [x]
    let dequeue q = match q with
                      _::t -> t
                    | [] -> []
    let first q = match q with
                    h::_ -> h
                  | [] -> raise(Empty "module Queue_list: first")
    let isEmpty q = q = []
  end;;

(*szybki test a)*)
let q1 = Queue_list.empty();;
let q1 = Queue_list.enqueue(0,q1);;
let q1 = Queue_list.enqueue(1,q1);;
let q1 = Queue_list.enqueue(2,q1);;
let q1 = Queue_list.enqueue(3,q1);;
Queue_list.first q1 = 0;;
let q1 = Queue_list.dequeue q1;;
Queue_list.first q1 = 1;;
let q1 = Queue_list.dequeue q1;;
Queue_list.first q1 = 2;;
let q1 = Queue_list.dequeue q1;;
Queue_list.first q1 = 3;;
let q1 = Queue_list.dequeue q1;;
Queue_list.first q1;;(*Exception: Queue_list.Empty "module Queue_list: first".*)

(*b)*)
module Queue_pair:QUEUE_FUN =
  struct
    type 'a t = 'a list * 'a list
    exception Empty of string
    let empty() = ([],[])
    let enqueue(x,q) = match q with
                      ([],[]) -> ([x],[])
                    | ([],y) -> (List.rev y,[x]) (*ten przypadek nie powinien sie wydarzyc*)
                    | (xs,y) -> (xs, x::y)
    let dequeue q = match q with
                      ([],[]) -> ([],[])
                    | ([],y) -> (List.tl(List.rev y),[]) (*ten przypadek nie powinien sie wydarzyc*)   
                    | (_::[],y) -> (List.rev(y),[])      
                    | (_::t,y) -> (t,y)         
    let first q = match q with
                    ([],[]) -> raise(Empty "module Queue_pair: first")
                  | ([],y) -> List.hd(List.rev y) (*ten przypadek nie powinien sie wydarzyc*)
                  | (xh::_,_) -> xh
    let isEmpty q = q = ([],[])
  end;;

let q2 = Queue_pair.empty();;
let q2 = Queue_pair.enqueue(0,q2);;
let q2 = Queue_pair.enqueue(1,q2);;
let q2 = Queue_pair.enqueue(2,q2);;
let q2 = Queue_pair.enqueue(3,q2);;
Queue_pair.first q2 = 0;;
let q2 = Queue_pair.dequeue q2;;
Queue_pair.first q2 = 1;;
let q2 = Queue_pair.dequeue q2;;
let q2 = Queue_pair.enqueue(4,q2);;
let q2 = Queue_pair.enqueue(5,q2);;
Queue_pair.first q2 = 2;;
let q2 = Queue_pair.dequeue q2;;
Queue_pair.first q2 = 3;;
let q2 = Queue_pair.dequeue q2;;
Queue_pair.first q2 = 4;;
let q2 = Queue_pair.dequeue q2;;
Queue_pair.first q2 = 5;;
let q2 = Queue_pair.dequeue q2;;
Queue_pair.first q2;;(*Exception: Queue_pair.Empty "module Queue_pair: first".*)
