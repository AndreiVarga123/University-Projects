//
// Created by varga on 28-May-22.
//

#include "Redo.h"

RedoAdd::RedoAdd(Movie _m, Repo &_r):m(_m), r(_r) {

}

void RedoAdd::redo() {
    r.add_movie(m);
}

RedoDelete::RedoDelete(std::string _m_title, Repo &_r):m_title(_m_title), r(_r) {

}

void RedoDelete::redo() {
    r.remove_movie(m_title);
}

RedoUpdate::RedoUpdate(std::string _m_title, Movie _m, Repo &_r):m_title(_m_title),m(_m),r(_r) {

}

void RedoUpdate::redo() {
    r.update_movie(m_title,m);
}
