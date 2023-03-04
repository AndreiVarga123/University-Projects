class Activity:
    def __init__(self, activity_id, list_of_peoples_id, date, time, description):
        self.__id = activity_id
        self.__list_of_peoples_id = list_of_peoples_id
        self.__date = date
        self.__time = time
        self.__description = description

    @property
    def id(self):
        return self.__id

    @id.setter
    def id(self, id):
        self.__id = id

    @property
    def list_of_peoples_id(self):
        return self.__list_of_peoples_id

    @list_of_peoples_id.setter
    def list_of_peoples_id(self, list_of_peoples_id):
        self.__list_of_peoples_id = list_of_peoples_id

    @property
    def date(self):
        return self.__date

    @date.setter
    def date(self, date):
        self.__date = date

    @property
    def time(self):
        return self.__time

    @time.setter
    def time(self, time):
        self.__time = time

    @property
    def description(self):
        return self.__description

    @description.setter
    def description(self, description):
        self.__description = description

    @property
    def starting_time(self):
        start_time,end_time=self.__time.split('-')
        return start_time.strip()

    @property
    def ending_time(self):
        start_time, end_time = self.__time.split('-')
        return end_time.strip()

    def __str__(self):
        return "Id: " + str(self.__id) + ", People's ids: " + str(
            self.__list_of_peoples_id) + ", Date: " + self.__date + ", Time: " + self.__time + ", Description: " + self.__description

    def __repr__(self):
        return str(self)
