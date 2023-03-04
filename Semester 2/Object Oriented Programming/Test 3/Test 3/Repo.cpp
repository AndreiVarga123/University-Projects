//
// Created by varga on 5/27/2022.
//

#include "Repo.h"

Repo::Repo() {
    read_from_file();
}

void Repo::read_from_file() {
    std::ifstream file("C:/Users/varga/CLionProjects/test/t.txt");
    if(!file.is_open())
        return;
    Equation e;
    while(file>>e)
    {
        arr.push_back(e);
    }
    file.close();
}
