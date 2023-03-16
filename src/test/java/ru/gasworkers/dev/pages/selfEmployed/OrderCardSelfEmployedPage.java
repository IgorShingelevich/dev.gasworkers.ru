package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.OfferPriceModalWindowSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.StatusBoxOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCheckListTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavInfoMasterTabOrderCardComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardSelfEmployedPage  extends BaseSelfEmployedPage{

    public final HeaderSelfEmployedComponent header;
    public final SelfEmployedSidebarComponent sidebar;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final OfferPriceModalWindowSelfEmployedComponent offerPriceModalWindow;
    public final StatusBoxOrderCardComponent statusBox;

    public final NavCommonTabOrderCardComponent commonTab;
    public final NavCheckListTabOrderCardComponent tabCheckList;
    public final NavInfoMasterTabOrderCardComponent tabInfoMaster;
    public final NavDocsTabOrderCardComponent tabDocs;



    public OrderCardSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        sidebar = new SelfEmployedSidebarComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        offerPriceModalWindow = new OfferPriceModalWindowSelfEmployedComponent(browser);
        statusBox = new StatusBoxOrderCardComponent(browser);
        commonTab = new NavCommonTabOrderCardComponent(browser);
        tabCheckList = new NavCheckListTabOrderCardComponent(browser);
        tabInfoMaster = new NavInfoMasterTabOrderCardComponent(browser);
        tabDocs = new NavDocsTabOrderCardComponent(browser);


    }

    private final String
            pageTitleText = "Заказ";

    SelenideElement
            titleLocator = driver.$("div.page-title .h3").as("Заголовок страницы"),
            orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
            editObjectButtonLocator = driver.$(byTagAndText("span", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
            startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе"),
            saveButtonLocator = driver.$(byTagAndText("span", "Сохранить")).as("Сохранить");

    ElementsCollection

            navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки");




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

    public void checkFinishLoading() {
        titleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(pageTitleText));
        String orderCardNumber = titleLocator.getText().substring(titleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("master orderCardNumber: " + orderCardNumber);
        });
    }

    public void checkInitialState(){
        stepWithRole("Убедиться. что страница находится в состоянии послке регистрации", () -> {
            stepWithRole("Убедиться, что 3  вкладки отображаются", () -> {
                navButtonsCollection.shouldHave(size(3));
            });
        });
        stepWithRole("Убедиться что в карточке заказа отсутствует вкладка чеклист", () -> {
            navButtonsCollection.shouldHave(size(3));
            navButtonsCollection.get(3).shouldHave(text("Документы"));
        });
        stepWithRole("Убедиться что отображается блок заказа", () -> {
            orderBlockLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться что отображается блок статуса заказа", () -> {
            statusBox.checkInitialState();
        });



    }



}
