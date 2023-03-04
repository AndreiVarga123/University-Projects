from src.domain.Person import Person
from src.domain.IterableCollection import IterableCollection


class PersonService:
    def __init__(self, person_repository, person_validator):
        self.__repository = person_repository
        self.__validator = person_validator

    @property
    def list_of_people(self):
        return self.__repository.list_of_people

    def check_if_id_valid(self, id):
        self.__validator.validate_id(id)
        self.__validator.raise_exception_if_needed()

    def add(self, name, phone_number, id=None):
        """
        Create new person's id, validate person's properties,create person instance,and add to list_of_activities
        :param name:The person's name
        :param phone_number:The person's phone number
        :return:None, but adds person to list_of_activities if it is valid, otherwise throws PersonException
        """
        if id is None:
            new_person_id = self.__repository.list_of_people[-1].id + 1
            self.__validator.validate_phone_number(phone_number)
            self.__validator.raise_exception_if_needed()
        else:
            new_person_id = id
        person = Person(new_person_id, name, phone_number)
        self.__repository.add(person)

    def remove(self, id):
        self.__repository.remove(id)

    def update(self, id, name_or_phone_number, what_to_change):
        if what_to_change == 'phone number':
            self.__validator.validate_phone_number(name_or_phone_number)
        self.__validator.raise_exception_if_needed()
        self.__repository.update(id, name_or_phone_number, what_to_change)

    def add_unavailable_datetime_interval(self, list_of_people_id, datetime_interval):
        for id in list_of_people_id:
            self.__repository.add_datetime_interval(id, datetime_interval)

    def remove_unavailable_datetime_interval(self, list_of_people_id, datetime_interval):
        for id in list_of_people_id:
            self.__repository.remove_datetime_interval(id, datetime_interval)

    def search(self, user_input, what_to_search):
        list_with_found_people = IterableCollection()
        if what_to_search == 'name':
            for person in self.list_of_people:
                if person.name.lower().find(user_input.lower()) != -1:
                    list_with_found_people.append(person)
        else:
            for person in self.list_of_people:
                if person.phone_number.lower().find(user_input.lower()) != -1:
                    list_with_found_people.append(person)
        return list_with_found_people
