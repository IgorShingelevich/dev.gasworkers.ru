package pages.components.sharedComponents.sidebarComponents;

import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SidebarClientComponent extends BaseComponent {

    public SidebarClientComponent(RoleBrowser browser) {
        super(browser);
    }



    private final String SUPPORT_SERVICE_PHONE = "8 800 302 89 04";
    private final String SUPPORT_SERVICE_EMAIL = "info@gasworkers.ru";

    SelenideElement homeLinkLocator = driver.$$(".sidebar-nav>li").get(0),
            objectsAndEquipmentLinkLocator = driver.$$(".sidebar-nav>li").get(1),
            ordersAndInvoicesDropdownLocator = driver.$$(".sidebar-nav>li").get(2),  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));
            ordersListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список заказов")),
            invoicesListLinkLocator = driver.$("ul.dropdown-menu").$(byText("Список счетов")),  //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));
            profileLinkLocator = driver.$$(".sidebar-nav>li").get(3), //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));
            supportServiceTitleLocator = driver.$(".support-service"),
            supportServicePhoneLocator = driver.$(".support-service__phone"),
            supportServiceEmailLocator = driver.$(".support-service .link-dark-blue");


    public SidebarClientComponent clickOrdersAndInvoicesDropdown() {
        ordersAndInvoicesDropdownLocator.shouldBe(visible);
        ordersAndInvoicesDropdownLocator.click();
        return this;
    }

    public SidebarClientComponent home() {
        stepWithRole("Перейти на домашнюю страницу", () -> {
            homeLinkLocator.click();
        });
        return this;
    }

    public SidebarClientComponent allObjects() {
        objectsAndEquipmentLinkLocator.shouldBe(visible).click();
        return this;
    }

    public SidebarClientComponent allOrders() {
        clickOrdersAndInvoicesDropdown();
        ordersListLinkLocator.shouldBe(visible);
        ordersListLinkLocator.click();
        return this;
    }

    public SidebarClientComponent invoices() {
        clickOrdersAndInvoicesDropdown();
        invoicesListLinkLocator.shouldBe(visible);
        invoicesListLinkLocator.click();
        return this;
    }

    public SidebarClientComponent verifyLocators() {
        homeLinkLocator.shouldBe(visible);
        objectsAndEquipmentLinkLocator.shouldBe(visible);
        ordersListLinkLocator.shouldBe(hidden);
        invoicesListLinkLocator.shouldBe(hidden);
        ordersAndInvoicesDropdownLocator.shouldBe(visible);
        ordersAndInvoicesDropdownLocator.click();
        ordersListLinkLocator.shouldBe(visible);
        invoicesListLinkLocator.shouldBe(visible);
        ordersAndInvoicesDropdownLocator.click();
        profileLinkLocator.shouldBe(visible);
        supportServiceTitleLocator.shouldBe(visible);
        supportServicePhoneLocator.shouldBe(visible);
        supportServicePhoneLocator.shouldHave(text(SUPPORT_SERVICE_PHONE));
        supportServiceEmailLocator.shouldBe(visible);
        supportServiceEmailLocator.shouldHave(text(SUPPORT_SERVICE_EMAIL));
        return this;
    }






}
