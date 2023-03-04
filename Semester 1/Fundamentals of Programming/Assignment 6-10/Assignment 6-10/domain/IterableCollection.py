class IterableCollection:
    class Iterator:
        def __init__(self, data):
            self._data = data
            self._position = 0

        def __next__(self):
            if self._position >= len(self._data):
                raise StopIteration()
            self._position += 1

            return self._data._iterable_data[self._position - 1]

    def __init__(self):
        self._iterable_data = []

    def __getitem__(self, item):
        return self._iterable_data[item]

    def __len__(self):
        return len(self._iterable_data)

    def __iter__(self):
        return self.Iterator(self)

    def __setitem__(self, key, value):
        self._iterable_data[key] = value

    def __delitem__(self, key):
        del self._iterable_data[key]

    def append(self, element):
        self._iterable_data.append(element)

    def remove(self, element):
        self._iterable_data.remove(element)

    @staticmethod
    def sort(list, method=None, reverse=False):
        index = 1

        if method is None:
            def value(elem):
                return elem

            method = value

        while index < len(list):
            if index == 0:
                index = index + 1
            if (method(list[index]) >= method(list[index - 1]) and reverse is False) or (method(list[index]) <= method(list[index - 1]) and reverse):
                index = index + 1
            else:
                list[index], list[index - 1] = list[index - 1], list[index]
                index = index - 1

        return list

    @staticmethod
    def filter(list, method):
        return [element for element in list if method(element)]
