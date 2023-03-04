import configparser

from src.ui.Ui import UI
from src.domain.Generator import Generator
from src.repository.PersonRepository import PersonRepository, PersonTextFileRepository, PersonBinFileRepository
from src.repository.ActivityRepository import ActivityRepository, ActivityTextFileRepository, ActivityBinFileRepository
from src.domain.IterableCollection import IterableCollection
from src.services.PersonService import PersonService
from src.services.ActivityService import ActivityService
from src.services.PersonValidator import PersonValidator
from src.services.ActivityValidator import ActivityValidator
from src.services.UndoRedoService import UndoRedoService
import os

settings = configparser.ConfigParser()
settings.read("settings.properties")

type_of_repository = settings.get("Settings", "type_of_repository")

file_for_activity_repository = settings.get("Settings", "file_for_activity_repository")
file_for_person_repository = settings.get("Settings", "file_for_person_repository")

activity_repository_to_use_string = settings.get("Settings", "activity_repository_to_use")
person_repository_to_use_string = settings.get("Settings", "person_repository_to_use")

activity_repository_to_use = globals()[activity_repository_to_use_string]
person_repository_to_use = globals()[person_repository_to_use_string]

generator = Generator()

if type_of_repository == "inmemmory":
    list_of_people = generator.generate_person_list()
elif os.stat(file_for_person_repository).st_size == 0:
    list_of_people = generator.generate_person_list()
else:
    list_of_people = IterableCollection()

if type_of_repository == "inmemmory":
    list_of_activities = generator.generate_person_list()
elif os.stat(file_for_activity_repository).st_size == 0 or type_of_repository == "inmemmory":
    list_of_activities = generator.generate_activity_list(list_of_people)
else:
    list_of_activities = IterableCollection()

person_repository = person_repository_to_use(list_of_people)
activity_repository = activity_repository_to_use(list_of_activities)

person_validator = PersonValidator(person_repository)
activity_validator = ActivityValidator(activity_repository, person_repository)

person_service = PersonService(person_repository, person_validator)
activity_service = ActivityService(activity_repository, activity_validator)

undo_service = UndoRedoService()

ui = UI(person_service, activity_service, undo_service)
ui.start()
