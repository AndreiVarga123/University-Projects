subset([],[]).
subset([H|T],[H|R]):-
    subset(T,R).
subset([_|T],R):-
    subset(T,R).

isSum([],S,S).
isSum([H|T],Cs,S):-
    Cs1 is Cs + H,
    Cs1 =< S,
    isSum(T,Cs1,S).


solution(L,S,R):-
    subset(L,R),
    isSum(R,0,S).

allSolutions(L,S,Rl):-
    findall(R,solution(L,S,R),Rl).