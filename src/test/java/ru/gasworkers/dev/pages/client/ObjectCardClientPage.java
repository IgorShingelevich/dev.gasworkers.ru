package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent.DistributorTabObjectCardClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent.DocsTabObjectCardClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent.ObjectTabObjectCardClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ObjectCardClientPage extends BaseClientPage {

    public final DistributorTabObjectCardClientComponent tabDistributor;
    public final DocsTabObjectCardClientComponent tabDocs;
    public final ObjectTabObjectCardClientComponent tabObject;
    public final SidebarClientComponent sidebar;
    public final ActionsBlockClientComponent actionBlock;

    public ObjectCardClientPage(RoleBrowser browser) {
        super(browser);
        tabDistributor = new DistributorTabObjectCardClientComponent(browser);
        tabDocs = new DocsTabObjectCardClientComponent(browser);
        tabObject = new ObjectTabObjectCardClientComponent(browser);
        sidebar = new SidebarClientComponent(browser);
        actionBlock = new ActionsBlockClientComponent(browser);
    }

    private final String OBJECT_CARD_TITLE = "Объекты и оборудование";

    SelenideElement objectCardTitleLocator = driver.$(".page-title .h3.mb-2");

    ElementsCollection
        navTabsCollection = driver.$$("ul.nav-primary .nav-primary__link ").as("Вкладки страницы объекта");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница загрузилась", () -> {
            objectCardTitleLocator.shouldHave(text(OBJECT_CARD_TITLE));
            navTabsCollection.get(0).shouldHave(cssClass("is-active"));

        });
    }

    public void navObjectTab() {
        stepWithRole("Перейти на вкладку Объект", () -> {
            navTabsCollection.findBy(text("Объект")).click();
        });
    }

    public void navDistributorTab() {
        stepWithRole("Перейти на вкладку Филиал газораспределительной компании", () -> {
            navTabsCollection.findBy(text("Филиал газораспределительной компании")).click();
        });
    }

    public void navDocsTab() {
        stepWithRole("Перейти на вкладку Заказы и Документы", () -> {
            navTabsCollection.findBy(text("Заказы и Документы")).click();
        });
    }
}
