package pages.dispatcher;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;
import model.client.OrderStatus;
import pages.components.dispatcherComponent.DatePickerOrderDispatcherComponent;
import pages.components.sharedComponent.headerComponent.actionblockComponent.ActionsBlockDispatcherComponent;
import pages.components.sharedComponent.sidebarComponent.SidebarDispatcherComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class OrderCardDispatcherPage extends BaseDispatcherPage {

public final SidebarDispatcherComponent sidebar;
public final DatePickerOrderDispatcherComponent datePicker;
    private final ActionsBlockDispatcherComponent actionBlockDispatcher;

    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        datePicker = new DatePickerOrderDispatcherComponent(browser);
        actionBlockDispatcher = new ActionsBlockDispatcherComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    SelenideElement
        pageTitleLocator = driver.$(".page-title .h3.mb-2"),
        orderBlockLocator = driver.$(".page-content #order"),
        orderNumberLocator = driver.$(".order-number"),
        orderStatusLocator = driver.$(".item-flex p.text"),
        primaryButtonLocator = driver.$(".btn.btn-primary"),
        acceptRequestButtonLocator = driver.$(byTagAndText("button", "Принять заказ")),
        declineRequestButtonLocator = driver.$(byTagAndText("button", "Отказаться")),
        selectTimeButtonLocator = driver.$(byTagAndText("button", "Назначить время")),
        selectAnotherTimeButtonLocator = driver.$(byTagAndText("button", "Назначить новое время")),
        selectMasterButtonLocator = driver.$(byTagAndText("button", "Выбрать мастера")),
        selectAnotherMasterButtonLocator = driver.$(byTagAndText("button", "Назначить другого мастера")),

        cancelButtonLocator = driver.$(byTagAndText("button", "Отменить заказ")),

        alreadyAcceptedButtonLocator = driver.$(".global-btn-wrapper.justify-content-end"),
        cancelOrderLocator = driver.$(byTagAndText("button", "Отменить заказ")),
        outlineButtonLocator = driver.$(".btn.btn-outline-primary");

    ElementsCollection
        navButtonsCollection = driver.$$("#navigation-block li");

    public void checkFinishLoading() {
        pageTitleLocator.as("Заголовок страницы").shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
        String orderCardNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Убедиться, что Карточка Заказа: " + orderCardNumber + " загружена", () -> {
            //how to war p up the whole method in the stepWithRole?
            orderBlockLocator.shouldBe(visible);
            System.out.println("orderCardNumber: " + orderCardNumber);
        });


    }

    public OrderCardDispatcherPage acceptOrder() {
        String factualOrderNumber = pageTitleLocator.getText().substring(pageTitleLocator.getText().length() - 4);
        stepWithRole("Принять заказ: " + factualOrderNumber , () -> {
            acceptRequestButtonLocator.as("Принять").scrollTo().click();
        });

        return this;
    }

    public void checkReviewTheTenderStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.as("Статус заказа").shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что при рассмотрении Тендера  представлена кнопка Прнять и кнопка Отказаться ", () -> {
//
                //TODO - check price, docs, buttons, info
            });
        });
        System.out.println("orderStatus + " + orderStatus);
    }

    public void checkParticipateTheTenderStatus( OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.as("Статус заказа").shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что при участии в Тендере  представлена неактивная серая кнопка Уже участвуете ", () -> {
                alreadyAcceptedButtonLocator.as("Уже участвуете").should(appear, Duration.ofSeconds(40));
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

        stepWithRole("Убедиться, что представлена кнопка Назначить время и Отменить заказ ", () -> {
            selectTimeButtonLocator.shouldBe(visible);
            cancelButtonLocator.shouldBe(visible);
        });
        System.out.println("orderStatus + " + orderStatus);
            //TODO - check price, docs, buttons, info

    }

    public void checkMasterDispatchedStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () -> orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что представлена кнопка Назначить Другого Мастера и Назанчить Новое Время ", () -> {
                selectAnotherTimeButtonLocator.as("Назначить Новое Время").shouldBe(visible, Duration.ofSeconds(10));
                selectAnotherMasterButtonLocator.as("Назначить Другого Мастера").shouldBe(visible, Duration.ofSeconds(10));
                //TODO - check price, docs, buttons, info
            });
        });
        System.out.println("orderStatus: " + orderStatus);
    }

    public OrderCardDispatcherPage selectMaster() {
        stepWithRole("Нажать на кнопку Выбрать мастера", () -> {
            selectMasterButtonLocator.as("Выбрать мастера").click();
        });
        return this;
    }
    public OrderCardDispatcherPage selectAnotherMaster() {
        stepWithRole("Нажать на кнопку Выбрать другого мастера", () -> {
            selectAnotherMasterButtonLocator.as("Выбрать другого мастера").click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectTimeButton() {
        stepWithRole("Нажать на кнопку Выбрать время", () -> {
            selectTimeButtonLocator.as("Выбрать время").click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectAnotherTime() {
        stepWithRole("Нажать на кнопку Выбрать новое время", () -> {
            selectAnotherTimeButtonLocator.as("Выбрать новое время").click();
        });
        return this;
    }




    public OrderCardDispatcherPage declineOrder() {
        declineRequestButtonLocator.click();
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
