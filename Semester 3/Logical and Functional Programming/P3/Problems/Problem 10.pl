subset([],[]).
subset([H|T],[H|R]):-
    subset(T,R).
subset([_|T],R):-
    subset(T,R).
    
isDiv([],N,S):-S mod N =:= 0.
isDiv([H|T],N,S):-
    S1 is S + H,
    isDiv(T,N,S1).

solution(L,N,R):-
    subset(L,R),
    isDiv(R,N,0).

subsetDiv(L,N,Rl):-
    findall(R,solution(L,N,R),Rl).
