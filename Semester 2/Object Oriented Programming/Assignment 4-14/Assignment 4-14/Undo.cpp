//
// Created by varga on 28-May-22.
//

#include "Undo.h"

UndoAdd::UndoAdd(std::string _m_title,Repo& _r): m_title(_m_title), r(_r){}

void UndoAdd::undo() {
    r.remove_movie(m_title);
}

UndoDelete::UndoDelete(Movie _m,Repo& _r): m(_m), r(_r) {}

void UndoDelete::undo() {
    r.add_movie(m);
}

UndoUpdate::UndoUpdate(std::string _m_title, Movie _m, Repo &_r): m_title(_m_title), m(_m), r(_r) {}

void UndoUpdate::undo() {
    r.update_movie(m_title,m);
}

