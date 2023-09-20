package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;

import static ru.gasworkers.dev.model.UserRole.SERVICE;


public final class ServicePages extends BaseRolePages {
    public ServicePages(SelenideDriver driver) {
        super(SERVICE, driver);
    }



}
