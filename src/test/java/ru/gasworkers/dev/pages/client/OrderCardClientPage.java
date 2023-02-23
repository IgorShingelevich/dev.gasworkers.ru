package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.pages.components.clientComponent.OffersClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardClientPage extends BaseClientPage {
    public final SidebarClientComponent sidebar;
    public final ActionsBlockClientComponent actionsBlock;
    public final StepperComponent stepper;
    public final OffersClientComponent offers;
    public final NavCommonTabOrderCardComponent commonTab;
    public final NavInfoMasterTabOrderCardComponent infoMasterTab;
    public final NavDocsTabOrderCardComponent docsTab;

    public OrderCardClientPage (RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        actionsBlock = new ActionsBlockClientComponent(browser);
        stepper = new StepperComponent(browser);
        offers = new OffersClientComponent(browser);
        commonTab = new NavCommonTabOrderCardComponent(browser);
        infoMasterTab = new NavInfoMasterTabOrderCardComponent(browser);
        docsTab = new NavDocsTabOrderCardComponent(browser);
    }

    private final String
        LAST_ORDER_CARD_TITLE = "Заказ №",
        COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию",
        SUBMIT_AGREEMENT_SUBTITLE = "Кнопка «передать договор» в газораспределительную компанию будет активна после размещения отзыва";

    SelenideElement
        titleNumberLocator = driver.$("h1.h3.mb-2").as(" Заголовок Карточки заказа"),
//        orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа"),
        completeOrderInfoBannerLocator = driver.$(".hint-box p").as("Баннер с информацией о завершении заказа"),
        submitAgreementSubtitleLocator = driver.$("div p.text-secondary").as("Пояснение кнопки Передать Договор"),
        orderDetailsBlockLocator = driver.$("div.order-details").as("Блок с информацией о заказе"),
        toMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")).as("Кнопка Показать на карте"),
        cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Кнопка Отменить заказ"),
        payBillButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")).as("Кнопка Оплатить счет"),
        signButtonLocator = driver.$(byTagAndText("span", "Подписать")).as("Кнопка Подписать"),
        submitAgreementButtonLocator = driver.$("div a.btn-link-custom").as("Кнопка Передать договор"),
        mainButtonLocator = driver.$("div button.btn.btn-primary").as("Главная кнопка"),
        submitReviewButtonLocator = mainButtonLocator.$(byTagAndText("span", "Оставить отзыв")).as("Кнопка Оставить отзыв");
    // TODO upd buttons to implement mainButtonLocator and be able to check enabled/disabled state

    ElementsCollection
        navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки"),
        orderDetailsCollection = driver.$$("div.order-details-item").as("Информация о заказе");


    public OrderCardClientPage checkFinishLoading() {
        titleNumberLocator.shouldBe(visible, Duration.ofSeconds(30));
        String orderNumber = titleNumberLocator.getText().substring(LAST_ORDER_CARD_TITLE.length());
        stepWithRole("Убедиться, что Карточка Заказа: " + orderNumber + " загружена", () -> {
            titleNumberLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(LAST_ORDER_CARD_TITLE));
            orderDetailsBlockLocator.shouldBe(visible, Duration.ofSeconds(20));
            System.out.println("orderNumber: " + orderNumber);
        });
        return this;
    }

    public OrderCardClientPage navCommon(){
        stepWithRole("Перейти на вкладку Описание заказа", () -> {
            navButtonsCollection.get(0).shouldHave(text("Описание заказа")).click();
        });
        return this;
    }

    public OrderCardClientPage navInfoMaster(){
        stepWithRole("Перейти на вкладку Информация по работам", () -> {
            navButtonsCollection.get(1).shouldHave(text("Информация по работам")).click();
        });
        return this;
    }

    public OrderCardClientPage navDocs(){
        stepWithRole("Перейти на вкладку Документы", () -> {
            navButtonsCollection.get(2).shouldHave(text("Документы")).click();
        });
        return this;
    }

    public void checkPublishedState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                offers.noOffers();
                stepWithRole("Убедиться, что  в Карточке заказа: " + orderStatus + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
                    toMapButtonLocator.shouldBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
//TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.noDocs();
            });
            System.out.println("client orderType: " + orderType + ", client orderStatus: " + orderStatus);
        });
    }

    public void checkReviewOffersState(OrderStatus orderStatus, OrderType orderType, Integer offersCount) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                offers.haveOffers(offersCount);
                stepWithRole("Убедиться, что  в Карточке заказа: " + orderStatus + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
                    toMapButtonLocator.shouldBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
//TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.noDocs();
            });
            System.out.println("client orderType: " + orderType + ", client orderStatus: " + orderStatus);
        });
    }

    public void checkScheduleVisitState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                    commonTab.orderStatus.currentStatus(orderStatus);
                    commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Отменить заказ ", () -> {
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
                    submitAgreementButtonLocator.shouldNotBe(visible);
                    mainButtonLocator.shouldNotBe(visible);
                    //TODO buttons for this order state
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                docsTab.checkFinishLoading(orderStatus);
                docsTab.presentedDocs(Doc.AGREEMENT, Doc.INSURANCE);
                stepWithRole("Скачать документы: Договор ТО и Страховой полис " , () -> {
                docsTab.downloadAgreement();
                docsTab.downloadInsurance();
                });
            });
            navCommon();
            System.out.println("client orderType: " + orderType + ", client orderStatus: " + orderStatus);
        });
    }

    public String getOrderNumber() {
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);
    }

    public  String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public OrderCardClientPage checkNotReviewedCompletedOrderState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться что баннер с информацией о завершении заказа присутствует", () -> {
                    completeOrderInfoBannerLocator.shouldHave(text(COMPLETE_ORDER_INFO));
                });
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Передать договор с описанием и кнопка На главную", () -> {
//                submitAgreementButtonLocator.shouldHave(text("Передать договор")).shouldHave(Condition.cssClass("disabled");
                submitAgreementButtonLocator.shouldHave(Condition.cssClass("disabled")).shouldHave(text("Передать договор"));
                submitAgreementSubtitleLocator.shouldHave(text(SUBMIT_AGREEMENT_SUBTITLE));
                submitReviewButtonLocator.shouldBe(visible);
