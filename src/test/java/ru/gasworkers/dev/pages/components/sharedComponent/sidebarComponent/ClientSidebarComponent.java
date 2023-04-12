package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ClientSidebarComponent extends BaseSidebarComponent {
    public final SupportServiceSidebarComponent support;


    public ClientSidebarComponent(RoleBrowser browser) {
        super(browser);
        support = new SupportServiceSidebarComponent(browser);
    }

    SelenideElement homeLinkLocator = driver.$$(".sidebar-nav>li").get(0),
            objectsAndEquipmentLinkLocator = driver.$$(".sidebar-nav>li").get(1),
            ordersAndInvoicesDropdownLocator = driver.$$(".sidebar-nav>li").get(2),  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));
            ordersListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список заказов")),
            invoicesListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список счетов")),  //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));
            profileLinkLocator = driver.$$(".sidebar-nav>li").get(3); //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));



    public ClientSidebarComponent home() {
        stepWithRole("Перейти на домашнюю страницу", () -> {
            homeLinkLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client");
        });
        return this;
    }

    public ClientSidebarComponent allObjects() {
        stepWithRole("Перейти на страницу  Объекты и оборудование", () -> {
            objectsAndEquipmentLinkLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/equipment");
        });
        return this;
    }

    public ClientSidebarComponent allOrders() {
        stepWithRole("Перейти на страницу Список заказов", () -> {
            allOrdersAndInvoicesDropdown();
            ordersListLinkLocator.shouldBe(visible);
            ordersListLinkLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client/orders");
        });
        return this;
    }

    public ClientSidebarComponent allOrdersAndInvoicesDropdown() {
        stepWithRole("Открыть выпадающий список Заказы/Счета", () -> {
            ordersAndInvoicesDropdownLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client/orders");
        });
        return this;
    }

    public ClientSidebarComponent allInvoices() {
        stepWithRole("Перейти на страницу Список счетов", () -> {
            allOrdersAndInvoicesDropdown();
            invoicesListLinkLocator.shouldBe(visible);
            invoicesListLinkLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client/invoices");

        });
        return this;
    }

    public ClientSidebarComponent profile() {
        stepWithRole("Перейти на страницу Профиль", () -> {
            profileLinkLocator.click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/edit");
        });
        return this;
    }

}
