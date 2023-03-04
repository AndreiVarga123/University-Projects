(defun deleteNth(L N P)
 (cond
  ((null L) nil)
  ((eql N P) (deleteNth (cdr L) N (+ 1 P)))
  (T (cons (car L) (deleteNth (cdr L) N (+ 1 P))))
 )
)

(defun getSucc(L C)
  (cond
   ((null L) nil)
   ((and (null (cdr L)) (eql (+ C (car L)) 10)) (list 0 1))
   ((null (cdr L)) (list (+ C (car L))))
   (T (cons (mod (+ C (car L)) 10) (getSucc (cdr L) (floor (+ C (car L)) 10))))
  )
)

(defun mainGetSucc(L)
  (reverse (getSucc (reverse L) 1))
)


(defun atomSet(L R)
  (cond
   ((null L) R)
   ((and (atom (car L)) (not (member (car L) R))) (atomSet (cdr L) (append R (list (car L)))))
   ((atom (car L)) (atomSet (cdr L) R))
   (T (atomSet (cdr L) (atomSet (car L) R)))
  )
)

(defun isSet (L R)
  (cond
   ((null L) T)
   ((member (car L) R) NIL)
   (T (isSet (cdr L) (cons (car L) R)))
  )
)