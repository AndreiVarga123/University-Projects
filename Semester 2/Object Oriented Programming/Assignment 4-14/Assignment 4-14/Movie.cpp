#include "Movie.h"

#include <utility>
#include <cstring>
#include <sstream>

Movie::Movie(std::string title,std::string genre, int release_year, int likes,std::string trailer)
{
    this->title=std::move(title);
    this->genre=std::move(genre);
    this->release_year=release_year;
    this->likes=likes;
    this->trailer=std::move(trailer);
}

std::string Movie::get_title() const
{
    return title;
}

std::string Movie::get_genre() const
{
    return genre;
}

int Movie::get_release_year() const
{
    return release_year;
}

int Movie::get_likes() const
{
    return likes;
}

std::string Movie::get_trailer() const
{
    return trailer;
}

void Movie::set_title(std::string _title)
{
    this->title=std::move(_title);
}

void Movie::set_genre(std::string _genre)
{
    this->genre=std::move(_genre);
}

void Movie::set_release_year(int _release_year)
{
    this->release_year=_release_year;
}

void Movie::set_likes(int _likes)
{
    this->likes=_likes;
}

void Movie::set_trailer(std::string _trailer)
{
    this->trailer=std::move(_trailer);
}

Movie::~Movie() = default;

std::string Movie::to_string() const
{
    std::string str_release_year = std::to_string(this->release_year),str_likes = std::to_string(this->likes);
    return "Title:"+this->title+", Genre:"+this->genre+", Release year:"+str_release_year+", Likes:"+str_likes+"\n";
}

void Movie::play_trailer() const
{
    ShellExecuteA(nullptr, nullptr, "opera.exe", this->trailer.c_str(), nullptr, SW_SHOWMAXIMIZED);
}

std::ofstream &operator<<(std::ofstream &file, Movie &m)
{
    std::string str_release_year = std::to_string(m.release_year),str_likes = std::to_string(m.likes);
    std::string movie= m.title+'|'+m.genre+'|'+str_release_year+'|'+str_likes+'|'+ m.trailer+'\n';
    file << movie;
    return file;
}

std::ifstream &operator>>(std::ifstream &file, Movie &m)
{
    std::string movie,token;
    std::getline(file,movie);
    if(movie.empty())
    {
        m.likes=-1;
        return file;
    }
    std::istringstream iss(movie);
    std::getline(iss,token,'|');
    m.title=token;
    std::getline(iss,token,'|');
    m.genre=token;
    std::getline(iss,token,'|');
    char *remaining;
    m.release_year=strtol(token.c_str(),&remaining,10);
    std::getline(iss,token,'|');
    m.likes=strtol(token.c_str(),&remaining,10);
    std::getline(iss,token,'|');
    m.trailer=token;
    return file;
}
