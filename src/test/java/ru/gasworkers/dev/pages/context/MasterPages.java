package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.master.*;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllCompletedOrdersHistoryMasterPage;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllScheduledOrdersHistoryMasterPage;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllNewOrdersHistoryMasterPage;

import static ru.gasworkers.dev.model.Role.*;


@Getter
public final class MasterPages extends BaseRolePages {

    public MasterPages(SelenideDriver driver) {
        super(MASTER, driver);
    }

    private final RegistrationMasterPage registrationPage = new RegistrationMasterPage(browser);
    private final HomeMasterPage homePage = new HomeMasterPage(browser);
    private final ProfileMasterPage profilePage = new ProfileMasterPage(browser);
    private final OrderCardMasterPage orderCardPage = new OrderCardMasterPage(browser);
    private final EditObjectMasterPage editObjectPage = new EditObjectMasterPage(browser);
    private final AllNewOrdersHistoryMasterPage allNewOrdersPage = new AllNewOrdersHistoryMasterPage(browser);
    private final AllScheduledOrdersHistoryMasterPage allScheduledOrdersPage = new AllScheduledOrdersHistoryMasterPage(browser);
    private final AllCompletedOrdersHistoryMasterPage allCompletedOrdersPage = new AllCompletedOrdersHistoryMasterPage(browser);

}
