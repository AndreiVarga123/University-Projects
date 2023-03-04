#pragma once
#include "Repo.h"
#include "Watchlist.h"
#include "Watchlist_html.h"
#include "Watchlist_csv.h"
#include "Undo.h"
#include "Redo.h"

class Service
{
private:
    Repo repo;
    Watchlist* watchlist;
    std::vector<Undo*> undo_list;
    std::vector<Redo*> redo_list;
    int index=-1;

public:
    Service();
    Service(Repo r, Watchlist* w);
    Service(const Service& s);
    void add_movie(std::string &title, std::string &genre, std::string &release_year, std::string &likes, std::string &trailer);
    void remove_movie(std::string &title);
    void update_movie(std::string &title,std::string &new_title, std::string &new_genre, std::string &new_release_year, std::string &new_likes, std::string &new_trailer);
    void add_movie_to_watch_list(const Movie& movie);
    void remove_movie_from_watch_list(const std::string& title);
    std::vector<Movie> get_movie_list() const;
    std::vector<Movie> get_movie_list_deleted() const;
    std::vector<Movie> get_watch_list() const;
    std::vector<Movie> filter_by_genre(std::string &genre) const;
    void add_like_to_movie(const std::string& title);
    void write_repo_to_file();
    void write_watch_list_to_file();
    void show_watch_list_from_file();
    ~Service();

    void undo();
    void redo();
};
