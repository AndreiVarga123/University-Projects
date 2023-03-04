import copy
from datetime import date, time

from src.services.PersonValidator import PersonValidator


class ActivityValidator:
    def __init__(self, activity_repository, person_repository):
        self.__activity_repository = activity_repository
        self.__person_repository = person_repository
        self.__messages = []

    @property
    def messages(self):
        return self.__messages

    def raise_exception_if_needed(self):
        if len(self.__messages) > 0:
            messages_to_print = copy.deepcopy(self.__messages)
            self.__messages = []
            raise ActivityException(messages_to_print)

    def validate_id(self, id):
        try:
            id = int(id)
            is_in_list=False
            for activity in self.__activity_repository.list_of_activities:
                if activity.id==id:
                    is_in_list=True
                    break
            if not is_in_list:
                self.__messages.append("Id " + str(id) + " not in activity list_of_activities")
        except ValueError:
            self.__messages.append("Id " + str(id) + " not a number")

    def validate_list_of_peoples_id_is_list_with_valid_ids(self, list_of_peoples_id):
        person_validator = PersonValidator(self.__person_repository)
        if (list_of_peoples_id.find(',') != -1 or list_of_peoples_id.isdigit()) and (list_of_peoples_id.find('[')==-1 or list_of_peoples_id.find(']')==-1):
            tokens = list_of_peoples_id.split(',')
            for i in range(0, len(tokens)):
                id = tokens[i].strip()
                person_validator.validate_id(id)
            if len(person_validator.messages) > 0:
                for message in person_validator.messages:
                    self.__messages.append(message)
            for token in tokens:
                tokens.remove(token)
                if token in tokens:
                    self.__messages.append("Can't have same id twice")
                    break
        else:
            self.__messages.append("Invalid list of people's ids format")

    def validate_list_of_people_no_time_overlap(self, list_of_peoples_id, date, time):
        person_validator = PersonValidator(self.__person_repository)
        for id in list_of_peoples_id:
            person_validator.validate_datetime_interval(id, [date, time])
        if len(person_validator.messages) > 0:
            for message in person_validator.messages:
                self.__messages.append(message)

    def validate_date(self, date_to_validate):
        months_with_30_days = [4, 6, 9, 11]
        february = 2

        if date_to_validate.find('-') != -1:
            tokens = date_to_validate.split('-')
            if len(tokens) != 3:
                self.__messages.append("Date has to contain exactly 3 elements")
            else:
                year, month, day = tokens[0].strip(), tokens[1].strip(), tokens[2].strip()
                try:
                    year = int(year)
                    month = int(month)
                    day = int(day)
                    date_parameters_are_valid = True
                    if month > 12 or month < 1:
                        date_parameters_are_valid = False
                        self.__messages.append("Invalid month")
                    if day > 31 or (day > 30 and month in months_with_30_days) or (day > 28 and month is february):
                        date_parameters_are_valid = False
                        self.__messages.append("Invalid day")
                    if date_parameters_are_valid:
                        if date(year, month, day) < date.today():
                            self.__messages.append("Can't add a past date")
                except ValueError:
                    self.__messages.append("Date should only contain numbers")
        else:
            self.__messages.append("Invalid date format")

    def validate_time(self, time_interval):
        if time_interval.find('-') == -1:
            self.__messages.append("Invalid time interval format")
        else:
            tokens = time_interval.split('-')
            if len(tokens) != 2:
                self.__messages.append("Interval should contain 2 times")
            else:
                starting_time, ending_time = tokens[0].strip(), tokens[1].strip()
                if starting_time.find(":") == -1 or ending_time.find(":") == -1:
                    self.__messages.append("Invalid time format")
                else:
                    tokens_starting_time = starting_time.split(':')
                    tokens_ending_time = ending_time.split(':')
                    if len(tokens_starting_time) != 2:
                        self.__messages.append("Starting time should only contain hour and minute")
                    elif len(tokens_ending_time) != 2:
                        self.__messages.append("Ending time should only contain hour and minute")
                    else:
                        starting_hour, starting_minute = tokens_starting_time[0].strip(), tokens_starting_time[
                            1].strip()
                        ending_hour, ending_minute = tokens_ending_time[0].strip(), tokens_ending_time[1].strip()
                        try:
                            starting_hour = int(starting_hour)
                            ending_hour = int(ending_hour)
                            time_parameters_are_valid = True
                            if starting_hour > 24 or starting_hour < 0:
                                self.__messages.append("Invalid starting hour")
                                time_parameters_are_valid = False
                            if ending_hour > 24 or ending_hour < 0:
                                self.__messages.append("Invalid ending hour")
                                time_parameters_are_valid = False
                            try:
                                starting_minute = int(starting_minute)
                                ending_minute = int(ending_minute)
                                if starting_minute > 59 or starting_minute < 0:
                                    self.__messages.append("Invalid starting minute")
                                    time_parameters_are_valid = False
                                if ending_minute > 59 or ending_minute < 0:
                                    self.__messages.append("Invalid ending minute")
                                    time_parameters_are_valid = False
                                if time_parameters_are_valid:
                                    if time(starting_hour, starting_minute) > time(ending_hour, ending_minute):
                                        self.__messages.append("Starting time has to be before ending time")
                            except ValueError:
                                self.__messages.append("Starting or ending minute is not a number")
                        except ValueError:
                            self.__messages.append("Starting or ending hour is not a number")


class ActivityException(Exception):
    def __init__(self, message_list):
        self.__messages = message_list

    def __str__(self):
        return str(self.__messages)
