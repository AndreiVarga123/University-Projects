insert(E,L,[E|L]).
insert(E,[H|T],[H|R]):-
    insert(E,T,R).

perm(0,[]).
perm(N,P):-
    N1 is N - 1,
    N1 >=0,
    perm(N1,R),
    insert(N,R,P).

myAbs(X,X):-X >= 0,!.
myAbs(X,X1):- X1 is X * -1.

getElemI([H|_],I,I,H).
getElemI([_|T],I,Ci,X):-
    Ci1 is Ci + 1,
    Ci1 =< I,
    getElemI(T,I,Ci1,X).

isGoodElem(I,J,E,[H|_]):-
    J<I,
    X is E - H,
    myAbs(X,X1),
    X1 =:= 1.
isGoodElem(I,J,E,[_|T]):-
    J1 is J + 1,
    J1 < I,
    isGoodElem(I,J,E,T).

isSolution(N,N,_).
isSolution(N,I,L):-
    getElemI(L,I,1,E),
    isGoodElem(I,1,E,L),
    I1 is I + 1,
    I1 =< N,
    isSolution(N,I1,L).
    

solution(N,R):-
	perm(N,R),
    isSolution(N,2,R).

allSolutions(N,Rl):-
    findall(R,solution(N,R),Rl).
    