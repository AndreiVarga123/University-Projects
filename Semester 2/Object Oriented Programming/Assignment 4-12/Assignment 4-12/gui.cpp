#include "gui.h"

Gui::Gui(QWidget *parent): QWidget{parent}{
    choose_repo();
}

void Gui::choose_repo()
{
    start=new QHBoxLayout(this);
    csv = new QPushButton("CSV");
    csv->resize(500,500);
    html = new QPushButton("HTML");
    html->resize(500,500);
    start->addWidget(csv);
    start->addWidget(html);
    QObject::connect(csv,&QPushButton::clicked,this,&Gui::init_csv);
    QObject::connect(html,&QPushButton::clicked,this,&Gui::init_html);
}

void Gui::init_html()
{
    delete start;
    delete csv;
    delete html;

    Repo repo;
    Watchlist* w;
    w = new Watchlist_html("C:\\Users\\varga\\Downloads\\a14\\a11\\watchlist.html");
    s=Service(repo,w);

    this->initGui();
    this->populateGui();
    this->connectRelations();
}

void Gui::init_csv()
{
    delete start;
    delete csv;
    delete html;

    Repo repo;
    Watchlist* w;
    w = new Watchlist_csv("C:\\Users\\varga\\Downloads\\a14\\a11\\watchlist.csv");
    s=Service(repo,w);

    this->initGui();
    this->populateGui();
    this->connectRelations();
}

