package pages.dispatcher;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.profileTabsComponent.NavContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NavNotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NavPasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonDispatcherTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.navProfileTab.NavProfileDispatcherTabProfileComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

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
