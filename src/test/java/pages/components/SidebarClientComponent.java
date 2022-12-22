package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SidebarClientComponent {


    SelenideElement
            mainPageLocator = $$(".sidebar-nav>li").get(0),
            objectsAndEquipmentLocator = $$(".sidebar-nav>li").get(1), ordersAndInvoicesDropdownLocator = $$(".sidebar-nav>li").get(2),  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));
            ordersListLocator = $("ul.dropdown-menu").$(byText("Список заказов")),
            invoicesListLocator = $("ul.dropdown-menu").$(byText("Список счетов")),  //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));
            profileLocator = $$(".sidebar-nav>li").get(3); //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));

    public void clickMainPage() {
        mainPageLocator.shouldBe(visible);
    }

    public void clickObjectsAndEquipmentLocator() {
        objectsAndEquipmentLocator.shouldBe(visible);
        objectsAndEquipmentLocator.click();
    }

    public void clickOrdersAndInvoicesDropdownLocator() {
        ordersAndInvoicesDropdownLocator.shouldBe(visible);
        ordersAndInvoicesDropdownLocator.click();
    }

    public void clickOrdersListLocator() {
        ordersListLocator.shouldBe(visible);
    }

    public void clickInvoicesListLocator() {
        invoicesListLocator.shouldBe(visible);
    }

    public void clickProfileLocator() {
        profileLocator.shouldBe(visible);
    }

    public void click2MainPage() {
        mainPageLocator.shouldBe(visible);
    }


}
