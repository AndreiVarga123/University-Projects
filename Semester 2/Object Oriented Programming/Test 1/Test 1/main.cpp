#include "Ui.h"

int main()
{
    Repo repo;
    Service serv(repo);
    Ui ui(serv);
    ui.start();
}

