max([X],X).
max([H|T], M):- max(T, M), M >= H.
max([H|T], H):- max(T, M), H > M.
/*
 *max(L){return X when X is last elem
 * 		{return max([l2,l3,..,ln]) if max([l2,l3,..,ln])>l1
 * 		{return l1 otherwise
*/

remove_max([],[],_).
remove_max([H|T],[H|Rez],M):-
           H=\=M,
           !,
           remove_max(T,Rez,M).
remove_max([_|T],Rez,M):-
    remove_max(T,Rez,M).
/*
 * remove_max(L,M(=max(L))={return [] if L is empty
 * 					       {l1 reunited with remove_max([l2,l3,...,ln],M) if l1==M
 * 						   {remove_max([l2,l3,...,ln],M) otherwise
*/
remove_maxM(L,Rez):-
    max(L,M),
	remove_max(L,Rez,M).