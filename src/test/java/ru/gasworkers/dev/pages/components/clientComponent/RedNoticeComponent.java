package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class RedNoticeComponent extends BaseComponent {
    public RedNoticeComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkInvoiceIssuedNotice() {
        stepWithRole("Убедиться, что появилось красное  уведомление о выставлении счета на оплату", () -> {
            textLocator.shouldHave(Condition.partialText("Вам выставлен счёт на оплату"));
        });
    }

    public void checkNeedSignActNotice() {
        stepWithRole("Убедиться, что появилось красное  уведомление о необходимости подписать акт", () -> {
            textLocator.shouldHave(Condition.partialText("Необходимо проставить подпись в Акте выполненных работ по заказу"));
        });
    }

    SelenideElement
            self = driver.$("div.gas-notice-danger").as("Красное уведомление в лк"),
            orderNumberLocator = self.$("div.justify-content-between").as("Номер заказа"),
            orderNumberTextLocator = orderNumberLocator.$("span").as("Номер заказа"),
            orderNumberLinkLocator = orderNumberLocator.$("a").as("Ссылка на номер заказа"),
            closeButtonLocator = orderNumberLocator.$("div.gas-notice-danger__text--close").as("Кнопка закрыть"),
            textLocator = self.$("p.gas-notice-danger__text--text").as("Текст уведомления"),
            buttonPrimaryLocator = self.$("button[data-test-id='primary']").as("Главная кнопка");

    public void noNotice() {
        stepWithRole("Убедиться, что красное уведомление не появилось", () -> {
            self.shouldNotBe(Condition.visible);
        });
    }

    public void checkOrderNumber(String orderNumber) {
        stepWithRole("Убедиться, что номер заказа: " + orderNumber + " соответствует номеру в красном уведомлении", () -> {
            orderNumberTextLocator.shouldHave(Condition.text(orderNumber));
        });
    }

    public void closeNotice() {
        stepWithRole("Закрыть красное уведомление", () -> {
            closeButtonLocator.click();
        });
    }

    public void mainButton() {
        stepWithRole("Нажать главную кнопку", () -> {
            buttonPrimaryLocator.click();
        });
    }

    public void checkFullFinishLoading() {
        stepWithRole("Убедиться, что загрузился компонент Красное уведомление", () -> {
            self.shouldBe(Condition.visible);
        });
    }

    public void checkCollapsedFinishLoading() {
        stepWithRole("Убедиться, что компонент свернутое Красное уведомление загрузился", () -> {
            self.$("div.bag-danger").shouldBe(Condition.visible);
        });
    }


}
