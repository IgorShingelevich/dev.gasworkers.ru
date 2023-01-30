package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import pages.master.HomeMasterPage;
import pages.master.OrderCardMasterPage;
import pages.master.ProfileMasterPage;
import pages.master.ordersPage.CompletedOrdersMasterPage;
import pages.master.ordersPage.InProgressOrdersMasterPage;
import pages.master.ordersPage.NewOrdersMasterPage;
import pages.master.RegistrationMasterPage;

import static model.Role.*;


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
