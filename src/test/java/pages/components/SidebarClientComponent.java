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

    public SidebarClientComponent visibilityMainPage() {
        mainPageLocator.shouldBe(visible);
        return this;
    }

    public SidebarClientComponent visibilityObjectsAndEquipmentLocator() {
        objectsAndEquipmentLocator.shouldBe(visible);
        return this;
    }

    public SidebarClientComponent clickOrdersAndInvoicesDropdownLocator() {
        ordersAndInvoicesDropdownLocator.shouldBe(visible);
        ordersAndInvoicesDropdownLocator.click();
        return this;
    }

    public SidebarClientComponent visibilityOrdersListLocator() {
        ordersListLocator.shouldBe(visible);
        return this;
    }

    public SidebarClientComponent visibilityInvoicesListLocator() {
        invoicesListLocator.shouldBe(visible);
        return this;
    }

    public SidebarClientComponent visibilityProfileLocator() {
        profileLocator.shouldBe(visible);
        return this;
    }




}
