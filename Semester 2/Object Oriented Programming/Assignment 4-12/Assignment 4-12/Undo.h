//
// Created by varga on 28-May-22.
//

#pragma once
#include "Movie.h"
#include "Repo.h"

class Undo{
public:
    virtual void undo(){}
    ~Undo()=default;
};

class UndoAdd: public Undo{
private:
    Repo& r;
    std::string m_title;
public:
    UndoAdd(std::string _m_title,Repo& _r);
    void undo() override;
};

class UndoDelete: public Undo{
private:
    Repo& r;
    Movie m;
public:
    UndoDelete(Movie _m,Repo& _r);
    void undo() override;
};

class UndoUpdate: public Undo{
private:
    Repo& r;
    std::string m_title;
    Movie m;
public:
    UndoUpdate(std::string _m_title, Movie _m, Repo& _r);
    void undo() override;
};