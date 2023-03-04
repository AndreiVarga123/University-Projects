(defun mergeSorted(L1 L2)
 (cond
  ((null L1) L2)
  ((null L2) L1)
  ((< (car L1) (car L2)) (cons (car L1) (mergeSorted (cdr L1) L2)))
  ((< (car L2) (car L1)) (cons (car L2) (mergeSorted L1 (cdr L2))))
  (T (cons (car L1) (cons (car L2) (mergeSorted (cdr L1) (cdr L2)))))
 )
)

(defun replaceEwithList (L E L1)
  (cond
   ((null L) nil)
   ((eql E (car L)) (cons L1 (replaceEwithList (cdr L) E L1)))
   ((atom (car L)) (cons (car L) (replaceEwithList (cdr L) E L1)))
   (T (cons (replaceEwithList (car L) E L1) (replaceEwithList (cdr L) E L1)))
  )
)

(defun listSum (L1 L2 C)
  (cond
   ((and (eql C 0) (and (null L1) (null L2))) nil)
   ((and (null L1) (null L2)) (list C))
   ((null L1) (cons (mod (+ C (car L2)) 10) (listSum nil (cdr L2) (floor (+ C (car L2)) 10))))
   ((null L2) (cons (mod (+ C (car L1)) 10) (listSum (cdr L1) nil (floor (+ C (car L1)) 10))))
   (T (cons (mod (+ C (+ (car L1) (car L2))) 10) (listSum (cdr L1) (cdr L2) (floor (+ C (+ (car L1) (car L2))) 10))))
  )
)

(defun mainListSum(L1 L2)
  (reverse (listSum (reverse L1) (reverse L2) 0))
)

(defun greatestD(L D)
  (cond
   ((eql 0 (apply #'+ (mapcar #'(lambda (X) (mod X D)) L))) D)
   (T (greatestD L (- D 1)))
  )
)

(defun mainGreatestD(L)
  (greatestD L (apply #'min L))
)