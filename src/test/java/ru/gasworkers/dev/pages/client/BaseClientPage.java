package ru.gasworkers.dev.pages.client;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.breadcrumbsComponent.ClientBreadcrumbsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

public abstract class BaseClientPage extends BasePage {

    private final SidebarClientComponent sidebarClient;
    private final ActionsBlockClientComponent actionBlockClient;
    private final ClientBreadcrumbsComponent breadcrumbsClient;




    public BaseClientPage(RoleBrowser browser) {
        super(browser);
        sidebarClient = new SidebarClientComponent(browser);
        actionBlockClient = new ActionsBlockClientComponent(browser);
        breadcrumbsClient = new ClientBreadcrumbsComponent(browser);
    }



}
