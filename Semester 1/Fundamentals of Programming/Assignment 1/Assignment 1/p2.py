# Solve the problem from the second set here

def verif(n1, n2, fr):
    if n1 == 0: fr[0] = 1
    while n1 != 0:
        fr[int(n1 % 10)] = 1
        n1 = n1 / 10
    if n2 == 0 and fr[0] != 1:
        return False
    while n2 != 0:
        if fr[int(n2 % 10)] != 1:
            return False
        n2 = n2 / 10
    return True


def read():
    n1 = input("Enter the first number:")
    n2 = input("Enter the second number:")
    return n1, n2


def main():
    fr = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    n1, n2 = read()
    print(verif(int(n1), int(n2), fr))


main()
