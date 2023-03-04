"""
  Program functionalities module
"""
import copy
from math import sqrt
import operator


def create_complex_number(real_part, imaginary_part):
    return real_part, imaginary_part


def get_real_part(complex_number):
    return complex_number[0]


def get_imaginary_part(complex_number):
    return complex_number[1]


def initial_complex_number_list():
    return [create_complex_number(1, 2), create_complex_number(-3, 4), create_complex_number(0, 0),
            create_complex_number(5, 1),
            create_complex_number(2, -6), create_complex_number(7, 0), create_complex_number(5, 5),
            create_complex_number(-2, -8),
            create_complex_number(-5, 3), create_complex_number(4, -1)]


def split_initial_command_into_command_word_and_parameters(user_command):
    """
    Splits the user's command into the command word and parameters
    :param user_command: the user's command
    :return: command word and parameters, command word and None if no parameters, None otherwise
    """
    user_command = user_command.lower().strip()
    tokens = user_command.split(maxsplit=1)
    command_word = tokens[0].strip() if len(tokens) > 0 else None
    command_parameters = tokens[1].strip() if len(tokens) == 2 else None
    return command_word, command_parameters


def test_split_initial_command_into_command_word_and_parameters():
    assert split_initial_command_into_command_word_and_parameters('exit') == ('exit', None)
    assert split_initial_command_into_command_word_and_parameters('  eXiT ') == ('exit', None)

    assert split_initial_command_into_command_word_and_parameters('add 4+3i') == ('add', '4+3i')
    assert split_initial_command_into_command_word_and_parameters('  aDD 4+3I') == ('add', '4+3i')

    assert split_initial_command_into_command_word_and_parameters('insert 4+3i at 2') == ('insert', '4+3i at 2')
    assert split_initial_command_into_command_word_and_parameters('inSErt    4+3i At   2') == ('insert', '4+3i at   2')

    assert split_initial_command_into_command_word_and_parameters('remove 2') == ('remove', '2')
    assert split_initial_command_into_command_word_and_parameters(' remOVe    2') == ('remove', '2')

    assert split_initial_command_into_command_word_and_parameters('replace 1+3i with 5-3i') == ('replace',
                                                                                                '1+3i with 5-3i')
    assert split_initial_command_into_command_word_and_parameters('replace   1+ 3i WiTh 5-3i') == ('replace',
                                                                                                   '1+ 3i with 5-3i')

    assert split_initial_command_into_command_word_and_parameters('list') == ('list', None)
    assert split_initial_command_into_command_word_and_parameters('lISt') == ('list', None)

    assert split_initial_command_into_command_word_and_parameters('list real 1 to 5') == ('list', 'real 1 to 5')
    assert split_initial_command_into_command_word_and_parameters('liSt   ReaL 1 To    5') == ('list', 'real 1 to    5')

    assert split_initial_command_into_command_word_and_parameters('list modulo < 10') == ('list', 'modulo < 10')
    assert split_initial_command_into_command_word_and_parameters('lISt   ModUlo  <10') == ('list', 'modulo  <10')


test_split_initial_command_into_command_word_and_parameters()


def complex_number_only_has_real_part(complex_number):
    if complex_number.find('i') == -1 and complex_number.find('+') == -1 and complex_number.find('-') == -1:
        return True
    return False


def complex_number_only_has_imaginary_part(complex_number):
    if complex_number.find('i') != -1 and complex_number.find('+') == -1 and complex_number.find('-') == -1:
        return True
    return False


def split_complex_number_into_real_and_imaginary_part(complex_number):
    """
    Splits the complex number given in the z=a+bi form into the real and imaginary part
    :param complex_number: the complex number in the z=a+bi form
    :return: the real and imaginary part, or None if error
    """
    complex_number = complex_number.strip()
    if complex_number_only_has_real_part(complex_number):
        try:
            real_part, imaginary_part = int(complex_number), 0
            return real_part, imaginary_part
        except ValueError:
            return None
    elif complex_number_only_has_imaginary_part(complex_number):
        complex_number = complex_number.removesuffix('i')
        try:
            real_part, imaginary_part = 0, int(complex_number)
            return real_part, imaginary_part
        except ValueError:
            return None
    else:
        if complex_number.find('i') == -1:
            return None
        complex_number = complex_number.removesuffix('i')
        if complex_number.find('+') != -1:
            tokens = complex_number.split('+')
        elif complex_number.find('-') != -1:
            tokens = complex_number.replace("-", "#-").split('#')
        else:
            return None
        if len(tokens) != 2:
            return None
        try:
            real_part, imaginary_part = int(tokens[0]), int(tokens[1])
            return real_part, imaginary_part
        except ValueError:
            return None


