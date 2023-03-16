package ru.gasworkers.dev.pages.sharedPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.BasePage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.MasterSidebarComponent;

public class MessagePage extends BasePage {

    public final ClientSidebarComponent sidebarClient;
    public final MasterSidebarComponent sidebarMaster;
    public final DispatcherSidebarComponent sidebarDispatcher;
    public final ActionsBlockMasterComponent actionsBlockMaster;
    public final ClientActionsBlockComponent actionsBlockClient;
    public final ActionsBlockDispatcherComponent actionsBlockDispatcher;
    // TODO public final Breadctumbs for all roles
    public MessagePage(RoleBrowser browser) {
        super(browser);
        sidebarClient = new ClientSidebarComponent(browser);
        sidebarMaster = new MasterSidebarComponent(browser);
        sidebarDispatcher = new DispatcherSidebarComponent(browser);
        actionsBlockMaster = new ActionsBlockMasterComponent(browser);
        actionsBlockClient = new ClientActionsBlockComponent(browser);
        actionsBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }


    SelenideElement
        pageTitleLocator = driver.$("div h1.h3.mb-2").as("Заголовок страницы");
    ElementsCollection
        messageListCollection = driver.$$("div.messages-list div.item").as("Список сообщений"),
        messageListNameCollection =  driver.$$("div.messages-list div.item p.messages-list__name").as("Имя отправителя");

}
