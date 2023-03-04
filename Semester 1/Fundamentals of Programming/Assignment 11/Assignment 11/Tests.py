import unittest
from Repository import RepositoryBoard
from Service import ServiceBoard, ServiceException
import numpy as np


class Tests(unittest.TestCase):

    def setUp(self) -> None:
        self.board_repository = RepositoryBoard()

        self.service_board = ServiceBoard(self.board_repository)

    def test_flip_board__valid_board__flips_board_correctly(self):
        board = np.zeros((6, 7))
        board[3][2] = 1
        self.service_board.drop_piece(3,2,1)
        self.assertEqual(str(self.service_board.flip_board()), str(np.flip(board, 0)))
        self.service_board.drop_piece(3, 2, 0)

    def test_get_board__valid_board__gets_board_correctly(self):
        board = np.zeros((6, 7))
        board[3][2]=1
        self.service_board.drop_piece(3, 2, 1)
        self.assertEqual(str(self.service_board.get_board()()), str(board))
        self.service_board.drop_piece(3, 2, 0)

    def test_drop_piece__valid_move__drops_piece_on_correct_position(self):
        self.service_board.drop_piece(0, 0, 1)
        self.assertEqual(self.board_repository.get_board()[0][0], 1)

    def test_valid_location__valid_location__no_exception_raised(self):
        self.assertEqual(self.service_board.is_valid_location(3), True)

    def test_valid_location__invalid_location__exception_thrown(self):
        self.assertRaises(ServiceException, self.service_board.is_valid_location, 7)

    def test_get_next_open_row__valid_input__gets_correct_row(self):
        self.assertEqual(self.service_board.get_next_open_row(3), 0)

    def test_ai_move__ai_one_move_from_victory__ai_wins(self):
        for column in range(7):
            for row in range(6):
                self.service_board.drop_piece(row, column, 0)
        self.service_board.drop_piece(0, 0, 2)
        self.service_board.drop_piece(0, 1, 2)
        self.service_board.drop_piece(0, 2, 2)
        self.service_board.ai_move(2)
        self.assertEqual(self.service_board.winning_move(2), True)

    def test_ai_move__player_one_move_from_victory__ai_blocks_winning_move(self):
        for column in range(7):
            for row in range(6):
                self.service_board.drop_piece(row, column, 0)
        self.service_board.drop_piece(0, 0, 1)
        self.service_board.drop_piece(0, 1, 1)
        self.service_board.drop_piece(0, 2, 1)
        self.service_board.drop_piece(0, 3, 0)
        self.service_board.ai_move(2)
        self.assertEqual(self.board_repository.get_board()[0][3], 2)

    def test_ai_move__no_win_to_be_had_or_blocked__ai_moves_on_random_location(self):
        for column in range(7):
            for row in range(6):
                self.service_board.drop_piece(row, column, 0)
        self.service_board.ai_move(2)
        ai_moves_randomly = False
        for column in range(7):
            if self.board_repository.get_board()[0][column] == 2:
                ai_moves_randomly = True
        self.assertEqual(ai_moves_randomly, True)

    def test_ai_move__no_win_to_be_had_or_blocked__ai_moves_on_first_valid_location(self):
        self.service_board.drop_piece(5, 0, 1)
        self.service_board.drop_piece(5, 1, 2)
        self.service_board.drop_piece(5, 2, 1)
        self.service_board.drop_piece(5, 4, 1)
        self.service_board.drop_piece(5, 5, 2)
        self.service_board.drop_piece(5, 6, 1)
        self.service_board.ai_move(2)
        self.assertEqual(self.board_repository.get_board()[0][3], 2)

    def test_winning_move__win_on_main_diagonal__is_win(self):
        self.service_board.drop_piece(0, 0, 3)
        self.service_board.drop_piece(1, 1, 3)
        self.service_board.drop_piece(2, 2, 3)
        self.service_board.drop_piece(3, 3, 3)
        self.assertEqual(self.service_board.winning_move(3), True)

    def test_winning_move__win_on_vertical__is_win(self):
        self.service_board.drop_piece(0, 0, 4)
        self.service_board.drop_piece(1, 0, 4)
        self.service_board.drop_piece(2, 0, 4)
        self.service_board.drop_piece(3, 0, 4)
        self.assertEqual(self.service_board.winning_move(4), True)

    def test_winning_move__win_on_secondary_diagonal__is_win(self):
        self.service_board.drop_piece(0, 3, 5)
        self.service_board.drop_piece(1, 2, 5)
        self.service_board.drop_piece(2, 1, 5)
        self.service_board.drop_piece(3, 0, 5)
        self.assertEqual(self.service_board.winning_move(5), True)

    def test_winning_move__win_on_horizontal__is_win(self):
        self.service_board.drop_piece(0, 0, 6)
        self.service_board.drop_piece(0, 1, 6)
        self.service_board.drop_piece(0, 2, 6)
        self.service_board.drop_piece(0, 3, 6)
        self.assertEqual(self.service_board.winning_move(6), True)

    def tearDown(self) -> None:
        pass
