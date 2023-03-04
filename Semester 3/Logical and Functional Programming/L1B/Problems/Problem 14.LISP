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

(defun myAppend(L1 L2)
  (cond
   ((null L1) L2)
   (T (cons (car L1) (myAppend (cdr L1) L2)))
  )
)

(defun postOrder(L)
  (cond
   ((null L) nil)
   (T (myAppend (postOrder (getLeftSubtree L)) (myAppend (postorder (getRightSubtree l)) (list (car L)))))
  )
)