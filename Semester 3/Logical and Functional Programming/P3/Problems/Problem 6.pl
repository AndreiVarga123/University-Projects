insert(L,E,[E|L]).
insert([H|T],E,[H|R]):-
    insert(T,E,R).

aranj([H|_],1,[H]).
aranj([H|T],K,R1):-
    K>1,
    K1 is K -1,
    aranj(T,K1,R),
    insert(R,H,R1).
aranj([_|T],K,R):-
    aranj(T,K,R).
      
      