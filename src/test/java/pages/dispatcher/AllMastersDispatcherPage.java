package pages.dispatcher;

import model.browser.RoleBrowser;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;
import pages.dispatcher.BaseDispatcherPage;

public class AllMastersDispatcherPage extends BaseDispatcherPage {

    private final SidebarDispatcherComponent sidebarDispatcher;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public AllMastersDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new SidebarDispatcherComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }





}
