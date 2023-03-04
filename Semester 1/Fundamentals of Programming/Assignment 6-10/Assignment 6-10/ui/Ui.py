from src.services.PersonValidator import PersonException
from src.services.ActivityValidator import ActivityException
from src.services.UndoRedoService import *
import copy


class UI:
    def __init__(self, person_service, activity_service, undo_service):
        self.__person_service = person_service
        self.__activity_service = activity_service
        self.__undo_service = undo_service

    def display_all_persons(self, list_of_people):
        for person in list_of_people:
            print(person)

    def display_all_activities(self, list_of_activities):
        for activity in list_of_activities:
            print(activity)

    def display_busiest_days(self, list_of_days):
        for day in list_of_days:
            date = day[0]
            what_to_print = "On " + date + " there are activities planned at the following time intervals: "
            unavailable_time_intervals = day[1]
            number_of_unavailable_time_intervals = len(day[1])
            for i in range(0, number_of_unavailable_time_intervals, 2):
                starting_time = unavailable_time_intervals[i]
                ending_time = unavailable_time_intervals[i + 1]
                what_to_print = what_to_print + str(starting_time) + " - " + str(ending_time)
                if i != number_of_unavailable_time_intervals - 2:
                    what_to_print = what_to_print + ", "
            print(what_to_print)

    def print_menu(self):
        print('1. Add')
        print('2. Remove')
        print('3. Update')
        print('4. Display')
        print('5. Search')
        print('6. Statistics')
        print('7. Undo')
        print('8. Redo')
        print('9. Exit')

    def print_add_menu(self):
        print('1. Add person')
        print('2. Add activity')

    def print_remove_menu(self):
        print('1. Remove person')
        print('2. Remove activity')

    def print_update_menu(self):
        print('1. Update person')
        print('2. Update activity')

    def print_update_person_menu(self):
        print('1. Update name')
        print('2. Update phone number')

    def print_update_activity_menu(self):
        print("1. Update list of people's id")
        print("2. Update date")
        print("3. Update time")
        print("4. Update description")

    def print_display_menu(self):
        print('1. Display people')
        print('2. Display activities')

    def print_search_menu(self):
        print('1. Search for people')
        print('2. Search for activities')

    def print_search_person_menu(self):
        print('1. Search by name')
        print('2. Search by phone number')

    def print_search_activity_menu(self):
        print('1. Search by date')
        print('2. Search by time')
        print('3. Search by description')

    def print_statistics_menu(self):
        print('1. Activities on a given date, in order of their start time')
        print('2. Busiest days')
        print('3. Activities for a given person')

    def start(self):
        while True:
            self.print_menu()
            option = input("Option: ")
            try:
                if option == '1':
                    self.print_add_menu()
                    option = input("Option: ")
                    if option == '1':
                        name = input("Name: ")
                        phone_number = input("Phone number: ")
                        self.__person_service.add(name.strip(), phone_number.strip())
                        print("Successfully added new person!")

                        person = self.__person_service.list_of_people[-1]
                        redo = Function(self.__person_service.add, person.name, person.phone_number)
                        undo = Function(self.__person_service.remove, person.id)
                        operation = Operation(undo, redo)
                        self.__undo_service.record(operation)

                    elif option == '2':
                        # add activity here
                        list_of_peoples_id = input("Type ids of participants as <id1>,<id2>,...: ")
                        date_to_add = input("Type date as YYYY-MM-DD: ")
                        time_to_add = input("Type time interval as HH:MM-HH:MM: ")
                        description = input("Type description: ")
                        self.__activity_service.add(list_of_peoples_id.strip(), date_to_add.strip(),
                                                    time_to_add.strip(), description.strip())
                        list_of_peoples_id_added = self.__activity_service.list_of_activities[-1].list_of_peoples_id
                        datetime_interval_added = [self.__activity_service.list_of_activities[-1].date,
                                                   self.__activity_service.list_of_activities[-1].time]
                        self.__person_service.add_unavailable_datetime_interval(list_of_peoples_id_added,
                                                                                datetime_interval_added)
                        print("Successfully added new activity!")

                        cascaded_operation = CascadedOperation()

                        activity = self.__activity_service.list_of_activities[-1]
                        redo = Function(self.__activity_service.add, activity.list_of_peoples_id,
                                        activity.date, activity.time, activity.description, activity.id)
                        undo = Function(self.__activity_service.remove, activity.id)
                        operation = Operation(undo, redo)
                        cascaded_operation.add(operation)

                        redo = Function(self.__person_service.add_unavailable_datetime_interval, list_of_peoples_id_added,
                                        datetime_interval_added)
                        undo = Function(self.__person_service.remove_unavailable_datetime_interval,
                                        list_of_peoples_id_added,
                                        datetime_interval_added)
                        datetime_operation = Operation(undo, redo)
                        cascaded_operation.add(datetime_operation)

                        self.__undo_service.record(cascaded_operation)

                    else:
                        print("Invalid command")
                elif option == '2':
                    self.print_remove_menu()
                    option = input("Option: ")
                    if option == '1':
                        id_to_remove = input("Type the id of the person to remove: ")
                        self.__person_service.check_if_id_valid(id_to_remove)
                        id_to_remove = int(id_to_remove)
                        for person in self.__person_service.list_of_people:
                            if person.id == int(id_to_remove):
                                person_to_record = copy.deepcopy(person)
                                break
                        self.__person_service.remove(id_to_remove)
                        print("Successfully removed person!")

                        redo = Function(self.__person_service.remove, person_to_record.id)
                        undo = Function(self.__person_service.add, person_to_record.name, person_to_record.phone_number,
                                        person_to_record.id)
                        operation = Operation(undo, redo)
                        self.__undo_service.record(operation)

                    elif option == '2':
                        # remove activity here
                        id_to_remove = input("Type the id of the activity to remove: ")
                        self.__activity_service.check_if_id_valid(id_to_remove)
                        id_to_remove = int(id_to_remove)
                        for activity in self.__activity_service.list_of_activities:
                            if activity.id == id_to_remove:
                                activity_to_remove = activity
                                break
                        list_of_people_that_do_activity = activity_to_remove.list_of_peoples_id
                        datetime_interval_to_remove = [activity_to_remove.date, activity_to_remove.time]
                        self.__activity_service.remove(id_to_remove)
                        self.__person_service.remove_unavailable_datetime_interval(list_of_people_that_do_activity,
                                                                                   datetime_interval_to_remove)
                        print("Successfully removed activity!")

                        cascaded_operation = CascadedOperation()

                        redo = Function(self.__activity_service.remove, activity_to_remove.id)
                        undo = Function(self.__activity_service.add, activity_to_remove.list_of_peoples_id,
                                        activity_to_remove.date, activity_to_remove.time, activity_to_remove.description,
                                        activity_to_remove.id)
                        operation = Operation(undo, redo)
                        cascaded_operation.add(operation)

                        redo = Function(self.__person_service.remove_unavailable_datetime_interval,
                                        list_of_people_that_do_activity, datetime_interval_to_remove)
                        undo = Function(self.__person_service.add_unavailable_datetime_interval,
                                        list_of_people_that_do_activity, datetime_interval_to_remove)
                        datetime_operation = Operation(undo, redo)
                        cascaded_operation.add(datetime_operation)

                        self.__undo_service.record(cascaded_operation)

                    else:
                        print("Invalid command")
                elif option == '3':
                    self.print_update_menu()
                    option = input("Option: ")
                    if option == '1':
                        id_to_update = input("Type the id of the person to update: ")
                        self.__person_service.check_if_id_valid(id_to_update)
                        id_to_update = int(id_to_update)
                        self.print_update_person_menu()
                        option = input("Option: ")
                        if option == '1':
                            new_name = input("Type the new name: ")

                            for person in self.__person_service.list_of_people:
                                if person.id == int(id_to_update):
                                    person_before_update = copy.deepcopy(person)
                                    break

                            self.__person_service.update(id_to_update, new_name.strip(), 'name')
                            print("Successfully updated person!")

                            for person in self.__person_service.list_of_people:
                                if person.id == int(id_to_update):
                                    person_after_update = copy.deepcopy(person)
                                    break

                            redo = Function(self.__person_service.update, person_after_update.id, person_after_update.name,
                                        'name')
                            undo = Function(self.__person_service.update, person_before_update.id,
                                            person_before_update.name, 'name')
                            operation = Operation(undo, redo)
                            self.__undo_service.record(operation)

                        elif option == '2':
                            new_phone_number = input("Type the new phone number: ")

                            for person in self.__person_service.list_of_people:
                                if person.id == int(id_to_update):
                                    person_before_update = copy.deepcopy(person)
                                    break

                            self.__person_service.update(id_to_update, new_phone_number.strip(), 'phone number')
                            print("Successfully updated person!")

                            for person in self.__person_service.list_of_people:
                                if person.id == int(id_to_update):
                                    person_after_update = copy.deepcopy(person)
                                    break

                            redo = Function(self.__person_service.update, id_to_update, person_after_update.name,
                                        'phone number')
                            undo = Function(self.__person_service.update, id_to_update, person_before_update.name,
                                        'phone number')
                            operation = Operation(undo, redo)
                            self.__undo_service.record(operation)


                        else:
                            print("Invalid command")
                    elif option == '2':
                        # update activity here
                        id_to_update = input("Type id of activity to update: ")
                        self.__activity_service.check_if_id_valid(id_to_update)
                        id_to_update = int(id_to_update)
                        self.print_update_activity_menu()
                        option = input("Option: ")
                        if option in ['1', '2', '3', '4']:
                            for activity in self.__activity_service.list_of_activities:
                                if activity.id == id_to_update:
                                    activity_to_update=activity
                                    break
                            list_of_people_to_remove = copy.deepcopy(activity_to_update.list_of_peoples_id)
                            datetime_interval_to_remove = copy.deepcopy([activity_to_update.date, activity_to_update.time])

                            old_list_of_people_string = copy.deepcopy(str(list_of_people_to_remove).removeprefix('[').removesuffix(']'))
                            old_date=copy.deepcopy(activity_to_update.date)
                            old_time=copy.deepcopy(activity_to_update.time)
                            old_description=copy.deepcopy(activity_to_update.description)

                            cascaded_operation=CascadedOperation()

                            if option == '1':
                                new_list_of_peoples_id = input("Type new list of people's ids as <id1>,<id2>,...: ")
                                self.__activity_service.update(id_to_update, new_list_of_peoples_id,
                                                               'list_of_peoples_id')
                                redo=Function(self.__activity_service.update, id_to_update, new_list_of_peoples_id, 'list_of_peoples_id')
                                undo=Function(self.__activity_service.update, id_to_update, old_list_of_people_string, 'list_of_peoples_id')
                            elif option == '2':
                                new_date = input("Type new date as YYYY-MM-DD: ")
                                self.__activity_service.update(id_to_update, new_date, 'date')
                                redo = Function(self.__activity_service.update, id_to_update, new_date, 'date')
                                undo = Function(self.__activity_service.update, id_to_update, old_date, 'date')
                            elif option == '3':
                                new_time = input("Type new time interval as HH:MM-HH:MM ")
                                self.__activity_service.update(id_to_update, new_time, 'time')
                                redo = Function(self.__activity_service.update, id_to_update, new_time, 'time')
                                undo = Function(self.__activity_service.update, id_to_update, old_time, 'time')
                            elif option == '4':
                                new_description = input("Type new description: ")
                                self.__activity_service.update(id_to_update, new_description, 'description')
                                redo = Function(self.__activity_service.update, id_to_update, new_description, 'description')
                                undo = Function(self.__activity_service.update, id_to_update, old_description, 'description')

                            list_of_people_to_add = copy.deepcopy(activity_to_update.list_of_peoples_id)
                            datetime_interval_to_add = copy.deepcopy([activity_to_update.date, activity_to_update.time])

                            self.__person_service.remove_unavailable_datetime_interval(list_of_people_to_remove,
                                                                                       datetime_interval_to_remove)
                            self.__person_service.add_unavailable_datetime_interval(
                                list_of_people_to_add, datetime_interval_to_add)
                            print("Successfully updated activity!")

                            update_operation = Operation(undo, redo)

                            redo = Function(self.__person_service.remove_unavailable_datetime_interval,
                                            list_of_people_to_remove, datetime_interval_to_remove)
                            undo = Function(self.__person_service.remove_unavailable_datetime_interval,
                                            list_of_people_to_add, datetime_interval_to_add)
                            remove_datetime_operation = Operation(undo, redo)

                            redo = Function(self.__person_service.add_unavailable_datetime_interval, list_of_people_to_add,
                                            datetime_interval_to_add)
                            undo = Function(self.__person_service.add_unavailable_datetime_interval,
                                            list_of_people_to_remove, datetime_interval_to_remove)
                            add_datetime_operation = Operation(undo, redo)

                            cascaded_operation.add(remove_datetime_operation)
                            cascaded_operation.add(update_operation)
                            cascaded_operation.add(add_datetime_operation)
                            self.__undo_service.record(cascaded_operation)

                        else:
                            print("Invalid command")
                    else:
                        print("Invalid command")
                elif option == '4':
                    self.print_display_menu()
                    option = input("Option: ")
                    if option == '1':
                        self.display_all_persons(self.__person_service.list_of_people)
                    elif option == '2':
                        self.display_all_activities(self.__activity_service.list_of_activities)
                    else:
                        print("Invalid command")
                elif option == '5':
                    self.print_search_menu()
                    option = input("Option: ")
                    if option == '1':
                        self.print_search_person_menu()
                        option = input("Option: ")
                        if option == '1':
                            name_to_search = input("Type name to search by: ")
                            list_of_found_people = self.__person_service.search(name_to_search, 'name')
                            if len(list_of_found_people) == 0:
                                print("No people found for given name")
                            else:
                                self.display_all_persons(list_of_found_people)
                        elif option == '2':
                            phone_number_to_search = input("Type phone number to search by: ")
                            list_of_found_people = self.__person_service.search(phone_number_to_search, 'phone number')
                            if len(list_of_found_people) == 0:
                                print("No people found for given phone number")
                            else:
                                self.display_all_persons(list_of_found_people)
                        else:
                            print("Invalid command")
                    elif option == '2':
                        self.print_search_activity_menu()
                        option = input("Option: ")
                        if option == '1':
                            date_to_search = input("Type date to search by: ")
                            list_of_found_activities = self.__activity_service.search(date_to_search, 'date')
                            if len(list_of_found_activities) == 0:
                                print("No activities found for given date")
                            else:
                                self.display_all_activities(list_of_found_activities)
                        elif option == '2':
                            time_to_search = input("Type time to search by: ")
                            list_of_found_activities = self.__activity_service.search(time_to_search, 'time')
                            if len(list_of_found_activities) == 0:
                                print("No activities found for given time")
                            else:
                                self.display_all_activities(list_of_found_activities)
                        elif option == '3':
                            description_to_search = input("Type description to search by: ")
                            list_of_found_activities = self.__activity_service.search(description_to_search,
                                                                                      'description')
                            if len(list_of_found_activities) == 0:
                                print("No activities found for given description")
                            else:
                                self.display_all_activities(list_of_found_activities)
                        else:
                            print("Invalid command")
                    else:
                        print("Invalid command")
                elif option == '6':
                    self.print_statistics_menu()
                    option = input("Option: ")
                    if option == '1':
                        date = input("Type date: ")
                        list_of_activities = self.__activity_service.activities_for_given_date(date)
                        if len(list_of_activities) == 0:
                            print("No activities found for given date")
                        else:
                            self.display_all_activities(list_of_activities)
                    elif option == '2':
                        list_of_days = self.__activity_service.order_days_by_time_interval_with_no_activity()
                        self.display_busiest_days(list_of_days)
                    elif option == '3':
                        id_of_person = input("Type id of the person: ")
                        self.__person_service.check_if_id_valid(id_of_person)
                        list_of_activities_with_person = self.__activity_service.find_activities_to_which_a_person_participates(
                            id_of_person)
                        if len(list_of_activities_with_person) == 0:
                            print("Person has no planned activities")
                        else:
                            self.display_all_activities(list_of_activities_with_person)

                    else:
                        print("Invalid command")
                elif option == '7':
                    self.__undo_service.undo()
                elif option == '8':
                    self.__undo_service.redo()
                elif option == '9':
                    return
                else:
                    print("Invalid option")
            except (PersonException, ActivityException, ValueError) as exception_messages:
                print(exception_messages)
