class Person:
    def __init__(self, id, name, phone_number):
        self.__id = id
        self.__name = name
        self.__phone_number = phone_number
        self.__unavailable_datetime = list()

    @property
    def id(self):
        return self.__id

    @id.setter
    def id(self, id):
        self.__id = id

    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @property
    def phone_number(self):
        return self.__phone_number

    @phone_number.setter
    def phone_number(self, phone_number):
        self.__phone_number = phone_number

    @property
    def unavailable_datetime_list(self):
        return self.__unavailable_datetime

    def add_unavailable_datetime(self, unavailable_date, unavailable_time):
        self.__unavailable_datetime.append([unavailable_date, unavailable_time])

    def remove_unavailable_datetime(self, unavailable_date, unavailable_time):
        self.__unavailable_datetime.remove([unavailable_date, unavailable_time])

    def __str__(self):
        return "Id: " + str(self.__id) + ", Name: " + self.__name + ", Phone number: " + self.__phone_number

    def __repr__(self):
        return str(self)
