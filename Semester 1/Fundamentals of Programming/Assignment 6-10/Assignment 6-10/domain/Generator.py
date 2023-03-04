import random
from datetime import time
from src.domain.Activity import Activity
from src.domain.Person import Person
from src.domain.IterableCollection import IterableCollection


class Generator:
    def generate_person_list(self, n=20):
        FIRST_NAME = ['Andrei ', 'Marius ', 'Daria ', 'Cosmin ', 'Alexandra ']
        SURNAME = ['Pop', 'Stan', 'Deac', 'Varga', 'Georgescu']
        person_list = IterableCollection()
        id = 100
        for i in range(n):
            person = Person(id, FIRST_NAME[random.randint(0, 4)] + SURNAME[random.randint(0, 4)],
                            '0' + str(random.randint(700000000, 799999999)))
            person_list.append(person)
            id = id + 1

        return person_list

    def generate_activity_list(self, people_list, n=20):
        DATE = ["2021-12-18", "2021-12-20", "2021-12-22", "2021-12-24", "2021-12-26"]
        TIME = [time(8), time(10), time(12), time(14)]
        DESCRIPTION = ['Swimming', 'Running', 'Hiking', 'Dancing', 'Reading']
        id = 100
        activity_list = IterableCollection()

        while id <= n + 99:
            description = random.choice(DESCRIPTION)
            DESCRIPTION.remove(description)

            date_ = random.choice(DATE)
            DATE.remove(date_)

            for i in range(len(TIME)):
                starting_time = TIME[i]
                ending_time = time(starting_time.hour + 1, starting_time.minute + random.choice([0, 30, 50]))
                time_interval = str(starting_time).removesuffix(":00") + " - " + str(ending_time).removesuffix(":00")
                number_of_people_that_do_activity = random.randint(1, 10)
                list_of_peoples_id = list()
                for j in range(number_of_people_that_do_activity):
                    id_of_person = random.randint(100, 119)
                    if id_of_person not in list_of_peoples_id:
                        list_of_peoples_id.append(id_of_person)
                        person = people_list[id_of_person - 100]
                        person.add_unavailable_datetime(date_, time_interval)

                activity = Activity(id, list_of_peoples_id, date_, time_interval, description)
                activity_list.append(activity)
                id = id + 1

        return activity_list
