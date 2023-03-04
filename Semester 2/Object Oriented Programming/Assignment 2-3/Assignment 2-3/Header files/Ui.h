#pragma once
#include "Header files/Service.h"

typedef struct
{
    OfferService* service;
}Ui;

Ui* create_ui(OfferService* service);
void destroy_ui(Ui* ui);

void start_ui(Ui* ui);