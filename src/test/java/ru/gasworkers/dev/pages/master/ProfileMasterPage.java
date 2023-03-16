package ru.gasworkers.dev.pages.master;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavContactsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavEquipmentTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavNotificationsTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.NavPasswordTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonMasterTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navProfileTab.NavProfileMasterTabProfileComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.MasterSidebarComponent;

public class ProfileMasterPage extends BaseMasterPage{
    public final NavProfileMasterTabProfileComponent profileTab;
    public final NavCommonMasterTabProfileComponent commonTab;
    public final NavContactsTabProfileComponent contactsTab;
    public final NavEquipmentTabProfileComponent equipmentTab;
    public final NavPasswordTabProfileComponent passwordTab;
    public final NavNotificationsTabProfileComponent notificationsTab;
    public final MasterSidebarComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;

    public ProfileMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new MasterSidebarComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        profileTab = new NavProfileMasterTabProfileComponent(browser);
        commonTab = new NavCommonMasterTabProfileComponent(browser);
        contactsTab = new NavContactsTabProfileComponent(browser);
        equipmentTab = new NavEquipmentTabProfileComponent(browser);
        passwordTab = new NavPasswordTabProfileComponent(browser);
        notificationsTab = new NavNotificationsTabProfileComponent(browser);
    }

}
