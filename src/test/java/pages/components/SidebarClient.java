package pages.components;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SidebarClient {


    SelenideElement
            mainPageLocator = $$(".sidebar-nav>li").get(0),
            objectsAndEquipmentLocator = $$(".sidebar-nav>li").get(1), ordersAndInvoicesDropdownLocator = $$(".sidebar-nav>li").get(2),  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));
            ordersListLocator = $("ul.dropdown-menu").$(byText("Список заказов")),
            invoicesListLocator = $("ul.dropdown-menu").$(byText("Список счетов")),  //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));
            profileLocator = $$(".sidebar-nav>li").get(3); //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));

    public void clickMainPage() {
        mainPageLocator.click();
    }

    public void clickObjectsAndEquipmentLocator() {
        objectsAndEquipmentLocator.click();
    }

    public void clickOrdersAndInvoicesDropdownLocator() {
        ordersAndInvoicesDropdownLocator.click();
    }

    public void clickOrdersListLocator() {
        ordersListLocator.click();
    }

    public void clickInvoicesListLocator() {
        invoicesListLocator.click();
    }

    public void clickProfileLocator() {
        profileLocator.click();
    }

    public void click2MainPage() {
        mainPageLocator.click();
    }


}
