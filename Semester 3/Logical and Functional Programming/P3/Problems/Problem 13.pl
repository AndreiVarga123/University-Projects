subset([],[]).
subset([H|T],[H|R]):-
    subset(T,R).
subset([_|T],R):-
    subset(T,R).

isMountain([_],1).
isMountain([H1,H2|T],0):-
    H1 < H2,
    !,
    isMountain([H2|T],0).
isMountain([H1,H2|T],_):-
    H1 > H2,
    !,
    isMountain([H2|T],1).


solution(L,R):-
    subset(L,R),
    isMountain(R,0).

allSolutions(L,Rl):-
    findall(R,solution(L,R),Rl).