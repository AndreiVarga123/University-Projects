#
# Write the implementation for A2 in this file
#


from math import sqrt, ceil


# UI section
# (write all functions that have input or print statements here). 
# Ideally, this section should not contain any calculations relevant to program functionalities


def add_number(number_list):
    number_real = input("Enter real part:")
    number_complex = input("Enter complex part:")

    if create_complex(number_real, number_complex) is None:
        print("Invalid input")
    else:
        number = create_complex(number_real, number_complex)
        number_list.append(number)
        print("Complex number added successfully")


def show_distinct(sequence):
    print("Longest sequence of distinct numbers:")
    show_all_numbers(sequence)


def show_dif_is_prime(sequence):
    if len(sequence) == 0:
        print("No difference is a prime number")
    else:
        print("Longest sequence whose difference between the modulus of consecutive numbers is a prime number:")
        show_all_numbers(sequence)


def show_number(number, index):
    if get_complex(number) >= 0:
        print('z' + str(index) + ' = ' + str(get_real(number)) + ' + ' + str(get_complex(number)) + 'i')
        # shows z = a + bi
    else:
        print('z' + str(index) + ' = ' + str(get_real(number)) + ' - ' + str(abs(get_complex(number))) + 'i')
        # shows z = a - bi


def show_all_numbers(number_list):
    index = 1
    for number in number_list:
        show_number(number, index)
        index = index + 1


def print_menu():
    print("1. Add complex number")
    print("2. Show all numbers")
    print("3. Longest sequence of distinct numbers")
    print("4. Longest sequence whose difference between the modulus of consecutive numbers is a prime number")
    print("5. Exit")


def start():
    number_list = initial_numbers()

    while True:
        print("")
        print_menu()
        option = input("User option: ").strip()
        print("")

        if option == '1':
            add_number(number_list)
        elif option == '2':
            show_all_numbers(number_list)
        elif option == '3':
            show_distinct(sequence_distinct(number_list))
        elif option == '4':
            show_dif_is_prime(sequence_dif_is_prime(number_list))
        elif option == '5':
            return
        else:
            print("Option does not exist")


# Function section
# (write all non-UI functions in this section)
# There should be no print or input statements below this comment
# Each function should do one thing only
# Functions communicate using input parameters and their return values


def initial_numbers():
    return [create_complex(2, 3), create_complex(1, 5), create_complex(-1, 2),
            create_complex(3, -7), create_complex(-2, -4),
            create_complex(20, 21), create_complex(-3, 12), create_complex(1, 5),
            create_complex(3, 4), create_complex(6, 8)]


def create_complex(a, b):
    """
    Create complex number
    :param a: Number's real part
    :param b: Number's complex part
    :return: New complex number, or None if the input was not an actual number
    """
    try:
        a = int(a)
        b = int(b)
    except ValueError:
        return None

    return [a, b]


def get_real(complex_number):
    return complex_number[0]


def get_complex(complex_number):
    return complex_number[1]


def sequence_distinct(number_list):
    """
    Determine the longest sequence of distinct numbers
    :param number_list: the list of all the complex numbers
    :return: list containing longest sequence
    """
    sequence = []
    sequence_intermediary = []

    for number in number_list:
        if number in sequence_intermediary:  # check if number is in intermediary list
            if len(sequence_intermediary) > len(sequence):
                # if number is in sequence check if intermediary list is longer than actual list
                sequence = sequence_intermediary.copy()
                # if intermediary list is longer than actual list, intermediary list becomes actual list
            sequence_intermediary.clear()
            sequence_intermediary.append(number)
        else:
            sequence_intermediary.append(number)  # if number is not in sequence add to intermediary list
    if len(sequence_intermediary) > len(sequence):
        sequence = sequence_intermediary

    return sequence


def complex_modulus(number):
    return sqrt(get_real(number) * get_real(number) + get_complex(number) * get_complex(number))


def prime(number):
    """
    Determines if a given number is prime
    :return: True if number is prime, false otherwise
    """
    if int(number) != ceil(number):
        return False
    if number < 2 or (number % 2 == 0 and number != 2):
        return False
    if number == 2:
        return True

    i = 3
    while i * i <= number:
        if number % i == 0:
            return False
        i = i + 2

    return True


def sequence_dif_is_prime(number_list):
    """
    Determine the longest sequence where the difference between the modulus of 2 consecutive numbers is a prime number
    :param number_list: the list of all the complex numbers
    :return: list containing longest sequence
    """
    sequence = []
    sequence_intermediary = []
    first_value = number_list[0]

    for i in range(1, len(number_list)):
        second_value = number_list[i]
        if prime(complex_modulus(second_value) - complex_modulus(first_value)):  # checks if said difference is prime
            if len(sequence_intermediary) == 0:
                sequence_intermediary.append(first_value)
            sequence_intermediary.append(second_value)
            # if intermediary list is empty add both values, otherwise only the second
        else:
            if len(sequence_intermediary) > len(sequence):
                sequence = sequence_intermediary.copy()
                # if intermediary list is longer than actual list, intermediary list becomes actual list
            sequence_intermediary.clear()
        first_value = second_value
    if len(sequence_intermediary) > len(sequence):
        sequence = sequence_intermediary

    return sequence


start()
