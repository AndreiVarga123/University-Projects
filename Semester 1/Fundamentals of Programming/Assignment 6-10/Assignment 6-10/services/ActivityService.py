from src.domain.Activity import Activity
from datetime import datetime
from src.domain.IterableCollection import IterableCollection


class ActivityService:
    def __init__(self, activity_repository, activity_validator):
        self.__repository = activity_repository
        self.__validator = activity_validator

    @property
    def list_of_activities(self):
        return self.__repository.list_of_activities

    def check_if_id_valid(self, id):
        self.__validator.validate_id(id)
        self.__validator.raise_exception_if_needed()

    def add(self, list_of_peoples_id, date, time, description, id=None):
        """
        Create new activity's id, validate activity's properties,create activity instance,and add to list_of_activities
        :param list_of_peoples_id: List that contains the ids of people that do activity
        :param date: Date of the activity
        :param time: Time interval of the activity
        :param description: Description of activity
        :return: None, but adds activity to list_of_activities if it is valid, otherwise throws ActivityException
        """
        if id is None:
            new_id = self.__repository.list_of_activities[-1].id + 1
            self.__validator.validate_date(date)
            self.__validator.validate_time(time)
            self.__validator.validate_list_of_peoples_id_is_list_with_valid_ids(list_of_peoples_id)
            self.__validator.raise_exception_if_needed()
            list_of_peoples_id = convert_string_to_list_of_int(list_of_peoples_id)
            self.__validator.validate_list_of_people_no_time_overlap(list_of_peoples_id, date, time)
            self.__validator.raise_exception_if_needed()
        else:
            new_id = id
        activity = Activity(new_id, list_of_peoples_id, date, time, description)
        self.__repository.add(activity)

    def remove(self, id):
        self.__repository.remove(id)

    def update(self, id, property, what_to_change):
        for activity in self.list_of_activities:
            if activity.id == id:
                date_of_activity_old = activity.date
                time_of_activity_old = activity.time
                list_of_peoples_old = activity.list_of_peoples_id
                break

        if what_to_change == 'list_of_peoples_id':
            self.__validator.validate_list_of_peoples_id_is_list_with_valid_ids(property)
            self.__validator.raise_exception_if_needed()
            property = convert_string_to_list_of_int(property)
            if not is_sublist(property, list_of_peoples_old):
                self.__validator.validate_list_of_people_no_time_overlap(property, date_of_activity_old,
                                                                         time_of_activity_old)
        if what_to_change == 'date':
            self.__validator.validate_date(property)
            self.__validator.validate_list_of_people_no_time_overlap(list_of_peoples_old, property,
                                                                     time_of_activity_old)
        elif what_to_change == 'time':
            self.__validator.validate_time(property)
            self.__validator.validate_list_of_people_no_time_overlap(list_of_peoples_old, date_of_activity_old,
                                                                     property)
        self.__validator.raise_exception_if_needed()
        self.__repository.update(id, property, what_to_change)

    def search(self, user_input, what_to_search):
        list_of_found_activities = IterableCollection()
        if what_to_search == 'date':
            for activity in self.list_of_activities:
                if activity.date.lower().find(user_input.lower()) != -1:
                    list_of_found_activities.append(activity)
        elif what_to_search == 'time':
            for activity in self.list_of_activities:
                if activity.time.lower().find(user_input.lower()) != -1:
                    list_of_found_activities.append(activity)
        else:
            for activity in self.list_of_activities:
                if activity.description.lower().find(user_input.lower()) != -1:
                    list_of_found_activities.append(activity)
        return list_of_found_activities

    def __starting_time_as_number(self, activity):
        starting_hour, starting_minute = activity.starting_time.split(':')
        return starting_hour * 60 + starting_minute

    def activities_for_given_date(self, given_date):
        list_of_activities_on_given_date = self.search(given_date, 'date')
        list_of_activities_on_given_date=IterableCollection.sort(list_of_activities_on_given_date, self.__starting_time_as_number)
        return list_of_activities_on_given_date

    def __get_list_of_dates(self):
        list_of_dates = list()
        for activity in self.list_of_activities:
            if activity.date not in list_of_dates:
                list_of_dates.append(activity.date)
        return list_of_dates

    def __compute_free_time_in_minutes(self, list_of_unavailable_time_intervals):
        last_unavailable_end_time = datetime.strptime(list_of_unavailable_time_intervals[-1], '%H:%M')
        first_unavailable_start_time = datetime.strptime(list_of_unavailable_time_intervals[0], '%H:%M')
        minutes_from_start_of_day_to_first_unavailable_start_time = first_unavailable_start_time.hour * 60 + first_unavailable_start_time.minute
        minutes_from_last_unavailable_end_time_to_end_of_day = 24 * 60 - last_unavailable_end_time.hour * 60 - last_unavailable_end_time.minute
        free_time = minutes_from_start_of_day_to_first_unavailable_start_time + minutes_from_last_unavailable_end_time_to_end_of_day
        if len(list_of_unavailable_time_intervals) >= 3:
            for i in range(1, len(list_of_unavailable_time_intervals) - 3, 2):
                end_of_current_time_interval = datetime.strptime(list_of_unavailable_time_intervals[i], '%H:%M')
                start_of_next_time_interval = datetime.strptime(list_of_unavailable_time_intervals[i + 1], '%H:%M')
                free_time_interval_as_number = start_of_next_time_interval.hour * 60 + start_of_next_time_interval.minute - end_of_current_time_interval.hour * 60 - end_of_current_time_interval.minute
                free_time = free_time + free_time_interval_as_number
        return free_time

    def __get_list_of_dates_with_unavailable_time_intervals_activities_and_amount_of_free_time(self):
        list_of_days = IterableCollection()
        list_of_dates = self.__get_list_of_dates()
        for date in list_of_dates:
            list_of_unavailable_time_intervals = IterableCollection()
            for activity in self.list_of_activities:
                if activity.date == date:
                    if activity.time not in list_of_unavailable_time_intervals:
                        list_of_unavailable_time_intervals.append(activity.starting_time)
                        list_of_unavailable_time_intervals.append(activity.ending_time)

            list_of_unavailable_time_intervals=IterableCollection.sort(list_of_unavailable_time_intervals)
            free_time = self.__compute_free_time_in_minutes(list_of_unavailable_time_intervals)
            list_of_days.append([date, list_of_unavailable_time_intervals, free_time])
        return list_of_days

    def __free_time(self, day):
        free_time = day[2]
        return free_time

    def order_days_by_time_interval_with_no_activity(self):
        list_of_days_to_order = self.__get_list_of_dates_with_unavailable_time_intervals_activities_and_amount_of_free_time()
        list_of_days_ordered = IterableCollection.sort(list_of_days_to_order, self.__free_time)
        return list_of_days_ordered

    def find_activities_to_which_a_person_participates(self, id_of_person):

        def __person_does_activity(activity):
            if int(id_of_person) in activity.list_of_peoples_id:
                return True
            return False
        list_of_activities_with_that_person=IterableCollection.filter(self.list_of_activities, __person_does_activity)

        return list_of_activities_with_that_person


def convert_string_to_list_of_int(string):
    list = string.split(',')
    for i in range(len(list)):
        list[i] = int(list[i].strip())
    return list


def is_sublist(possible_sublist, list):
    is_sublist = True
    for element in possible_sublist:
        if element not in list:
            is_sublist = False
            break
    return is_sublist
