import copy
from datetime import datetime


class PersonValidator:
    def __init__(self, person_repository):
        self.__person_repository = person_repository
        self.__messages = []

    @property
    def messages(self):
        return self.__messages

    def raise_exception_if_needed(self):
        if len(self.__messages) > 0:
            messages_to_print = copy.deepcopy(self.__messages)
            self.__messages = []
            raise PersonException(messages_to_print)

    def validate_phone_number(self, phone_number):
        if len(str(phone_number)) != 10:
            self.__messages.append("Phone number too short")
        else:
            if phone_number[0:2] != '07':
                self.__messages.append("Phone number has an invalid prefix")
            if not phone_number.isdigit():
                self.__messages.append("Phone should only contain digits")

    def validate_id(self, id):
        try:
            id = int(id)
            is_in_list = False
            for person in self.__person_repository.list_of_people:
                if person.id == id:
                    is_in_list = True
                    break
            if not is_in_list:
                self.__messages.append("Id " + str(id) + " not in person list_of_activities")
        except ValueError:
            self.__messages.append("Id " + str(id) + " not a number")

    def validate_datetime_interval(self, id, datetime_interval):
        for person in self.__person_repository.list_of_people:
            if person.id==id:
                person_to_validate = person
                break
        date_to_be_added = datetime_interval[0]
        time_to_be_added = datetime_interval[1]
        starting_time_to_be_added, ending_time_to_be_added = time_to_be_added.split('-')
        starting_time_to_be_added = datetime.strptime(starting_time_to_be_added.strip(), '%H:%M')
        ending_time_to_be_added = datetime.strptime(ending_time_to_be_added.strip(), '%H:%M')
        for unavailable_datetime in person_to_validate.unavailable_datetime_list:
            date_in_list = unavailable_datetime[0]
            if date_in_list == date_to_be_added:
                time_in_list = unavailable_datetime[1]
                starting_time_in_list, ending_time_in_list = time_in_list.split('-')
                starting_time_in_list = datetime.strptime(starting_time_in_list.strip(), '%H:%M')
                ending_time_in_list = datetime.strptime(ending_time_in_list.strip(), '%H:%M')
                if (
                        starting_time_to_be_added >= starting_time_in_list and starting_time_to_be_added <= ending_time_in_list) or (
                        ending_time_to_be_added >= starting_time_in_list and ending_time_to_be_added <= ending_time_in_list):
                    self.messages.append(person_to_validate.name + " (id=" + str(id) + ") " + "can't do activity at " + str(
                        datetime_interval) + " because it overlaps with another activity: " + str(unavailable_datetime))


class PersonException(Exception):
    def __init__(self, message_list):
        self.__messages = message_list

    def __str__(self):
        return str(self.__messages)