void Gui::initGui() {

    ///main

    QHBoxLayout* mainLayout = new QHBoxLayout(this);

    QTabWidget* tabs = new QTabWidget();

    ///admin

    QWidget* admin = new QWidget();

    QHBoxLayout* adminLayout = new QHBoxLayout(admin);

        ///main functions layout

    QVBoxLayout* adminFunctionsLayout = new QVBoxLayout();

            ///movie list layout

    QFormLayout* movieListLayout = new QFormLayout();

    QLabel* listLabel = new QLabel("Movies");
    movieList = new QListWidget();
    movieList->setSelectionMode(QAbstractItemView::SingleSelection);
    QString style = QString("background-color: qlineargradient(spread:pad, x1:0, y1:0, x2:1, y2:0,")+
                    QString("stop:0.000 rgba(%1, %2, %3, 255),").arg(255).arg(0).arg(0) +
                    QString("stop:1.000 rgba(%1, %2, %3, 255));").arg(0).arg(100).arg(100);
    movieList->setStyleSheet(style);
    listLabel->setBuddy(movieList);

    movieListLayout->addWidget(listLabel);
    movieListLayout->addWidget(movieList);

    adminFunctionsLayout->addLayout(movieListLayout);

            ///attributes layout

    QGridLayout* editsLayout = new QGridLayout();

    QLabel* title = new QLabel("Title: ");
    QLabel* genre = new QLabel("Genre: ");
    QLabel* release_year = new QLabel("Release year: ");
    QLabel* likes = new QLabel("Likes: ");
    QLabel* trailer = new QLabel("Trailer: ");

    title_line = new QLineEdit();
    genre_line = new QLineEdit();
    release_year_line = new QLineEdit();
    likes_line = new QLineEdit();
    trailer_line = new QLineEdit();

    title->setBuddy(title_line);
    genre->setBuddy(genre_line);
    release_year->setBuddy(release_year_line);
    likes->setBuddy(likes_line);
    trailer->setBuddy(trailer_line);

    editsLayout->addWidget(title, 0, 0);
    editsLayout->addWidget(title_line, 0, 1, 1, 2);
    editsLayout->addWidget(genre, 1, 0);
    editsLayout->addWidget(genre_line, 1, 1, 1, 2);
    editsLayout->addWidget(release_year, 2, 0);
    editsLayout->addWidget(release_year_line, 2, 1, 1, 2);
    editsLayout->addWidget(likes, 3, 0);
    editsLayout->addWidget(likes_line, 3, 1, 1, 2);
    editsLayout->addWidget(trailer,4, 0);
    editsLayout->addWidget(trailer_line, 4, 1, 1 , 2);

    adminFunctionsLayout->addLayout(editsLayout);

            ///buttons layout

    QGridLayout* buttonsLayout = new QGridLayout();

    add = new QPushButton("Add");
    del = new QPushButton("Delete");
    update = new QPushButton("Update");
    undo = new QPushButton("Undo");
    redo = new QPushButton("Redo");

    buttonsLayout->addWidget(add,0,0);
    buttonsLayout->addWidget(del,0,1);
    buttonsLayout->addWidget(update,0,2);
    buttonsLayout->addWidget(undo,2,0);
    buttonsLayout->addWidget(redo,2,2);

    adminFunctionsLayout->addLayout(buttonsLayout);

            ///activity layout

    QFormLayout* activityLayout = new QFormLayout();

    QLabel* filteredBoxLabel = new QLabel("Objects filtered: ");
    filterBox = new QLineEdit();
    filteredObjects = new QListWidget();
    filteredBoxLabel->setBuddy(filteredObjects);
    QLabel* filterLabel = new QLabel("Input filter (for title): ");
    filterLabel->setBuddy(filterBox);

    activityLayout->addRow(filterLabel);
    activityLayout->addRow(filterBox);
    activityLayout->addRow(filteredBoxLabel);
    activityLayout->addRow(filteredObjects);

    ///adding layout to admin

    adminLayout->addLayout(adminFunctionsLayout);
    adminLayout->addLayout(activityLayout);

    ///user

    QWidget* user =  new QWidget();

    QVBoxLayout* userLayout = new  QVBoxLayout(user);

        ///watchlist layout

    QFormLayout* watchlistLayout = new QFormLayout();

    QLabel* watchlistLabel = new QLabel("Watchlist");
    watchlist = new QListWidget();
    watchlist->setSelectionMode(QAbstractItemView::SingleSelection);
    watchlistLabel->setBuddy(watchlist);

    watchlistLayout->addWidget(watchlistLabel);
    watchlistLayout->addWidget(watchlist);

    browser=new QPushButton("Open watchlist with html/csv");
    show_table=new QPushButton("Open watchlist in tableview");
    watchlistLayout->addWidget(browser);
    watchlistLayout->addWidget(show_table);

    userLayout->addLayout(watchlistLayout);

        ///current movies layout

    QGridLayout* currentMovieLayout = new QGridLayout();

    QLabel* filterGenreLabel = new QLabel("Cycle through movies from this genre");
    filter_genre=new QLineEdit();
    filter=new QPushButton("Filter");

    QLabel* currentMovieLabel = new QLabel("Current movie");
    current_movie = new QLineEdit();
    currentMovieLabel->setBuddy(currentMovieLabel);

    next = new QPushButton("Next");
    add_to_wl = new QPushButton("Add");
    stop = new QPushButton("Stop");


    currentMovieLayout->addWidget(filterGenreLabel,0,0);
    currentMovieLayout->addWidget(filter_genre,0,1,1,2);
    currentMovieLayout->addWidget(filter,1,0,1,3);
    currentMovieLayout->addWidget(currentMovieLabel,2,0);
    currentMovieLayout->addWidget(current_movie,3,0,1,3);
    currentMovieLayout->addWidget(next,4,0);
    currentMovieLayout->addWidget(add_to_wl,4,1);
    currentMovieLayout->addWidget(stop,4,2);

    userLayout->addLayout(currentMovieLayout);

        ///user functions layout

    QGridLayout* userFunctionsLayout = new QGridLayout();

    QLabel*  wl_title = new QLabel("Title: ");
    wl_title_line = new QLineEdit();
    wl_title->setBuddy(wl_title_line);

    wl_del = new QPushButton("Delete");

    userFunctionsLayout->addWidget(wl_title, 0, 0);
    userFunctionsLayout->addWidget(wl_title_line, 0, 1, 1, 2);
    userFunctionsLayout->addWidget(wl_del,1, 0, 1, 3);

    userLayout->addLayout(userFunctionsLayout);

    ///adding tabs to main

    tabs->addTab(admin,"Admin");
    tabs->addTab(user,"User");

    mainLayout->addWidget(tabs);
}

void Gui::populateGui(){
    movieList->clear();
    for(auto o : s.get_movie_list()){
        QString itemInList = QString::fromStdString(o.to_string());
        QListWidgetItem* item = new QListWidgetItem{itemInList};
        movieList->addItem(item);
    }
}

