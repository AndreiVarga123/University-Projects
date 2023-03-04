#pragma once
#include "Service.h"

class Ui{
private:
    Service serv;
public:
    explicit Ui(Service s);
    ~Ui();
    void start();
};
