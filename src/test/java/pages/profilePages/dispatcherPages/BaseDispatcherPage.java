package pages.profilePages.dispatcherPages;

import pages.BasePage;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;

import static com.codeborne.selenide.Selenide.$;

public class BaseDispatcherPage extends BasePage {

    public ActionsBlockDispatcherComponent actionBlockDispatcher = new ActionsBlockDispatcherComponent();
    public  SidebarDispatcherComponent sidebarDispatcher = new SidebarDispatcherComponent();





}
