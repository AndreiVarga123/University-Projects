generate(0,[]).
generate(K,[1|R]):-
    K1 is K - 1,
    K1>=0,
    generate(K1,R).
generate(K,[2|R]):-
    K1 is K - 1,
    K1>=0,
    generate(K1,R).
generate(K,["X"|R]):-
    K1 is K - 1,
    K1>=0,
    generate(K1,R).
 
isGood([X],C):-
    X=\=2,
    X=:="X",
    !,
    C1 is C + 1,
    C1=<2.
isGood([X],_):-
    X=\=2.
isGood([H|T],C):-
    H =:= "X",
    !,
    C1 is C + 1,
    isGood(T,C1).
isGood([_|T],C):-
    isGood(T,C).

solution(R):-
    generate(4,R),
    isGood(R,0).

mainGenerate(Rl):-
    findall(R,solution(R),Rl).
      