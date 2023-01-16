package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import static com.codeborne.selenide.Condition.text;
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
    orderPageTitle = $(".page-title .h3.mb-2");


    ElementsCollection
        listNumberLinkCollection = $$("p.h5.link-blue.pointer"),
        dropdownActionButtonCollection = $$x("(//button[contains(@type,'button')])"),
        // div.actions  $$x("(//button[contains(@type,'button')])")
        openActionLinkCollection = $$x("(//a[contains(@class,'actions__slot--link')])");
        // div.col-lg-6.mb-30 $$x("(//a[contains(@class,'actions__slot--link')])")





    public AllOrdersClientPage openPage() {
        open("/profile/client/orders");
        isOpened();
        return this;
    }

    public AllOrdersClientPage isOpened() {
        orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
        return this;
    }


    public AllOrdersClientPage orderByNumber(int orderNumber) {
        //change int to String
        String orderNumberString = String.valueOf(orderNumber);
        listNumberLinkCollection.filter(text(orderNumberString)).first().click();
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
