package pages.master;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.profileTabsComponent.NavContactsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NavEquipmentTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NavNotificationsTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.NavPasswordTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonMasterTabProfileComponent;
import pages.components.sharedComponent.profileTabsComponent.navProfileTab.NavProfileMasterTabProfileComponent;

public class ProfileMasterPage extends BaseMasterPage{
    public final NavProfileMasterTabProfileComponent profileTab;
    public final NavCommonMasterTabProfileComponent commonTab;
    public final NavContactsTabProfileComponent contactsTab;
    public final NavEquipmentTabProfileComponent equipmentTab;
    public final NavPasswordTabProfileComponent passwordTab;
    public final NavNotificationsTabProfileComponent notificationsTab;
    public ProfileMasterPage(RoleBrowser browser) {
        super(browser);
        profileTab = new NavProfileMasterTabProfileComponent(browser);
        commonTab = new NavCommonMasterTabProfileComponent(browser);
        contactsTab = new NavContactsTabProfileComponent(browser);
        equipmentTab = new NavEquipmentTabProfileComponent(browser);
        passwordTab = new NavPasswordTabProfileComponent(browser);
        notificationsTab = new NavNotificationsTabProfileComponent(browser);
    }

}
