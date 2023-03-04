comb([H|_],1,[H]).
comb([H|T],K,[H|R]):-
    K1 is K-1,
    comb(T,K1,R).
comb([_|T],K,R):-
    comb(T,K,R).

allCombs(L,K,Rl):-
    findall(R,comb(L,K,R),Rl).