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

    /*public void scrollIntoButton(String button, boolean align) {
        stepWithRole("Скролл до кнопок", () -> {
            driver.$(byTagAndText("span", button)).scrollIntoView(align);
            Selenide.sleep(2000);
        });
    }*/

    public void scrollPxls(int pxls) {
        stepWithRole("Скролл на " + pxls + " пикселей", () -> {
            driver.executeJavaScript("window.scrollBy(0," + pxls + ")");
        });
    }

    public void open(String orderId) {
        stepWithRole("Открыть карточку заказа: " + orderId, () -> {
            driver.open("/profile/dispatcher/orders/" + orderId);
        });
    }


}