void Gui::connectRelations() {
    QObject::connect(filterBox,&QLineEdit::textChanged,this,&Gui::filterFunc);
    QObject::connect(add,&QPushButton::clicked,this,&Gui::_add);
    QObject::connect(del,&QPushButton::clicked,this,&Gui::_del);
    QObject::connect(update,&QPushButton::clicked,this,&Gui::_update);
    QObject::connect(undo,&QPushButton::clicked,this,&Gui::_undo);
    QObject::connect(redo,&QPushButton::clicked,this,&Gui::_redo);

    QObject::connect(browser,&QPushButton::clicked,this,&Gui::openInBrowser);
    QObject::connect(show_table,&QPushButton::clicked,this,&Gui::showTable);
    QObject::connect(filter,&QPushButton::clicked,this,&Gui::filterByGenre);
    QObject::connect(next,&QPushButton::clicked,this,&Gui::_next);
    QObject::connect(add_to_wl,&QPushButton::clicked,this,&Gui::_addToWl);
    QObject::connect(stop,&QPushButton::clicked,this,&Gui::_stop);
    QObject::connect(wl_del,&QPushButton::clicked,this,&Gui::removeFromWl);
}

void Gui::filterFunc() {
    filteredObjects->clear();
    QString filterData = filterBox->text();
    std::string filterDataString = filterData.toStdString();
    for (auto o: s.get_movie_list()) {
        if (o.get_title().find(filterDataString) != -1) {
            QString itemInList = QString::fromStdString(o.to_string());
            QListWidgetItem *item = new QListWidgetItem{itemInList};
            filteredObjects->addItem(item);
        }
    }
}

void Gui::_add()
{
    std::string title=title_line->text().toStdString();
    std::string genre=genre_line->text().toStdString();
    std::string release_year=release_year_line->text().toStdString();
    std::string likes=likes_line->text().toStdString();
    std::string trailer=trailer_line->text().toStdString();

    try {
        s.add_movie(title, genre, release_year, likes, trailer);
        this->populateGui();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::_del() {
    if(movieList->currentItem()== nullptr)
        return;
    std::string movie=movieList->currentItem()->text().toStdString();
    std::string title;
    std::istringstream stream(movie);
    std::getline(stream,title,':');
    std::getline(stream,title,',');

    try{
        s.remove_movie(title);
        this->populateGui();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::_update() {
    if(movieList->currentItem()== nullptr)
        return;
    std::string movie=movieList->currentItem()->text().toStdString();
    std::string old_title;
    std::istringstream stream(movie);
    std::getline(stream,old_title,':');
    std::getline(stream,old_title,',');

    std::string title=title_line->text().toStdString();
    std::string genre=genre_line->text().toStdString();
    std::string release_year=release_year_line->text().toStdString();
    std::string likes=likes_line->text().toStdString();
    std::string trailer=trailer_line->text().toStdString();

    try{
        s.update_movie(old_title,title,genre,release_year,likes,trailer);
        this->populateGui();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::closeEvent(QCloseEvent *event) {
    s.write_repo_to_file();
    event->accept();
}

void Gui::populateWatchlist()
{
    watchlist->clear();
    for(auto& o: s.get_watch_list())
    {
        QString itemInList = QString::fromStdString(o.to_string());
        QListWidgetItem* item = new QListWidgetItem{itemInList};
        watchlist->addItem(item);
    }
}

void Gui::openInBrowser()
{
    s.write_watch_list_to_file();
    s.show_watch_list_from_file();
}

void Gui::filterByGenre()
{
    std::string genre=filter_genre->text().toStdString();
    filtered_list=s.filter_by_genre(genre);
    index=0;
    current_movie->setText(QString::fromStdString(filtered_list[index].to_string()));
}

void Gui::_addToWl()
{
    if(current_movie->text().isEmpty())
        return;
    try{
        s.add_movie_to_watch_list(filtered_list[index]);
        this->populateWatchlist();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::_next()
{
    if(current_movie->text().isEmpty())
        return;
    if(index+1<filtered_list.size())
        current_movie->setText(QString::fromStdString(filtered_list[++index].to_string()));
    else {
        index=0;
        current_movie->setText(QString::fromStdString(filtered_list[index].to_string()));
    }
}

void Gui::_stop()
{
    filtered_list.clear();
    current_movie->clear();
    filter_genre->clear();
    index=0;
}

void Gui::removeFromWl()
{
    if(wl_title_line->text().isEmpty())
        return;
    try{
        s.remove_movie_from_watch_list(wl_title_line->text().toStdString());
        this->populateWatchlist();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::_undo() {
    try{
        s.undo();
        this->populateGui();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::_redo() {
    try{
        s.redo();
        this->populateGui();
    }
    catch(Validation_exception & ve){
        QErrorMessage* error=new QErrorMessage();
        error->showMessage(QString::fromStdString(ve.get_message()));
    }
}

void Gui::showTable() {
    QTableView* table_view = new QTableView(this);
    Model* model=new Model(s.get_watch_list(),this);
    table_view->setModel(model);
    table_view->show();
}



