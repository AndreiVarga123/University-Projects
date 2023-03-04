# Solve the problem from the first set here

def prime(x):
    if x == 2: return True
    if x % 2 == 0:
        return False
    i = 3
    while i * i <= x:
        if x % i == 0:
            return False
        i = i + 2
    return True


def large_prime(n):
    while n >= 2:
        if prime(n) == True:
            return n
        n = n - 1

def solve(n):
    if n - 1 < 2:
        print("No prime number smaller than the given value")
    else:
        print(large_prime(n - 1))

def main():
    n = int(input("Give a number:"))
    solve(n)


main()
