package ru.gasworkers.dev.pages.master;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavContactsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavEquipmentTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavNotificationsTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.NavPasswordTabProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon.NavCommonTabMasterProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile.NavProfileTabMasterProfilePageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.MasterSidebarComponent;

public class ProfileMasterPage extends BaseMasterPage{
    public final NavProfileTabMasterProfilePageComponent tabProfile;
    public final NavCommonTabMasterProfilePageComponent tabCommon;
    public final NavContactsTabProfilePageComponent tabContacts;
    public final NavEquipmentTabProfilePageComponent tabEquipment;
    public final NavPasswordTabProfilePageComponent tabPassword;
    public final NavNotificationsTabProfilePageComponent tabNotifications;
    public final MasterSidebarComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;

    public ProfileMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new MasterSidebarComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        tabProfile = new NavProfileTabMasterProfilePageComponent(browser);
        tabCommon = new NavCommonTabMasterProfilePageComponent(browser);
        tabContacts = new NavContactsTabProfilePageComponent(browser);
        tabEquipment = new NavEquipmentTabProfilePageComponent(browser);
        tabPassword = new NavPasswordTabProfilePageComponent(browser);
        tabNotifications = new NavNotificationsTabProfilePageComponent(browser);
    }

}
