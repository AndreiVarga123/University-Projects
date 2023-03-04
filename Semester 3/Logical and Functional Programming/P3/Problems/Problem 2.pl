colinear(_,[]).
colinear(_,[[_,_]]).
colinear([X1,X2],[[H1,H2],[H3,H4]|_]):-
    (X1-X2)/(H1-H2) =:= (H1-H2)/(H3-H4).

subset([],[]).
subset([[H1,H2]|T],[[H1,H2]|R]):-
    subset(T,R),
    colinear([H1,H2],R).
subset([_|T],R):-
    subset(T,R).
    