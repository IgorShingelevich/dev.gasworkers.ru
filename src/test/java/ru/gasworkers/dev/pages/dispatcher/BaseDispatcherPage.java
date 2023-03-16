package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;


public class BaseDispatcherPage extends BasePage {

    private final DispatcherSidebarComponent sidebarDispatcher;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public BaseDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new DispatcherSidebarComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }




}
