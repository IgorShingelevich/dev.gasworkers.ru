package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class CountdownComponent extends BaseComponent {

    public CountdownComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkPublishedActiveState() {
        stepWithRole("Убедиться, что счетчик активен", () -> {
            checkFinishLoading();
            infoBoxActiveCounterTextLocator.shouldHave(text("Мастера, зарегистрированные на платформе, получили Ваш заказ и сейчас готовят свои предложения для Вас. После каждого нового предложения, счетчик обратного отсчёта обнуляется и Вы сможете выбрать подходящую Вам стоимость ремонта по Вашему заказу."));
            activeCounterNoOffersFakeButtonLocator.shouldBe(visible);
            infoBoxStoppedCounterTitleTextLocator.shouldNotBe(visible);
            infoBoxStoppedCounterFirstTextLocator.shouldNotBe(visible);
            infoBoxStoppedCounterIOSQRCodeLocator.shouldNotBe(visible);
            infoBoxStoppedCounterAndroidQRCodeLocator.shouldNotBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldNotBe(visible);
            stoppedCounterWaitButtonLocator.shouldNotBe(visible);
            hasOfferBellLocator.shouldNotBe(visible);
            hasOfferSelectButtonLocator.shouldNotBe(visible);
            hasOfferFilteredMasterCardLocator.shouldNotBe(visible);
        });
    }

    public void checkPublishedStoppedState() {
        stepWithRole("Убедиться, что счетчик остановлен", () -> {
            checkFinishLoading();
            infoBoxStoppedCounterTitleTextLocator.shouldHave(text("При готовности мастера или сервисной компании выполнить заказ Вам поступит уведомление."));
            infoBoxStoppedCounterFirstTextLocator.shouldHave(text("Удобнее ожидать предложения сервисных компаний и отслеживать заказ через наше мобильное приложение"));
            infoBoxStoppedCounterIOSQRCodeLocator.shouldBe(visible);
            infoBoxStoppedCounterAndroidQRCodeLocator.shouldBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldHave(text("Сервисные компании запросили еще время для расчета по вашему заказу. Среднее время отклика до 24 часов."));
            stoppedCounterWaitButtonLocator.shouldBe(visible);
            activeCounterNoOffersFakeButtonLocator.shouldNotBe(visible);
            infoBoxActiveCounterTextLocator.shouldNotBe(visible);
            hasOfferBellLocator.shouldNotBe(visible);
            hasOfferSelectButtonLocator.shouldNotBe(visible);
            hasOfferFilteredMasterCardLocator.shouldNotBe(visible);
        });
    }

    public void checkHasOfferState() {
        stepWithRole("Убедиться, что компонент счетчик в Состоянии Есть предложения", () -> {
            checkFinishLoading();
            hasOfferBellLocator.shouldBe(visible);
            infoBoxStoppedCounterTitleTextLocator.shouldHave(text("Выберите готовое предложение по ремонту."));
            infoBoxStoppedCounterFirstTextLocator.shouldHave(text("Не тратьте время на ожидание новых предложений, скачайте наше мобильное приложение и получайте в нём мгновенные уведомления."));
            infoBoxStoppedCounterIOSQRCodeLocator.shouldBe(visible);
            infoBoxStoppedCounterAndroidQRCodeLocator.shouldBe(visible);
            hasOfferFilteredMasterCardLocator.shouldBe(visible);
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что счетчик отображается", () -> {
            self.shouldBe(visible);
            countdownBlockLocator.shouldBe(visible);
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
            infoBoxStoppedCounterAndroidQRCodeLocator = infoBoxLocator.$("img[src='/images/qr-code-android-custom.svg']").as("QR код для Android"),
            hasOfferBellLocator = self.$("img[title='колокольчик']").as("Колокольчик"),
            hasOfferFilteredMasterCardLocator = self.$("div.bg-light-secondary").as("Карточка отфильтрованного мастера"),
            filteredMasterCardFullName = hasOfferFilteredMasterCardLocator.$("p.medium.link-dark-blue.w-100.m-0.p-0").as("ФИО отфильтрованного мастера"),
            filteredMasterCardAvatar = hasOfferFilteredMasterCardLocator.$("div.ava").as("Аватар отфильтрованного мастера"),
            filteredMasterCardDeparturePriceLocator = hasOfferFilteredMasterCardLocator.$$("div.animated-bg.bag-success").get(0).as("Цена выезда отфильтрованного мастера"),
            filteredMasterCardEstimatedRepairPriceLocator = hasOfferFilteredMasterCardLocator.$$("div.animated-bg.bag-success").get(1).as("Цена ремонта отфильтрованного мастера"),
            filteredMasterCardReviewCountLocator = hasOfferFilteredMasterCardLocator.$("p b").as("Количество отзывов отфильтрованного мастера"),
            filteredMasterCardFinishedOrdersCountLocator = hasOfferFilteredMasterCardLocator.$("div.bag.small.ms-auto.px-3").as("Количество выполненных заказов отфильтрованного мастера"),
            hasOfferSelectButtonLocator = self.$(byTagAndText("span", "Выбрать")).as("Кнопка выбрать отфильтрованного мастера");
    ElementsCollection
            filteredMasterCardRatingStarsCountLocator = hasOfferFilteredMasterCardLocator.$$("div.gas-stars__star.active").as("Количество звезд рейтинга отфильтрованного мастера");

}
