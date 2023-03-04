makeList(N,N,[N]).
makeList(N,Cn,[Cn|L]):-
    Cn1 is Cn + 1,
    Cn1 =< N,
    makeList(N,Cn1,L).

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

isConsecutive([]).
isConsecutive([_]).
isConsecutive([H1,H2|T]):-
    H2-H1 =:= 1,
    isConsecutive([H2|T]).
    
mySolution(L,R,N):-
	subset(L,R),
	isSum(R,0,N),
	isConsecutive(R).

main(N,Rl):-
    makeList(N,0,L),
    findall(R,mySolution(L,R,N),Rl).