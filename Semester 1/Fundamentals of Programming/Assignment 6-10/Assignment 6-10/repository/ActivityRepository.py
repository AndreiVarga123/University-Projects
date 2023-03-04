from src.domain.Activity import Activity
from src.services.ActivityService import convert_string_to_list_of_int
import os
import pickle


class ActivityRepository:
    def __init__(self, activity_list):
        self.__activity_list = activity_list

    def __len__(self):
        return len(self.__activity_list)

    def __getitem__(self, item):
        return self.__activity_list[item]

    @property
    def list_of_activities(self):
        return self.__activity_list

    def add(self, activity):
        """
        Adds activity to existing list of activities
        :param activity: The activity to add
        :return: None, but adds a new activity to end of list
        """
        self.__activity_list.append(activity)

    def remove(self, id):
        for activity in self.__activity_list:
            if activity.id == id:
                self.__activity_list.remove(activity)
                break

    def update(self, id, property, what_to_change):
        for activity in self.__activity_list:
            if activity.id == id:
                if what_to_change == 'list_of_peoples_id':
                    activity.list_of_peoples_id = property
                elif what_to_change == 'date':
                    activity.date = property
                elif what_to_change == 'time':
                    activity.time = property
                else:
                    activity.description = property
                break


class ActivityTextFileRepository(ActivityRepository):

    def __init__(self, initial_activity_list):
        self.__file_name = "activity.txt"
        if os.stat(self.__file_name).st_size != 0:
            self.__load_file(initial_activity_list)
        super().__init__(activity_list=initial_activity_list)
        if os.stat(self.__file_name).st_size == 0:
            self.__save_file()

    def __load_file(self, initial_activity_list):
        file = open(self.__file_name, "rt")
        for line in file.readlines():
            id, rest_of_activity = line.split(maxsplit=1, sep=',')
            list_of_peoples_id, rest_of_activity = rest_of_activity.split(maxsplit=1, sep="],")
            list_of_peoples_id = convert_string_to_list_of_int(list_of_peoples_id.removeprefix('['))
            date, time, description = rest_of_activity.split(maxsplit=2, sep=',')
            initial_activity_list.append(Activity(int(id), list_of_peoples_id, date, time, description.strip()))
        file.close()

    def __save_file(self):
        file = open(self.__file_name, "wt")
        for activity in self.list_of_activities:
            file.write(str(activity.id) + ',' + str(
                activity.list_of_peoples_id) + ',' + activity.date + ',' + activity.time + ',' + activity.description + "\n")
        file.close()

    def add(self, activity):
        super(ActivityTextFileRepository, self).add(activity)
        self.__save_file()

    def remove(self, id):
        super(ActivityTextFileRepository, self).remove(id)
        self.__save_file()

    def update(self, id, property, what_to_change):
        super(ActivityTextFileRepository, self).update(id, property, what_to_change)
        self.__save_file()


class ActivityBinFileRepository(ActivityRepository):
    def __init__(self, initial_activity_list):
        self.__file_name = "activity.bin"
        if os.stat(self.__file_name).st_size != 0:
            super().__init__(activity_list=self.__load_file())
        else:
            super().__init__(activity_list=initial_activity_list)
        if os.stat(self.__file_name).st_size == 0:
            self.__save_file()

    def __load_file(self):
        file = open(self.__file_name, "rb")
        initial_activity_list = pickle.load(file)
        file.close()
        return initial_activity_list

    def __save_file(self):
        file = open(self.__file_name, "wb")
        pickle.dump(self.list_of_activities, file)
        file.close()

    def add(self, activity):
        super(ActivityBinFileRepository, self).add(activity)
        self.__save_file()

    def remove(self, id):
        super(ActivityBinFileRepository, self).remove(id)
        self.__save_file()

    def update(self, id, property, what_to_change):
        super(ActivityBinFileRepository, self).update(id, property, what_to_change)
        self.__save_file()