def test_split_complex_number_into_real_and_imaginary():
    assert split_complex_number_into_real_and_imaginary_part('4+    3i') == (4, 3)
    assert split_complex_number_into_real_and_imaginary_part('5') == (5, 0)
    assert split_complex_number_into_real_and_imaginary_part('2 i') == (0, 2)
    assert split_complex_number_into_real_and_imaginary_part('4+ 3') is None
    assert split_complex_number_into_real_and_imaginary_part('4i +3i') is None
    assert split_complex_number_into_real_and_imaginary_part('ab + 3i') is None
    assert split_complex_number_into_real_and_imaginary_part('6+ 4+3i') is None


test_split_complex_number_into_real_and_imaginary()


def split_command_parameters_into_complex_number_and_position(command_parameters):
    """
    Splits the parameters of add type commands into the complex number and position
    :param command_parameters:the parameters of the add type command in string form
    :return: the complex number and position if insert command,complex number and None if add command, None if error
    """
    if command_parameters.find("at") != -1:
        tokens = command_parameters.split("at")
        if len(tokens) != 2:
            return None
        complex_number, position = tokens[0], tokens[1]
        try:
            position = int(position)
        except ValueError:
            return None
    else:
        complex_number, position = command_parameters, None

    if split_complex_number_into_real_and_imaginary_part(complex_number) is None:
        return None
    real_part, imaginary_part = split_complex_number_into_real_and_imaginary_part(complex_number)
    return create_complex_number(real_part, imaginary_part), position


def test_split_command_parameters_into_complex_number_and_position():
    assert split_command_parameters_into_complex_number_and_position('4+3i') == (create_complex_number(4, 3), None)
    assert split_command_parameters_into_complex_number_and_position('4+3 i at 2') == (create_complex_number(4, 3), 2)
    assert split_command_parameters_into_complex_number_and_position('4 +  3i at afa') is None
    assert split_command_parameters_into_complex_number_and_position('hello') is None
    assert split_command_parameters_into_complex_number_and_position('a dsc at 3') is None
    assert split_command_parameters_into_complex_number_and_position('5+5i at 2 at 3') is None


test_split_command_parameters_into_complex_number_and_position()


def split_command_parameters_into_start_and_end_position(command_parameters):
    """
    Splits remove type command's parameters into  start and end position or does nothing if only one value is removed
    :param command_parameters:the parameters of the remove command in string form
    :return:start and end position if multiple values are removed,
            initial parameters and None if only one value to remove, or None if error
    """
    if command_parameters.find("to") != -1:
        tokens = command_parameters.split("to")
        if len(tokens) != 2:
            return None
        start_position, end_position = tokens[0], tokens[1]
        try:
            start_position = int(start_position.strip())
            end_position = int(end_position.strip())
        except ValueError:
            return None
        return start_position, end_position
    else:
        try:
            command_parameters = int(command_parameters)
        except ValueError:
            return None
        return command_parameters, None


def test_split_command_parameters_into_start_and_end_position():
    assert split_command_parameters_into_start_and_end_position('2') == (2, None)
    assert split_command_parameters_into_start_and_end_position('0 to 2') == (0, 2)
    assert split_command_parameters_into_start_and_end_position('0 to 2 to 3') is None
    assert split_command_parameters_into_start_and_end_position('daf to  2') is None
    assert split_command_parameters_into_start_and_end_position('2 to  ffa') is None
    assert split_command_parameters_into_start_and_end_position('dsa to rac') is None
    assert split_command_parameters_into_start_and_end_position('das') is None


test_split_command_parameters_into_start_and_end_position()


def split_command_parameters_into_old_number_and_new_number(command_parameters):
    """
    Splits replace type command's into the old number and the new one
    :param command_parameters: replace command's parameters in string form
    :return: old_number and new_number, or None if error
    """
    if command_parameters.find("with") == -1:
        return None
    else:
        tokens = command_parameters.split("with")
        if len(tokens) != 2:
            return None
        old_number, new_number = tokens[0], tokens[1]
        if (split_complex_number_into_real_and_imaginary_part(old_number) is None) or (
                split_complex_number_into_real_and_imaginary_part(new_number) is None):
            return None

        old_number_real_part, old_number_imaginary_part = split_complex_number_into_real_and_imaginary_part(old_number)
        old_number = create_complex_number(old_number_real_part, old_number_imaginary_part)

        new_number_real_part, new_number_imaginary_part = split_complex_number_into_real_and_imaginary_part(new_number)
        new_number = create_complex_number(new_number_real_part, new_number_imaginary_part)

        return old_number, new_number


