package pages.dispatcher;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

public class AllMastersDispatcherPage extends BaseDispatcherPage {

    public final SidebarDispatcherComponent sidebar;
    public final ActionsBlockDispatcherComponent actionBlock;

    public AllMastersDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
    }





}
