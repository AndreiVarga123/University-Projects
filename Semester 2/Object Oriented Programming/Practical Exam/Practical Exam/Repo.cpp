//
// Created by varga on 6/8/2022.
//

#include "Repo.h"

Repo::Repo() {
    read_from_files();
}

void Repo::read_from_files() {
    std::ifstream f1("C:\\Users\\varga\\CLionProjects\\practic\\code.txt");
    std::ifstream f2("C:\\Users\\varga\\CLionProjects\\practic\\member.txt");

    if(!f1.is_open()||!f2.is_open())
        return;

    Code c;
    while(f1>>c){
        carr.push_back(c);
    }
    f1.close();

    Member m;
    while(f2>>m)
    {
        marr.push_back(m);
    }
    f2.close();
}
