"""
  Start the program by running this module
"""

import copy
import functions
import ui


def start():
    complex_number_list = functions.initial_complex_number_list()
    instances_of_data = [copy.deepcopy(complex_number_list)]
    command_dictionary = {'add': ui.add_command, 'list': ui.list_command, 'insert': ui.add_command,
                          'remove': ui.remove_command, 'replace': ui.replace_command, 'sum': ui.sum_command,
                          'product': ui.product_command,
                          'filter': ui.filter_command}
    while True:
        user_command = input("Type a command:")
        command_word, command_parameters = functions.split_initial_command_into_command_word_and_parameters(
            user_command)
        if command_word == 'exit':
            return
        elif command_word == 'undo':
            if len(instances_of_data) == 1:
                print("Data is at initial state")
            else:
                complex_number_list = functions.undo_command(instances_of_data)
                print("Last command successfully undone")
        elif command_word not in command_dictionary or (command_word != 'list' and command_parameters is None):
            print("Invalid command")
        else:
            command_dictionary[command_word](complex_number_list, command_parameters)
            if instances_of_data[-1] != complex_number_list:
                instances_of_data.append(copy.deepcopy(complex_number_list))


start()
