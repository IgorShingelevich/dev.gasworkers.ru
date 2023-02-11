package ru.gasworkers.dev.pages.common;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

public class ChatPage extends BasePage {
    public final SidebarClientComponent sidebarClient;
    public final SidebarMasterComponent sidebarMaster;
    public final SidebarDispatcherComponent sidebarDispatcher;
    public final ActionsBlockMasterComponent actionsBlockMaster;
    public final ActionsBlockClientComponent actionsBlockClient;
    public final ActionsBlockDispatcherComponent actionsBlockDispatcher;
    // TODO public final Breadctumbs for all roles
    public ChatPage(RoleBrowser browser) {
        super(browser);
        sidebarClient = new SidebarClientComponent(browser);
        sidebarMaster = new SidebarMasterComponent(browser);
        sidebarDispatcher = new SidebarDispatcherComponent(browser);
        actionsBlockMaster = new ActionsBlockMasterComponent(browser);
        actionsBlockClient = new ActionsBlockClientComponent(browser);
        actionsBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }

    SelenideElement
        pageTitleLocator = driver.$("div h1.h3.mb-2").as("Заголовок страницы");

}
