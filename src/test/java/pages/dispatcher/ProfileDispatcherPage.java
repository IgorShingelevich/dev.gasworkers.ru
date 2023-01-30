package pages.dispatcher;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.profileTabsComponent.ContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.PasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.commonTab.CommonDispatcherProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.profileTab.ProfileDispatcherProfileComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;
import pages.dispatcher.BaseDispatcherPage;

public class ProfileDispatcherPage extends BaseDispatcherPage {
    public final SidebarDispatcherComponent sidebar;
    public final ActionsBlockDispatcherComponent actionsBlock;
    public final ProfileDispatcherProfileComponent profileTab;
    public final CommonDispatcherProfileComponent commonTab;
    public final ContactsTabProfileComponent contactsTab;
    public final NotificationsTabProfileComponent notificationsTab;
    public final PasswordTabProfileComponent passwordTab;

    public ProfileDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        actionsBlock = new ActionsBlockDispatcherComponent(browser);
        profileTab = new ProfileDispatcherProfileComponent(browser);
        commonTab = new CommonDispatcherProfileComponent(browser);
        contactsTab = new ContactsTabProfileComponent(browser);
        notificationsTab = new NotificationsTabProfileComponent(browser);
        passwordTab = new PasswordTabProfileComponent(browser);
    }



}
