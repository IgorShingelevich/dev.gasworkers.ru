package pages.master;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.profileTabsComponent.ContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.EquipmentTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.PasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.commonTab.CommonMasterProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.profileTab.ProfileMasterProfileComponent;

import java.io.ObjectOutputStream;

public class ProfileMasterPage extends BaseMasterPage{
    public final ProfileMasterProfileComponent profileTab;
    public final CommonMasterProfileComponent commonTab;
    public final ContactsTabProfileComponent contactsTab;
    public final EquipmentTabProfileComponent equipmentTab;
    public final PasswordTabProfileComponent passwordTab;
    public final NotificationsTabProfileComponent notificationsTab;
    public ProfileMasterPage(RoleBrowser browser) {
        super(browser);
        profileTab = new ProfileMasterProfileComponent(browser);
        commonTab = new CommonMasterProfileComponent(browser);
        contactsTab = new ContactsTabProfileComponent(browser);
        equipmentTab = new EquipmentTabProfileComponent(browser);
        passwordTab = new PasswordTabProfileComponent(browser);
        notificationsTab = new NotificationsTabProfileComponent(browser);
    }

}
