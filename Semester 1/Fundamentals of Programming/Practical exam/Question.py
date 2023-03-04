class Question:
    def __init__(self,q_id,text,answer1,answer2,answer3,correct_answer,difficulty):
        self._q_id=q_id
        self._text=text
        self._answer1=answer1
        self._answer2=answer2
        self._answer3=answer3
        self._correct_answer=correct_answer
        self._difficulty=difficulty

    @property
    def q_id(self):
        return self._q_id

    @property
    def text(self):
        return self._text

    @property
    def answer1(self):
        return self._answer1

    @property
    def answer2(self):
        return self._answer2

    @property
    def answer3(self):
        return self._answer3

    @property
    def correct_answer(self):
        return self._correct_answer

    @property
    def difficulty(self):
        return self._difficulty

    @q_id.setter
    def q_id(self,q_id):
        self._q_id=q_id

    @answer1.setter
    def answer1(self, answer1):
        self._answer1 = answer1

    @text.setter
    def text(self, text):
        self._text = text

    @answer2.setter
    def answer2(self, answer2):
        self._answer2 = answer2

    @answer3.setter
    def answer3(self, answer3):
        self._answer3 = answer3

    @correct_answer.setter
    def correct_answer(self, correct_answer):
        self._correct_answer = correct_answer

    @difficulty.setter
    def difficulty(self, difficulty):
        self._difficulty = difficulty
