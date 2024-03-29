package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.dispatcherComponent.DatePickerOrderDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardDispatcherPage extends BaseDispatcherPage {

    public final SidebarDispatcherComponent sidebar;
    public final DatePickerOrderDispatcherComponent datePicker;
    public final ActionsBlockDispatcherComponent actionBlock;
    public final NavCommonTabOrderCardComponent commonTab;
    public final NavInfoMasterTabOrderCardComponent infoMasterTab;
    public final NavDocsTabOrderCardComponent docsTab;

    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        datePicker = new DatePickerOrderDispatcherComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
        commonTab = new NavCommonTabOrderCardComponent(browser);
        infoMasterTab = new NavInfoMasterTabOrderCardComponent(browser);
        docsTab = new NavDocsTabOrderCardComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    ElementsCollection
        navButtonsCollection = driver.$$("div.navigation-block ul li");

    SelenideElement
        pageTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок страницы"),
        orderDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
        orderInfoButtonLocator = navButtonsCollection.get(1).as("Информация по работам"),
        orderDocumentsButtonLocator = navButtonsCollection.get(2).as("Документы"),
        orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
        orderStatusLocator = driver.$(".item-flex p.text").as("Статус заказа"),
        mainButtonLocator = driver.$("button.btn.btn-primary").as("Основная кнопка"),
        acceptRequestButtonLocator = driver.$(byTagAndText("span", "Принять заказ")).as("Принять заказ"),
        declineRequestButtonLocator = driver.$(byTagAndText("span", "Отказаться")).as("Отказаться"),
        selectTimeButtonLocator = driver.$(byTagAndText("span", "Назначить время")).as("Назначить время"),
        selectAnotherTimeButtonLocator = driver.$(byTagAndText("span", "Назначить новое время")).as("Назначить новое время"),
        selectMasterButtonLocator = driver.$(byTagAndText("span", "Выбрать мастера")).as("Выбрать мастера"),
        selectAnotherMasterButtonLocator = driver.$(byTagAndText("span", "Назначить другого мастера")).as("Назначить другого мастера"),
        cancelButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ"),
        alreadyAcceptedButtonLocator = mainButtonLocator.$(byTagAndText("span", "Уже участвуете")).as("Уже участвуете"),
        cancelOrderLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ");


    public void checkFinishLoading() {
        pageTitleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            orderBlockLocator.shouldBe(visible);
            System.out.println("dispatcher orderCardNumber: " + orderCardNumber);
        });
    }

    public void navCommon(){
        stepWithRole("Перейти на вкладку Описание заказа", () -> {
            navButtonsCollection.get(0).shouldHave(text("Описание заказа")).click();
        });
    }

    public void navInfoMaster(){
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navButtonsCollection.get(1).shouldHave(text("Информация по работам")).click();
        });
    }

    public void navDocs(){
        stepWithRole("Перейти на вкладку Документы", () -> {
            navButtonsCollection.get(2).shouldHave(text("Документы")).click();
        });
    }

    public void checkNewTenderState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа  представлена кнопка Принять Заказ и Отказаться ", () -> {
                    acceptRequestButtonLocator.scrollTo().shouldBe(visible);
                    declineRequestButtonLocator.shouldBe(visible);
                    alreadyAcceptedButtonLocator.shouldNotBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.noDocs();
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }

    public OrderCardDispatcherPage acceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ: " + factualOrderNumber , () -> {
            acceptRequestButtonLocator.scrollTo().click();
            stepWithRole("Убедиться, что в Карточке заказа  представлена неактивная кнопка Уже участвуете ", () -> {
                alreadyAcceptedButtonLocator.shouldBe(visible);
                acceptRequestButtonLocator.shouldNotBe(visible);
                declineRequestButtonLocator.shouldNotBe(visible);
            });
        });
        return this;
    }

    public void checkParticipateTenderState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа  представлена неактивная  кнопка Уже участвуете ", () -> {
                    alreadyAcceptedButtonLocator.shouldBe(visible);
                    acceptRequestButtonLocator.shouldNot(visible);
                    declineRequestButtonLocator.shouldNot(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.noDocs();
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }

    public void checkScheduleVisitState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что  в Карточке заказа представлена кнопка Назначить время и Отменить заказ ", () -> {
                    selectTimeButtonLocator.shouldBe(visible);
                    cancelButtonLocator.shouldBe(visible);
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*/ , () -> {
                    docsTab.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadInsurance();
                });
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }

    public void checkMasterDispatchedState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что  в Карточке заказа представлена кнопка Назначить Другого Мастера и Назанчить Новое Время ", () -> {
                    selectAnotherTimeButtonLocator.as("Назначить Новое Время").shouldBe(visible, Duration.ofSeconds(10));
                    selectAnotherMasterButtonLocator.as("Назначить Другого Мастера").shouldBe(visible, Duration.ofSeconds(10));
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*/ , () -> {
                    docsTab.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadInsurance();
                });
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }

    public OrderCardDispatcherPage selectMaster() {
        stepWithRole("Нажать на кнопку Выбрать мастера", () -> {
            selectMasterButtonLocator.click();
        });
        return this;
    }
    public OrderCardDispatcherPage selectAnotherMaster() {
        stepWithRole("Нажать на кнопку Выбрать другого мастера", () -> {
            selectAnotherMasterButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectTimeButton() {
        stepWithRole("Нажать на кнопку Выбрать время", () -> {
            selectTimeButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectAnotherTime() {
        stepWithRole("Нажать на кнопку Выбрать новое время", () -> {
            selectAnotherTimeButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage declineOrder() {
        declineRequestButtonLocator.click();
        return this;
    }

}