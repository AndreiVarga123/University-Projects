from src.book.book import Book
import copy


class Services:
    def __init__(self):
        self.__book_list = [Book("001", "Author 1", "Title 1"), Book("002", "Author 2", "Title 2"),
                            Book("003", "Author 3", "Special Title 3"), Book("004", "Author 4", "Title 4"),
                            Book("005", "Author 5", "Special Title 5"), Book("006", "Author 6", "Special Title 6"),
                            Book("007", "Author 7", "Title 7"), Book("008", "Author 8", "Title 8"),
                            Book("009", "Author 9", "Special Title 9"), Book("010", "Author 10", "Title 10")]

    def get_book_list(self):
        return self.__book_list

    def __check_if_isbn_is_unique(self, isbn):
        for book in self.__book_list:
            if book.get_isbn() == isbn:
                return False
        return True

    def add_book(self, isbn, author, title):
        """
        Adds a book to the list of books
        :param isbn: the id of the book (has to be unique)
        :param author: the author of the book
        :param title: the title of the book
        :return: modifies the book if the isbn is unique, raises an exception otherwise
        """
        if self.__check_if_isbn_is_unique(isbn):
            if isbn != "" and author != "" and title != "":
                self.__book_list.append(Book(isbn, author, title))
            else:
                raise ServiceException("Not all parameters are given")
        else:
            raise ServiceException("Non unique isbn")

    def delete_all_books_with_title_that_starts_with_given_word(self, given_word):
        self.__book_list = [book for
                            book in self.__book_list if book.get_first_word_of_title().lower() != given_word.lower()]

    def undo_command(self, instances_of_data):
        instances_of_data.pop(-1)
        self.__book_list = copy.deepcopy(instances_of_data[-1])


class ServiceException(Exception):
    pass


def test_add_command__some_value__adds_that_value_to_end():
    services = Services()
    services.add_book("011", "Author 11", "Title 11")
    value_that_was_added = services.get_book_list()[-1]
    assert value_that_was_added.is_equal(Book("011", "Author 11", "Title 11"))


def add_command_raises_an_exception(isbn, title, author):
    services = Services()
    try:
        services.add_book(isbn, title, author)
        return False
    except ServiceException:
        return True


def test_add_command__value_with_existing_isbn__raises_exception():
    assert add_command_raises_an_exception("001", "Author 11", "Title 11") is True


def test_add_command__isbn_unfilled__raises_exception():
    assert add_command_raises_an_exception("", "Author 11", "Title 11") is True


def test_add_command__author_unfilled__raises_exception():
    assert add_command_raises_an_exception("011", "", "Title 11") is True


def test_add_command__title_unfilled__raises_exception():
    assert add_command_raises_an_exception("011", "Author 11", "") is True


test_add_command__some_value__adds_that_value_to_end()
test_add_command__value_with_existing_isbn__raises_exception()
test_add_command__isbn_unfilled__raises_exception()
test_add_command__author_unfilled__raises_exception()
test_add_command__title_unfilled__raises_exception()
