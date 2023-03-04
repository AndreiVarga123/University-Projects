make_number([],N,N).
make_number([H|T],R,N):-
    R1 is R*10+H,
    make_number(T,R1,N).

make_list(L,L,0).
make_list(T,Rez,N):-
    H is N mod 10,
    N1 is N//10,
    make_list([H|T],Rez,N1).

main(L,X,R):-
    make_number(L,0,N),
    N1 is N*X,
    make_list([],R,N1).
    