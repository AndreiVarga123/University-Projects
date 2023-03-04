(defun writeTwice(L N P)
  (cond
   ((null L) nil)
   ((eql N P) (cons (car L) (cons (car L) (writeTwice (cdr L) N (+ 1 P)))))
   (T (cons (car L) (writeTwice (cdr L) N (+ 1 P ))))
  )
)

(defun asoList(L1 L2)
  (cond
   ((null L1) nil)
   (T (cons (list (car L1) (car L2)) (asoList (cdr L1) (cdr L2))))
  )
)

(defun allSl(L)
  (cond
   ((null L) 0)
   ((listp (car L)) (+ 1 (+ (allSl (car L)) (allSl (cdr L))))) 
   (T (allSl (cdr L)))
  )
)

(defun mainSL(L)
  (+ 1 (allSl L))
)

(defun nrAtoms(L)
  (cond
   ((null L) 0)
   ((numberp (car L)) (+ 1 (nrAtoms (cdr L))))
   (T (nrAtoms (cdr L)))
  )
)

