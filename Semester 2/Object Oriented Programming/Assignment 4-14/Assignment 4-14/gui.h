#include "qwidget.h"
#include "qboxlayout.h"
#include "qformlayout.h"
#include "qpushbutton.h"
#include "qlabel.h"
#include "qtabwidget.h"
#include "qlineedit.h"
#include "qlistwidget.h"
#include "Service.h"
#include "Movie.h"
#include "Service.h"
#include "QErrorMessage"
#include "Validator.h"
#include "QCloseEvent"
#include "QTableView"
#include "Model.h"
#include <sstream>


class Gui:public QWidget{
private:
    QOBJECT_H

    Service s;

    QHBoxLayout* start;
    QPushButton* csv;
    QPushButton* html;

    QListWidget* movieList;
    QListWidget* filteredObjects;
    QLineEdit* filterBox;

    QLineEdit* title_line;
    QLineEdit* genre_line;
    QLineEdit* release_year_line;
    QLineEdit* likes_line;
    QLineEdit* trailer_line;

    QPushButton* add;
    QPushButton* del;
    QPushButton* update;

    QPushButton* undo;
    QPushButton* redo;


    QListWidget* watchlist;
    QPushButton* browser;
    QPushButton* show_table;

    QLineEdit* filter_genre;
    QPushButton* filter;
    QLineEdit* current_movie;
    std::vector<Movie> filtered_list;
    int index;

    QPushButton* next;
    QPushButton* add_to_wl;
    QPushButton* stop;

    QLineEdit* wl_title_line;
    QPushButton* wl_del;

    void closeEvent(QCloseEvent *event) override;

public:
    Gui(QWidget *parent = 0);

    void choose_repo();
    void init_html();
    void init_csv();

    void initGui();
    void populateGui();
    void connectRelations();

    void filterFunc();

    void _add();
    void _del();
    void _update();
    void _undo();
    void _redo();

    void populateWatchlist();

    void openInBrowser();
    void filterByGenre();
    void _addToWl();
    void _next();
    void _stop();
    void removeFromWl();

    void showTable();
};

