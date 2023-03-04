import unittest
from src.services.ActivityValidator import ActivityValidator
from src.services.PersonValidator import PersonValidator
from src.repository.ActivityRepository import ActivityRepository
from src.repository.PersonRepository import PersonRepository
from src.services.ActivityService import ActivityService
from src.services.PersonService import PersonService
from src.domain.Generator import Generator
from src.domain.Activity import Activity
from src.domain.Person import Person

generator = Generator()
list_of_people = generator.generate_person_list()
list_of_activities = generator.generate_activity_list(list_of_people)

person_repository = PersonRepository(list_of_people)
activity_repository = ActivityRepository(list_of_activities)

person_validator = PersonValidator(person_repository)
activity_validator = ActivityValidator(activity_repository, person_repository)

person_service = PersonService(person_repository, person_validator)
activity_service = ActivityService(activity_repository, activity_validator)


class ServiceValidator(unittest.TestCase):

    @classmethod
    def setUpClass(cls) -> None:
        cls.__activity_service = activity_service
        cls.__person_service = person_service

    def test_add_person__valid_person__adds_it_to_end_of_list(self):
        self.__person_service.add('Test Person', '0712345678')
        person_to_test = self.__person_service.list_of_people[-1]
        self.assertEqual(str(person_to_test), str(Person(120, 'Test Person', '0712345678')))

    def test_add_activity__valid_activity__adds_it_to_end_of_list(self):
        self.__activity_service.add('100,101,102', '2021-12-30', '11:00-12:00', 'Hiking')
        activity_to_test = self.__activity_service.list_of_activities[-1]
        self.assertEqual(str(activity_to_test),
                         str(Activity(120, [100, 101, 102], '2021-12-30', '11:00-12:00', 'Hiking')))

    def test_remove_person__valid_person__removes_it_from_list(self):
        initial_list_of_people = self.__person_service.list_of_people
        person_to_remove = initial_list_of_people[0]
        self.__person_service.remove(100)
        self.assertNotIn(person_to_remove, initial_list_of_people)

    def test_remove_activity__valid_activity__removes_it_from_list(self):
        initial_list_of_activities = self.__activity_service.list_of_activities
        activity_to_remove = initial_list_of_activities[0]
        self.__activity_service.remove(100)
        self.assertNotIn(activity_to_remove, initial_list_of_activities)

    def test_update_person_name__valid_person__updates_it_correctly(self):
        person_to_test = self.__person_service.list_of_people[0]
        self.__person_service.update(person_to_test.id, 'Updated Name', 'name')
        updated_person = self.__person_service.list_of_people[0]
        self.assertEqual(updated_person.name, 'Updated Name')

    def test_update_person_phone_number__valid_person__updates_it_correctly(self):
        person_to_test = self.__person_service.list_of_people[0]
        self.__person_service.update(person_to_test.id, '0712345678', 'phone number')
        updated_person = self.__person_service.list_of_people[0]
        self.assertEqual(updated_person.phone_number, '0712345678')

    def test_update_activity_list_of_peoples_id__valid_activity__updates_it_correctly(self):
        self.__activity_service.add('101,102', '2021-12-30', '13:00-14:00', 'Hiking')
        activity_to_test = self.__activity_service.list_of_activities[-1]
        self.__activity_service.update(activity_to_test.id, '101', 'list_of_peoples_id')
        updated_activity = self.__activity_service.list_of_activities[-1]
        self.assertEqual(updated_activity.list_of_peoples_id, [101])

    def test_update_activity_date__valid_activity__updates_it_correctly(self):
        self.__activity_service.add('101,102', '2021-12-30', '13:00-14:00', 'Hiking')
        activity_to_test = self.__activity_service.list_of_activities[-1]
        self.__activity_service.update(activity_to_test.id, '2021-12-29', 'date')
        updated_activity = self.__activity_service.list_of_activities[-1]
        self.assertEqual(updated_activity.date, '2021-12-29')

    def test_update_activity_time__valid_activity__updates_it_correctly(self):
        self.__activity_service.add('101,102', '2021-12-30', '13:00-14:00', 'Hiking')
        activity_to_test = self.__activity_service.list_of_activities[-1]
        self.__activity_service.update(activity_to_test.id, '11:00-12:00', 'time')
        updated_activity = self.__activity_service.list_of_activities[-1]
        self.assertEqual(updated_activity.time, '11:00-12:00')

    def test_update_activity_description__valid_activity__updates_it_correctly(self):
        self.__activity_service.add('101,102', '2021-12-30', '13:00-14:00', 'Hiking')
        activity_to_test = self.__activity_service.list_of_activities[-1]
        self.__activity_service.update(activity_to_test.id, 'Swimming', 'description')
        updated_activity = self.__activity_service.list_of_activities[-1]
        self.assertEqual(updated_activity.description, 'Swimming')
