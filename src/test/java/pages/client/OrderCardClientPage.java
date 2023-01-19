package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import model.client.OrderStatus;
import model.client.OrderType;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import java.time.Duration;

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
        toMapButtonLocator = driver.$(byTagAndText("button", "Показать на карте")),  // $("button.map-ic")
        cancelOrderButtonLocator = driver.$(byTagAndText("button", "Отменить заказ")),
        payBillButtonLocator = driver.$(byTagAndText("button", "Оплатить счет")),
        finalPriceLocator = driver.$(".big.bold.d-flex.justify-content-between.w-100.mb-4"),
        orderStatusLocator = driver.$(".item-flex p.text"),
        offersBlockLocator = driver.$("div.map-sticky__header--offers");

    ElementsCollection
        navigationBlockCollection = driver.$$("div.navigation-block li"),
        docsDownloadCollection = driver.$$(".link-pdf-wrap .ic"),
        orderDetailsCollection = driver.$$("div.order-details-item");

//    public String orderNumber = titleNumberLocator.getText().substring(LAST_ORDER_CARD_TITLE.length());  // .InvocationTargetException

    public OrderCardClientPage checkFinishLoading() {
        titleNumberLocator.shouldBe(visible, Duration.ofSeconds(30));
        String orderNumber = titleNumberLocator.getText().substring(LAST_ORDER_CARD_TITLE.length());
        stepWithRole("Убедиться, что Карточка Заказа: " + orderNumber + " загружена", () -> {
            titleNumberLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(LAST_ORDER_CARD_TITLE));
            orderDetailsBlockLocator.shouldBe(visible, Duration.ofSeconds(20));
            System.out.println("Order number: " + orderNumber);
        });
        return this;
    }

    public OrderCardClientPage commonNav(){
        navigationBlockCollection.get(0).click();
        return this;
    }

    public OrderCardClientPage masterNav(){
        navigationBlockCollection.get(1).click();
        return this;
    }

    public OrderCardClientPage docsNav(){
        navigationBlockCollection.get(2).click();
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
    }

    public void checkOrderStatus( OrderStatus orderStatus) {
        step("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            //how to make this step more flexible?
           stepWithRole("Убедиться, что статус заказа является: " + orderStatusLocator.getText(), () ->
                    orderStatusLocator.shouldHave(text(OrderStatus.NEW_ORDER.toString()))
           );
           stepWithRole("Убедиться, что в статусе: " + orderStatusLocator.getText() + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
               // how to avoid hardcode of button names?
               toMapButtonLocator.shouldBe(visible);
               cancelOrderButtonLocator.shouldBe(visible);
               orderStatusLocator.shouldHave(text(OrderStatus.NEW_ORDER.toString()));
           });
        });
    }

    public String getOrderNumber() {
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);
    }

    public  String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public OrderCardClientPage isCompleteState() {
        completeOrderInfoLocator.shouldBe(visible).shouldHave(text(COMPLETE_ORDER_INFO));
        orderStatusLocator.shouldBe(visible).shouldHave(text("Завершен"));
//        finalPriceLocator.shouldBe(visible);
        docsNav();
        docsDownloadCollection.get(0).shouldBe(visible);
        docsDownloadCollection.get(1).shouldBe(visible);
        docsDownloadCollection.get(2).shouldBe(visible);
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
        cancelOrderButtonLocator.scrollTo().click();
        return this;
    }
}
