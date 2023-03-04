from Repository import RepositoryBoard
from Service import ServiceBoard
from Ui import UI

repository_board = RepositoryBoard()

service_board = ServiceBoard(repository_board)

ui = UI(service_board)

ui.start()
