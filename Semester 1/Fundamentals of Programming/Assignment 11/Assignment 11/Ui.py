from Service import ServiceException


class UI:
    def __init__(self, service_board):
        self.service_board = service_board

    def start(self):
        game_over = False
        turn = 0
        tie = 0
        while not game_over:
            try:
                if turn == 0:
                    column = int(input("Player  make selection: "))
                    if self.service_board.is_valid_location(column):
                        row = self.service_board.get_next_open_row(column)
                        self.service_board.drop_piece(row, column, 1)
                        tie += 1
                        if self.service_board.winning_move(1):
                            print("Player  wins!")
                            game_over = True
                    else:
                        print("Invalid location")
                        turn += 1

                else:
                    self.service_board.ai_move(2)
                    tie += 1
                    if self.service_board.winning_move(2):
                        print("Computer wins!")
                        game_over = True

                turn += 1
                turn = turn % 2
                self.print_board()
                print('\n')
                if tie == 42:
                    game_over = True
                    print("Tie")
            except(ServiceException, TypeError, ValueError) as ve:
                print(str(ve))

    def print_board(self):
        print(self.service_board.flip_board())
