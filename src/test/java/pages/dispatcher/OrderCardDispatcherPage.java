package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import model.client.OrderStatus;
import pages.components.sharedComponents.sidebarComponents.SidebarDispatcherComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardDispatcherPage extends BaseDispatcherPage {

public final SidebarDispatcherComponent sidebar;

    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    SelenideElement
        pageTitleLocator = driver.$(".page-title .h3.mb-2"),
        orderBlockLocator = driver.$(".page-content #order"),
        orderNumberLocator = driver.$(".order-number"),
        orderStatusLocator = driver.$(".item-flex p.text"),
        primaryButtonLocator = driver.$(".btn.btn-primary"),

        selectTimeButtonLocator = driver.$(byTagAndText("button", "Назначить время")),
        selectAnotherTimeButtonLocator = driver.$(byTagAndText("button", "Назначить другое время")),
        selectMasterButtonLocator = driver.$(byTagAndText("button", "Выбрать мастера")),
        selectAnotherMasterButtonLocator = driver.$(byTagAndText("button", "Назначить другого мастера")),

        cancelButtonLocator = driver.$(byTagAndText("button", "Отменить заказ")),

        alreadyAcceptedButtonLocator = driver.$(".global-btn-wrapper.justify-content-end"),
        outlineButtonLocator = driver.$(".btn.btn-outline-primary");

    ElementsCollection
        navButtonsCollection = driver.$$("#navigation-block li");

    public void checkFinishLoading() {
        String orderCardNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            pageTitleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
            orderBlockLocator.shouldBe(visible);
            System.out.println("orderCardNumber: " + orderCardNumber);
        });


    }

    public OrderCardDispatcherPage acceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ:" + factualOrderNumber , () -> {
            primaryButtonLocator.as("Принять").scrollTo().click();
        });

        return this;
    }

    public void checkNewTenderStatus( OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что при участии в Тендере  представлена кнопка Прнять и кнопка Отказаться ", () -> {
//
                //TODO - check price, docs, buttons, info
            });
        });
        System.out.println("orderStatus + " + orderStatus);
    }

    public void checkAcceptedTenderStatus( OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что при участии в Тендере  представлена неактивная кнопка Уже участвуете ", () -> {
                alreadyAcceptedButtonLocator.should(appear, Duration.ofSeconds(40));
//                acceptButtonLocator.shouldBe(hidden);
//                        declineButtonLocator.shouldBe(hidden);
            });
            //TODO - check price, docs, buttons, info
        });
        System.out.println("orderStatus + " + orderStatus);
    }


    public void checkOrderStatusScheduleVisit(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () -> orderStatusLocator.shouldHave(text(orderStatus.toString())));
            });
            stepWithRole("Убедиться, что представлена кнопка Назначить время ", () -> {
            //TODO - check price, docs, buttons, info
        });
        System.out.println("orderStatus + " + orderStatus);
    }

    public OrderCardDispatcherPage selectMaster() {
          outlineButtonLocator.shouldHave(text("Отменить заказ")).shouldBe(visible, Duration.ofSeconds(10));
        primaryButtonLocator.shouldHave(text("Выбрать мастера")).shouldBe(visible, Duration.ofSeconds(10)).click();

        return this;
    }




    public OrderCardDispatcherPage declineOrder() {
        outlineButtonLocator.shouldBe(visible).shouldHave(text("Отказаться")).click();
        return this;
    }

    public OrderCardDispatcherPage orderDescription() {
        navButtonsCollection.get(0).click();
        return this;
    }

    public OrderCardDispatcherPage orderInfo() {
        navButtonsCollection.get(1).click();
        return this;
    }

    public OrderCardDispatcherPage orderDocuments() {
        navButtonsCollection.get(2).click();
        return this;
    }




}
