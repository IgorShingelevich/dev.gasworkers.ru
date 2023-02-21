package ru.gasworkers.dev.pages.components.clientComponent.guideComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FirstRepairGuideComponent extends BaseComponent {
    public FirstRepairGuideComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        GUIDE_Step_1 = "Ваш заказ отправлен в сервисные компании, когда они примут его и сделают вам предложение, вы будете получать уведомления на сайте и письма по электронной почте об этом.",
        GUIDE_Step_2 = "Здесь вы будете получать уведомления. Посмотрите сейчас ваше первое уведомление что ваш заказ размещен на платформе",
        GUIDE_Step_3 = "Тут будут показано количество откликов. Если нажмете, то сразу попадете на карту с предложениями.",
        GUIDE_Step_4 = "У вашего заказа есть номер. Подробную информацию по заказу вы всегда можете увидеть на главной странице личного кабинета или в разделе “Заказы/Счета”",
        GUIDE_Step_5 = "Проверить ваш объект, оборудование, отредактировать его и увидеть документацию по заказу всегда можно в объекте. Эта информация есть и в заказе.",
        GUIDE_Step_6 = "Заполнив Профиль, вы более легко найдете мастера для ремонта вашего оборудования. Тут же вы сможете изменить пароль и настроить уведомления.",
        GUIDE_Step_7 = "Вы сможете видеть в реальном времени заказы переданные в сервисные компании, посмотреть рейтинг этих компаний, название и увидеть всю актуальную информацию сервисной компании. Также местоположение офиса компании на карте.",
        GUIDE_Step_8 = "Когда сервисная компания сделает вам предложение, то вы увидите предложенного мастера и стоимость его первичного выезда, также увидеть предложения мастеров можно из карточки заказа. После приезда, мастер рассчитает стоимость ремонта и составит подробную смету.",
        GUIDE_Step_9 = "Посмотреть заказ и увидеть предложенных мастеров можно по этой кнопке.",
        GUIDE_Step_10 = "Возьмите в руки телефон отсканируйте QR чтобы установить наше приложение, с которым вы никогда не пропустите новых заказов, сообщения мастера и вся информация о заказе и документах будет всегда с вами.",
        TO_IOS_QR_CODE_DESCRIPTION = "Скачать приложение в Apple Store",
        TO_ANDROID_QR_CODE_DESCRIPTION = "Скачать приложение для Android";

    SelenideElement
        guideFrameLocator = driver.$("div.gas-guide-main").as("Гайд"),
        guideTipLocator = driver.$("div.gas-guide-tip-light").as("Баннер с информацией"),
        guideButtonLocator = driver.$("button.btn.btn-secondary.disable-outline.small").as("Кнопка Ok баннера"),
        guideSkipButtonLocator = driver.$(".gas-guide-footer button.btn.btn-secondary.disable-outline").as("Кнопка баннера Пропустить"),
        guideNavPrevButtonLocator = driver.$("button.gas-screen-toggler__btn.prev").as("Кнопка гида Назад"),
        guideNavNextButtonLocator = driver.$("button.gas-screen-toggler__btn.next").as("Кнопка гида Вперед");
    ElementsCollection
        qrCodeDescriptionLocator = driver.$$("div.mt-2.small").as("Коллекция Описание QR кодов");

    public void playSequence() {
        stepWithRole("Проиграть гайд", () -> {
            guideFrameLocator.shouldBe(visible, Duration.ofSeconds(10)); // added 10 seconds
            for (int i = 0; i < 10; i++) {
                switch (i) {
                    case 0:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/client"));
                        guideTipLocator.shouldHave(text(GUIDE_Step_1));
                        nextStepGuide();
                        break;
                    case 1:
                        guideTipLocator.shouldHave(text(GUIDE_Step_2));
                        nextStepGuide();
                        break;
                    case 2:
                        guideTipLocator.shouldHave(text(GUIDE_Step_3));
                        nextStepGuide();
                        break;
                    case 3:
                        guideTipLocator.shouldHave(text(GUIDE_Step_4));
                        nextStepGuide();
                        break;
                    case 4:
                        guideTipLocator.shouldHave(text(GUIDE_Step_5));
                        nextStepGuide();
                        break;
                    case 5:
                        guideTipLocator.shouldHave(text(GUIDE_Step_6));
                        nextStepGuide();
                        break;
                    case 6:
                        guideTipLocator.shouldHave(text(GUIDE_Step_7));
                        nextStepGuide();
                        break;
                    case 7:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/orders/repair/"));
                        guideTipLocator.shouldHave(text(GUIDE_Step_8));
                        nextStepGuide();
                        break;
                    case 8:
                        guideTipLocator.shouldHave(text(GUIDE_Step_9));
                        nextStepGuide();
                        break;
                    case 9:
                        guideTipLocator.shouldHave(text(GUIDE_Step_10));
                        qrCodeDescriptionLocator.get(0).shouldHave(text(TO_IOS_QR_CODE_DESCRIPTION));
                        qrCodeDescriptionLocator.get(1).shouldHave(text(TO_ANDROID_QR_CODE_DESCRIPTION));
                        nextStepGuide();
                        break;
                }
            }
            guideFrameLocator.shouldNotBe(visible);
        });
    }

    public void skipGuide() {
        stepWithRole("Пропустить гид", () -> {
            guideButtonLocator.click();
            guideFrameLocator.shouldNotBe(visible);
        });
    }

    public void nextStepGuide() {
        stepWithRole("Нажать кнопку гида  Вперед", () -> {
            guideNavNextButtonLocator.click();
        });
    }

    public void prevStepGuide() {
        stepWithRole("Нажать кнопку гида  Назад", () -> {
            guideNavPrevButtonLocator.click();
        });
    }

    public void okButton() {
        stepWithRole("Нажать кнопку гида  Ok", () -> {
            guideButtonLocator.click();
        });
    }
}
