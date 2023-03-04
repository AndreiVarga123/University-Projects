//
// Created by varga on 28-Apr-22.
//

#include "Watchlist_csv.h"
#include <windows.h>
#include <shellapi.h>

Watchlist_csv::Watchlist_csv(std::string file) {
    this->file=file;
}

void Watchlist_csv::write_watch_list_to_file() const {
    std::ofstream f(file);
    if(!f.is_open())
        return;
    for (auto m: this->get_watch_list())
        f<<m;
    f.close();
}

void Watchlist_csv::show_watch_list_from_file() const {
    ShellExecuteA(NULL, "open",file.c_str(), NULL,NULL,SW_SHOWNORMAL);
}
