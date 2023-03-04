(defun findLvl (L lvl)
  (cond
   ((null L) -1)
   ((null (cdr L)) lvl)
   (T (max (findLvl (cadr L) (+ 1 lvl)) (findLvl (caddr L) (+ 1 lvl))))
  )
)

(defun isBalanced (L)
  (cond
   ((null L) T)
   ((and (<= (abs (- (findLvl (cadr L) 0) (findLvl (caddr L) 0))) 1) (and (isBalanced (cadr L)) (isBalanced (caddr L)))))
   (T nil)
  )
)