from Question import Question

class Repository:
    def __init__(self,file):
        self.__question_list=list()
        self.__question_file=file
        self.__quiz_question_list=list()
        self._load_file(self.__question_file,self.__question_list)

    @property
    def question_list(self):
        return self.__question_list

    def _load_file(self,filename,question_list):
        question_list.clear()
        file=open(filename,"rt")
        for line in file.readlines():
            id,text,answer1,answer2,answer3,correct_answer,difficulty=line.split(maxsplit=6,sep=';')
            question_list.append(Question(int(id),text,answer1,answer2,answer3,correct_answer,difficulty.strip()))
        file.close()

    def _save_file(self,filename,question_list):
        file=open(filename,"wt")
        for question in question_list:
            file.write(str(question.q_id)+';'+question.text+';'+question.answer1+';'+question.answer2+';'+question.answer3+';'+question.correct_answer+';'+question.difficulty+'\n')
        file.close()

    def add_question(self,question):
        self.__question_list.append(question)
        self._save_file(self.__question_file,self.__question_list)

    def remove_question(self,index):
        self.__question_list.pop(index)
        self._save_file(self.__question_file,self.__question_list)

    def create_quiz(self,filename,question_list):
        self._save_file(filename,question_list)

    def load_quiz(self,quiz):
        self._load_file(quiz,self.__quiz_question_list)
        return self.__quiz_question_list



