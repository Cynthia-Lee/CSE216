let rec pow x n =
	match n with
		| 0 -> 1
		| _ -> x * pow x (n-1);;

(* 
pow 3 5;;

pow 3 1;;

pow 3 0;;

pow 3 2;;

pow (-3) 3;;

pow 3 0;;
*)

let rec float_pow x n = 
	match n with
		| 0 -> 1.0
		| _ -> x *. float_pow x (n-1);;

(*
float_pow 3.0 2;;

float_pow 2.0 2;;

float_pow 15. 0;;

float_pow 3.3 4;;
*)

let reverse list =
	let rec rec_rev acc = function
		| [] -> acc
		| h::t -> rec_rev (h::acc) t in rec_rev [] list;;

(*
reverse ["a" ; "b" ; "c"];;

reverse [1 ; 2 ; 3];;

reverse ["one" ; "two" ; "three"];;

reverse [1.0; 2.0; 3.0];;

reverse [];;

reverse [1; 2; 1];;
*)

let rec compress = function
	| h::(b::_ as t) -> if h=b then compress t else h::compress t
	| others -> others;;

(*
compress ["a";"a";"b";"c";"c";"a";"a";"d";"e";"e";"e"];;
*)

let cluster list =
	let rec rec_clus current acc = function
		| [] -> []
		| [x] -> (x::current)::acc
		| h::(b::_ as t) -> if h=b then rec_clus(h::current) acc t 
							else rec_clus[] ((h::current)::acc) t in reverse(rec_clus[] [] list);;

(*
cluster ["a";"a";"a";"b";"c";"c";"a";"a"];;

cluster [];;

cluster ["a"; "a";];;
*)

let slice list i j = (*i inclusive, j not inclusive*)
	if i>=j then [] else 

	let rec take_n n = function (*head until n*)
		| [] -> []
		| h::t -> if n=0 then [] else h::take_n(n-1) t
	in 
	let rec drop_start n = function (*remove from beginning *)
		| [] -> []
		| h::t as l -> if n=0 then l else drop_start(n-1) t
	in 
	take_n(j-i)(drop_start i list);;

(*
slice ["a";"b";"c";"d";"e";"f";"g";"h"] 2 6;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 0 7;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 1 8;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 3 2;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 3 3;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] (-2) 3;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 0 3;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] (-3) 5;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] (-3) (-2);;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 3 (-2);;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 3 20;;

slice ["a";"b";"c";"d";"e";"f";"g";"h"] 6 44;;

slice [] 0 2;;
*)

(* let square_of_increment = composition square increment;;
it would increment first and then do the square*)
let composition f g = fun x -> f (g x);;

(*
let square x  = x*x;;
let increment x = x+1;;
let square_of_increment = composition square increment;;
square_of_increment 4;;
square_of_increment 3;;
*)

let equiv_on f g lst = List.map (f) lst = List.map (g) lst;;

(*
let f i = i * i;;
let g i = 3 * i;;
equiv_on f g [3];;
equiv_on f g [1;2;3];;
equiv_on f g [3; 3];;
equiv_on f g [];;
*)

let rec pairwisefilter cmp lst =
	match lst with
		| [] -> []
		| [x] -> lst
		| h::(b::t) -> (cmp h b)::pairwisefilter cmp t;;

(*
pairwisefilter min [14;11;20;25;10;11];;
pairwisefilter min [1;2;3];;

let shorter a b = if String.length(a) < String.length(b) then a else b;;
pairwisefilter shorter ["and";"this";"makes";"shorter";"strings";"always";"win"];;

pairwisefilter min [];;
*)

let rec polynomial = function
	| [] -> fun x -> 0
	| [(a,b)] -> fun x -> (pow x b) * a
	| (a,b)::t -> 
		let add_fun f g = fun x -> f x + g x in
		add_fun (fun x -> (pow x b) * a) (polynomial t);;

(*
let f = polynomial [3,3; -2, 1; 5,0];;
f 2;;
f 1;;
f (-2);;
let f = polynomial [];;
f 1;;
let f = polynomial [1, 2; 1, 1];;
f 3;;
*)

type bool_expr = 
	| Lit of string
	| Not of bool_expr
	| And of bool_expr * bool_expr
	| Or of bool_expr * bool_expr;;

let rec bool_answer a a_bool b b_bool = function
	| Lit x -> if x = a then a_bool else if x = b then b_bool else failwith "Invalid variables in the expression"
	| Not ex -> not(bool_answer a a_bool b b_bool ex)
	| And(ex1, ex2) -> bool_answer a a_bool b b_bool ex1 && bool_answer a a_bool b b_bool ex2
	| Or(ex1, ex2) -> bool_answer a a_bool b b_bool ex1 || bool_answer a a_bool b b_bool ex2;;

let truth_table a b expr = [(true, true, bool_answer a true b true expr);
							(true, false, bool_answer a true b false expr);
							(false, true, bool_answer a false b true expr);
							(false, false, bool_answer a false b false expr)];;

(*
truth_table "a" "b" (And(Lit("a"), Lit("b")));;
truth_table "c" "d" (Or(Lit("c"), Lit("d")));;
truth_table "c" "d" (Or(Lit("e"), Lit("f")));;
*)


type 'a binary_tree = 
	| Empty
	| Node of 'a * 'a binary_tree * 'a binary_tree;;

let rec tree2str = function
	| Empty -> ""
	| Node(valu, l, r) ->
		let valu = string_of_int valu in
		match l, r with
		| Empty, Empty -> valu
		| _, _ -> valu ^ "(" ^ (tree2str l) ^ "," ^ (tree2str r) ^ ")";; 

(*
let a_tree = Node(1, Node(2, Node(4, Empty, Empty), Node(5, Empty, Empty)),
Node(3, Node(6, Empty, Empty), Node(7, Empty, Empty)));;
tree2str a_tree;;

let int_tree =
    Node(0, Node(1, Node(3, Empty, Empty), Node(4, Empty, Empty)),
         Node(2, Empty, Node(5, Node(6, Empty, Empty), Empty)));;
tree2str int_tree;;
*)



