package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ClientActionsBlockComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.ClientSidebarComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllInvoicesClientPage extends BaseClientPage {
    public final ClientActionsBlockComponent actionsBlock;
    public final ClientSidebarComponent sidebar;


    public AllInvoicesClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new ClientSidebarComponent(browser);
        actionsBlock = new ClientActionsBlockComponent(browser);
    }

    private final String
        PAGE_TITLE = "Список счетов";

    SelenideElement
        pageTitleLocator = driver.$("div h1").as("Page title");
    ElementsCollection
        invoicesCollection = driver.$$("div span a").as("Invoices");


    public void checkInitialState() {
        stepWithRole("Убедиться, что страница в  начальном состоянии", () -> {
            stepWithRole("Убедиться, что отсутствуют ранее созданные Счета", () -> {
                invoicesCollection.shouldHave(size(0));
            });
        });
    }

    public void openInvoiceByNumber(String invoiceNumber) {
        stepWithRole("Открыть счет с номером: " + invoiceNumber, () -> {
            invoicesCollection.findBy(text(invoiceNumber)).click();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Список счетов загрузилась", () -> {
            pageTitleLocator.shouldHave(text(PAGE_TITLE));
        });
    }

    public void checkItemsAmount(int ItemsAmount) {
        stepWithRole("Убедиться, что количество счетов равно: " + ItemsAmount, () -> {
            invoicesCollection.shouldHave(size(ItemsAmount));
        });
    }
}
