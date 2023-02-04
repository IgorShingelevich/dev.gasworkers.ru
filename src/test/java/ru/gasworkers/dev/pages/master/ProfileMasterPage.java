package ru.gasworkers.dev.pages.master;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavContactsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavEquipmentTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavNotificationsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavPasswordTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonMasterTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navProfileTab.NavProfileMasterTabProfileComponent;

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
