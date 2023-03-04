makeList(0,R,R).
makeList(N,R,Cr):-
	N>0,
	N1 is N-1,
	makeList(N1,R,[N|Cr]).

subset([],[]).
subset([H|T],[H|R]):-
    subset(T,R).
subset([_|T],R):-
    subset(T,R).

myAbs(A,A):-A>=0.
myAbs(A,A1):-A<0,A1 is -1 * A.

isDiffM(_,[_]).
isDiffM(M,[H1,H2|T]):-
    Hc is H1 - H2,
    myAbs(Hc,H),
    H>= M,
    isDiffM(M,[H2|T]).

solution(M,L,R):-
    subset(L,R),
    isDiffM(M,R).

mainSeqNM(N,M,Rl):-
    makeList(N,L,[]),
    findall(R,solution(M,L,R),Rl).