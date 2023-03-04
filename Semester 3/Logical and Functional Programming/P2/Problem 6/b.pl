max([X],X).
max([H|T], M):- max(T, M), M >= H.
max([H|T], H):- max(T, M), H > M.

make_index_list([],[],_,_).
make_index_list([H|T],[Poz|Rez],Poz,Max):-
    H=:=Max,
    !,
    Poz1 is Poz+1,
    make_index_list(T,Rez,Poz1,Max).
make_index_list([_|T],Rez,Poz,Max):-
    Poz1 is Poz+1,
    make_index_list(T,Rez,Poz1,Max).

isList([_|_]).
isList([]).

make_final_list([],[]).
make_final_list([H|T],[X|Rez]):-
    isList(H),
    !,
    max(H,Max),
    make_index_list(H,X,1,Max),
    make_final_list(T,Rez).
make_final_list([H|T],[H|Rez]):-
    make_final_list(T,Rez).


    