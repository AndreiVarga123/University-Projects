#include "Service.h"
#include "Validator.h"
#include <regex>

Service::Service()=default;

Service::Service(const Service &s) {
    this->repo=s.repo;
    this->watchlist=s.watchlist;
}

Service::Service(Repo r, Watchlist *w): repo(r),watchlist(w){

}

std::vector<Movie> Service::get_movie_list() const
{
    return this->repo.get_movie_list();
}

void Service::add_movie(std::string &title, std::string &genre, std::string &release_year, std::string &likes, std::string &trailer)
{
    Validator::validate_add(this->repo,title,release_year,likes,trailer);
    int release_year_nr= std::stoi(release_year);
    int likes_nr=std::stoi(likes);
    Movie movie(title,genre,release_year_nr,likes_nr,trailer);

    while(index+1!=undo_list.size())
    {
        undo_list.erase(undo_list.end()-1);
        redo_list.erase(redo_list.end()-1);
    }
    Undo* u=new UndoAdd(title,repo);
    undo_list.push_back(u);
    Redo* r=new RedoAdd(movie,repo);
    redo_list.push_back(r);
    index++;

    this->repo.add_movie(movie);
}

void Service::remove_movie(std::string &title)
{
    Validator::validate_delete(this->repo,title);

    for(auto&m :repo.get_movie_list())
        if(m.get_title()==title)
        {
            while(index+1!=undo_list.size())
            {
                undo_list.erase(undo_list.end()-1);
                redo_list.erase(redo_list.end()-1);
            }
            Undo* u=new UndoDelete(m,repo);
            undo_list.push_back(u);
            Redo* r=new RedoDelete(title,repo);
            redo_list.push_back(r);
            index++;

            break;
        }

    this->repo.remove_movie(title);
}

void Service::update_movie(std::string &title,std::string &new_title, std::string &new_genre, std::string &new_release_year, std::string &new_likes, std::string &new_trailer)
{
    Validator::validate_update(this->repo,title,new_release_year,new_likes,new_trailer);
    int new_release_year_nr= std::stoi(new_release_year);
    int new_likes_nr=std::stoi(new_likes);
    Movie movie(new_title,new_genre,new_release_year_nr,new_likes_nr,new_trailer);

    for(auto&m :repo.get_movie_list())
        if(m.get_title()==title)
        {
            while(index+1!=undo_list.size())
            {
                undo_list.erase(undo_list.end()-1);
                redo_list.erase(redo_list.end()-1);
            }
            Undo* u=new UndoUpdate(new_title,m,repo);
            undo_list.push_back(u);
            Redo* r=new RedoUpdate(title,movie,repo);
            redo_list.push_back(r);
            index++;

            break;
        }

    this->repo.update_movie(title,movie);
}

std::vector<Movie> Service::filter_by_genre(std::string &genre) const
{
    if(!genre.empty())
    {
        std::vector<Movie> movie_list;
        for(auto & movie: get_movie_list())
            if(movie.get_genre()==genre)
                movie_list.push_back(movie);
        return movie_list;
    }
    return get_movie_list();
}

std::vector<Movie> Service::get_watch_list() const
{
    return watchlist->get_watch_list();
}

void Service::add_movie_to_watch_list(const Movie& movie)
{
    Validator::validate_add_to_watchlist(*this->watchlist, movie.get_title());
    watchlist->add_movie_to_watch_list(movie);
}

void Service::remove_movie_from_watch_list(const std::string& title)
{
    Validator::validate_remove_from_watchlist(*this->watchlist, title);
    watchlist->remove_movie_from_watch_list(title);
}

void Service::add_like_to_movie(const std::string &title)
{
    repo.add_like_to_movie(title);
}

std::vector<Movie> Service::get_movie_list_deleted() const
{
    return repo.get_movie_list_deleted();
}

void Service::write_repo_to_file()
{
    repo.write_movie_list_to_file();
    repo.write_deleted_list_to_file();
}

void Service::write_watch_list_to_file() {
    watchlist->write_watch_list_to_file();
}

void Service::show_watch_list_from_file() {
    watchlist->show_watch_list_from_file();
}

void Service::undo()
{
    if(index!=-1)
    {
        undo_list[index]->undo();
        index--;
    }
    else
    {
        throw Validation_exception("Can't undo anymore");
    }
}

void Service::redo()
{
    if(index+1!=redo_list.size())
    {
        index++;
        redo_list[index]->redo();
    }
    else
    {
        throw Validation_exception("Can't redo anymore");
    }
}

Service::~Service()=default;
