candidate(N,N).
candidate(N,R):-
    N > -1,
    N1 is N-1,
    candidate(N1,R).

generate(0,_,[]).
generate(N,M,[X|R]):-
    N>0,
    candidate(M,X),
    N1 is N - 1,
    generate(N1, M, R).

isSum([],S,S).
isSum([H|T],Cs,S):-
    Cs1 is Cs + H,
    isSum(T,Cs1,S).

myAbs(X,X):-X >= 0.
myAbs(X,X1):-X1 is X * -1.

isSolution([_]).
isSolution([H1,H2|T]):-
    H is H1 - H2,
    myAbs(H,X),
    (X =:= 1 ; X =:= 2),
    !,
    isSolution([H2|T]).

solution(N,R):-
    generate(N,1,R),
    isSum(R,0,0),
    isSolution(R).

allSolutions(N,Rl):-
    N1 is 2 * N + 1,
    findall(R,solution(N1,R),Rl).
    