package ru.gasworkers.dev.pages.master;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.StatusBoxOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockMasterComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.MasterSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavCheckListTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavCommonTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavDocsTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavInfoMasterTabOrderCardPageComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardMasterPage extends BaseMasterPage {

    public final MasterSidebarComponent sidebar;
    public final ActionsBlockMasterComponent actionsBlock;
    public final StatusBoxOrderCardComponent statusBox;
    public final NavCommonTabOrderCardPageComponent commonTab;
    public final NavCheckListTabOrderCardPageComponent tabCheckList;
    public final NavInfoMasterTabOrderCardPageComponent tabInfoMaster;
    public final NavDocsTabOrderCardPageComponent tabDocs;
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
            startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе"),
            saveButtonLocator = driver.$(byTagAndText("span", "Сохранить")).as("Сохранить");


    public OrderCardMasterPage(RoleBrowser browser) {
        super(browser);
        sidebar = new MasterSidebarComponent(browser);
        actionsBlock = new ActionsBlockMasterComponent(browser);
        statusBox = new StatusBoxOrderCardComponent(browser);
        commonTab = new NavCommonTabOrderCardPageComponent(browser);
        tabCheckList = new NavCheckListTabOrderCardPageComponent(browser);
        tabInfoMaster = new NavInfoMasterTabOrderCardPageComponent(browser);
        tabDocs = new NavDocsTabOrderCardPageComponent(browser);
    }

    public void checkFinishLoading() {
        checkUrl();
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("master orderCardNumber: " + orderCardNumber);
        });
    }

    public void navCommon() {
        stepWithRole("Перейти на вкладку Описание заказа", () -> {
            navButtonsCollection.get(0).shouldHave(text("Описание заказа")).click();
        });
    }

    public void navCheckList() {
        stepWithRole("Перейти на вкладку Чек лист", () -> {
            navButtonsCollection.get(1).shouldHave(text("Чек лист")).click();
        });
    }

    public void navInfoMaster() {
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navButtonsCollection.get(2).shouldHave(text("Информация по работам")).click();
        });
    }

    public void navDocs() {
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
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isDisableRadioButtonsState();
                stepWithRole("Убедиться, представлена кнопки  Редактировать объект/оборудование, кнопка Кнопка Сохранить неактивная и кнопка Заказ на ремонт", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(disabled);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Приступить к работе и кнопка Заказ на ремонт", () -> {
                    startWorkingButtonLocator.shouldBe(visible);
                    editObjectButtonLocator.shouldBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис ", () -> {
                    tabDocs.downloadAgreement();
                    tabDocs.downloadInsurance();
                });
            });
            navCommon();
        });
    }

    public void startWork() {
        stepWithRole("Нажать кнопку Приступить к работе", () -> {
            startWorkingButtonLocator.shouldBe(visible).click();
        });
    }

    public void checkFillingCheckListState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться что открыта вкладка Чеклист", () -> {
                tabCheckList.checkFinishLoading(orderStatus);
            });
            stepWithRole("Убедиться что на всех вкладках карточки заказа отсутствует баннер Заполните чек-лист", () -> {
                tabCheckList.fillUpBanner.checkBannerIsNotPresent();
                navCommon();
                commonTab.fillUpBanner.checkBannerIsNotPresent();
                navInfoMaster();
                tabInfoMaster.fillUpBanner.checkBannerIsNotPresent();
                navDocs();
                tabDocs.fillUpBanner.checkBannerIsNotPresent();
            });
            stepWithRole("Вкладка Описание заказа", () -> {
                navCommon();
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что  представлены кнопки Приступить к работе, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);

                });
            });
            stepWithRole("Вкладка Чек лист", () -> {
                navCheckList();
                tabCheckList.checkFinishLoading(orderStatus);
                tabCheckList.checkListComponent.isEnableRadioButtonsState();
                stepWithRole("Убедиться, представлена кнопки  Редактировать объект/оборудование, кнопка Кнопка Сохранить активная и кнопка Заказ на ремонт", () -> {
                    saveButtonLocator.ancestor("button").shouldBe(enabled);
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                navInfoMaster();
                tabInfoMaster.checkFinishLoading(orderStatus);
                //TODO table, prices
                //todo check all the equipment of Client is present with numbers and description and mandatory positions are presented
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                tabDocs.checkFinishLoading(orderStatus);
                tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Убедиться, что  представлены кнопки  Редактировать объект/оборудование, кнопка Заказ на ремонт", () -> {
                    editObjectButtonLocator.shouldNotBe(visible);
                    repairFromMaintenanceButtonLocator.shouldBe(visible);
                    startWorkingButtonLocator.shouldBe(visible);
                });
            });
            navCommon();
        });
    }

    public void editObject() {
        stepWithRole("Нажать кнопку Редактировать объект/оборудование", () -> {
            editObjectButtonLocator.shouldBe(visible).click();
        });
    }

    public void saveCheckList() {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            saveButtonLocator.shouldBe(visible).click();
        });
    }


    public void checkUrl() {
        urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/master/orders/");
    }
}