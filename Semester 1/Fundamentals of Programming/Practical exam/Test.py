import unittest
from Question import Question
from repository import Repository
from controller import Controller,ControllerException
import os

class Test(unittest.TestCase):
    def setUp(self) -> None:
        self._repository=Repository("test.txt")
        self._service=Controller(self._repository)

    def test_add(self):
        question=Question(11,'Test','bad1','bad2','good','good','easy')
        self._service.add_question("11;Test;bad1;bad2;good;good;easy")
        self.assertEqual(question.q_id,self._repository.question_list[-1].q_id)
        self.assertEqual(question.text,self._repository.question_list[-1].text)
        self.assertEqual(question.answer1,self._repository.question_list[-1].answer1)
        self.assertEqual(question.answer2,self._repository.question_list[-1].answer2)
        self.assertEqual(question.answer3,self._repository.question_list[-1].answer3)
        self.assertEqual(question.correct_answer,self._repository.question_list[-1].correct_answer)
        self.assertEqual(question.difficulty,self._repository.question_list[-1].difficulty)
        self._repository.remove_question(-1)

    def test_create_and_load_quiz(self):
        self._service.create_quiz("create easy 5 easyquiz.txt")
        self.assertNotEqual(os.stat("easyquiz.txt"),0)
        questions=self._service.load_quiz("start easyquiz.txt")
        self.assertNotEqual(len(questions),0)
        os.remove("easyquiz.txt")
        try:
            self._service.create_quiz("create easy 10 easyquiz.txt")
        except ControllerException:
            pass
        self._service.create_quiz("create easy 2 easyquiz.txt")
        os.remove("easyquiz.txt")
