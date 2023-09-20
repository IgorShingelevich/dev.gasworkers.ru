package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.selfEmployed.*;
import ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory.AllCompletedOrderHistorySelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory.AllNewOrderHistorySelfEmployedPage;
import ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory.AllScheduledOrderHistorySelfEmployedPage;
import ru.gasworkers.dev.pages.sharedPages.LandingPage;

import static ru.gasworkers.dev.model.UserRole.SELF_EMPLOYED;

@Getter
public final class SelfEmployedPages extends BaseRolePages {

    public SelfEmployedPages(SelenideDriver driver) {
        super(SELF_EMPLOYED, driver);
    }
    private final LandingPage landingPage = new LandingPage(browser);
    private final RegistrationSelfEmployedPage registrationPage = new RegistrationSelfEmployedPage(browser);
    private final AccountTypeSelfEmployedPage accountTypePage = new AccountTypeSelfEmployedPage(browser);
    private final SuccessfulRegistrationSelfEmployedPage successfulRegistrationPage = new SuccessfulRegistrationSelfEmployedPage(browser);
    private final HomeSelfEmployedPage homePage = new HomeSelfEmployedPage(browser);
    private final ProfileSelfEmployedPage profilePage = new ProfileSelfEmployedPage(browser);
    private final AllNewOrderHistorySelfEmployedPage allNewOrderHistoryPage = new AllNewOrderHistorySelfEmployedPage(browser);
    private final AllScheduledOrderHistorySelfEmployedPage allScheduledOrderHistoryPage = new AllScheduledOrderHistorySelfEmployedPage(browser);
    private final AllCompletedOrderHistorySelfEmployedPage allCompletedOrderHistoryPage = new AllCompletedOrderHistorySelfEmployedPage(browser);
    private final CertificatesAndEquipmentSelfEmployedPage certificatesAndEquipmentPage = new CertificatesAndEquipmentSelfEmployedPage(browser);
    private final OrderCardSelfEmployedPage orderCardPage = new OrderCardSelfEmployedPage(browser);
    private final FillProfileInfoSelfEmployedPage fillProfileInfoPage = new FillProfileInfoSelfEmployedPage(browser);


}
