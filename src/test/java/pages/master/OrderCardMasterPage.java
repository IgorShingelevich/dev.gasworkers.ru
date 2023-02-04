package pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardMasterPage extends BaseMasterPage {

    private final SidebarMasterComponent sidebar;

    public  OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    ElementsCollection
            navButtonsCollection = driver.$$("div.navigation-block ul li");

    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
        navDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
        navCheckListButtonLocator = navButtonsCollection.get(1).as("Чек лист"),
        navInfoButtonLocator = navButtonsCollection.get(2).as("Информация по работам"),
        navDocumentsButtonLocator = navButtonsCollection.get(3).as("Документы"),
        editObjectButtonLocator = driver.$(byTagAndText("span", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
        startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе");

    public void checkFinishLoading() {
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("orderCardNumber: " + orderCardNumber);
        });

    }





}
