from src.domain.Person import Person
import os
import pickle


class PersonRepository:
    def __init__(self, list_of_people):
        self.__list_of_people = list_of_people

    def __len__(self):
        return len(self.__list_of_people)

    def __getitem__(self, item):
        return self.__list_of_people[item]

    @property
    def list_of_people(self):
        return self.__list_of_people

    def add(self, person):
        """
        Adds person to existing list of people
        :param person: The person to add
        :return: None, but adds a new person to end of list
        """
        self.__list_of_people.append(person)

    def remove(self, id):
        for person in self.__list_of_people:
            if person.id == id:
                self.__list_of_people.remove(person)
                break

    def update(self, id, name_or_phone_number, what_to_change):
        for person in self.__list_of_people:
            if person.id == id:
                if what_to_change == 'name':
                    person.name = name_or_phone_number
                else:
                    person.phone_number = name_or_phone_number
                break

    def add_datetime_interval(self, id, datetime_interval):
        date = datetime_interval[0]
        time = datetime_interval[1]
        for person in self.__list_of_people:
            if person.id == id:
                person.add_unavailable_datetime(date, time)
                break

    def remove_datetime_interval(self, id, datetime_interval):
        date = datetime_interval[0]
        time = datetime_interval[1]

        for person in self.__list_of_people:
            if person.id == id:
                person.remove_unavailable_datetime(date, time)
                break


class PersonTextFileRepository(PersonRepository):

    def __init__(self, initial_person_list):
        self.__file_name = "person.txt"
        if os.stat(self.__file_name).st_size != 0:
            self.__load_file(initial_person_list)
        super().__init__(list_of_people=initial_person_list)
        if os.stat(self.__file_name).st_size == 0:
            self.__save_file()

    def __load_file(self, initial_person_list):
        file = open(self.__file_name, "rt")
        for line in file.readlines():
            id, name, phone_number, unavailable_datetime_list = line.split(maxsplit=3, sep=',')
            person = Person(int(id), name, phone_number)
            if unavailable_datetime_list.find(']]') != -1:
                unavailable_datetime_list = unavailable_datetime_list.strip().removeprefix('[').removesuffix(']]')
                unavailable_datetime_list = unavailable_datetime_list.split(sep='], ')
                for unavailable_datetime in unavailable_datetime_list:
                    unavailable_datetime = unavailable_datetime.removeprefix('[').split(sep=',')
                    unavailable_date = unavailable_datetime[0].strip().removeprefix("'").removesuffix("'")
                    unavailable_time = unavailable_datetime[1].strip().removeprefix("'").removesuffix("'")
                    person.add_unavailable_datetime(unavailable_date, unavailable_time)
            initial_person_list.append(person)
        file.close()

    def __save_file(self):
        file = open(self.__file_name, "wt")
        for person in self.list_of_people:
            file.write(str(person.id) + ',' + person.name + ',' + person.phone_number + ',' + str(
                person.unavailable_datetime_list).strip() + '\n')
        file.close()

    def add(self, person):
        super(PersonTextFileRepository, self).add(person)
        self.__save_file()

    def remove(self, id):
        super(PersonTextFileRepository, self).remove(id)
        self.__save_file()

    def update(self, id, property, what_to_change):
        super(PersonTextFileRepository, self).update(id, property, what_to_change)
        self.__save_file()

    def add_datetime_interval(self, id, datetime_interval):
        super(PersonTextFileRepository, self).add_datetime_interval(id, datetime_interval)
        self.__save_file()

    def remove_datetime_interval(self, id, datetime_interval):
        super(PersonTextFileRepository, self).remove_datetime_interval(id, datetime_interval)
        self.__save_file()


class PersonBinFileRepository(PersonRepository):
    def __init__(self, initial_people_list):
        self.__file_name = "person.bin"
        if os.stat(self.__file_name).st_size != 0:
            super().__init__(list_of_people=self.__load_file())
        else:
            super().__init__(list_of_people=initial_people_list)
        if os.stat(self.__file_name).st_size == 0:
            self.__save_file()

    def __load_file(self):
        file = open(self.__file_name, "rb")
        initial_people_list = pickle.load(file)
        file.close()
        return initial_people_list

    def __save_file(self):
        file = open(self.__file_name, "wb")
        pickle.dump(self.list_of_people, file)
        file.close()

    def add(self, person):
        super(PersonBinFileRepository, self).add(person)
        self.__save_file()

    def remove(self, id):
        super(PersonBinFileRepository, self).remove(id)
        self.__save_file()

    def update(self, id, property, what_to_change):
        super(PersonBinFileRepository, self).update(id, property, what_to_change)
        self.__save_file()

    def add_datetime_interval(self, id, datetime_interval):
        super(PersonBinFileRepository, self).add_datetime_interval(id, datetime_interval)
        self.__save_file()

    def remove_datetime_interval(self, id, datetime_interval):
        super(PersonBinFileRepository, self).remove_datetime_interval(id, datetime_interval)
        self.__save_file()
