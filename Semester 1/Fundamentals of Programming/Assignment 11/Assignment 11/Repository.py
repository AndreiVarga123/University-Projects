import numpy as np


class RepositoryBoard:
    def __init__(self):
        self.board = np.zeros((6, 7))

    def get_board(self):
        return self.board
