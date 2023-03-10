package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.common.LandingPage;
import ru.gasworkers.dev.pages.selfEmployed.RegistrationSelfEmployedPage;

import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

import static ru.gasworkers.dev.model.Role.*;
@Getter
public final class SelfEmployedPages extends BaseRolePages {

    public SelfEmployedPages(SelenideDriver driver) {
        super(SELF_EMPLOYED, driver);
    }
    private final LandingPage landingPage = new LandingPage(browser);
    private final RegistrationSelfEmployedPage registrationPage = new RegistrationSelfEmployedPage(browser);


}
