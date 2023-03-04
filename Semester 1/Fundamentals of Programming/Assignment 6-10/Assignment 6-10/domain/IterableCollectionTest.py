import unittest
import random
from src.domain.IterableCollection import IterableCollection


class TestIterableCollection(unittest.TestCase):
    def setUp(self) -> None:
        self.__iterable_collection = IterableCollection()
        self.__iterator = IterableCollection.Iterator(self.__iterable_collection)

    def test_append__valid_list__adds_correct_element_to_end_of_list(self):
        self.__iterable_collection.append(1)
        self.assertEqual(self.__iterable_collection[-1], 1)
        self.__iterable_collection.remove(1)

    def test_remove__valid_list__removes_correct_element(self):
        self.__iterable_collection.append(1)
        self.__iterable_collection.append(2)
        self.__iterable_collection.remove(1)
        self.assertNotIn(1, self.__iterable_collection)

    def test_iterator_next__valid_list__goes_to_next_element(self):
        self.__iterable_collection.append(1)
        self.__iterable_collection.append(2)
        self.__iterator.__next__()
        self.assertEqual(self.__iterator._position, 1)

        self.__iterable_collection.remove(1)
        self.__iterable_collection.remove(2)

    def test_iterator_next__valid_list__raises_StopIteration_when_list_is_over(self):
        self.__iterable_collection.append(1)
        self.__iterable_collection.append(2)
        self.__iterable_collection.append(3)
        self.__iterator.__next__()
        self.__iterator.__next__()
        self.__iterator.__next__()
        self.assertRaises(StopIteration, self.__iterator.__next__)

        self.__iterable_collection.remove(1)
        self.__iterable_collection.remove(2)
        self.__iterable_collection.remove(3)

    def test_setitem__valid_list__sets_correct_element_to_wanted_value(self):
        self.__iterable_collection.append(1)
        self.__iterable_collection[0] = 5
        self.assertEqual(self.__iterable_collection[0], 5)
        self.__iterable_collection.remove(5)

    def test_delitem__valid_list__deletes_correct_element(self):
        self.__iterable_collection.append(1)
        self.__iterable_collection.append(2)
        del self.__iterable_collection[0]
        self.assertNotIn(1, self.__iterable_collection)

    @staticmethod
    def keep_odd(a):
        return a % 2

    def test_sort__unordered_list__sorts_list_ascending(self):
        list_to_test = list(range(10))
        random.shuffle(list_to_test)
        ordered_list = IterableCollection.sort(list_to_test)
        self.assertEqual(ordered_list, list(range(10)))

    def test_sort__unordered_list__sorts_list_descending(self):
        list_to_test = list(range(10))
        random.shuffle(list_to_test)
        ordered_list = IterableCollection.sort(list_to_test,reverse=True)
        self.assertEqual(ordered_list, [9,8,7,6,5,4,3,2,1,0])

    def test_filter__unfiltered_list__filters_list_correctly(self):
        list_to_test = list(range(10))
        filtered_list = IterableCollection.filter(list_to_test, TestIterableCollection.keep_odd)
        self.assertEqual(filtered_list, [1, 3, 5, 7, 9])
