package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import pages.dispatcher.*;

import static model.Role.*;

@Getter
public final class DispatcherPages extends BaseRolePages {

    public DispatcherPages(SelenideDriver driver) {
        super(DISPATCHER, driver);
    }

    private final HomeDispatcherPage homePage = new HomeDispatcherPage(browser);
    private final OrderCardDispatcherPage orderCardPage = new OrderCardDispatcherPage(browser);
    private final SelectMasterDispatcherPage selectMasterPage = new SelectMasterDispatcherPage(browser);
    private final AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage(browser);
    private final AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage(browser);
    private final ProfileDispatcherPage profilePage = new ProfileDispatcherPage(browser);



}
