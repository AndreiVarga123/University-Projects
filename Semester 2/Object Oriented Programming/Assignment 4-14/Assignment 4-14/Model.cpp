//
// Created by varga on 03-Jun-22.
//

#include "Model.h"

Model::Model(std::vector<Movie> w, QObject *parent): QAbstractTableModel(parent) {
    watchlist=w;
}

int Model::rowCount(const QModelIndex &parent) const {
    return watchlist.size();
}

int Model::columnCount(const QModelIndex &parent) const {
    return 5;
}

QVariant Model::data(const QModelIndex &index, int role) const {
    int row =index.row();
    int column = index.column();

    if(row==watchlist.size())
    {
        return QVariant();
    }

    Movie mov = watchlist[row];

    if(role == Qt::DisplayRole)
    {
        switch (column)
        {
            case 0:
                return QString::fromStdString(mov.get_title());
            case 1:
                return QString::fromStdString(mov.get_genre());
            case 2:
                return QString::fromStdString(std::to_string(mov.get_release_year()));
            case 3:
                return QString::fromStdString(std::to_string(mov.get_likes()));
            case 4:
                return QString::fromStdString(mov.get_trailer());
        }
    }
    return QVariant();
}

QVariant Model::headerData(int section, Qt::Orientation orientation, int role) const {
    if(role == Qt::DisplayRole)
    {
        if(orientation == Qt::Horizontal)
        {
            switch (section)
            {
                case 0:
                    return QString{"Title"};
                case 1:
                    return QString{"Genre"};
                case 2:
                    return QString{"Release year"};
                case 3:
                    return QString{"Likes"};
                case 4:
                    return QString{"Trailer"};
            }
        }
    }
    return QVariant();
}
