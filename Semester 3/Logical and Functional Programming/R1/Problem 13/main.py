class Nod:
    def __init__(self, e):
        self.e = e
        self.urm = None


class Lista:
    def __init__(self):
        self.prim = None



'''
crearea unei liste din valori citite pana la 0
'''


def creareLista():
    lista = Lista()
    lista.prim = creareLista_rec()
    return lista


def creareLista_rec():
    x = int(input("x="))
    if x == 0:
        return None
    else:
        nod = Nod(x)
        nod.urm = creareLista_rec()
        return nod


'''
tiparirea elementelor unei liste
'''


def tipar(lista):
    tipar_rec(lista.prim)


def tipar_rec(nod):
    if nod != None:
        print(nod.e)
        tipar_rec(nod.urm)


'''
program pentru test
'''


def a(lista1,lista2):
    return a_test(lista1.prim,lista2) or a_test(lista2.prim,lista1)

def test_nod(nod_verif,nod_lista):
    if nod_verif.e == nod_lista.e:
        return True
    if nod_lista.urm == None:
        return False
    return test_nod(nod_verif,nod_lista.urm)

'''
test_node(nod_verif,nod_lista) = true if nod_verif.e(element)=nod_lista.e(element) - elem have same value
                                 false if nod_lista.urm is none - elem not found in list
                                 test_node(nod_verif,nod_lista.urm) otherwise
'''


def a_test(nod,lista):
    if not test_nod(nod,lista.prim):
        return False
    if nod.urm == None:
        return True
    return a_test(nod.urm,lista)

'''
a_test(nod,(l1,l2,...ln)) = false if test_node(nod,l1) = false - if elem not in list
                            true if nod.urm is none - if all elem in list
                            a_test(nod.urm,lista) otherwise
'''



def b(nod_del,nod_lista_curr,nod_lista_prev,lista):
    if nod_del==nod_lista_curr.e:
        if nod_lista_prev==None:
            lista.prim=nod_lista_curr.urm
            nod_lista_curr=None
            if lista.prim.urm != None:
                b(nod_del,lista.prim,None,lista)
        else:
            nod_lista_prev.urm=nod_lista_curr.urm
            nod_lista_curr=None
            if nod_lista_prev.urm !=None:
                b(nod_del,nod_lista_prev.urm,nod_lista_prev,lista)
    elif nod_lista_curr.urm !=None:
        b(nod_del,nod_lista_curr.urm,nod_lista_curr,lista)

'''
b(nod_del,nod_lista_nod_curr,nod_lista_prev,(l1,l2,...ln)) = b(nod_del,l2,none,(l2,l3,...,ln) if first element has to be deleted
                                                             b(nod_del,nod_lista_prev.urm,nod_lista_prev,(l1,l2,...ln)\li, li.e=nod_del.e) if an elem on a random pos has to be deleted
                                                             b(nod_del,nod_lista_curr.urm,nod_lista_curr,(l1,l2,...ln)) otherwise
'''

def b_del(elem,curr):
    if curr == None:
        return None
    if elem !=curr.e:
        nod = Nod(curr.e)
        nod.urm=b_del(elem, curr.urm)
        return nod
    return b_del(elem, curr.urm)

'''
b_del(elem,(l1,l2,..,ln)) = None if list is empty (at the end of the list)
                            l1(added to the new list) + b_del(elem,(l2..ln)) if l1!= elem
                            b_del(elem,(l1,l2,...ln)) otherwise
'''




def main():
    '''
    print("Problem a:")
    list1 = creareLista()
    list2 = creareLista()
    print(a(list1,list2))
    '''

    print("Problem b:")
    list= creareLista()
    tipar(list)
    elem=int(input("Elem to delete:"))
    list.prim=b_del(elem,list.prim)
    tipar(list)


main()
