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
    private final ActionsBlockDispatcherComponent actionBlock;

    public OrderCardDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebar = new SidebarDispatcherComponent(browser);
        datePicker = new DatePickerOrderDispatcherComponent(browser);
        actionBlock = new ActionsBlockDispatcherComponent(browser);
    }

    private final String PAGE_TITLE = "Заказ";

    ElementsCollection
            navButtonsCollection = driver.$$("div.navigation-block ul li");

    SelenideElement
    pageTitleLocator = driver.$(".page-title .h3.mb-2").as("Заголовок страницы"),
    orderDescriptionButtonLocator = navButtonsCollection.get(0).as("Описание заказа"),
    orderInfoButtonLocator = navButtonsCollection.get(1).as("Информация по работам"),
    orderDocumentsButtonLocator = navButtonsCollection.get(2).as("Документы"),
    orderBlockLocator = driver.$(".page-content #order").as("Блок заказа"),
    orderStatusLocator = driver.$(".item-flex p.text").as("Статус заказа"),
    acceptRequestButtonLocator = driver.$(byTagAndText("span", "Принять заказ")).as("Принять заказ"),
    declineRequestButtonLocator = driver.$(byTagAndText("span", "Отказаться")).as("Отказаться"),
    selectTimeButtonLocator = driver.$(byTagAndText("span", "Назначить время")).as("Назначить время"),
    selectAnotherTimeButtonLocator = driver.$(byTagAndText("span", "Назначить новое время")).as("Назначить новое время"),
    selectMasterButtonLocator = driver.$(byTagAndText("span", "Выбрать мастера")).as("Выбрать мастера"),
    selectAnotherMasterButtonLocator = driver.$(byTagAndText("span", "Назначить другого мастера")).as("Назначить другого мастера"),
    cancelButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ"),
    alreadyAcceptedButtonLocator = driver.$(".global-btn-wrapper.justify-content-end").as("Уже принят'"),
    cancelOrderLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Отменить заказ");


    public void checkFinishLoading() {
        pageTitleLocator.shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text(PAGE_TITLE));
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
            acceptRequestButtonLocator.scrollTo().click();
        });

        return this;
    }

    public void checkReviewNewTheTenderStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что в Карточке заказа  представлена кнопка Принять Заказ и Отказаться ", () -> {
                alreadyAcceptedButtonLocator.as("Уже участвуете").should(appear, Duration.ofSeconds(40));
                acceptRequestButtonLocator.scrollTo()
                        .shouldBe(visible);
                declineRequestButtonLocator.shouldBe(visible);
//                alreadyAcceptedButtonLocator.shouldNot(visible, Duration.ofSeconds(10)); // fall
            });
            //TODO - check price, docs, buttons, info
        });
        System.out.println("orderStatus: " + orderStatus);
    }

    public void checkParticipateTheTenderStatus( OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () ->

                    orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что в Карточке заказа  представлена неактивная серая кнопка Уже участвуете ", () -> {
                alreadyAcceptedButtonLocator.as("Уже участвуете").should(appear, Duration.ofSeconds(40));
                acceptRequestButtonLocator.shouldNot(visible);
                declineRequestButtonLocator.shouldNot(visible);
            });
            //TODO - check price, docs, buttons, info
        });
        System.out.println("orderStatus: " + orderStatus);
    }


    public void checkOrderStatusScheduleVisit(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () -> orderStatusLocator.shouldHave(text(orderStatus.toString())));
        });

        stepWithRole("Убедиться, что  в Карточке заказа представлена кнопка Назначить время и Отменить заказ ", () -> {
            selectTimeButtonLocator.shouldBe(visible);
            cancelButtonLocator.shouldBe(visible);
        });
        System.out.println("orderStatus: " + orderStatus);
            //TODO - check price, docs, buttons, info

    }

    public void checkMasterDispatchedStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа соответствует его Признакам ", () -> {
            stepWithRole("Убедиться, что статус заказа является: " + orderStatus, () -> orderStatusLocator.shouldHave(text(orderStatus.toString())));
            stepWithRole("Убедиться, что  в Карточке заказа представлена кнопка Назначить Другого Мастера и Назанчить Новое Время ", () -> {
                selectAnotherTimeButtonLocator.as("Назначить Новое Время").shouldBe(visible, Duration.ofSeconds(10));
                selectAnotherMasterButtonLocator.as("Назначить Другого Мастера").shouldBe(visible, Duration.ofSeconds(10));
                //TODO - check price, docs, buttons, info
            });
        });
        System.out.println("orderStatus: " + orderStatus);
    }

    public OrderCardDispatcherPage selectMaster() {
        stepWithRole("Нажать на кнопку Выбрать мастера", () -> {
            selectMasterButtonLocator.click();
        });
        return this;
    }
    public OrderCardDispatcherPage selectAnotherMaster() {
        stepWithRole("Нажать на кнопку Выбрать другого мастера", () -> {
            selectAnotherMasterButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectTimeButton() {
        stepWithRole("Нажать на кнопку Выбрать время", () -> {
            selectTimeButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage selectAnotherTime() {
        stepWithRole("Нажать на кнопку Выбрать новое время", () -> {
            selectAnotherTimeButtonLocator.click();
        });
        return this;
    }

    public OrderCardDispatcherPage declineOrder() {
        declineRequestButtonLocator.click();
        return this;
    }

    public OrderCardDispatcherPage navOrderDescription() {
        stepWithRole("Нажать на кнопку Описание заказа", () -> {
            orderDescriptionButtonLocator.shouldHave(text("Описание заказа")).click();
        });
        return this;
    }

    public OrderCardDispatcherPage navOrderInfo() {
        stepWithRole("Нажать на кнопку Информация по работам", () -> {
            orderInfoButtonLocator.shouldHave(text("Информация по работам")).click();
        });
        return this;
    }

    public OrderCardDispatcherPage navOrderDocuments() {
        stepWithRole("Нажать на кнопку Документы", () -> {
            orderDocumentsButtonLocator.shouldHave(text("Документы")).click();
        });
        return this;
    }

}
