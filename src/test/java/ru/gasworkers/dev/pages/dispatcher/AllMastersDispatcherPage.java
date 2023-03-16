package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;

public class AllMastersDispatcherPage extends BaseDispatcherPage {

    public final DispatcherSidebarComponent sidebar;
    public final ActionsBlockDispatcherComponent actionBlock;

    public AllMastersDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new DispatcherSidebarComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
    }





}
