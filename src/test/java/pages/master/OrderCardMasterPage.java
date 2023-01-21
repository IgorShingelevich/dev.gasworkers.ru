package pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardMasterPage extends BaseMasterPage {

    private final SidebarMasterComponent sidebar;

    public  OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
    }

    ElementsCollection
            navButtonsCollection = driver.$$("div.navigation-block ul li");

    SelenideElement
       titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
       orderDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
        checkListButtonLocator = navButtonsCollection.get(1).as("Чек лист"),
        orderInfoButtonLocator = navButtonsCollection.get(2).as("Информация по работам"),
        orderDocumentsButtonLocator = navButtonsCollection.get(3).as("Документы"),
        editObjectButtonLocator = driver.$(byTagAndText("button", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
        startWorkingButtonLocator = driver.$(byTagAndText("button", "Приступить к работе")).as("Приступить к работе");





}
