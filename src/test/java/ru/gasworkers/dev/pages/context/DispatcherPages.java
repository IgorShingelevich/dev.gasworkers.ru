package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.dispatcher.*;

import static ru.gasworkers.dev.model.UserRole.DISPATCHER;

@Getter
public final class DispatcherPages extends BaseRolePages {

    public DispatcherPages(SelenideDriver driver) {
        super(DISPATCHER, driver);
    }

    private final HomeDispatcherPage homePage = new HomeDispatcherPage(browser);
    private final ProfileDispatcherPage profilePage = new ProfileDispatcherPage(browser);
    private final OrderCardDispatcherPage orderCardPage = new OrderCardDispatcherPage(browser);
    private final SelectMasterDispatcherPage selectMasterPage = new SelectMasterDispatcherPage(browser);
    private final AllMastersDispatcherPage allMastersPage = new AllMastersDispatcherPage(browser);
    private final AllNotificationsDispatcherPage allNotificationsPage = new AllNotificationsDispatcherPage(browser);

}
