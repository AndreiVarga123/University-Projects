paran(N, N, N, []).
paran(N, O, C, ['('|R]) :-
    O1 is O + 1,
    O1 =< N,
    paran(N, O1, C, R).
paran(N, O, C, [')'|R]) :-
    C1 is C + 1,
    C1 =< O,
    paran(N, O, C1, R).

generate(N,Rl):-
    N1 is N/2,
    findall(R,paran(N1,0,0,R),Rl).
    
      