def test_split_command_parameters_into_old_number_and_new_number():
    assert split_command_parameters_into_old_number_and_new_number('1 + 3i with 5-3i') == (create_complex_number(1, 3),
                                                                                           create_complex_number(5, -3))
    assert split_command_parameters_into_old_number_and_new_number('1 + 3i with 5-3i with 2+3i') is None
    assert split_command_parameters_into_old_number_and_new_number('1 + 3i with 5fs') is None
    assert split_command_parameters_into_old_number_and_new_number('asf with 5-3i') is None
    assert split_command_parameters_into_old_number_and_new_number('fsa with 3gdi') is None


def split_command_parameters_into_comparison_operator_and_given_number(command_parameters):
    """
    Splits list modulo command's parameters into comparison operator and given number
    :param command_parameters: list modulo command's parameters
    :return: comparison operator and given number, or None if error
    """
    if command_parameters.find('<') != -1 or command_parameters.find('=') != -1 or command_parameters.find('>') != -1:
        if command_parameters.find(' ') == -1:
            comparison_operator, given_number = command_parameters[0], command_parameters.removeprefix(
                command_parameters[0])
        else:
            comparison_operator, given_number = command_parameters.split()
        try:
            given_number = int(given_number)
            return comparison_operator, given_number
        except ValueError:
            return None
    else:
        return None


def test_split_command_parameters_into_comparison_operator_and_given_number():
    assert split_command_parameters_into_comparison_operator_and_given_number('<10') == ('<', 10)
    assert split_command_parameters_into_comparison_operator_and_given_number('= 10') == ('=', 10)
    assert split_command_parameters_into_comparison_operator_and_given_number('> 10') == ('>', 10)
    assert split_command_parameters_into_comparison_operator_and_given_number('equal 10') is None
    assert split_command_parameters_into_comparison_operator_and_given_number('< ten') is None
    assert split_command_parameters_into_comparison_operator_and_given_number('hello') is None


test_split_command_parameters_into_comparison_operator_and_given_number()


def add_complex_number_at_position(complex_number_list, complex_number, position):
    """
    Adds a complex number on a certain position
    :param complex_number_list: the list of complex numbers
    :param complex_number: the complex number to add
    :param position: the position on which we will add the number
    :return: modifies the list by adding a number to it
    """
    if position is None:
        complex_number_list.append(complex_number)
    else:
        complex_number_list.insert(position, complex_number)


def remove_complex_number_at_positions(complex_number_list, start_position, end_position):
    """
    Remove complex number(s) at certain positions
    :param complex_number_list: the list of complex numbers
    :param start_position: the starting position of the interval where we remove values
    :param end_position: the ending position of the interval where we remove values
    :return: modifies the list by removing complex number(s)
    """
    if end_position is None:
        complex_number_list.pop(start_position)
    else:
        for i in range(start_position, end_position + 1):
            complex_number_list.pop(start_position)


def replace_all_occurrences_of_complex_number_in_list_with_new_one(complex_number_list, old_number, new_number):
    """
    Replaces all occurrences of the old number with the new one
    :param complex_number_list: the list of complex numbers
    :param old_number: the number to replace
    :param new_number: the number that will replace the old value
    :return: modifies the list in the aforementioned way
    """
    while old_number in complex_number_list:
        index_of_old_number = complex_number_list.index(old_number)
        complex_number_list.remove(old_number)
        complex_number_list.insert(index_of_old_number, new_number)


def compute_modulus_of_complex_number(complex_number):
    """
    Computes the modulus of a complex number
    :param complex_number:the complex number
    :return:the modulus
    """
    return sqrt(get_real_part(complex_number) * get_real_part(complex_number) + get_imaginary_part(
        complex_number) * get_imaginary_part(complex_number))


def sum_of_numbers_between_positions(complex_number_list, start_position, end_position):
    """
    Computes the sum of numbers between two positions
    :param complex_number_list: the list of complex numbers
    :param start_position: the position where we start the addition
    :param end_position: the position where we end the addition
    :return: the sum's real and imaginary part
    """
    real_part_of_sum = 0
    imaginary_part_of_sum = 0
    for i in range(start_position, end_position + 1):
        real_part_to_add = complex_number_list[i][0]
        imaginary_part_to_add = complex_number_list[i][1]
        real_part_of_sum = real_part_of_sum + real_part_to_add
        imaginary_part_of_sum = imaginary_part_of_sum + imaginary_part_to_add

    return real_part_of_sum, imaginary_part_of_sum


