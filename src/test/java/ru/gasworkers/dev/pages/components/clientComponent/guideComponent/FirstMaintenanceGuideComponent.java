package ru.gasworkers.dev.pages.components.clientComponent.guideComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class FirstMaintenanceGuideComponent extends BaseComponent {
    public FirstMaintenanceGuideComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
        GUIDE_Step_1 = "Ваш заказ отправлен в сервисные компании. Ожидайте информацию о готовности сервисной компании выполнить заказ, она будет поступать в виде уведомлений в личный кабинет на сайте, писем на электронную почту и смс сообщений.",
        GUIDE_Step_2 = "Нажмите на кнопку, тут находится «центр уведомлений». Уже сейчас Вы можете прочитать первое уведомление о размещении заказа на платформе.",
        GUIDE_Step_3 = "Мы покажем Вам количество откликов от сервисных компаний и мастеров. Нажмите на кнопку чтобы перейти на карту с предложениями и ознакомиться с деталями.",
        GUIDE_Step_4 = "Во вкладке «Заказы/Счета» Вы видите номер заказа и его статус, информацию по работам и сформированные документы, которые будут доступны Вам в любое время.",
        GUIDE_Step_5 = "Обратите внимание на меню с левой стороны. Во вкладке «Объекты/оборудования» Вам доступна подробная информация о вашем объекте и газовом оборудовании, а при необходимости Вы можете отредактировать объект или добавить новый.",
        GUIDE_Step_6 = "Для заключения договора технического обслуживания Вам необходимо заполнить личные и паспортные данные, сделать это можно в меню «Профиль». Здесь Вы можете изменить пароль и настроить уведомления.",
        GUIDE_Step_7 = "Gasworkers на карте покажет Вам: информацию, рейтинг и местоположение сервисных компаний, в которые отправлен Ваш заказ.",
        GUIDE_Step_8 = "Так выглядит предложение откликнувшейся сервисной компании. Напоминаем, что уведомления о готовности выполнить заказ поступит SMS сообщением на телефон, электронную почту и в личный кабинет.",
        GUIDE_Step_9 = "Нажимая кнопку «Смотреть заказ» Вы переходите в личный кабинет для управления заказом. Вы всегда сможете вернуться на карту и выбрать лучшее предложение сервисных компаний.",
        GUIDE_Step_10 = "Отсканируйте QR код и установите на мобильный телефон приложение «Gasworkers» клиент. Вы никогда не пропустите предложений сервисных компаний, сообщений мастера, вся информация о вашем объекте, оборудовании, заказе и документах всегда будут с вами.",
        TO_IOS_QR_CODE_DESCRIPTION = "Скачать приложение для IOS",
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
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/orders/maintenance/"));
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
