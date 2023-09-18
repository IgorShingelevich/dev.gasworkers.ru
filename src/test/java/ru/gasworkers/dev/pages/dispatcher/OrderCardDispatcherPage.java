package ru.gasworkers.dev.pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.OffersCounterClientComponent;
import ru.gasworkers.dev.pages.components.dispatcherComponent.DatePickerOrderDispatcherComponent;
import ru.gasworkers.dev.pages.components.dispatcherComponent.OfferPriceModalDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.notificationsComponent.conferenceNotification.ConsultationNotificationSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.NavigationOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.CheckListTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.DocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common.CommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster.InfoMasterTabOrderCardClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardDispatcherPage extends BaseDispatcherPage {

    public final DispatcherSidebarComponent sidebar;
    public final ActionsBlockDispatcherComponent actionBlock;
    public final StepperComponent stepper;
    public final ConsultationNotificationSharedComponent consultationNotification;
    public final OffersCounterClientComponent offersCounter;
    public final DatePickerOrderDispatcherComponent datePicker;
    public final OfferPriceModalDispatcherComponent offerModal;
    public final NavigationOrderCardPageComponent nav;
    public final CommonTabOrderCardComponent commonTab;
    public final InfoMasterTabOrderCardClientComponent infoMasterTab;
    public final DocsTabOrderCardComponent docsTab;
    public final CheckListTabOrderCardComponent checkListTab;


    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new DispatcherSidebarComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
        consultationNotification = new ConsultationNotificationSharedComponent(browser);
        offersCounter = new OffersCounterClientComponent(browser);
        stepper = new StepperComponent(browser);
        datePicker = new DatePickerOrderDispatcherComponent(browser);
        nav = new NavigationOrderCardPageComponent(browser);
        commonTab = new CommonTabOrderCardComponent(browser);
        infoMasterTab = new InfoMasterTabOrderCardClientComponent(browser);
        checkListTab = new CheckListTabOrderCardComponent(browser);
        docsTab = new DocsTabOrderCardComponent(browser);
        offerModal = new OfferPriceModalDispatcherComponent(browser);
    }


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


    public void checkFinishLoading(String orderNumber) {
        stepWithRole("Убедиться, что Карточка Заказа: " + orderNumber + " загружена", () -> {
            pageTitleLocator.shouldHave(partialText(orderNumber));
        });
    }

    public void checkUrl(String orderNumber) {
        stepWithRole("Убедиться, что url соответствует номеру заказа: " + orderNumber, () -> {
            boolean endsWith = driver.url().endsWith(orderNumber);
        });
    }

    public void open(String orderId) {
        stepWithRole("Открыть карточку заказа: " + orderId, () -> {
            driver.open("/profile/dispatcher/orders/" + orderId);
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

    /*public void checkNewTenderState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
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
                tabDocs.noDocs();
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }*/

    /*public void acceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ: " + factualOrderNumber , () -> {
            acceptRequestButtonLocator.scrollTo().click();
            offerPrice.checkFinishLoading();
            offerPrice.saveButton();
            stepWithRole("Убедиться, что в Карточке заказа  представлена неактивная кнопка Уже участвуете ", () -> {

                alreadyAcceptedButtonLocator.shouldBe(visible, Duration.ofSeconds(10));
                acceptRequestButtonLocator.shouldNotBe(visible);
                declineRequestButtonLocator.shouldNotBe(visible);
            });
        });
    }*/

    /*public void checkParticipateTenderState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
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
                tabDocs.noDocs();
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }*/

   /* public void checkScheduleVisitState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
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
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " */
    /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*//* , () -> {
                    tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                    tabDocs.downloadAgreement();
                    tabDocs.downloadInsurance();
                });
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }*/

  /*  public void checkMasterDispatchedState(OrderStatus orderStatus, ServiceType serviceType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                tabCommon.status.checkCurrentStatus(orderStatus);
                tabCommon.details.checkServiceType(serviceType);
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
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " *//*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*//* , () -> {
                    tabDocs.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                });
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                    tabDocs.downloadAgreement();
                    tabDocs.downloadInsurance();
                });
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }*/

  /*  public OrderCardDispatcherPage selectMaster() {
        stepWithRole("Нажать на кнопку Выбрать мастера", () -> {
            selectMasterButtonLocator.click();
        });
        return this;
    }
    public void selectAnotherMaster() {
        stepWithRole("Нажать на кнопку Выбрать другого мастера", () -> {
            selectAnotherMasterButtonLocator.click();
        });
    }

    public void selectTimeButton() {
        stepWithRole("Нажать на кнопку Выбрать время", () -> {
            selectTimeButtonLocator.click();
        });
    }

    public void selectAnotherTime() {
        stepWithRole("Нажать на кнопку Выбрать новое время", () -> {
            selectAnotherTimeButtonLocator.click();
        });
    }

    public OrderCardDispatcherPage declineOrder() {
        declineRequestButtonLocator.click();
        return this;
    }*/


}