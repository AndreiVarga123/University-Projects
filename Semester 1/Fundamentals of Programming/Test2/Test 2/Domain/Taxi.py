class Location:
    def __init__(self,x,y):
        self.__x=x
        self.__y=y

    @property
    def x(self):
        return self.__x

    @property
    def y(self):
        return self.__y

    @x.setter
    def x(self,x):
        self.__x=x

    @y.setter
    def y(self, y):
        self.__y = y

    def __str__(self):
        return "("+str(self.__x)+","+str(self.__y)+")"

    def __repr__(self):
        return str(self)

class Taxi:
    def __init__(self, id, location, fare):
        self.__id=id
        self.__location=location
        self.__fare=fare

    @property
    def id(self):
        return self.__id

    @property
    def location(self):
        return self.__location

    @property
    def fare(self):
        return self.__fare

    @id.setter
    def id(self, id):
        self.__id = id

    @location.setter
    def location(self, location):
        self.__location = location

    @fare.setter
    def fare(self, fare):
        self.__fare = fare

    def __str__(self):
        return "Id: " + str(self.__id) + " Location: "+str(self.__location)+ " Fare: "+str(self.__fare)

    def __repr__(self):
        return str(self)