def test_sum_of_numbers_between_positions():
    assert sum_of_numbers_between_positions([(1, 2), (2, 3), (3, 4)], 0, 2) == (6, 9)
    assert sum_of_numbers_between_positions([(-1, 2), (-2, 3), (-3, -4)], 0, 2) == (-6, 1)
    assert sum_of_numbers_between_positions([(0, 0), (0, 0), (0, 0)], 0, 2) == (0, 0)


test_sum_of_numbers_between_positions()


def product_of_numbers_between_positions(complex_number_list, start_position, end_position):
    """
    Computes the product of numbers between two positions
    :param complex_number_list: the list of complex numbers
    :param start_position: the position where we start the multiplication
    :param end_position: the position where we end the multiplication
    :return: the product's real and imaginary part
    """
    real_part_of_product = 1
    imaginary_part_of_product = 0
    for i in range(start_position, end_position + 1):
        real_part_of_first_complex_number = real_part_of_product
        imaginary_part_of_first_complex_number = imaginary_part_of_product
        real_part_of_second_complex_number = complex_number_list[i][0]
        imaginary_part_of_second_complex_number = complex_number_list[i][1]

        real_part_of_product = \
            real_part_of_first_complex_number * \
            real_part_of_second_complex_number - \
            imaginary_part_of_first_complex_number * imaginary_part_of_second_complex_number
        imaginary_part_of_product = \
            real_part_of_first_complex_number * \
            imaginary_part_of_second_complex_number + \
            imaginary_part_of_first_complex_number * real_part_of_second_complex_number

    return real_part_of_product, imaginary_part_of_product


def test_product_of_numbers_between_positions():
    assert product_of_numbers_between_positions([(1, 3), (2, 1)], 0, 1) == (-1, 7)
    assert product_of_numbers_between_positions([(2, 5), (4, -3)], 0, 1) == (23, 14)
    assert product_of_numbers_between_positions([(5, -2), (5, 2)], 0, 1) == (29, 0)


test_product_of_numbers_between_positions()


def filter_real_numbers(complex_number_list):
    """
    Modifies the list of numbers so that only real ones are left
    :param complex_number_list: the list of complex numbers
    :return: None, but modifies the list
    """
    index = 0
    while index < len(complex_number_list):
        complex_number = complex_number_list[index]
        if get_imaginary_part(complex_number) != 0:
            complex_number_list.pop(index)
            index = index - 1
        index = index + 1


def filter_numbers_with_modulo_smaller_larger_or_equal_to_given_number(complex_number_list, comparison_operator,
                                                                       given_number):
    """
    Modifies the list so that only numbers that fulfill the given criteria remain
    :param complex_number_list: the list of complex numbers
    :param comparison_operator: the comparison sign
    :param given_number: the number to which we compare the numbers in the list
    :return: None, but modifies the list
    """
    operator_dictionary = {'<': operator.lt, '=': operator.eq, '>': operator.gt}

    index = 0
    while index < len(complex_number_list):
        complex_number = complex_number_list[index]
        if not operator_dictionary[comparison_operator](compute_modulus_of_complex_number(complex_number),
                                                        given_number):
            complex_number_list.pop(index)
            index = index - 1
        index = index + 1


def undo_command(instances_of_data):
    """
    Undoes the last command that modified data
    :param instances_of_data: list of lists containing each instance of the list of complex numbers
    :return: returns the last instance, after removing one
    """
    instances_of_data.pop(-1)
    return copy.deepcopy(instances_of_data[-1])


def test_undo_command():
    assert undo_command([[(1, 2), (-3, 4), (0, 0), (5, 1), (2, -6), (7, 0), (5, 5), (-2, -8), (-5, 3), (4, -1)],
                         [(1, 2), (-3, 4), (0, 0), (5, 1), (2, -6), (7, 0), (5, 5), (-2, -8), (-5, 3), (4, -1), (0, 4)],
                         [(0, 4)]]) == [(1, 2), (-3, 4), (0, 0), (5, 1), (2, -6), (7, 0), (5, 5), (-2, -8), (-5, 3),
                                        (4, -1), (0, 4)]
    assert undo_command([[(2, 1)], [(2, 1), (4, 2)], [(2, 1), (4, 2), (4, 2)]]) == [(2, 1), (4, 2)]
    assert undo_command([[(2, 1)], [(2, 1), (4, 2)]]) == [(2, 1)]


test_undo_command()
