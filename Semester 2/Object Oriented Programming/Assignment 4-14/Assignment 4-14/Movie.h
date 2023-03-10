#pragma once
#include <string>
#include <Windows.h>
#include <iostream>
#include <fstream>

class Movie{
private:
    std::string title;
    std::string  genre;
    int release_year;
    int likes;
    std::string trailer;

public:
    explicit Movie(std::string title="",std::string genre="",int release_year=-1,int likes=-1,std::string trailer="");

    std::string get_title() const;
    std::string get_genre() const;
    int get_release_year() const;
    int get_likes() const;
    std::string get_trailer() const;

    void set_title(std::string _title);
    void set_genre(std::string _genre);
    void set_release_year(int _release_year);
    void set_likes(int likes);
    void set_trailer(std::string _trailer);

    std::string to_string() const;

    void play_trailer() const;

    friend std::ofstream& operator<<(std::ofstream& file,Movie& m);
    friend std::ifstream& operator>>(std::ifstream& file,Movie& m);

    ~Movie();
};
