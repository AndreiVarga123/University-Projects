//
// Created by varga on 03-Jun-22.
//

#include <QAbstractTableModel>
#include "Movie.h"

class Model: public QAbstractTableModel
{
Q_OBJECT
public:
    std::vector<Movie> watchlist;
    Model(std::vector<Movie> w,QObject *parent=nullptr);
    int rowCount(const QModelIndex &parent = QModelIndex()) const override;
    int columnCount(const QModelIndex &parent = QModelIndex()) const override;
    QVariant data(const QModelIndex &index, int role = Qt::DisplayRole) const override;
    QVariant headerData(int section, Qt::Orientation orientation,int role = Qt::DisplayRole) const override;
};
