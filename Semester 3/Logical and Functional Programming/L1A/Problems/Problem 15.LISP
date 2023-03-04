(defun insertE(L E N P)
  (cond
   ((null L) nil)
   ((eql N P) (cons E (cons (car L) (cdr L))))
   (T (cons (car L) (insertE (cdr L) E N (+ 1 P))))
  )
)

(defun deepSum(L)
  (cond
   ((null L) 0)
   ((numberp (car L)) (+ (car L) (deepSum (cdr L))))
   ((listp (car L)) (+ (deepSum (car L)) (deepSum (cdr L))))
   (T (deepSum (cdr L)))
  )
)


(defun howManyElems(L)
  (cond
   ((null L) 0)
   (T (+ 1 (howManyElems (cdr L))))
  )
)

(defun areSetsEqual(L1 L2)
  (cond 
   ((null L1) T)
   ((not (member (car L1) L2)) nil)
   (T (areSetsEqual (cdr L1) L2))
  )
)

(defun mainEqualSets(L1 L2)
  (cond
   ((and (eql (howManyElems L1) (howManyElems L2)) (areSetsEqual L1 L2)))
   (T NIL)
  )
)
