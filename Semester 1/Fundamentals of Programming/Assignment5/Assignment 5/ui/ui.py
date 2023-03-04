import copy

from src.services.services import Services, ServiceException


class Ui:
    def __init__(self):
        self.services = Services()

    def display_book(self, book):
        print("Book " + book.get_isbn() + ": " + book.get_title() + " by " + book.get_author())

    def display_all_books(self):
        print("")
        book_list = self.services.get_book_list()
        for book in book_list:
            self.display_book(book)

    def show_menu(self):
        print("\n1. Add a book")
        print("2. Display the list of books")
        print("3. Filter the list so that book titles starting with a given word are deleted from the list")
        print("4. Undo")
        print("5. Exit")

    def start(self):
        instances_of_data = [copy.deepcopy(self.services.get_book_list())]

        while True:
            self.show_menu()
            option = input("Choose option: ")

            if option == '1':
                isbn = input("\nisbn: ").strip()
                title = input("title: ").strip()
                author = input("author: ").strip()
                try:
                    self.services.add_book(isbn, author, title)
                    print("Number added successfully")
                    instances_of_data.append(copy.deepcopy(self.services.get_book_list()))
                except ServiceException as service_exception:
                    print(service_exception)
            elif option == '2':
                self.display_all_books()
            elif option == '3':
                starting_word = input("\nType starting word: ").strip()
                self.services.delete_all_books_with_title_that_starts_with_given_word(starting_word)
                print("List filtered")
                instances_of_data.append(copy.deepcopy(self.services.get_book_list()))
            elif option == '4':
                if len(instances_of_data) != 1:
                    self.services.undo_command(instances_of_data)
                    print("\nCommand undone successfully")
                else:
                    print("\nData at initial state")
            elif option == '5':
                return
            else:
                print("Invalid input")
