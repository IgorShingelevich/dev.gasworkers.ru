package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class TimeCounterComponent extends BaseComponent {
    public TimeCounterComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkActiveCounterState() {
        stepWithRole("Убедиться, что счетчик активен", () -> {
            checkFinishLoading();
            infoBoxActiveCounterTextLocator.shouldHave(text("Мастера, зарегистрированные на платформе, получили Ваш заказ и сейчас готовят свои предложения для Вас. После каждого нового предложения, счетчик обратного отсчёта обнуляется и Вы сможете выбрать подходящую Вам стоимость ремонта по Вашему заказу."));
            activeCounterNoOffersFakeButtonLocator.shouldBe(visible);
            //stopped elements should not be visible
            infoBoxStoppedCounterTitleTextLocator.shouldNotBe(visible);
            infoBoxStoppedCounterFirstTextLocator.shouldNotBe(visible);
            infoBoxStoppedCounterIOSQRCodeLocator.shouldNotBe(visible);
            infoBoxStoppedCounterAndroidQRCodeLocator.shouldNotBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldNotBe(visible);
            stoppedCounterWaitButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkStoppedCounterState() {
        stepWithRole("Убедиться, что счетчик остановлен", () -> {
            checkFinishLoading();
            infoBoxStoppedCounterTitleTextLocator.shouldHave(text("При готовности мастера или сервисной компании выполнить заказ Вам поступит уведомление."));
            infoBoxStoppedCounterFirstTextLocator.shouldHave(text("Удобнее ожидать предложения сервисных компаний и отслеживать заказ через наше мобильное приложение"));
            infoBoxStoppedCounterIOSQRCodeLocator.shouldBe(visible);
            infoBoxStoppedCounterAndroidQRCodeLocator.shouldBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldHave(text("Сервисные компании запросили еще время для расчета по вашему заказу. Среднее время отклика до 24 часов."));
            stoppedCounterWaitButtonLocator.shouldBe(visible);
            //active elements should not be visible
            activeCounterNoOffersFakeButtonLocator.shouldNotBe(visible);
            infoBoxActiveCounterTextLocator.shouldNotBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.border-light-blue").as("Компонент счетчика времени"),
            infoBoxLocator = self.$("div.gas-tip").as("Информационная панель"),
            countdownBlockLocator = self.$("div.countdown-wrap").as("Блок счетчика обратного отсчета"),
            activeCounterNoOffersFakeButtonLocator = self.$("div.btn-text").as("Кнопка нет предложений"),
            stoppedNeedMoreTimeTextLocator = self.$("div.w-auto").as("Текст о том, что нужно подождать"),
            stoppedCounterWaitButtonLocator = self.$(byTagAndText("span", "Подождать")).as("Кнопка подождать"),
            infoBoxActiveCounterTextLocator = infoBoxLocator.$("p").as("Текст активного счетчика"),
            infoBoxStoppedCounterTitleTextLocator = infoBoxLocator.$("p.title-text").as("Заголовок информационной панели при остановленном счетчике"),
            infoBoxStoppedCounterFirstTextLocator = infoBoxLocator.$("p.first-text").as("Описание информационной панели при остановленном счетчике"),
            infoBoxStoppedCounterIOSQRCodeLocator = infoBoxLocator.$("img[src='/images/qr-code-ios-custom.svg']").as("QR код для IOS"),
            infoBoxStoppedCounterAndroidQRCodeLocator = infoBoxLocator.$("img[src='/images/qr-code-android-custom.svg']").as("QR код для Android");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что счетчик отображается", () -> {
            self.shouldNotBe(visible);
            countdownBlockLocator.shouldBe(visible);
        });
    }




}
