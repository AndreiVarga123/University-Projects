//
// Created by varga on 28-Apr-22.
//

#include "Watchlist_html.h"

Watchlist_html::Watchlist_html(std::string file) {
    this->file=file;
}

void Watchlist_html::write_watch_list_to_file() const {
    std::ofstream f(file);
    if(!f.is_open())
        return;
    f<<"<!DOCTYPE html>\n<html>\n\t<head>\n\t\t<title>Watchlist</title>\n\t</head>\n\t<body>\n\t\t<table border=\"\">\n";
    f<<"\t\t<tr>\n\t\t\t<td>Title</td>\n\t\t\t<td>Genre</td>\n\t\t\t<td>Release year</td>\n\t\t\t<td>Likes</td>\n\t\t\t<td>Trailer</td>\n\t\t</tr>\n";
    for (auto m: this->get_watch_list())
    {
        f<<"\t\t<tr>\n";
        f << "\t\t\t<td>" << m.get_title() << "</td>\n";
        f << "\t\t\t<td>" << m.get_genre() << "</td>\n";
        f << "\t\t\t<td>" << m.get_release_year()<<" </td>\n";
        f << "\t\t\t<td>" << m.get_likes() << "</td>\n";
        f << "\t\t\t<td><a href = \"" << m.get_trailer() << "\">Link</a></td>\n";
        f<<"\t\t</tr>\n";
    }
    f<<"\t\t</table>\n\t</body>\n</html>\n";
    f.close();

}

void Watchlist_html::show_watch_list_from_file() const {
    ShellExecuteA(NULL, "open",file.c_str(), NULL,NULL,SW_SHOWNORMAL);
}
