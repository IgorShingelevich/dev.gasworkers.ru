package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AllInvoicesClientPage extends BaseClientPage {
    public final ActionsBlockClientComponent actionsBlock;
    public final SidebarClientComponent sidebar;


    public AllInvoicesClientPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
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
}
