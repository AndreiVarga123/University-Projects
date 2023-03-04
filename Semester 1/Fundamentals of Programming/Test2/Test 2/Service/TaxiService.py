from Domain.Taxi import Location, Taxi
import random


class TaxiService:
    def __init__(self, taxi_repository):
        self.__taxi_repository = taxi_repository

    def distance(self, location1, location2):
        return abs(location1.x - location2.x) + abs(location1.y - location2.y)

    @property
    def list_of_taxies(self):
        return self.__taxi_repository.list_of_taxies

    def check_if_valid(self, new_taxi_location):
        for taxi in self.list_of_taxies:
            if abs(taxi.location.x - new_taxi_location.x) + abs(taxi.location.y - new_taxi_location.y) <= 5:
                return False
        return True

    def generate_initial_list(self, number_of_taxis):
        taxi_id = 1
        for i in range(number_of_taxis):
            is_valid_location = False
            while is_valid_location is False:
                taxi_location = Location(random.randint(0, 100), random.randint(0, 100))
                is_valid_location = self.check_if_valid(taxi_location)
            taxi = Taxi(taxi_id, taxi_location, 0)
            self.__taxi_repository.add(taxi)
            taxi_id += 1

    def str_to_location(self,location):
        location_str=location.replace('(','').replace(')','')
        location_x,location_y=location_str.split(",")
        new_location=Location(int(location_x),int(location_y))
        return  new_location

    def add_ride(self, start_point, end_point):
        start_point=self.str_to_location(start_point)
        end_point=self.str_to_location(end_point)
        closest_taxi = self.list_of_taxies[0]
        for taxi in self.list_of_taxies:
            if self.distance(taxi.location, start_point) < self.distance(closest_taxi.location, start_point):
                closest_taxi = taxi
        closest_taxi.location=end_point
        closest_taxi.fare += self.distance(start_point, end_point)

    def simulate_ride(self,number_of_rides):
        for ride in range(number_of_rides):
            start_location=Location(random.randint(0, 100),random.randint(0, 100))
            is_valid_location = False
            while is_valid_location is False:
                end_location = Location(random.randint(0, 100), random.randint(0, 100))
                is_valid_location = self.check_if_valid(end_location)
            closest_taxi = self.list_of_taxies[0]
            for taxi in self.list_of_taxies:
                if self.distance(taxi.location, start_location) < self.distance(closest_taxi.location, start_location):
                    closest_taxi = taxi
            closest_taxi.location = end_location
            closest_taxi.fare += self.distance(start_location, end_location)

    def __fare(self, taxi):
        taxi_fare = taxi.fare
        return taxi_fare

    def order(self):
        sorted_list=sorted(self.__taxi_repository.list_of_taxies, key=self.__fare,reverse=True)
        return sorted_list
