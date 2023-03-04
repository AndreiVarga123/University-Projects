(defun dotProduct(V1 V2)
  (cond
   ((or (null V1) (null V2)) 0)
   (T (+ (* (car V1) (car V2)) (dotProduct (cdr V1) (cdr V2))))
  )
)

(defun findLvl (L lvl)
  (cond
   ((null L) -1)
   ((null (cdr L)) lvl)
   (T (max (findLvl (cadr L) (+ 1 lvl)) (findLvl (caddr L) (+ 1 lvl))))
  )
)

(defun delE(L E)
  (cond 
   ((null L) nil)
   ((eq (car L) E) (delE (cdr L) E))
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

(defun myIntersect(L1 L2 R)
  (cond
   ((null L1) R)
   ((and (member (car L1) L2) (not (member (car L1) R))) (myIntersect (cdr L1) L2 (append R (list (car L1))))) 
   (T (myIntersect (cdr L1) L2 R))
  )
)