package ru.gasworkers.dev.pages.client.video;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SelectPaymentVideoClientPage extends BaseClientPage {
    public final FocusHeaderComponent header;
    public final StepperComponent stepper;
    public SelectPaymentVideoClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
    }

    private final String
        SELECT_PAYMENT_TITLE = "Оплата видеоконсультации",
        RESERVE_TEXT = "Мы забронировали вам время консультации. Необходимо оплатить его в течении 5 минут",
        PAYMENT_METHODS_SUBTITLE = "Выберите способ оплаты:";

    ElementsCollection
        mediumsCollection = driver.$$("p.medium").as("Коллекция детали оплаты"),
        paymentMethodsCollection = driver.$$("div.logo-wrap").as("Способы оплаты");


    SelenideElement
        titleLocator = driver.$("div p.h3").as("Заголовок страницы Выбор Способа Оплаты"),
    reserveTextLocator = driver.$("div.reserved-info").as("Текст о бронировании времени"),
    paymentMethodsSubtitleLocator = driver.$("div.payment-method p").as("Подзаголовок Способы оплаты"),
    payMirButtonLocator = paymentMethodsCollection.get(0).as("Кнопка оплаты через МИР"),
    paySpbButtonLocator = paymentMethodsCollection.get(1).as("Кнопка оплаты через Сбербанк");

    public void checkFinishLoading() {
        stepWithRole("Проверка загрузки страницы Выбор Способа Оплаты", () -> {
        stepper.checkFinishLoading();
        header.checkFinishLoading();
        titleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SELECT_PAYMENT_TITLE));
        reserveTextLocator.shouldBe(visible).shouldHave(text(RESERVE_TEXT));
        paymentMethodsSubtitleLocator.shouldBe(visible).shouldHave(text(PAYMENT_METHODS_SUBTITLE));
        payMirButtonLocator.shouldBe(visible);
        paySpbButtonLocator.shouldBe(visible);
        });

    }

    public void payMir () {
        stepWithRole("Оплата через МИР", () -> {
            payMirButtonLocator.click();
        });
    }


}
