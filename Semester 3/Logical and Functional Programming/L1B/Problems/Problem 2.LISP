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

(defun howManyOnLvl(L lvl clvl)
  (cond
   ((null L) 0)
   ((eql lvl clvl) 1)
   (T (+ (howManyOnLvl (getLeftSubtree L) lvl (+ 1 clvl)) (howManyOnLvl (getRightSubtree L) lvl (+ 1 clvl))))
  )
)
