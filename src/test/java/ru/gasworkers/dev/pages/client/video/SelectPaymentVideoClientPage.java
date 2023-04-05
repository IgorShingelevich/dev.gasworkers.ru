package ru.gasworkers.dev.pages.client.video;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        paymentMethodsCollection = driver.$$("div.logo-wrap").as("Способы оплаты"),
        feeDetailsCollection = driver.$$("p.small.text-secondary").as("Детали оплаты");


    SelenideElement
        titleLocator = driver.$("div p.h3").as("Заголовок страницы Выбор Способа Оплаты"),
    reserveTextLocator = driver.$("div.reserved-info").as("Текст о бронировании времени"),
    paymentMethodsSubtitleLocator = driver.$("div.payment-method p").as("Подзаголовок Способы оплаты"),
    payMirButtonLocator = paymentMethodsCollection.get(0).as("Кнопка оплаты через МИР"),
    paySpbButtonLocator = paymentMethodsCollection.get(1).as("Кнопка оплаты через Сбербанк");

    public void checkFinishLoading() {
        stepWithRole("Проверка загрузки страницы Выбор Способа Оплаты", () -> {
        stepper.checkFinishLoading();
//        header.checkFinishLoading(); // headed changed
            //todo: add check for header
        titleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SELECT_PAYMENT_TITLE));
        reserveTextLocator.shouldBe(visible).shouldHave(text(RESERVE_TEXT));
        paymentMethodsSubtitleLocator.shouldBe(visible).shouldHave(text(PAYMENT_METHODS_SUBTITLE));
        payMirButtonLocator.shouldBe(visible);
        paySpbButtonLocator.shouldBe(visible);
        });
    }

    public String getCommissionValue(Integer paymentMethodIndex) {
        String feeDetailsText = feeDetailsCollection.get(paymentMethodIndex).getText();
        Pattern pattern = Pattern.compile("(\\d+\\.\\d{2})");
        Matcher matcher = pattern.matcher(feeDetailsText);
        if (matcher.find()) {
            String price = matcher.group(1);
            return price;
        } else {
            return null;
        }
    }

    public void payMir () {
        stepWithRole("Оплата через МИР", () -> {
            stepWithRole("Комиссия банка: " + getCommissionValue(0), () -> {
            getCommissionValue(0);
            });
            payMirButtonLocator.click();
        });
    }

    public void paySpb () {
        stepWithRole("Оплата через СПБ", () -> {
            stepWithRole("Комиссия банка: " + getCommissionValue(1), () -> {
            getCommissionValue(1);
            });
            paySpbButtonLocator.click();
        });
    }


}
