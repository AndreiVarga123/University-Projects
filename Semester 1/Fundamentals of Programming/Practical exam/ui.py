from controller import ControllerException

class UI:
    def __init__(self,service):
        self._service=service

    def start_quiz(self,questions):
        points=0
        for question in questions:
            print(str(question.q_id)+'.'+question.text+'?\n'+question.answer1+'\n'+question.answer2+'\n'+question.answer3+'\n')
            option=input("Choose an answer:")
            if option.lower().strip()==question.correct_answer:
                if question.difficulty=='easy':
                    points+=1
                elif question.difficulty=='medium':
                    points+=2
                elif question.difficulty=='hard':
                    points+=3
        print("Your score is:"+str(points))

    def print_menu(self):
        print("1.Add new question")
        print("2.Create new quiz")
        print("3.Take created quiz")
        print("4.Exit")


    def start(self):
        while True:
            try:
                self.print_menu()
                option=input("Option: ")
                if option=='1':
                    question=input("Question:")
                    self._service.add_question(question)
                elif option =='2':
                    quiz=input("Quiz: ")
                    self._service.create_quiz(quiz)
                elif option == '3':
                    quiz = input("Quiz: ")
                    questions=self._service.load_quiz(quiz)
                    self.start_quiz(questions)
                elif option =='4':
                    return
            except (ValueError,ControllerException) as ve:
                print(ve)