from Question import Question
from repository import Repository
from controller import Controller
from ui import UI

repository=Repository("master.txt")
controller=Controller(repository)
ui=UI(controller)
ui.start()