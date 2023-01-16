package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import pages.dispatcher.HomeDispatcherPage;
import pages.dispatcher.AllMastersDispatcherPage;
import pages.dispatcher.AllNotificationsDispatcherPage;
import pages.dispatcher.ProfileDispatcherPage;

import static model.Role.*;

@Getter
public final class DispatcherPages extends BaseRolePages {

    public DispatcherPages(SelenideDriver driver) {
        super(DISPATCHER, driver);
    }

    private final HomeDispatcherPage homePage = new HomeDispatcherPage(browser);
    private final AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage(browser);
    private final AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage(browser);
    private final ProfileDispatcherPage profilePage = new ProfileDispatcherPage(browser);



}
