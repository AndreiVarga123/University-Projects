(defun goLeft(L nodes edges)
  (cond
   ((null L) nil)
   ((eql nodes (+ 1 edges)) nil)
   (T (cons (car L) (cons (cadr L) (goLeft (cddr L) (+ 1 nodes) (+ (cadr L) edges)))))
  )
)

(defun getLeftSubtree(L)
  (goLeft (cddr L) 0 0)
)

(defun goRight(L nodes edges)
  (cond 
   ((null L) nil)
   ((eql nodes (+ 1 edges)) L)
   (T (goRight (cddr L) (+ 1 nodes) (+ (cadr L) edges)))
  )
)

(defun getRightSubtree(L)
  (goRight (cddr L) 0 0)
)

(defun nrOfLvls(L lvl)
  (cond
   ((null L) 0)
   ((eql (cadr L) 0) lvl)
   (T (max (nrOfLvls (getLeftSubtree L) (+ 1 lvl)) (nrOfLvls (getRightSubTree L) (+ 1 lvl))))
  )
)

