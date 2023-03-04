class UndoRedoService:

    def __init__(self):
        self.__history = []
        self.__index = -1

    def record(self, operation):
        self.__history.append(operation)
        self.__history=[operation for operation in self.__history if self.__history.index(operation) not in range(self.__index+1,len(self.__history)-1)]
        self.__index = len(self.__history) - 1

    def undo(self):
        if self.__index == - 1:
            print("Can't undo anymore")
        else:
            self.__history[self.__index].undo()
            self.__index -= 1
            print("Successful undo")

    def redo(self):
        if self.__index == len(self.__history) - 1:
            print("Can't redo anymore")
        else:
            self.__index += 1
            self.__history[self.__index].redo()
            print("Successful redo")


class Function:

    def __init__(self, function_name, *function_params):
        self.__function_name = function_name
        self.__function_params = function_params

    def call(self):
        self.__function_name(*self.__function_params)


class Operation:

    def __init__(self, undo_function, redo_function):
        self.__undo_function = undo_function
        self.__redo_function = redo_function

    def undo(self):
        self.__undo_function.call()

    def redo(self):
        self.__redo_function.call()


class CascadedOperation:
    def __init__(self):
        self.__operations = []

    def add(self, operation):
        self.__operations.append(operation)

    def undo(self):
        for operation in self.__operations:
            operation.undo()

    def redo(self):
        for operation in self.__operations:
            operation.redo()
