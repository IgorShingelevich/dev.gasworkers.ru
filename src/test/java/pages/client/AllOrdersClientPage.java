package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AllOrdersClientPage extends BaseClientPage {



    private final  SidebarClientComponent sidebar;
    public  AllOrdersClientPage (RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
    }

//    public ClientBreadcrumbsComponent breadcrumbs = new ClientBreadcrumbsComponent();


private final String ORDER_PAGE_TITLE = "Список заказов";

    SelenideElement
    orderPageTitle = driver.$(".page-title .h3.mb-2");

    ElementsCollection
        listNumberLinkCollection = driver.$$("p.h5.link-blue.pointer"),
        dropdownActionButtonCollection = driver.$$x("(//button[contains(@type,'button')])"),
        openActionLinkCollection = driver.$$x("(//a[contains(@class,'actions__slot--link')])");


    public AllOrdersClientPage checkFinishLoading() {
        orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
        listNumberLinkCollection.last().shouldBe(visible, Duration.ofSeconds(20));
        return this;
    }

    public AllOrdersClientPage orderByNumber(int orderNumber) {
        //change int to String
        String orderNumberString = String.valueOf(orderNumber);
        listNumberLinkCollection.filter(text(orderNumberString)).first().click(); //is it good way?
        return this;
    }

    public AllOrdersClientPage dropdownAction(int listNumber) {
        dropdownActionButtonCollection.get(listNumber+1).click();
        return this;
    }

    public AllOrdersClientPage openAction(int listNumber) {
        openActionLinkCollection.get(listNumber-1).click();
        return this;
    }

}
