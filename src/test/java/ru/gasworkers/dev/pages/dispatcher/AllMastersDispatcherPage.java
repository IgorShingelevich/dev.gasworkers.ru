package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

public class AllMastersDispatcherPage extends BaseDispatcherPage {

    public final SidebarDispatcherComponent sidebar;
    public final ActionsBlockDispatcherComponent actionBlock;

    public AllMastersDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
    }





}
