package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.selfEmployed.*;
import ru.gasworkers.dev.pages.selfEmployed.allOrders.AllAcceptedOrderSelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.allOrders.AllCompletedOrderSelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.allOrders.AllNewOrderSelfEmployedPage;
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
    private final AllNewOrderSelfEmployedPage allNewOrderSelfEmployedPage = new AllNewOrderSelfEmployedPage(browser);
    private final AllAcceptedOrderSelfEmployedPage allAcceptedOrderSelfEmployedPage = new AllAcceptedOrderSelfEmployedPage(browser);
    private final AllCompletedOrderSelfEmployedPage allCompletedOrderSelfEmployedPage = new AllCompletedOrderSelfEmployedPage(browser);
    private final OrderCardSelfEmployedPage orderCardPage = new OrderCardSelfEmployedPage(browser);
    private final FillProfileInfoSelfEmployedPage fillProfileInfo = new FillProfileInfoSelfEmployedPage(browser);


}
