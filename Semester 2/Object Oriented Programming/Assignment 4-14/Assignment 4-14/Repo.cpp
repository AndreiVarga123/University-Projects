#include "Repo.h"
#include <cstring>
#include  <algorithm>

Repo::Repo()
{
    Movie m,dm;
    std::ifstream file;
    file.open("C:\\Users\\varga\\Downloads\\a14\\a11\\movielist.txt");
    while(!file.eof())
    {
        file >> m;
        if(m.get_likes()!=-1)
            arr.push_back(m);
    }
    file.close();
    file.open("C:\\Users\\varga\\Downloads\\a14\\a11\\deletedlist.txt");
    while(!file.eof())
    {
        file >> dm;
        if(m.get_likes()!=-1)
            deleted_arr.push_back(dm);
    }
    file.close();
}

std::vector<Movie> Repo::get_movie_list() const
{
    return arr;
}

void Repo::add_movie(Movie &new_movie)
{
    arr.push_back(new_movie);
}

void Repo::remove_movie(const std::string&  title)
{
    for (int i=0;i<arr.size();i++)
        if(arr[i].get_title()==title)
        {
            deleted_arr.push_back(arr[i]);
            arr.erase(arr.begin()+i);
            break;
        }
}

void Repo::update_movie(const std::string& title, Movie &new_movie)
{
    for (auto & movie : arr)
        if(movie.get_title() == title)
        {
            movie=new_movie;
            break;
        }
}

void Repo::add_like_to_movie(const std::string &title)
{
    for (auto & movie : arr)
        if(movie.get_title() == title)
        {
            movie.set_likes(movie.get_likes() + 1);
            return;
        }
}

std::vector<Movie> Repo::get_movie_list_deleted() const
{
    return deleted_arr;
}

void Repo::write_movie_list_to_file()
{
    std::ofstream file;
    file.open("C:\\Users\\varga\\Downloads\\a11\\movielist.txt");
    for (auto & movie : arr)
        file<<movie;
    file.close();
}

void Repo::write_deleted_list_to_file()
{
    std::ofstream file;
    file.open("C:\\Users\\varga\\Downloads\\a11\\deletedlist.txt");
    for (auto & movie : deleted_arr)
        file<<movie;
    file.close();
}


Repo::~Repo() = default;
