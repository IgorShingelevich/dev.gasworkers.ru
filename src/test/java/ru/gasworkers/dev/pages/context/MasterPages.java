package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.master.HomeMasterPage;
import ru.gasworkers.dev.pages.master.OrderCardMasterPage;
import ru.gasworkers.dev.pages.master.ProfileMasterPage;
import ru.gasworkers.dev.pages.master.ordersPage.CompletedOrdersMasterPage;
import ru.gasworkers.dev.pages.master.ordersPage.InProgressOrdersMasterPage;
import ru.gasworkers.dev.pages.master.ordersPage.NewOrdersMasterPage;
import ru.gasworkers.dev.pages.master.RegistrationMasterPage;

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
    private final NewOrdersMasterPage newOrdersPage = new NewOrdersMasterPage(browser);
    private final InProgressOrdersMasterPage inProgressOrdersPage = new InProgressOrdersMasterPage(browser);
    private final CompletedOrdersMasterPage completedOrdersPage = new CompletedOrdersMasterPage(browser);

}
