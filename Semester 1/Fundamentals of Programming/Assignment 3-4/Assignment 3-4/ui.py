"""
  User interface module
"""

import functions
import operator


def add_command(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_complex_number_and_position(command_parameters) is None:
        print("Invalid command")
    else:
        complex_number, position = functions.split_command_parameters_into_complex_number_and_position(
            command_parameters)
        if position in range(0, len(complex_number_list)) or position is None:
            functions.add_complex_number_at_position(complex_number_list, complex_number, position)
        else:
            print("Index out of range")
        print("Number added successfully")


def remove_command(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_start_and_end_position(command_parameters) is None:
        print("Invalid command")
    else:
        start_position, end_position = functions.split_command_parameters_into_start_and_end_position(
            command_parameters)
        if start_position in range(0, len(complex_number_list)) and \
                (end_position in range(0, len(complex_number_list)) or end_position is None):
            functions.remove_complex_number_at_positions(complex_number_list, start_position, end_position)
        else:
            print("Start or end position out of range")
        print("Number(s) removed successfully")


def replace_command(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_old_number_and_new_number(command_parameters) is None:
        print("Invalid command")
    else:
        old_number, new_number = functions.split_command_parameters_into_old_number_and_new_number(command_parameters)
        if old_number in complex_number_list:
            functions.replace_all_occurrences_of_complex_number_in_list_with_new_one(complex_number_list, old_number,
                                                                                     new_number)
            print("Number replaced successfully")
        else:
            print("That number does not exist in the list")


def list_command(complex_number_list, command_parameters):
    if command_parameters is None:
        display_all_complex_numbers(complex_number_list)
    elif command_parameters.find("real") != -1:
        command_parameters = command_parameters.removeprefix("real").strip()
        display_all_real_numbers_in_given_interval(complex_number_list, command_parameters)
    elif command_parameters.find("modulo") != -1:
        command_parameters = command_parameters.removeprefix("modulo").strip()
        display_all_numbers_with_modulo_smaller_larger_or_equal_to_given_number(complex_number_list, command_parameters)
    else:
        print("Invalid command")


def sum_command(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_start_and_end_position(command_parameters) is None:
        print("invalid input")
    else:
        start_position, end_position = functions.split_command_parameters_into_start_and_end_position(
            command_parameters)
        if start_position in range(0, len(complex_number_list)) and end_position in range(0, len(complex_number_list)):
            print("Sum between positions " + str(start_position) + " and " + str(end_position) + " is ")
            display_complex_number(
                functions.sum_of_numbers_between_positions(complex_number_list, start_position, end_position))
        else:
            print("Start or end position out of range")


def product_command(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_start_and_end_position(command_parameters) is None:
        print("invalid input")
    else:
        start_position, end_position = functions.split_command_parameters_into_start_and_end_position(
            command_parameters)
        if start_position in range(0, len(complex_number_list)) and end_position in range(0, len(complex_number_list)):
            print("Product between positions " + str(start_position) + " and " + str(end_position) + " is ")
            display_complex_number(
                functions.product_of_numbers_between_positions(complex_number_list, start_position, end_position))
        else:
            print("Start or end position out of range")


def filter_command(complex_number_list, command_parameters):
    if command_parameters == "real":
        filter_real_numbers_ui(complex_number_list)
    elif command_parameters.find("modulo") != -1:
        command_parameters = command_parameters.removeprefix("modulo").strip()
        filter_numbers_with_modulo_smaller_larger_or_equal_to_given_number_ui(complex_number_list, command_parameters)
    else:
        print("Invalid command")


def filter_real_numbers_ui(complex_number_list):
    functions.filter_real_numbers(complex_number_list)
    print("Only real numbers left")


def filter_numbers_with_modulo_smaller_larger_or_equal_to_given_number_ui(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_comparison_operator_and_given_number(command_parameters) is None:
        print("Invalid input")
    else:
        comparison_operator, given_number = \
            functions.split_command_parameters_into_comparison_operator_and_given_number(command_parameters)
        functions.filter_numbers_with_modulo_smaller_larger_or_equal_to_given_number(complex_number_list,
                                                                                     comparison_operator, given_number)
        print("Only numbers with modulo " + comparison_operator + " " + str(given_number) + " left")


def display_all_complex_numbers(complex_number_list):
    index = 0
    if len(complex_number_list) == 0:
        print("No numbers in list")
    else:
        for complex_number in complex_number_list:
            display_complex_number(complex_number, index)
            index = index + 1


def display_all_real_numbers_in_given_interval(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_start_and_end_position(command_parameters) is None:
        print("Invalid input")
    else:
        start_position, end_position = functions.split_command_parameters_into_start_and_end_position(
            command_parameters)
        if start_position in range(0, len(complex_number_list)) and end_position in range(0, len(complex_number_list)):
            no_number_fulfills_condition = True
            for i in range(start_position, end_position + 1):
                complex_number = complex_number_list[i]
                if functions.get_imaginary_part(complex_number) == 0:
                    no_number_fulfills_condition = False
                    display_complex_number(complex_number, i)
            if no_number_fulfills_condition:
                print("No real numbers in given interval")
        else:
            print("Start or end position out of range")


def display_all_numbers_with_modulo_smaller_larger_or_equal_to_given_number(complex_number_list, command_parameters):
    if functions.split_command_parameters_into_comparison_operator_and_given_number(command_parameters) is None:
        print("Invalid input")
    else:
        comparison_operator, given_number = \
            functions.split_command_parameters_into_comparison_operator_and_given_number(command_parameters)
        operator_dictionary = {'<': operator.lt, '=': operator.eq, '>': operator.gt}
        no_number_fulfills_condition = True
        for i in range(0, len(complex_number_list)):
            complex_number = complex_number_list[i]
            if operator_dictionary[comparison_operator](functions.compute_modulus_of_complex_number(complex_number),
                                                        given_number):
                no_number_fulfills_condition = False
                display_complex_number(complex_number, i)
        if no_number_fulfills_condition:
            print("No number has it's modulus " + str(comparison_operator) + " " + str(given_number))


def display_complex_number(complex_number, index=None):
    if index is None:
        index = ''
    if functions.get_imaginary_part(complex_number) > 0 and functions.get_real_part(complex_number) != 0:
        print('z' + str(index) + ' = ' + str(functions.get_real_part(complex_number)) + ' + ' + str(
            functions.get_imaginary_part(complex_number)) + 'i')
        # shows z = a + bi
    elif functions.get_imaginary_part(complex_number) < 0 and functions.get_real_part(complex_number) != 0:
        print('z' + str(index) + ' = ' + str(functions.get_real_part(complex_number)) + ' - ' + str(
            abs(functions.get_imaginary_part(complex_number))) + 'i')
        # shows z = a - bi
    elif functions.get_imaginary_part(complex_number) == 0:
        print('z' + str(index) + ' = ' + str(functions.get_real_part(complex_number)))
        # shows z = a
    else:
        print('z' + str(index) + ' = ' + str(functions.get_imaginary_part(complex_number)) + 'i')
        # shows z = bi
