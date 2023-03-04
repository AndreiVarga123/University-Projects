import random

from Question import Question
import copy

class Controller:
    def __init__(self,repository):
        self._repository=repository

    def add_question(self,question):
        """
        The functions takes a question in the add <id>;<text>;<choice_a>;<choice_b>;<choice_c>;<correct_choice>;<difficulty> format
        We split the string in the above mentioned format and we create a question instance which we add to the question list in the repo
        :param question: question as string in the shown format
        :return: nothing, but adds a new question to the question list
        """
        id,text,answer1,answer2,answer3,correct_answer,difficulty=question.split(maxsplit=6,sep=';')
        id=int(id)
        self._repository.add_question(Question(id,text,answer1,answer2,answer3,correct_answer,difficulty))

    def create_quiz(self,quiz):
        """
        The function creates a new quiz by splitting the given string into the quiz difficulty,number of questions and filename.
        Then we search the question list for all the questions of that difficulty and if there are not enough we check wheter
        the remaining number of questions that we have to find is larger than half the number of questions.If yes we will raise an exception
        otherwise we will randomly add the rest of the questions
        :param quiz: quiz to create as string in the format 'create <difficulty><number_of_questions><file>'
        :return: nothing, but create a new file with the mentioned requirements
        """
        quiz=quiz.removeprefix("create").strip()
        difficulty,number_of_questions,file=quiz.split(maxsplit=2,sep=' ')
        quiz_question=list()
        initial_number_of_questions=copy.deepcopy(int(number_of_questions))
        number_of_questions=int(number_of_questions)
        for question in self._repository.question_list:
            if question.difficulty==difficulty:
                quiz_question.append(question)
                number_of_questions-=1
                if number_of_questions==0:
                    break
        if number_of_questions>0:
            if number_of_questions>initial_number_of_questions/2:
                    raise ControllerException("Not enough questions of that difficulty")
            else:
                while number_of_questions!=0:
                    index=random.randint(0,len(self._repository.question_list))
                    if self._repository.question_list[index] not in quiz_question:
                        quiz_question.append(self._repository.question_list[index])
                        number_of_questions-=1

        self._repository.create_quiz(file,quiz_question)

    def load_quiz(self,quiz):
        """
        The function loads the questions that are in the given quiz in a list and returns it
        :param quiz: the quiz to start in the 'start <file>' format
        :return: the list of questions in that file
        """
        quiz=quiz.removeprefix("start").strip()
        question=self._repository.load_quiz(quiz)
        return question



class ControllerException(Exception):
    pass