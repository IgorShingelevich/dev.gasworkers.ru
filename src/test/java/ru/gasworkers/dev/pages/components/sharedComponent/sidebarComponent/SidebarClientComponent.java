package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SidebarClientComponent extends BaseSidebarComponent {

    public SidebarClientComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement homeLinkLocator = driver.$$(".sidebar-nav>li").get(0),
            objectsAndEquipmentLinkLocator = driver.$$(".sidebar-nav>li").get(1),
            ordersAndInvoicesDropdownLocator = driver.$$(".sidebar-nav>li").get(2),  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));
            ordersListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список заказов")),
            invoicesListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список счетов")),  //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));
            profileLinkLocator = driver.$$(".sidebar-nav>li").get(3); //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));



    public SidebarClientComponent allOrdersAndInvoicesDropdown() {
        stepWithRole("Открыть выпадающий список Заказы/Счета", () -> {
            ordersAndInvoicesDropdownLocator.click();
        });
        return this;
    }

    public SidebarClientComponent home() {
        stepWithRole("Перейти на домашнюю страницу", () -> {
            homeLinkLocator.click();
        });
        return this;
    }

    public SidebarClientComponent allObjects() {
        stepWithRole("Перейти на страницу  Объекты и оборудование", () -> {
            objectsAndEquipmentLinkLocator.click();
        });
        return this;
    }

    public SidebarClientComponent allOrders() {
        stepWithRole("Перейти на страницу Список заказов", () -> {
            allOrdersAndInvoicesDropdown();
            ordersListLinkLocator.shouldBe(visible);
            ordersListLinkLocator.click();
        });
        return this;
    }

    public SidebarClientComponent allInvoices() {
        stepWithRole("Перейти на страницу Список счетов", () -> {
            allOrdersAndInvoicesDropdown();
            invoicesListLinkLocator.shouldBe(visible);
            invoicesListLinkLocator.click();
        });
        return this;
    }

    public SidebarClientComponent profile() {
        stepWithRole("Перейти на страницу Профиль", () -> {
            profileLinkLocator.click();
        });
        return this;
    }

}
