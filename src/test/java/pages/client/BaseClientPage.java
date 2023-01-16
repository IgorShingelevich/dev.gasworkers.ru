package pages.client;

import model.browser.RoleBrowser;
import pages.BasePage;
import pages.components.sharedComponents.breadcrumbsComponent.ClientBreadcrumbsComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

public abstract class BaseClientPage extends BasePage {

    public BaseClientPage(RoleBrowser browser) {
        super(browser);
    }

    public ActionsBlockClientComponent actionBlockClient = new ActionsBlockClientComponent();
    public SidebarClientComponent sidebarClient = new SidebarClientComponent();
    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();

}
