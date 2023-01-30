package pages.client;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import pages.components.sharedComponent.profileTabsComponent.ContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.PasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.commonTab.CommonClientProfileComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

public class ProfileClientPage extends BaseClientPage{

    public final SidebarClientComponent sidebar;
    public final ActionsBlockClientComponent actionsBlock;
    public final CommonClientProfileComponent commonTab;
    public final ContactsTabProfileComponent contactsTab;
    public final PasswordTabProfileComponent passwordTab;
    public final NotificationsTabProfileComponent notificationsTab;

    public ProfileClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
        commonTab = new CommonClientProfileComponent(browser);
        contactsTab = new ContactsTabProfileComponent(browser);
        passwordTab = new PasswordTabProfileComponent(browser);
        notificationsTab = new NotificationsTabProfileComponent(browser);
    }





}
