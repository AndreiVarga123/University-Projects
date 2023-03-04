class Book:
    def __init__(self, isbn, author, title):
        self.__isbn = isbn
        self.__author = author
        self.__title = title

    def get_isbn(self):
        return self.__isbn

    def get_author(self):
        return self.__author

    def get_title(self):
        return self.__title

    def get_first_word_of_title(self):
        first_word_of_title, rest_of_title = self.get_title().split(maxsplit=1)
        return first_word_of_title.strip()

    def is_equal(self, book):
        if not isinstance(book, Book):
            raise TypeError("Parameter is not a book")
        return self.__isbn == book.__isbn and self.__title == book.__title and self.__author == book.__author
