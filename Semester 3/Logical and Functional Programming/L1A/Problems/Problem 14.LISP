(defun setUnion(L1 L2)
  (cond
   ((null L1) L2)
   ((not (member (car L1) L2)) (cons (car L1) (setUnion (cdr L1) L2)))
   (T (setUnion (cdr L1) L2))
  )
)

(defun deepProd(L)
  (cond
   ((null L) 1)
   ((numberp (car L)) (* (car L) (deepProd (cdr L))))
   (T (* (deepProd (car L)) (deepProd (cdr L))))
  )
)

(defun delE(L E)
  (cond 
   ((null L) nil)
   ((eq (car L) E) (cdr L))
   (T (cons (car L) (delE (cdr L) E)))
  )
)

(defun myMax(L M)
  (cond
   ((null L) M)
   (T (max (car L) (myMax (cdr L) M)))
  )
)

(defun distSort(L R)
  (cond
   ((null L) R)
   (T (distSort (delE L (myMax L (car L))) (cons (myMax L (car L)) R)))
  )
)

(defun mainSort(L)
  (distSort L NIL)
)

(defun numMin(L M)
  (cond
   ((null L) M)
   ((and (numberp (car L)) (> M (car L))) (numMin (cdr L) (car L)))
   (T (numMin (cdr L) M))
  )
)

(defun minPos(L M P)
  (cond
   ((null L) nil)
   ((eql (car L) M) (cons P (minPos (cdr L) M (+ 1 P))))
   (T (minPos (cdr L) M (+ 1 P)))
  )
)

(defun mainMinPos(L)
  (minPos L (numMin L 100) 1)
)