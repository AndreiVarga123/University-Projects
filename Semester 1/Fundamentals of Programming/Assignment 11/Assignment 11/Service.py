import numpy as np
import random


class ServiceException(Exception):
    pass


class ServiceBoard:
    def __init__(self, repository_board):
        self.board = repository_board

    def get_board(self):
        return self.board.get_board

    def drop_piece(self, row, column, piece):
        self.board.get_board()[row][column] = piece

    def is_valid_location(self, column):
        if column < 0 or column > 6:
            raise ServiceException("Please make a valid input (row 0 to 6)")
        return self.board.get_board()[5][column] == 0

    def get_next_open_row(self, column):
        for row in range(6):
            if self.board.get_board()[row][column] == 0:
                return row

    def flip_board(self):
        return np.flip(self.board.get_board(), 0)

    def winning_move(self, piece):
        for column in range(7 - 3):
            for row in range(6):
                if self.board.get_board()[row][column] == piece and self.board.get_board()[row][column + 1] == piece and \
                        self.board.get_board()[row][column + 2] == piece and self.board.get_board()[row][
                    column + 3] == piece:
                    return True

        for column in range(7):
            for row in range(6 - 3):
                if self.board.get_board()[row][column] == piece and self.board.get_board()[row + 1][column] == piece and \
                        self.board.get_board()[row + 2][column] == piece and self.board.get_board()[row + 3][
                    column] == piece:
                    return True

        for column in range(7 - 3):
            for row in range(6 - 3):
                if self.board.get_board()[row][column] == piece and self.board.get_board()[row + 1][
                    column + 1] == piece and \
                        self.board.get_board()[row + 2][column + 2] == piece and \
                        self.board.get_board()[row + 3][column + 3] == piece:
                    return True

        for column in range(7 - 3):
            for row in range(3, 6):
                if self.board.get_board()[row][column] == piece and self.board.get_board()[row - 1][
                    column + 1] == piece and \
                        self.board.get_board()[row - 2][column + 2] == piece and \
                        self.board.get_board()[row - 3][column + 3] == piece:
                    return True

    def ai_move(self, ai_number):
        made_move = 0
        for piece in range(7):
            if self.is_valid_location(piece):
                row = self.get_next_open_row(piece)
                self.drop_piece(row, piece, ai_number)
                if self.winning_move(ai_number):
                    made_move = 1
                    break
                else:
                    self.drop_piece(row, piece, 0)

        if made_move == 0:
            for piece in range(7):
                if self.is_valid_location(piece):
                    row = self.get_next_open_row(piece)
                    self.drop_piece(row, piece, 1)
                    if self.winning_move(1):
                        made_move = 1
                        self.drop_piece(row, piece, ai_number)
                        break
                    else:
                        self.drop_piece(row, piece, 0)

        if made_move == 0:
            piece = random.randint(0, 6)
            if self.is_valid_location(piece):
                row = self.get_next_open_row(piece)
                self.drop_piece(row, piece, ai_number)
            else:
                for piece in range(7):
                    if self.is_valid_location(piece):
                        row = self.get_next_open_row(piece)
                        self.drop_piece(row, piece, ai_number)
                        break
