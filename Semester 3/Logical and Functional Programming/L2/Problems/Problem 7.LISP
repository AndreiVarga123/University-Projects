(defun replaceWithList(l e l1 r)
  (cond
   ((null l) r)
   ((eql e l) (append r l1))
   ((atom l) (append r l))
   (T (append r (list (mapcar #'(lambda (l) (replaceWithList l e l1 r)) l))))
  )
)