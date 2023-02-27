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
        GUIDE_Step_1 = "Ваш заказ отправлен в сервисные компании. Ожидайте информацию о готовности сервисной компании выполнить заказ, она будет поступать в виде уведомлений в личный кабинет на сайте, писем на электронную почту и смс сообщений.",
        GUIDE_Step_2 = "Сюда приходят уведомления. Посмотрите первое сообщение о размещении заказа на сайте.",
        GUIDE_Step_3 = "Здесь можно увидеть предложения от сервисных компаний. Нажмите на кнопку чтоб перейти на карту с предложениями.",
        GUIDE_Step_4 = "У вашего заказа есть номер. Подробную информацию по заказу вы всегда можете увидеть на главной странице личного кабинета или в разделе “Заказы/Счета”",
        GUIDE_Step_5 = "В меню «Объекты/оборудование», а так же «Заказы» Вы можете: проверить правильность заполнения данных объекта, а при необходимости внести правки и видеть документы по заказу.",
        GUIDE_Step_6 = "Заполнив Профиль, вы более легко найдете мастера для ремонта вашего оборудования. Тут же вы сможете изменить пароль и настроить уведомления.",
        GUIDE_Step_7 = "Здесь Вы увидите информацию о сервисных компаниях, в которые направлен Ваш заказ, их рейтинг, местоположение офиса. После подтверждения цвет заказа изменится на зелёный и отобразится стоимость выезда мастера.",
        GUIDE_Step_8 = "Когда сервисная компания сделает вам предложение, то вы увидите предложенного мастера и стоимость его первичного выезда, также увидеть предложения мастеров можно из карточки заказа. После приезда, мастер рассчитает стоимость ремонта и составит подробную смету.",
        GUIDE_Step_9 = "Посмотреть заказ и увидеть предложенных мастеров можно по этой кнопке.",
        GUIDE_Step_10 = "Возьмите в руки телефон, отсканируйте QR-код, установите приложение «Gasworkers Клиент» и Вы никогда не пропустите предложений сервисных компаний, сообщений мастера, вся информация о вашем объекте, оборудовании, заказе и документах всегда будут с вами.",
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
                        guideTipLocator.$(".mb-3").shouldHave(text(GUIDE_Step_10));
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
