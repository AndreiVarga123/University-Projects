(defun isLinear(L)
  (cond
   ((null L) T)
   ((listp (car L)) nil)
   (T (isLinear (cdr L)))
  )
)

(defun replaceEwithO(L E O)
  (cond 
   ((null L) nil)
   ((eql (car L) E) (cons O (cdr L)))
   (T (cons (car L) (replaceEwithO (cdr L) E O)))
  )
)

(defun lastELem(L)
  (cond
   ((null L) nil)
   ((and (atom (car l)) (null (cdr L))) (car L))
   ((null (cdr L)) (lastElem (car L)))
   (T (lastElem (cdr L)))
  )
)

(defun replaceWithLast(L)
  (cond
   ((null L) nil)
   ((atom (car L)) (cons (car L) (replaceWithLast (cdr L))))
   (T (cons (lastElem (car L)) (replaceWithLast (cdr L))))
  )
)

(defun mergeSorted(L1 L2)
 (cond
  ((null L1) L2)
  ((null L2) L1)
  ((< (car L1) (car L2)) (cons (car L1) (mergeSorted (cdr L1) L2)))
  ((< (car L2) (car L1)) (cons (car L2) (mergeSorted L1 (cdr L2))))
  (T (cons (car L1) (mergeSorted (cdr L1) (cdr L2))))
 )
)