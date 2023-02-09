package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.client.OrderState;
import ru.gasworkers.dev.model.client.OrderType;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavCommonTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavDocsTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent.NavInfoMasterTabOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarClientComponent;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
        COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию";

    SelenideElement
        titleNumberLocator = driver.$("h1.h3.mb-2").as(" Заголовок Карточки заказа"),
        completeOrderInfoLocator = driver.$(".hint-box p").as("Информация о завершении заказа"),
        orderDetailsBlockLocator = driver.$("div.order-details").as("Блок с информацией о заказе"),
        toMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")).as("Кнопка Показать на карте"),
        cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Кнопка Отменить заказ"),
        payBillButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")).as("Кнопка Оплатить счет"),
        finalPriceLocator = driver.$(".big.bold.d-flex.justify-content-between.w-100.mb-4").as("Итоговая цена"),
        orderStateLocator = driver.$(".item-flex p.text").as("Статус заказа"),
        offersBlockLocator = driver.$("div.map-sticky__header--offers").as("Блок с предложениями");

    ElementsCollection
        navButtonsCollection = driver.$$("div.navigation-block li").as("Навигационные кнопки"),
        docsTitleCollection = driver.$$("div .link-pdf ").as("Названия документов"),
        docsDownloadCollection = driver.$$(".link-pdf span").as("Кнопки скачать документы"),
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

    public OrderCardClientPage downloadAgreement()  throws Exception {
        File agreement = docsTitleCollection.findBy(text("Договор ТО")).download();
        InputStream is = new FileInputStream(agreement);
        return this;
    }

    public OrderCardClientPage downloadCompletionAct() throws Exception {
        File completionAct = docsTitleCollection.findBy(text("Акт выполненных работ")).download();
        InputStream is = new FileInputStream(completionAct);
        return this;
    }

    public OrderCardClientPage downloadInsurance() throws Exception {
        File insurance = docsTitleCollection.findBy(text("Страховой полис")).download();
        InputStream is = new FileInputStream(insurance);
        return this;
    }

    public void checkOrderType(OrderType orderType) {
        step("Убедиться, что тип заказа: " +orderType, () -> {
            orderDetailsCollection.findBy(text("Тип заказа")).shouldHave(text(orderType.toString()));
        });
        System.out.println("orderType: " + orderType);
    }

    public void checkOrderStateNew(OrderState orderState) {
        step("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            //how to make this step more flexible?
           stepWithRole("Убедиться, что заказа статус заказа является: " + orderState, () ->
                    orderStateLocator.shouldHave(text(orderState.toString()))
           );
           stepWithRole("Убедиться, что  в Карточке заказа: " + orderState + " представлены кнопки Показать на карте и Отменить заказ " , () -> {
               // how to avoid hardcode of button names?
               toMapButtonLocator.shouldBe(visible);
               cancelOrderButtonLocator.shouldBe(visible);
               orderStateLocator.shouldHave(text(orderState.toString()));
           });
            stepWithRole("Убедиться, что  в Карточке заказа документы отсутствуют  " , () -> {
                navDocs();
                docsTitleCollection.shouldBe(size(0));
                navCommon();
            });
            System.out.println("orderStatus: " + orderState);
        });
    }

    public void checkOrderStateScheduleVisit(OrderState orderState) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            //how to make this step more flexible?
            stepWithRole("Убедиться, что статус заказа является: " + orderState, () ->
                    orderStateLocator.shouldHave(text(orderState.toString()))
            );
            stepWithRole("Убедиться, что в Карточке заказа: " + orderState + " представлена кнопка Отменить заказ " , () -> {
                // how to avoid hardcode of button names?
                toMapButtonLocator.shouldNotBe(visible);
                cancelOrderButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что  в Карточке заказа в документах присутствует Договор ТО и Страховой полис " /*+ docsTitleCollection.get(0).getText() + docsTitleCollection.get(1).getText()*/ , () -> {
               navDocs();
               docsTitleCollection.get(0).shouldHave(text("Договор ТО"));
               docsTitleCollection.get(1).shouldHave(text("Страховой полис"));
               docsTitleCollection.shouldBe(size(2));
            });
            navCommon();

            //TODO get href from element
            stepWithRole("TODO Скачать документы: Договор ТО и Страховой полис " , () -> {
//                downloadAgreement();
//                downloadInsurance();
            });

            System.out.println("orderStatus: " + orderState);
        });
    }

    public String getOrderNumber() {
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);
    }

    public  String getTitleNumber() {
        return driver.$("h1.h3.mb-2").getText();
    }

    public OrderCardClientPage checkOrderStatusComplete() {
        stepWithRole("Убедиться, что статус заказа является: " + OrderState.COMPLETE, () -> {
            orderStateLocator.shouldHave(text(OrderState.COMPLETE.toString()));
            completeOrderInfoLocator.shouldBe(visible).shouldHave(text(COMPLETE_ORDER_INFO));
            orderStateLocator.shouldBe(visible).shouldHave(text("Завершен"));
//          finalPriceLocator.shouldBe(visible);
            navDocs();
            docsDownloadCollection.get(0).shouldHave(text("Договор ТО"));
            docsDownloadCollection.get(1).shouldHave(text("Акт выполненных работ"));
            docsDownloadCollection.get(2).shouldHave(text("Страховой полис"));
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
