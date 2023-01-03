package pages.profilePages.clientPages;

import pages.components.sharedComponents.breadcrumbsComponent.ClientBreadcrumbsComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

public class BaseClientPage {

    public ActionsBlockClientComponent actionBlock = new ActionsBlockClientComponent();
    public SidebarClientComponent sidebar = new SidebarClientComponent();
    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();


}
