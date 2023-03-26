package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.orderPageComponent.FillUpOfferBannerOrderPageSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.orderPageComponent.OfferPriceModalWindowSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.StatusBoxOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.DispatcherModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavCheckListTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavCommonTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavDocsTabOrderCardPageComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent.NavInfoMasterTabOrderCardPageComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class OrderCardSelfEmployedPage extends BaseSelfEmployedPage {

    public final HeaderSelfEmployedComponent header;
    public final DispatcherModeSelfEmployedSidebarComponent sidebar;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final FillUpOfferBannerOrderPageSelfEmployedComponent fillUpOfferPriceBanner;
    public final OfferPriceModalWindowSelfEmployedComponent offerPriceModalWindow;
    public final StatusBoxOrderCardComponent statusBox;

    public final NavCommonTabOrderCardPageComponent commonTab;
    public final NavCheckListTabOrderCardPageComponent tabCheckList;
    public final NavInfoMasterTabOrderCardPageComponent tabInfoMaster;
    public final NavDocsTabOrderCardPageComponent tabDocs;


    public OrderCardSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        sidebar = new DispatcherModeSelfEmployedSidebarComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        fillUpOfferPriceBanner = new FillUpOfferBannerOrderPageSelfEmployedComponent(browser);
        offerPriceModalWindow = new OfferPriceModalWindowSelfEmployedComponent(browser);
        statusBox = new StatusBoxOrderCardComponent(browser);
        commonTab = new NavCommonTabOrderCardPageComponent(browser);
        tabCheckList = new NavCheckListTabOrderCardPageComponent(browser);
        tabInfoMaster = new NavInfoMasterTabOrderCardPageComponent(browser);
        tabDocs = new NavDocsTabOrderCardPageComponent(browser);


    }

    private final String
            pageTitleText = "Заказ",
            offerPriceButtonText = "Предложить свою цену";

    ElementsCollection

            navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки");

    SelenideElement
            titleLocator = driver.$("div.page-title .h3").as("Заголовок страницы"),
            editObjectButtonLocator = driver.$(byTagAndText("span", "Редактировать объект/оборудование")).as("Редактировать объект/оборудование"),
            startWorkingButtonLocator = driver.$(byTagAndText("span", "Приступить к работе")).as("Приступить к работе"),
            offerPriceButtonLocator = driver.$("button[data-guide='order-pricing']").as("Предложить свою цену"),
            refuseOrderButtonLocator = driver.$(byTagAndText("span", "Отказаться")).as("Отказаться от заказа");

//    dispatcher SelenideElements

    SelenideElement
            pageTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок страницы"),
            orderDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
            orderInfoButtonLocator = navButtonsCollection.get(1).as("Информация по работам"),
            orderDocumentsButtonLocator = navButtonsCollection.get(2).as("Документы"),
            orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
            orderStatusLocator = driver.$(".item-flex p.text").as("Статус заказа"),
            mainButtonLocator = driver.$("button.btn.btn-primary").as("Основная кнопка"),
            acceptRequestButtonLocator = driver.$(byTagAndText("span", "Принять заказ")).as("Принять заказ"),
            declineRequestButtonLocator = driver.$(byTagAndText("span", "Отказаться от заказа")).as("Отказаться от заказа"),
            selectTimeButtonLocator = driver.$(byTagAndText("span", "Назначить время")).as("Назначить время"),
            selectAnotherTimeButtonLocator = driver.$(byTagAndText("span", "Назначить новое время")).as("Назначить новое время"),
            selectMasterButtonLocator = driver.$(byTagAndText("span", "Выбрать мастера")).as("Выбрать мастера"),
            selectAnotherMasterButtonLocator = driver.$(byTagAndText("span", "Назначить другого мастера")).as("Назначить другого мастера"),
            cancelButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ"),
            alreadyAcceptedButtonLocator = mainButtonLocator.$(byTagAndText("span", "Уже участвуете")).as("Уже участвуете"),
            cancelOrderLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ");


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

    public void offerPriceButton() {
        stepWithRole("Нажать на кнопку Предложить свою цену", () -> {
            offerPriceButtonLocator.click();
        });
    }

    public void refuseOrderButton() {
        stepWithRole("Нажать на кнопку Отказаться от заказа", () -> {
            refuseOrderButtonLocator.click();
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

    public void checkInitialState() {
        stepWithRole("Убедиться. что страница находится в состоянии послке регистрации", () -> {
            stepWithRole("Убедиться, что 3  вкладки отображаются", () -> {
                navButtonsCollection.shouldHave(size(3), Duration.ofSeconds(10));
            });
        });
        stepWithRole("Убедиться, что в карточке заказа отсутствует вкладка чеклист", () -> {
            navButtonsCollection.shouldHave(size(3));
            navButtonsCollection.get(2).shouldHave(text("Документы"));
        });
        stepWithRole("Убедиться, что отображается блок заказа", () -> {
            orderBlockLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться, что статуса заказа Тендер ", () -> {
            statusBox.newTenderState();
        });
        stepWithRole("Убедиться, что отображается кнопка Предложить свою цену", () -> {
            offerPriceButtonLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться, что отображается кнопка Отказаться", () -> {
            refuseOrderButtonLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться, что  кнопка Принять заказ и Отказаться от заказа отсутствует", () -> {
            acceptRequestButtonLocator.shouldNotBe(visible);
            declineRequestButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkNewTenderState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа Тендер ", () -> {
            stepWithRole("Убедиться что вкладок заказа - 3", () -> {
                navButtonsCollection.shouldHave(size(3));
            });
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа  представлена кнопка Принять Заказ и Отказаться ", () -> {
                    acceptRequestButtonLocator.scrollTo().shouldBe(visible);
                    declineRequestButtonLocator.shouldBe(visible);
                    alreadyAcceptedButtonLocator.shouldNotBe(visible);
                    stepWithRole("Убедиться, что  кнопка Предложить свою цену ", () -> {
                        offerPriceButtonLocator.shouldNotBe(visible);
                    });
                    stepWithRole("Убедиться, что  кнопка Отказаться от заказа отсутствует", () -> {
                        refuseOrderButtonLocator.shouldNotBe(visible);
                    });
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navButtonsCollection.get(2).shouldHave(text("Документы"));
                tabDocs.noDocs();
            });
            navCommon();
        });
        //TODO - check price, docs, buttons, info
        System.out.println("dispatcher orderStatus: " + orderStatus);
    }

    public void initialStateAcceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ: " + factualOrderNumber, () -> {
            acceptRequestButtonLocator.scrollTo().click();
        });
    }


}
