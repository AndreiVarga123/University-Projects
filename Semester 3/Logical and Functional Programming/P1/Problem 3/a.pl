unique(_,[],C):-C=:=1.
unique(E,[H|T],C):-
    E=:=H,
    !,
    unique(E,T,C+1).
unique(E,[_|T],C):-
       unique(E,T,C).
/*unique(E,L,C){return true if C==1 and L is empty
 * 			   {return false if C!=1 and L is empty
 * 			   {return unique(E,[l2,l3,..,ln],C+1) if l1==E
 * 			   {return unique(E,[l2,l3,..,ln],C) otherwise
*/

createU([],[_|_],[]).
createU([H|T],L,[H|Rez]):-
    unique(H,L,0),
    !,
    createU(T,L,Rez).
createU([_|T],L,Rez):-
    createU(T,L,Rez).
/*
 *createU(L,iL(=initial list))={return [] if L is empty 
 * 			       {l1 reunited with createU([l2,l3,...,ln],il) if unique(l1,il)
 * 			       {createU([l2,l3,...,ln],il) otherwise
*/
createUL(L,Rez):-createU(L,L,Rez).