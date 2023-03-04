class UI:
    def __init__(self,taxi_service):
        self.__taxi_service=taxi_service

    def display_taxies(self,order_list):
        for taxi in order_list:
            print(taxi)

    def print_menu(self):
        print("1.Add Ride")
        print("2.Simulate rides")

    def start(self):
        number_of_taxis=input("Please enter the number of taxis to generate: ")
        self.__taxi_service.generate_initial_list(int(number_of_taxis))
        while True:
            self.print_menu()
            option=input("Option:")
            if option == "1":
                start_point=input("Start point:")
                end_point=input("End point:")
                self.__taxi_service.add_ride(start_point,end_point)
            elif option =="2":
                number_of_rides=input("Number of rides:")
                self.__taxi_service.simulate_ride(int(number_of_rides))
            self.display_taxies(self.__taxi_service.order())

