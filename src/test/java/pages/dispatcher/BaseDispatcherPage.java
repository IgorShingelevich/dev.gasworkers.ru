package pages.dispatcher;

import model.browser.RoleBrowser;
import pages.BasePage;
import pages.components.sharedComponents.headerComponents.actionblockComponents.ActionsBlockDispatcherComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;


public class BaseDispatcherPage extends BasePage {

    private final SidebarDispatcherComponent sidebarDispatcher;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public BaseDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new SidebarDispatcherComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }




}
