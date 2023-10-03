package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class CountdownComponent extends BaseComponent {

    public CountdownComponent(RoleBrowser browser) {
        super(browser);
    }


    public void checkState(StateRepair state, SuggestServicesResponseDto dto) {
        checkFinishLoading();
        switch (state) {
            case PUBLISHED:
                checkPublishedActiveState();
                break;
            case PUBLISHED_STOPPED_COUNTDOWN:
                checkPublishedStoppedState();
                break;
            case HAS_OFFER:
                checkHasOfferState(dto);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    public void checkPublishedActiveState() {
        stepWithRole("Убедиться, что счетчик активен", () -> {
            checkFinishLoading();
            offersCounterLocator.shouldHave(text("Нет предложений"));
//            infoBoxActiveCounterTextLocator.shouldHave(text("Мастера, зарегистрированные на платформе, получили Ваш заказ и сейчас готовят свои предложения для Вас. После каждого нового предложения, счетчик обратного отсчёта обнуляется и Вы сможете выбрать подходящую Вам стоимость ремонта по Вашему заказу."));
//            infoBoxStoppedCounterTitleTextLocator.shouldNotBe(visible);
//            infoBoxStoppedCounterFirstTextLocator.shouldNotBe(visible);
//            infoBoxStoppedCounterIOSQRCodeLocator.shouldNotBe(visible);
//            infoBoxStoppedCounterAndroidQRCodeLocator.shouldNotBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldNotBe(visible);
            stoppedCounterWaitButtonLocator.shouldNotBe(visible);
            hasOfferBellLocator.shouldNotBe(visible);
            hasOfferSelectButtonLocator.shouldNotBe(visible);
            masterCardLocator.shouldNotBe(visible);
        });
    }

    public void checkPublishedStoppedState() {
        stepWithRole("Убедиться, что счетчик остановлен", () -> {
            checkFinishLoading();
//            infoBoxStoppedCounterTitleTextLocator.shouldHave(text("При готовности мастера или сервисной компании выполнить заказ Вам поступит уведомление."));
//            infoBoxStoppedCounterFirstTextLocator.shouldHave(text("Удобнее ожидать предложения сервисных компаний и отслеживать заказ через наше мобильное приложение"));
//            infoBoxStoppedCounterIOSQRCodeLocator.shouldBe(visible);
//            infoBoxStoppedCounterAndroidQRCodeLocator.shouldBe(visible);
//            infoBoxActiveCounterTextLocator.shouldNotBe(visible);
            stoppedNeedMoreTimeTextLocator.shouldHave(text("Сервисные компании запросили еще время для расчета по вашему заказу. Среднее время отклика до 24 часов."));
            stoppedCounterWaitButtonLocator.shouldBe(visible);
            offersCounterLocator.shouldNotBe(visible);
            hasOfferBellLocator.shouldNotBe(visible);
            hasOfferSelectButtonLocator.shouldNotBe(visible);
            masterCardLocator.shouldNotBe(visible);
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что счетчик отображается", () -> {
            self.shouldBe(visible);
            countdownBlockLocator.shouldBe(visible);
        });
    }


    ElementsCollection
            filteredMasterCardRatingStarsCountLocator = driver.$("div.bg-light-secondary")
            .$$("div.gas-stars__star.active").as("Количество звезд рейтинга отфильтрованного мастера");

    public void checkHasOfferState(SuggestServicesResponseDto dto) {
        stepWithRole("Убедиться, что компонент счетчик в Состоянии Есть предложения", () -> {
            checkFinishLoading();
            hasOfferBellLocator.shouldBe(visible);
//            infoBoxStoppedCounterTitleTextLocator.shouldHave(text("Выберите готовое предложение по ремонту."));
//            infoBoxStoppedCounterFirstTextLocator.shouldHave(text("Не тратьте время на ожидание новых предложений, скачайте наше мобильное приложение и получайте в нём мгновенные уведомления."));
//            infoBoxStoppedCounterIOSQRCodeLocator.shouldBe(visible);
//            infoBoxStoppedCounterAndroidQRCodeLocator.shouldBe(visible);
//            infoBoxActiveCounterTextLocator.shouldNotBe(visible);
            masterCardLocator.shouldBe(visible);
            offersCounterLocator.shouldHave(partialText("У вас ")); //todo dto value
            stoppedNeedMoreTimeTextLocator.shouldNotBe(visible);
        });
    }

    public void checkMasterCardState(StateRepair stateRepair, SuggestServicesResponseDto dto) {
        stepWithRole("Проверить состояние карточки мастера", () -> {
            switch (stateRepair) {
                case PUBLISHED:
                case PUBLISHED_STOPPED_COUNTDOWN:
                    noMasterCard();
                    break;
                case HAS_OFFER:
                    /*filteredMasterCardFullName.shouldHave(text(dto.getMasterFullName()));
                    filteredMasterCardAvatar.shouldHave(cssValue("background-image", "url(\"" + dto.getMasterAvatar() + "\")"));
                    filteredMasterCardDeparturePriceLocator.shouldHave(text(dto.getDeparturePrice()));
                    filteredMasterCardEstimatedRepairPriceLocator.shouldHave(text(dto.getEstimatedRepairPrice()));
                    filteredMasterCardReviewCountLocator.shouldHave(text(dto.getReviewCount()));
                    filteredMasterCardFinishedOrdersCountLocator.shouldHave(text(dto.getFinishedOrdersCount()));
                    filteredMasterCardRatingStarsCountLocator.shouldHaveSize(dto.getRatingStarsCount());*/
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + stateRepair);
            }
        });
    }    SelenideElement
            self = driver.$("div.border-light-blue").as("Компонент счетчика времени"),
    //            infoBoxLocator = self.$("div.gas-tip").as("Информационная панель"),
    countdownBlockLocator = self.$("div.countdown-wrap").as("Блок счетчика обратного отсчета"),
            offersCounterLocator = self.$("div.btn-text").as("Счетчик предложений"),
            stoppedNeedMoreTimeTextLocator = self.$("div.w-auto").as("Текст о том, что нужно подождать"),
            stoppedCounterWaitButtonLocator = self.$(byTagAndText("span", "Подождать")).as("Кнопка подождать"),
    //            infoBoxActiveCounterTextLocator = infoBoxLocator.$("p").as("Текст активного счетчика"),
//            infoBoxStoppedCounterTitleTextLocator = infoBoxLocator.$("p.title-text").as("Заголовок информационной панели при остановленном счетчике"),
//            infoBoxStoppedCounterFirstTextLocator = infoBoxLocator.$("p.first-text").as("Описание информационной панели при остановленном счетчике"),
//            infoBoxStoppedCounterIOSQRCodeLocator = infoBoxLocator.$("img[src='/images/qr-code-ios-custom.svg']").as("QR код для IOS"),
//            infoBoxStoppedCounterAndroidQRCodeLocator = infoBoxLocator.$("img[src='/images/qr-code-android-custom.svg']").as("QR код для Android"),
    hasOfferBellLocator = self.$("img[title='колокольчик']").as("Колокольчик"),
            masterCardLocator = self.$("div.bg-light-secondary").as("Карточка отфильтрованного мастера"),
            filteredMasterCardFullName = masterCardLocator.$("p.medium.link-dark-blue.w-100.m-0.p-0").as("ФИО отфильтрованного мастера"),
            filteredMasterCardAvatar = masterCardLocator.$("div.ava").as("Аватар отфильтрованного мастера"),
            filteredMasterCardDeparturePriceLocator = masterCardLocator.$$("div.animated-bg.bag-success").get(0).as("Цена выезда отфильтрованного мастера"),
            filteredMasterCardEstimatedRepairPriceLocator = masterCardLocator.$$("div.animated-bg.bag-success").get(1).as("Цена ремонта отфильтрованного мастера"),
            filteredMasterCardReviewCountLocator = masterCardLocator.$("p b").as("Количество отзывов отфильтрованного мастера"),
            filteredMasterCardFinishedOrdersCountLocator = masterCardLocator.$("div.bag.small.ms-auto.px-3").as("Количество выполненных заказов отфильтрованного мастера"),
            hasOfferSelectButtonLocator = self.$(byTagAndText("span", "Выбрать")).as("Кнопка выбрать отфильтрованного мастера");





    private void noMasterCard() {
        stepWithRole("Проверить отсутствие карточки мастера", () -> {
            masterCardLocator.shouldNotBe(visible);
        });
    }


}