//                toHomeButtonLocator.shouldHave(text("На главную")); - -after review
                toMapButtonLocator.shouldNotBe(visible);
                cancelOrderButtonLocator.shouldNotBe(visible);
                payBillButtonLocator.shouldNotBe(visible);
                signButtonLocator.shouldNotBe(visible);
                //TODO buttons for this order state
                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует: " + Doc.AGREEMENT + ",  " + Doc.COMPLETION_ACT + " и " + Doc.INSURANCE, () -> {
                    docsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("COMPLETION_ACT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole("Скачать документы: Договор ТО, Акт выполненных работ и Страховой полис ", () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadCompletionAct();
                    docsTab.downloadInsurance();
                });
            });
            navCommon();
    });
        System.out.println("client orderType: " + orderType + "client orderStatus: " + orderStatus);
        return this;
    }

    public OrderCardClientPage checkReviewedCompletedOrderState(OrderStatus orderStatus, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться что баннер с информацией о завершении заказа присутствует", () -> {
                    completeOrderInfoBannerLocator.shouldHave(text(COMPLETE_ORDER_INFO));
                });
                commonTab.orderStatus.currentStatus(orderStatus);
                commonTab.orderDetails.currentType(orderType);
                stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Передать договор с описанием и кнопка На главную", () -> {
                    submitAgreementButtonLocator.shouldBe(enabled).shouldHave(text("Передать договор"));
                    submitAgreementSubtitleLocator.shouldHave(text(SUBMIT_AGREEMENT_SUBTITLE));
                    submitReviewButtonLocator.shouldBe(visible);
                    mainButtonLocator.shouldHave(text("На главную")).as("На главную");
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldNotBe(visible);
                    payBillButtonLocator.shouldNotBe(visible);
                    signButtonLocator.shouldNotBe(visible);
                    //TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует: " + Doc.AGREEMENT + ",  " + Doc.COMPLETION_ACT + " и " + Doc.INSURANCE, () -> {
                    docsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("COMPLETION_ACT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole(" Скачать документы: Договор ТО, Акт выполненных работ и Страховой полис ", () -> {
                    docsTab.downloadAgreement();
                    docsTab.downloadCompletionAct();
                    docsTab.downloadInsurance();
                });
            });
            navCommon();
        });
        System.out.println("client orderType: " + orderType + "client orderStatus: " + orderStatus);
        return this;
    }

    public OrderCardClientPage showOnMap() {
        stepWithRole("Нажать на кнопку Показать на карте", () -> {
            toMapButtonLocator.shouldBe(visible,Duration.ofSeconds(30)).click();  //.scrollTo()
        });
        return this;
    }

    public OrderCardClientPage cancelOrder() {
        stepWithRole("Нажать на кнопку Отменить заказ", () -> {
            cancelOrderButtonLocator.shouldBe(visible, Duration.ofSeconds(30)).click();
        });
        return this;
    }
}