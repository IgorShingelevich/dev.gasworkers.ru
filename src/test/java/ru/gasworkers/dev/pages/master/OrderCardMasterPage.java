package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.client.OrderState;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;


    public  OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    ElementsCollection
            navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки"),
            docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов");


    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
        navDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
        navCheckListButtonLocator = navButtonsCollection.get(1).as("Чек лист"),
        navInfoButtonLocator = navButtonsCollection.get(2).as("Информация по работам"),
        navDocumentsButtonLocator = navButtonsCollection.get(3).as("Документы"),
        orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа"),
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
    public void navCommon(){
     stepWithRole("Перейти на вкладку Описание заказа", () -> {
        navButtonsCollection.get(0).shouldHave(text("Описание заказа")).click();
    });
    }
    public void navCheckList(){
        stepWithRole("Перейти на вкладку Чек лист", () -> {
            navButtonsCollection.get(1).shouldHave(text("Чек лист")).click();
        });
    }
    public void navInfo(){
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navButtonsCollection.get(2).shouldHave(text("Информация по работам")).click();
        });
    }
    public void navDocs(){
        stepWithRole("Перейти на вкладку Документы", () -> {
            navButtonsCollection.get(3).shouldHave(text("Документы")).click();
        });
    }


    public void checkOrderStateMasterDispatched(OrderState orderState) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderState, () ->
                    orderStateLocator.shouldHave(text(orderState.toString()))
            );
            stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " представлена кнопка Приступить к работе", () ->
                    startWorkingButtonLocator.shouldBe(visible)
            );
            stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " представлена кнопка  Редактировать объект/оборудование", () ->
                    editObjectButtonLocator.shouldBe(visible)
            );
            stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " отсутствуют нехарактерные для этого статуса кнопки", () -> {
                //TODO: add check for not present buttons
            });
            stepWithRole("Убедиться, что  в Карточке заказа отображаются документы: " + docsTitleCollection.get(0).getText() + " и " + docsTitleCollection.get(1).getText(), () -> {
                navDocs();
                docsTitleCollection.get(0).shouldHave(text("Договор ТО"));
                docsTitleCollection.get(1).shouldHave(text("Страховой полис"));
                navCommon();
            });
        });
    }
}
