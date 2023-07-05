package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.master.*;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllCompletedOrdersHistoryMasterPage;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllNewOrdersHistoryMasterPage;
import ru.gasworkers.dev.pages.master.ordersHystoryMasterPage.AllScheduledOrdersHistoryMasterPage;
import ru.gasworkers.dev.pages.sharedPages.conference.ConferenceQrPage;
import ru.gasworkers.dev.pages.sharedPages.conference.ConferenceSharedPage;

import static ru.gasworkers.dev.model.Role.MASTER;


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

    private final ConferenceQrPage conferenceQrPage = new ConferenceQrPage(browser);
    private final ConferenceSharedPage conferencePage = new ConferenceSharedPage(browser);
    private final ResumeConferenceMasterPage resumeConferencePage = new ResumeConferenceMasterPage(browser);


}
