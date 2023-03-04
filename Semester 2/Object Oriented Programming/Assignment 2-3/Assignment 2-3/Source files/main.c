#include "Header files/Repo.h"
#include "Header files/Service.h"
#include "Header files/Ui.h"
#include <crtdbg.h>
#include <stdio.h>

int main()
{
    test_repo();
    test_service();
    OfferRepo * repo=create_repo();
    add(repo, create_offer("mountain","Alps","20/04/2022",1800));
    add(repo, create_offer("city break","Rome","05/05/2022",1200));
    add(repo, create_offer("seaside","Mamaia","12/05/2022",400));
    add(repo, create_offer("city break","Paris","24/06/2022",2400));
    add(repo, create_offer("seaside","Barcelona","07/04/2022",1500));
    OfferService * service= create_service(repo);

    Ui* ui= create_ui(service);
    start_ui(ui);

    destroy_ui(ui);

    printf("%d",_CrtDumpMemoryLeaks());

    return 0;
}

