(defun findLvl (l e lvl)
  (cond
   ((null l) 0)
   ((and (atom (car l)) (eql (car l) e)) lvl)
   ((listp (car l)) (+ (findLvl (car l) e (+ lvl 1)) (findLvl (cdr l) e lvl)))
   (T (findLvl (cdr l) e lvl))
  )
)
;l - list, e - elem, lvl - number
;flow(i,i,o)
;findLvl([l1..ln],e,lvl) = 0, if list is empty
;                          lvl, if l1 is atomic and l1 = e
;                          findLvl([l11..l1n],e,lvl+1) + findLvl([l2...ln],e,lvl), if l1 is a list
;                          findLvl([l2..ln],e,lvl), otherwise

(defun mainFindLvl (l e)
  (findLvl l e 0)
)
;l - list,e - elem
;flow(i, i)
;mainFindLvl([l1..ln],e) = findLvl([l1..ln],e,0)