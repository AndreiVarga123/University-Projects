subset([],[]).
subset([H|T],[H|R]):-
    subset(T,R).
subset([_|T],R):-
    subset(T,R).

isAsc([]).
isAsc([_]).
isAsc([H1,H2|L]):-
    H1 < H2,
    isAsc([H2|L]).

solution(L,R):-
    subset(L,R),
    isAsc(R).

mainAscSubset(L,Rl):-
    findall(R,solution(L,R),Rl).
    