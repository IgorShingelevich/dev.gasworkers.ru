package ru.gasworkers.dev.pages.client;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.breadcrumbsComponent.ClientBreadcrumbsComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;

public abstract class BaseClientPage extends BasePage {

    private final ClientSidebarComponent sidebarClient;
    private final ClientActionsBlockComponent actionBlockClient;
    private final ClientBreadcrumbsComponent breadcrumbsClient;




    public BaseClientPage(RoleBrowser browser) {
        super(browser);
        sidebarClient = new ClientSidebarComponent(browser);
        actionBlockClient = new ClientActionsBlockComponent(browser);
        breadcrumbsClient = new ClientBreadcrumbsComponent(browser);
    }



}
