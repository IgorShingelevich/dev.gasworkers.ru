package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavContactsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavNotificationsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavPasswordTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon.NavCommonTabDispatcherProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile.NavProfileTabDispatcherProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;

public class ProfileDispatcherPage extends BaseDispatcherPage {
    public final DispatcherSidebarComponent sidebar;
    public final ActionsBlockDispatcherComponent actionsBlock;
    public final NavProfileTabDispatcherProfilePageComponent profileTab;
    public final NavCommonTabDispatcherProfilePageComponent commonTab;
    public final NavContactsTabProfilePageComponent contactsTab;
    public final NavNotificationsTabProfilePageComponent notificationsTab;
    public final NavPasswordTabProfilePageComponent passwordTab;

    public ProfileDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new DispatcherSidebarComponent(browser);
        actionsBlock = new ActionsBlockDispatcherComponent(browser);
        profileTab = new NavProfileTabDispatcherProfilePageComponent(browser);
        commonTab = new NavCommonTabDispatcherProfilePageComponent(browser);
        contactsTab = new NavContactsTabProfilePageComponent(browser);
        notificationsTab = new NavNotificationsTabProfilePageComponent(browser);
        passwordTab = new NavPasswordTabProfilePageComponent(browser);
    }



}
