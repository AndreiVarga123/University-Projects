# Solve the problem from the third set here

class date:
    day: int
    month: int
    year: int


def leap_year(x):
    if x % 4 == 0 and x % 100 != 0:
        return True
    elif x % 100 == 0 and x % 400 == 0:
        return True
    return False


def days(bday, today):
    count_days = 0
    month = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

    i = bday.year
    while i < today.year:
        if leap_year(i):
            count_days = count_days + 1
        if (count_days != 0):
            i = i + 4
        else:
            i = i + 1

    for i in range(bday.month, 13):
        count_days = count_days + month[i]

    for i in range(1, today.month):
        count_days = count_days + month[i]
        if i == 1 and leap_year(today.year):
            count_days = count_days + 1

    return count_days + today.day - bday.day + (today.year - bday.year - 1) * 365


def read_date():
    x = date()
    x.day = int(input(" Day:"))
    x.month = int(input(" Month:"))
    x.year = int(input(" Year:"))
    return x


def read():
    print("Enter birth date:")
    bday = read_date()
    print("Enter current date:")
    today = read_date()
    return bday, today


def main():
    bday, today = read()
    print(days(bday, today))


main()
