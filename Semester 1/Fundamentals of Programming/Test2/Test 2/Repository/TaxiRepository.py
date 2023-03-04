class TaxiRepository():
    def __init__(self):
        self.__list_of_taxies=list()

    @property
    def list_of_taxies(self):
        return self.__list_of_taxies

    def add(self,taxi):
        self.__list_of_taxies.append(taxi)