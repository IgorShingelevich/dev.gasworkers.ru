package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class sidebarClient {


    SelenideElement mainPageLocator = $$(".sidebar-nav>li").get(0);


    SelenideElement objectsAndEquipmentLocator =  $$(".sidebar-nav>li").get(1);

    SelenideElement ordersAndInvoicesDropdownLocator = $$(".sidebar-nav>li").get(2);  //$(By.xpath("//div[@class='link']")).$(byText("Заказы/Счета"));

    SelenideElement ordersListLocator  = $("ul.dropdown-menu").$(byText("Список заказов"));

    SelenideElement invoicesListLocator = $("ul.dropdown-menu").$(byText("Список счетов"));
    //$(By.xpath("//div[@class='link']")).$(byText("Список счетов"));

    SelenideElement profileLocator = $$(".sidebar-nav>li").get(3); //$(By.xpath("//div[@class='link']")).$(byText("Профиль"));

 // after 2 sec timeout click on each element from list
    public void   clickMainPage () {
        Selenide.sleep(2000);
        mainPageLocator.click();
    }
    public void clickObjectsAndEquipmentLocator () {
        Selenide.sleep(2000);
        objectsAndEquipmentLocator.click();
    }
    public void clickOrdersAndInvoicesDropdownLocator () {
        Selenide.sleep(2000);
        ordersAndInvoicesDropdownLocator.click();
    }
    public void clickOrdersListLocator () {
        Selenide.sleep(2000);
        ordersListLocator.click();
    }
    public void clickInvoicesListLocator () {
        Selenide.sleep(2000);
        invoicesListLocator.click();
    }
    public void clickProfileLocator () {
        Selenide.sleep(2000);
        profileLocator.click();
    }
    public void   click2MainPage () {
        Selenide.sleep(2000);
        mainPageLocator.click();
    }







}
