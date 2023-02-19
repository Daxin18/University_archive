(*Kamil Ciaglo*)

(*zadanie 1*)
type 'a tree3 = Empty | Node of 'a * 'a tree3 * 'a tree3 * 'a tree3;;

let rec mapTree3 f t =
  match t with
    Empty -> Empty
  | Node(x, l, m, r) -> Node(f x, mapTree3 f l, mapTree3 f m, mapTree3 f r);;

let t1 = Node(1,
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
                   Empty));;

mapTree3 (fun x->x+1) t1;;
mapTree3 (fun x->x*x) t1;;

(*zadanie 2*)
type word = Word of char*string;;

type sentence = Affirmative of word*word list
              | Question of word*word list
              | Exclamation of word*word list;;
 
type text = Text of sentence*sentence list;;

let text1 = Text(Affirmative
                   (Word('t',"o"),
                   [Word('c',"hyba");
                    Word('d',"ziala")]),
                [Question
                   (Word('t',"ak"),[]);
                 Exclamation
                   (Word('t',"ak"),
                   [Word('t',"o");
                    Word('d',"ziala")])]);;

let char_to_string = String.make 1;;

let wordToString w =
  match w with
    Word(c,s) -> (char_to_string c) ^ s;;

let typeToString s =
  match s with
    Affirmative(_,_) -> "."
  | Question(_,_) -> "?"
  | Exclamation(_,_) -> "!";;

