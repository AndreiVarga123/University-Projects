//
// Created by varga on 28-May-22.
//

#pragma once
#include "Movie.h"
#include "Repo.h"

class Redo{
public:
    virtual void redo(){}
    ~Redo()=default;
};

class RedoAdd: public Redo{
private:
    Repo& r;
    Movie m;
public:
    RedoAdd(Movie _m,Repo& _r);
    void redo() override;
};

class RedoDelete: public Redo{
private:
    Repo& r;
    std::string m_title;
public:
    RedoDelete(std::string _m_title,Repo& _r);
    void redo() override;
};

class RedoUpdate: public Redo{
private:
    Repo& r;
    std::string m_title;
    Movie m;
public:
    RedoUpdate(std::string _m_title, Movie _m, Repo& _r);
    void redo() override;
};
