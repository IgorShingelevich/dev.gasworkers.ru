package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.selfEmployed.*;
import ru.gasworkers.dev.pages.selfEmployed.orderPage.AcceptedOrderSelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.orderPage.CompletedOrderSelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.orderPage.NewOrderSelfEmployedPage;
import ru.gasworkers.dev.pages.sharedPages.LandingPage;

import static ru.gasworkers.dev.model.Role.SELF_EMPLOYED;

@Getter
public final class SelfEmployedPages extends BaseRolePages {

    public SelfEmployedPages(SelenideDriver driver) {
        super(SELF_EMPLOYED, driver);
    }
    private final LandingPage landingPage = new LandingPage(browser);
    private final RegistrationSelfEmployedPage registrationPage = new RegistrationSelfEmployedPage(browser);
    private final TypeSelfEmployedPage typeSelfEmployedPage = new TypeSelfEmployedPage(browser);
    private final SuccessfulRegistrationSelfEmployedPage successfulRegistrationSelfEmployedPage = new SuccessfulRegistrationSelfEmployedPage(browser);
    private final HomeSelfEmployedPage homeSelfEmployedPage = new HomeSelfEmployedPage(browser);
    private final ProfileSelfEmployedPage profileSelfEmployedPage = new ProfileSelfEmployedPage(browser);
    private final NewOrderSelfEmployedPage newOrderSelfEmployedPage = new NewOrderSelfEmployedPage(browser);
    private final AcceptedOrderSelfEmployedPage acceptedOrderSelfEmployedPage = new AcceptedOrderSelfEmployedPage(browser);
    private final CompletedOrderSelfEmployedPage completedOrderSelfEmployedPage = new CompletedOrderSelfEmployedPage(browser);


}
