package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavCheckListTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarMasterComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;

public class OrderCardMasterPage extends BaseMasterPage {

    public final SidebarMasterComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;
    private final NavCommonTabOrderCardComponent commonTab;
    private final NavCheckListTabOrderCardComponent checkListTab;
    private final NavInfoMasterTabOrderCardComponent infoMasterTab;
    private final NavDocsTabOrderCardComponent docsTab;



    public  OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarMasterComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        commonTab = new NavCommonTabOrderCardComponent(browser);
        checkListTab = new NavCheckListTabOrderCardComponent(browser);
        infoMasterTab = new NavInfoMasterTabOrderCardComponent(browser);
        docsTab = new NavDocsTabOrderCardComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    ElementsCollection

        navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки"),
        orderDetailsCollection = driver.$$("div.order-details-item").as("Информация о заказе"),
        docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов");


    SelenideElement
        titleLocator = driver.$("h1.h3.mb-2").as("Заголовок страницы"),
        repairFromMaintenanceButtonLocator = driver.$(byTagAndText("span", "Заказ на ремонт")).as("Заказ на ремонт из ТО"),
        orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
        navDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
        navCheckListButtonLocator = navButtonsCollection.get(1).as("Чек лист"),
        navInfoButtonLocator = navButtonsCollection.get(2).as("Информация по работам"),
        navDocumentsButtonLocator = navButtonsCollection.get(3).as("Документы"), mainButtonLocator = driver.$("button.btn.btn-primary").as("Основная кнопка"),
        saveCheckListButtonLocator = mainButtonLocator.$(byTagAndText("span", "Сохранить")).as("Сохранить"),
        editObjectButtonLocator = driver.$(byTagAndText("span", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
        startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе");

    public void checkFinishLoading() {
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("master orderCardNumber: " + orderCardNumber);
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

    public void navInfoMaster(){
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navButtonsCollection.get(2).shouldHave(text("Информация по работам")).click();
        });
    }
    public void navDocs(){
        stepWithRole("Перейти на вкладку Документы", () -> {
            navButtonsCollection.get(3).shouldHave(text("Документы")).click();
        });
    }

    public void checkMasterDispatchedOrderState(OrderStatus orderStatus, OrderType orderType) {
        //TODO check current nav tab is navCommon
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.fillUpBanner.checkBannerDetails();
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Чек лист", () -> {
                commonTab.fillUpBanner.clickOnCheckListLink();
                checkListTab.checkFinishLoading(orderStatus);
                stepWithRole("Убедиться, представлена кнопки  Редактировать объект/оборудование, кнопка Кнопка Сохранить неактивная и кнопка Заказ на ремонт", () -> {
                    mainButtonLocator.shouldBe(disabled).shouldHave(text("Сохранить")).as("Кнопка Сохранить неактивная");
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                navInfoMaster();
                infoMasterTab.checkFinishLoading(orderStatus);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
                //TODO table, prices
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.checkFinishLoading(orderStatus);
                docsTab.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadInsurance();
                });
            });
            navCommon();
        });
    }

}