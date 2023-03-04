import random
import datetime

"""
Implement the solution here. 
You may add other source files.
Make sure you commit & push the source code before the end of the test.

Solutions using user-defined classes will not be graded.
"""


def is_digit_in_number(digit,number):
    number_copy=number
    while number_copy!=0:
        if digit==number_copy%10:
            return True
        number_copy=number_copy/10
    return False


def generate_number_to_guess():
    """
    Generates the number to guess by adding a new digit only if it distinct from the others
    :return: the number to guess if it is distinct from 8086, otherwise the function gets called again
    """

    generated_number=random.randint(1,9)
    while generated_number <= 999:
        digit=random.randint(0,9)
        if is_digit_in_number(digit,generated_number) is False:
            generated_number=generated_number*10+digit
    if generated_number!=8086:
        return generated_number
    generate_number_to_guess()


def unique_digits_in_number(number):
    while number>9:
        digit=number%10
        number=number/10
        number_copy=number
        while number_copy !=0:
            if digit==number_copy%10:
                return False
            number_copy=number_copy/10


def validate(number):

    try:
        number=int(number)
    except ValueError:
        print("Computer wins")
        return None
    if number>9999 or number<1000:
        print("Computer wins")
        return None
    if unique_digits_in_number(number) is False:
        print("Computer wins")
        return None
    return number


def number_of_codes_and_runners(number,number_to_guess):
    if number==8086:
        print("Selected number is " + str(number_to_guess))
    elif number==number_to_guess:
        print("Human wins")
        return -1
    else:
        number_of_codes=0
        number_of_runners=0
        number_to_guess_copy=number_to_guess
        while number_to_guess_copy>0:
            digit= number%10
            number=int(number/10)
            if digit==number_to_guess_copy%10:
                number_of_codes=number_of_codes+1
            number_to_guess_copy=int(number_to_guess_copy/10)
            number_to_guess_copy_2=number_to_guess_copy
            while number_to_guess_copy_2>0:
                if digit==number_to_guess_copy_2%10:
                    number_of_runners=number_of_runners+1
                number_to_guess_copy_2=int(number_to_guess_copy_2/10)
        print("Computer reports " + str(number_of_codes) + " codes and " + str(number_of_runners) + " runners")



def start():
    time= datetime.datetime.now()
    number_to_guess=generate_number_to_guess()
    while True:
        human_input=input("Take a guess: ")
        if validate(human_input) is None:
            return
        else:
            number=validate(human_input)
        if number_of_codes_and_runners(number,number_to_guess) == -1:
            return
        if time.minute-datetime.datetime.now().minute==1:
            print("No more time")
            return


start()

