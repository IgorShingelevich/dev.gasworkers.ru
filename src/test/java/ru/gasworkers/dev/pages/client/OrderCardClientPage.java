package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.Doc;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.OrderState;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardClientPage extends BaseClientPage {
    public final SidebarClientComponent sidebar;
    private final NavCommonTabOrderCardComponent navCommonTab;
    private final NavInfoMasterTabOrderCardComponent navInfoMasterTab;
    private final NavDocsTabOrderCardComponent navDocsTab;

    public OrderCardClientPage (RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
        navCommonTab = new NavCommonTabOrderCardComponent(browser);
        navInfoMasterTab = new NavInfoMasterTabOrderCardComponent(browser);
        navDocsTab = new NavDocsTabOrderCardComponent(browser);
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
        toHomeButtonLocator = driver.$("div button.btn.btn-primary span").as("Кнопка На главную"),
        offersBlockLocator = driver.$("div.map-sticky__header--offers").as("Блок с предложениями");

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

    public void checkNewOrderState(OrderState orderState, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться, что заказа статус заказа является: " + orderState, () -> {
                    navCommonTab.orderState.currentState(orderState);
                });
                step("Убедиться, что тип заказа: " +orderType, () -> {
                    orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
                });
                stepWithRole("Убедиться, что  в Карточке заказа: " + orderState + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
                    toMapButtonLocator.shouldBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
//TODO buttons for this order state

                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                stepWithRole("Убедиться, что  в Карточке заказа документы отсутствуют  " , () -> {
                    navDocs();
                    navDocsTab.noDocs();
                });
            });
            System.out.println("client orderType: " + orderType + "client orderState: " + orderState);
        });
    }

    public void checkScheduleVisitOrderState(OrderState orderState, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться, что заказа статус заказа является: " + orderState, () -> {
                    navCommonTab.orderState.currentState(orderState);
                });
                stepWithRole("Убедиться, что тип заказа: " + orderType, () -> {
                    orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
                });
                stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " представлена кнопка Отменить заказ ", () -> {
                    toMapButtonLocator.shouldNotBe(visible);
                    cancelOrderButtonLocator.shouldBe(visible);
                    submitAgreementButtonLocator.shouldNotBe(visible);
                    toHomeButtonLocator.shouldNotBe(visible);
//TODO buttons for this order state
                });
            });
            stepWithRole("Вкладка Информация по работам", () -> {
                //TODO: add steps for this tab
            });
            stepWithRole("Вкладка Документы", () -> {
                navDocs();
                stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*/ , () -> {
                 navDocsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole("TODO Скачать документы: Договор ТО и Страховой полис " , () -> {
                    //TODO get href from element
//                downloadAgreement();
//                downloadInsurance();
                });
            });
                navCommon();
            System.out.println("client orderType: " + orderType + "client orderState: " + orderState);
        });
    }

    public String getOrderNumber() {
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);
    }

    public  String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public OrderCardClientPage checkOrderStatusComplete(OrderState orderState, OrderType orderType) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Вкладка Описание заказа", () -> {
                stepWithRole("Убедиться что баннер с информацией о завершении заказа присутствует", () -> {
                    completeOrderInfoBannerLocator.shouldHave(text(COMPLETE_ORDER_INFO));
                });
                stepWithRole("Убедиться, что статус заказа  заказа является: " + orderState, () -> {
                    navCommonTab.orderState.currentState(orderState);
                });
                stepWithRole("Убедиться, что тип заказа: " +orderType, () -> {
                    orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
                });
                stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " представлена кнопка Передать договор с описанием и кнопка На главную", () -> {
                submitAgreementButtonLocator.shouldHave(text("Передать договор"));
                submitAgreementSubtitleLocator.shouldHave(text(SUBMIT_AGREEMENT_SUBTITLE));
                toHomeButtonLocator.shouldHave(text("На главную"));
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
                    navDocsTab.presentedDocs(Doc.valueOf("AGREEMENT"), Doc.valueOf("COMPLETION_ACT"), Doc.valueOf("INSURANCE"));
                });
                stepWithRole("TODO Скачать документы: Договор ТО и Страховой полис " , () -> {
                    //TODO get href from element
//                downloadAgreement();
//                downloadInsurance();
                });
            });
                navCommon();
    });
        System.out.println("client orderType: " + orderType + "client orderState: " + orderState);
        return this;
    }

    public OrderCardClientPage showOnMap() {
        stepWithRole("Нажать на кнопку Показать на карте", () -> {
            toMapButtonLocator.shouldBe(visible,Duration.ofSeconds(30)).click();  //.scrollTo()
        });
        return this;
    }

    public void clickOffersBlock() {
        stepWithRole("Нажать на блок Предложения", () -> {
            offersBlockLocator.shouldBe(visible, Duration.ofSeconds(30)).click();
        });
    }

    public OrderCardClientPage cancelOrder() {
        stepWithRole("Нажать на кнопку Отменить заказ", () -> {
            cancelOrderButtonLocator.shouldBe(visible, Duration.ofSeconds(30)).click();
        });
        return this;
    }
}
