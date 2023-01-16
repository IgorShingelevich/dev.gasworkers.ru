package pages.client;

import model.browser.RoleBrowser;
import pages.BasePage;
import pages.components.sharedComponents.breadcrumbsComponent.ClientBreadcrumbsComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

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
