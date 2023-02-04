package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.client.OrderStatus;
import ru.gasworkers.dev.model.client.OrderType;
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
    public OrderCardClientPage (RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarClientComponent(browser);
    }

    private final String
        LAST_ORDER_CARD_TITLE = "Заказ №",
        COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию";

    SelenideElement
        titleNumberLocator = driver.$("h1.h3.mb-2"),
        completeOrderInfoLocator = driver.$(".hint-box p"),
        orderDetailsBlockLocator = driver.$("div.order-details"),
        toMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")),  // $("button.map-ic")
        cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")),
        payBillButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")),
        finalPriceLocator = driver.$(".big.bold.d-flex.justify-content-between.w-100.mb-4"),
        orderStatusLocator = driver.$(".item-flex p.text"),
        offersBlockLocator = driver.$("div.map-sticky__header--offers");

    ElementsCollection
        navButtonsCollection = driver.$$("div.navigation-block li"),
        docsTitleCollection = driver.$$(".link-pdf "),
        docsDownloadCollection = driver.$$(".link-pdf span"),
        orderDetailsCollection = driver.$$("div.order-details-item");

//    public String orderNumber = titleNumberLocator.getText().substring(LAST_ORDER_CARD_TITLE.length());  // .InvocationTargetException

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
        navButtonsCollection.get(0).shouldHave(text("Описание заказа")).click();
        return this;
    }

    public OrderCardClientPage navInfoMaster(){
        navButtonsCollection.get(1).shouldHave(text("Информация по работам")).click();
        return this;
    }

    public OrderCardClientPage navDocs(){
        navButtonsCollection.get(2).shouldHave(text("Документы")).click();
//        driver.$( "div.navigation-block li:nth-child(3)" ).shouldHave(text("Документы")).click();
        return this;
    }

    public OrderCardClientPage docsAgreementDownload()  {
        docsDownloadCollection.get(1).$("a.link-pdf-download").attr("href");
        return this;
    }

    public OrderCardClientPage docsCompletionActDownload() {
        // no href
        return this;
    }

    public OrderCardClientPage docsInsuranceDownload() {
        // no href
        return this;
    }

    public void checkOrderType(OrderType orderType) {
        step("Убедиться, что тип заказа: " +orderType, () -> {
            orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
        });
        System.out.println("orderType: " + orderType);
    }

    public void checkOrderStatusNew(OrderStatus orderStatus) {
        step("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            //how to make this step more flexible?
           stepWithRole("Убедиться, что заказа статус заказа является: " + orderStatus, () ->
                    orderStatusLocator.shouldHave(text(orderStatus.toString()))
           );
           stepWithRole("Убедиться, что  в Карточке заказа: " + orderStatus + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
               // how to avoid hardcode of button names?
               toMapButtonLocator.shouldBe(visible);
               cancelOrderButtonLocator.shouldBe(visible);
               orderStatusLocator.shouldHave(text(orderStatus.toString()));
           });
            stepWithRole("Убедиться, что  в Карточке заказа документы отсутствуют  " , () -> {
                navDocs();
                docsTitleCollection.shouldBe(size(0));
                navCommon();
            });
            System.out.println("orderStatus: " + orderStatus);
        });
    }

    public void checkOrderStatusScheduleVisit(OrderStatus orderStatus) {
        step("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            //how to make this step more flexible?
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->
                    orderStatusLocator.shouldHave(text(orderStatus.toString()))
            );
            stepWithRole("Убедиться, что в Карточке заказа: " + orderStatus + " представлена кнопка Отменить заказ " , () -> {
                // how to avoid hardcode of button names?
                toMapButtonLocator.shouldNotBe(visible);
                cancelOrderButtonLocator.shouldBe(visible);
                orderStatusLocator.shouldHave(text(orderStatus.toString()));
            });
            stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*/ , () -> {
               navDocs();
               docsTitleCollection.get(0).shouldHave(text("Договор ТО"));
               docsTitleCollection.get(1).shouldHave(text("Страховой полис"));
               docsTitleCollection.shouldBe(size(2));
               navCommon();
            });
            System.out.println("orderStatus: " + orderStatus);
        });
    }

    public String getOrderNumber() {
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);
    }

    public  String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public OrderCardClientPage checkOrderStatusComplete() {
        stepWithRole("Убедиться, что статус заказа является: " + OrderStatus.COMPLETE, () -> {
            orderStatusLocator.shouldHave(text(OrderStatus.COMPLETE.toString()));
            completeOrderInfoLocator.shouldBe(visible).shouldHave(text(COMPLETE_ORDER_INFO));
            orderStatusLocator.shouldBe(visible).shouldHave(text("Завершен"));
//          finalPriceLocator.shouldBe(visible);
            navDocs();
            docsDownloadCollection.get(0).shouldBe(visible);
            docsDownloadCollection.get(1).shouldBe(visible);
            docsDownloadCollection.get(2).shouldBe(visible);
            docsDownloadCollection.shouldBe(size(3));
            navCommon();
        });

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
