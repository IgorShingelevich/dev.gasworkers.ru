package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavContactsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavNotificationsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavPasswordTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonDispatcherTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navProfileTab.NavProfileDispatcherTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

public class ProfileDispatcherPage extends BaseDispatcherPage {
    public final SidebarDispatcherComponent sidebar;
    public final ActionsBlockDispatcherComponent actionsBlock;
    public final NavProfileDispatcherTabProfileComponent profileTab;
    public final NavCommonDispatcherTabProfileComponent commonTab;
    public final NavContactsTabProfileComponent contactsTab;
    public final NavNotificationsTabProfileComponent notificationsTab;
    public final NavPasswordTabProfileComponent passwordTab;

    public ProfileDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        actionsBlock = new ActionsBlockDispatcherComponent(browser);
        profileTab = new NavProfileDispatcherTabProfileComponent(browser);
        commonTab = new NavCommonDispatcherTabProfileComponent(browser);
        contactsTab = new NavContactsTabProfileComponent(browser);
        notificationsTab = new NavNotificationsTabProfileComponent(browser);
        passwordTab = new NavPasswordTabProfileComponent(browser);
    }



}
