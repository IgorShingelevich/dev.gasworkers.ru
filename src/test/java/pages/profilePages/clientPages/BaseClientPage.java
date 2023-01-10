package pages.profilePages.clientPages;

import pages.BasePage;
import pages.components.sharedComponents.breadcrumbsComponent.ClientBreadcrumbsComponent;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import static com.codeborne.selenide.Selenide.$;

public class BaseClientPage extends BasePage {

    public ActionsBlockClientComponent actionBlockClient = new ActionsBlockClientComponent();
    public SidebarClientComponent sidebarClient = new SidebarClientComponent();
    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();




}
