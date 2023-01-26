package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
            pageTitleLocator.shouldHave(text(PAGE_TITLE));
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

}
