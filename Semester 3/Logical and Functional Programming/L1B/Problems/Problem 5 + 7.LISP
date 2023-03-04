;type 2: (A (B) (C (D E)))
;type 1: (A 2 B 0 C 2 D 0 E 0)

;goLeft ->returns the left subtree
(defun goLeft (L nrNodes nrEdges)
  (cond
   ((null L) nil)
   ((EQUAL nrNodes (+ 1 nrEdges)) nil)
   (T (cons (car L) (cons (cadr L) (goLeft (cddr L) (+ 1 nrNodes) (+ (cadr L) nrEdges)))))
  )
)


;getLeftSubTree
(defun getLeftSubtree (L)
  (goLeft (cddr L) 0 0)
)


;goRight -> returns the right subtree
(defun goRight (L nrNodes nrEdges)
  (cond
   ((null L) nil)
   ((EQUAL nrNodes (+ 1 nrEdges)) L)
   (T (goRight (cddr L) (+ 1 nrNodes) (+ (cadr L) nrEdges)))
  )
)


;getRightSubtree
(defun getRightSubtree (L)
  (goRight (cddr L) 0 0)
)

(defun findLvl(L X)
  (cond
   ((eql (car L) X) 0)
   ((member X (getLeftSubtree L)) (+ 1 (findLvl (getLeftSubtree L) X)))
   ((member X (getRightSubtree L)) (+ 1 (findLvl (getRightSubtree L) X)))
   (T -1)
  )
)