import unittest
from src.services.ActivityValidator import ActivityValidator, ActivityException
from src.services.PersonValidator import PersonValidator, PersonException
from src.repository.ActivityRepository import ActivityRepository
from src.repository.PersonRepository import PersonRepository
from src.domain.Generator import Generator

generator = Generator()
list_of_people = generator.generate_person_list()
list_of_activities = generator.generate_activity_list(list_of_people)

person_repository = PersonRepository(list_of_people)
activity_repository = ActivityRepository(list_of_activities)


class TestPersonValidator(unittest.TestCase):

    @classmethod
    def setUpClass(cls) -> None:
        cls.__person_validator = PersonValidator(person_repository)
        cls.__activity_validator = ActivityValidator(activity_repository, person_repository)

    def test_validate_phone_number__input_with_length_less_than_10__adds_correct_exception_message(self):
        self.__person_validator.validate_phone_number("123")
        self.assertEqual(self.__person_validator.messages[-1], "Phone number too short")

    def test_validate_phone_number__input_without_07_prefix__adds_correct_exception_message(self):
        self.__person_validator.validate_phone_number("0812345678")
        self.assertEqual(self.__person_validator.messages[-1], "Phone number has an invalid prefix")

    def test_validate_phone_number__input_doesnt_contain_only_digits__adds_correct_exception_message(self):
        self.__person_validator.validate_phone_number('07cdefghij')
        self.assertEqual(self.__person_validator.messages[-1], "Phone should only contain digits")

    def test_validate_person_id__input_not_number__adds_correct_exception_message(self):
        self.__person_validator.validate_id('abc')
        self.assertEqual(self.__person_validator.messages[-1], "Id abc not a number")

    def test_validate_person_id__input_not_in_range__adds_correct_exception_message(self):
        self.__person_validator.validate_id(123)
        self.assertEqual(self.__person_validator.messages[-1], "Id 123 not in person list_of_activities")

    def test_raise_exception_if_needed__some_exception_message__raises_PersonException(self):
        self.__person_validator.validate_phone_number("123")
        self.assertRaises(PersonException, lambda: self.__person_validator.raise_exception_if_needed())

    def test_validate_activity_id__input_not_number__adds_correct_exception_message(self):
        self.__activity_validator.validate_id('abc')
        self.assertEqual(self.__activity_validator.messages[-1], "Id abc not a number")

    def test_validate_activity_id__input_not_in_range__adds_correct_exception_message(self):
        self.__activity_validator.validate_id(123)
        self.assertEqual(self.__activity_validator.messages[-1], "Id 123 not in activity list_of_activities")

    def test_validate_list_of_peoples_id_is_list_with_valid_ids__input_has_invalid_ids__adds_correct_exception_message(
            self):
        self.__activity_validator.validate_list_of_peoples_id_is_list_with_valid_ids('abc,110,111')
        self.assertEqual(self.__activity_validator.messages[-1], "Id abc not a number")

    def test_validate_list_of_peoples_id_is_list_with_valid_ids__not_all_ids_are_unique__adds_correct_exception_message(
            self):
        self.__activity_validator.validate_list_of_peoples_id_is_list_with_valid_ids('110,110,111')
        self.assertEqual(self.__activity_validator.messages[-1], "Can't have same id twice")

    def test_validate_list_of_peoples_id_is_list_with_valid_ids__input_has_invalid_format__adds_correct_exception_message(
            self):
        self.__activity_validator.validate_list_of_peoples_id_is_list_with_valid_ids('110 111 112')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid list of people's ids format")

    def test_validate_list_of_people_no_time_overlap__person_with_certain_id_unavailable_at_time_of_activity__adds_correct_exception_message(
            self):
        person_to_test = person_repository[0]
        person_to_test.add_unavailable_datetime('2021-11-30', '10:00-12:00')
        self.__activity_validator.validate_list_of_people_no_time_overlap([100], '2021-11-30', '10:00-13:00')
        self.assertEqual(self.__activity_validator.messages[-1],
                         person_to_test.name + " (id=100) can't do activity at " + str(
                             ['2021-11-30', '10:00-13:00']) + " because it overlaps with another activity: " + str(
                             ['2021-11-30', '10:00-12:00']))

    def test_validate_date__invalid_date_format__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('2021/11/20')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid date format")

    def test_validate_date__input_doesnt_contain_3_elements__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('2021-11')
        self.assertEqual(self.__activity_validator.messages[-1], "Date has to contain exactly 3 elements")

    def test_validate_date__certain_element_isnt_number__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('yyyy-11-20')
        self.assertEqual(self.__activity_validator.messages[-1], "Date should only contain numbers")

    def test_validate_date__invalid_month__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('2021-23-11')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid month")

    def test_validate_date__invalid_day__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('2021-11-33')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid day")

    def test_validate_date__past_date__adds_correct_exception_message(self):
        self.__activity_validator.validate_date('2000-11-15')
        self.assertEqual(self.__activity_validator.messages[-1], "Can't add a past date")

    def test_validate_time__invalid_time_interval_format__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00 to 12:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid time interval format")

    def test_validate_time__input_doesnt_contain_to_elements__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00-12:00-14:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Interval should contain 2 times")

    def test_validate_time__invalid_time_format__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10.00-20.00')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid time format")

    def test_validate_time__starting_time_doesnt_have_2_elements__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00:00-12:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Starting time should only contain hour and minute")

    def test_validate_time__ending_time_doesnt_have_2_elements__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00-12:00:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Ending time should only contain hour and minute")

    def test_validate_time__starting_or_ending_hour_not_number__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('aa:00-bb:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Starting or ending hour is not a number")

    def test_validate_time__starting_or_ending_minute_not_number__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:aa-12:bb')
        self.assertEqual(self.__activity_validator.messages[-1], "Starting or ending minute is not a number")

    def test_validate_time__invalid_starting_hour__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('25:00-12:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid starting hour")

    def test_validate_time_invalid_ending_hour__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('12:00-25:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid ending hour")

    def test_validate_time__invalid_starting_minute__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:60-12:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid starting minute")

    def test_validate_time__invalid_ending_minute__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00-11:60')
        self.assertEqual(self.__activity_validator.messages[-1], "Invalid ending minute")

    def test_validate_time__starting_time_after_ending_time__adds_correct_exception_message(self):
        self.__activity_validator.validate_time('10:00-9:00')
        self.assertEqual(self.__activity_validator.messages[-1], "Starting time has to be before ending time")

    def test_raise_exception_if_needed__some_exception_message__raises_ActivityException(self):
        self.__activity_validator.validate_time('10:00-9:00')
        self.assertRaises(ActivityException, lambda: self.__activity_validator.raise_exception_if_needed())
