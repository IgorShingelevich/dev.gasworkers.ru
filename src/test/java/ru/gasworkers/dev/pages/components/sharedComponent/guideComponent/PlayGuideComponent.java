package ru.gasworkers.dev.pages.components.sharedComponent.guideComponent;

import com.codeborne.selenide.Selenide;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class PlayGuideComponent extends BaseGuideComponent {
    public PlayGuideComponent(RoleBrowser browser) {
        super(browser);
    }

    public void playSequenceFirstMaintenanceGuide() {
        String
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
        stepWithRole("Проиграть гайд", () -> {
            self.shouldBe(visible, Duration.ofSeconds(10)); // added 10 seconds
            for (int i = 0; i < 10; i++) {
                switch (i) {
                    case 0:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/client"));
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_1));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 1:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_2));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 2:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_3));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 3:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_4));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 4:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_5));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 5:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_6));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 6:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_7));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 7:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/orders/maintenance/"));
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_8));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 8:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_9));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                    case 9:
                        tipTextBoxLocator.$(".mb-3").shouldHave(text(GUIDE_Step_10));
                        qrCodeDescriptionLocator.get(0).shouldHave(text(TO_IOS_QR_CODE_DESCRIPTION));
                        qrCodeDescriptionLocator.get(1).shouldHave(text(TO_ANDROID_QR_CODE_DESCRIPTION));
                        nextButton();
                        Selenide.sleep(500);
                        break;
                }
            }
            Selenide.sleep(1000);
            self.shouldNotBe(visible);
        });
    }

    public void playSequenceFirstRepairGuide() {
        String
                GUIDE_Step_1 = "Ваш заказ отправлен в сервисные компании. Ожидайте информацию о готовности сервисной компании выполнить заказ, она будет поступать в виде уведомлений в личный кабинет на сайте, писем на электронную почту и смс сообщений.",
                GUIDE_Step_2 = "Нажмите на кнопку, тут находится «центр уведомлений». Уже сейчас Вы можете прочитать первое уведомление о размещении заказа на платформе.",
                GUIDE_Step_3 = "Мы покажем Вам количество откликов от сервисных компаний и мастеров. Нажмите на кнопку чтобы перейти на карту с предложениями и ознакомиться с деталями.",
                GUIDE_Step_4 = "Во вкладке «Заказы/Счета» Вы видите номер заказа и его статус, информацию по работам и сформированные документы, которые будут доступны Вам в любое время.",
                GUIDE_Step_5 = "Обратите внимание на меню с левой стороны. Во вкладке «Объекты/оборудования» Вам доступна подробная информация о вашем объекте и газовом оборудовании, а при необходимости Вы можете отредактировать объект или добавить новый.",
                GUIDE_Step_6 = "Заполнив Профиль, вы более легко найдете мастера для ремонта вашего оборудования. Тут же вы сможете изменить пароль и настроить уведомления.",
                GUIDE_Step_7 = "Gasworkers на карте покажет Вам: информацию, рейтинг и местоположение сервисных компаний, в которые отправлен Ваш заказ . После подтверждения цвет заказа изменится на зелёный и отобразится стоимость выезда мастера.",
                GUIDE_Step_8 = "Когда сервисная компания сделает вам предложение, то вы увидите предложенного мастера и стоимость его первичного выезда, также увидеть предложения мастеров можно из карточки заказа. После приезда, мастер рассчитает стоимость ремонта и составит подробную смету.",
                GUIDE_Step_9 = "Посмотреть заказ и увидеть предложенных мастеров можно по этой кнопке.",
                GUIDE_Step_10 = "Отсканируйте QR код и установите на мобильный телефон приложение «Gasworkers» клиент. Вы никогда не пропустите предложений сервисных компаний, сообщений мастера, вся информация о вашем объекте, оборудовании, заказе и документах всегда будут с вами.",
                TO_IOS_QR_CODE_DESCRIPTION = "Скачать приложение в Apple Store",
                TO_ANDROID_QR_CODE_DESCRIPTION = "Скачать приложение для Android";

        stepWithRole("Проиграть гайд", () -> {
            self.shouldBe(visible, Duration.ofSeconds(10)); // added 10 seconds
            for (int i = 0; i < 10; i++) {
                switch (i) {
                    case 0:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/client"));
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_1));
                        nextButton();
                        break;
                    case 1:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_2));
                        nextButton();
                        break;
                    case 2:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_3));
                        nextButton();
                        break;
                    case 3:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_4));
                        nextButton();
                        break;
                    case 4:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_5));
                        nextButton();
                        break;
                    case 5:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_6));
                        nextButton();
                        break;
                    case 6:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_7));
                        nextButton();
                        break;
                    case 7:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/orders/repair/"));
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_8));
                        nextButton();
                        break;
                    case 8:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_9));
                        nextButton();
                        break;
                    case 9:
                        tipTextBoxLocator.$(".mb-3").shouldHave(text(GUIDE_Step_10));
                        qrCodeDescriptionLocator.get(0).shouldHave(text(TO_IOS_QR_CODE_DESCRIPTION));
                        qrCodeDescriptionLocator.get(1).shouldHave(text(TO_ANDROID_QR_CODE_DESCRIPTION));
                        nextButton();
                        break;
                }
            }
            self.shouldNotBe(visible);
        });
    }


    public void playSequenceFirstSEGuide() {
        String
                // map page
                GUIDE_Step_1 = "Вы зарегистрировались на платформе Gasworkers как самозанятый. Для того чтобы участвовать в тендерах, назначать и принимать заказы используйте режим «Диспетчер», выполнять заказы и вести график видеоконсультаций можно в режиме «Мастер»",
                GUIDE_Step_2 = "Вы можете отсортировать заказы по типу, статусу, и дате добавления, нажав на кнопку «Фильтр и сортировка»",
                GUIDE_Step_3 = "Слева расположены карточки заказа. Они содержат важные детали: оборудование клиента, желаемую дату и время визита мастера. В зависимости от типа заказа, каждая карточка имеет свой цветовой индикатор. Нажмите на кнопку «Легенда карты» и ознакомьтесь подробнее.",
                GUIDE_Step_4 = "В правом верхнем углу карты отображается количество доступных Вам тендеров.",
                GUIDE_Step_5 = "Важно! Для работы с заказами на платформе Gasworkers необходимо заполнить профиль.",
                // profile page - common tab
                GUIDE_Step_6 = "Укажите ваши личные данные. Они понадобятся для заключения договора.",
                GUIDE_Step_7 = "Для получения денежных средств за выполнение заказов заполните реквизиты банковского счета, а также прикрепите справку о постановке на учет в качестве самозанятого. Обратите внимание! Прикрепляемые документы должны быть в хорошем разрешении.",
                GUIDE_Step_8 = "Прикрепите скан или фотографию удостоверения мастера-это обязательное условие участия в тендерах. Напоминаем: все прикрепленные документы должны быть в хорошем качестве.",
                // profile page - profile tab
                GUIDE_Step_9 = "Во вкладке профиль: укажите Вашу специальность и ключевые навыки. Рекомендуем загрузить имеющиеся у Вас дипломы и сертификаты, они являются весомым преимуществом работы на платформе.",
                GUIDE_Step_10 = "Как правило клиенты стараются выбрать мастера, который находится рядом. Укажите Ваш адрес, чтоб повысить вероятность отклика от клиента. В случае, если Вы укажите радиус, в пределах которого готовы выезжать на заказы, Gasworkers будет предлагать заказы только в нем. Если вы готовы проводить платные видеоконсультации, отметьте это и укажите вашу цену.",
                // orders page
                GUIDE_Step_11 = "Нажмите на кнопку «Заказы». Здесь содержится вся подробная информация о них: номер, тип, оборудования, адрес объекта.",
                GUIDE_Step_12 = "Для участия в тендере необходимо указать стоимость работ по оборудованию и стоимость выезда.",
                // set price Modal
                GUIDE_Step_13 = "Прикрепите удостоверение мастера и укажите срок его действия. Скан или фотография документа должна быть хорошего качества. Обратите внимание! В случае, если Вы загрузили его ранее во вкладке «Профиль», второй раз загружать не нужно.",
                GUIDE_Step_14 = "Платформа Gasworkers формирует полную сумму Вашего предложения исходя из: стоимости работ по оборудованию и первичного выезда мастера. Сохранить расценку и предложить ее клиенту можно нажав на кнопку «Принять заказ».",
                GUIDE_Step_15 = "Добро пожаловать на Gasworkers! Изучите доступные Вам заказы. Примите этот заказ и начните работать с нами!";
        stepWithRole("Проиграть гид", () -> {
            self.shouldBe(visible, Duration.ofSeconds(10)); // added 10 seconds
            for (int i = 0; i < 15; i++) {
                switch (i) {
                    case 0:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/dispatcher"));
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_1));
                        Selenide.sleep(300);
                        nextButton();
                        break;
                    case 1:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_2));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 2:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_3));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 3:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_4));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 4:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_5));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 5:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_6));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/edit?tab=common"));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 6:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_7));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 7:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_8));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 8:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_9));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/edit?tab=profile"));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 9:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_10));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 10:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_11));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/dispatcher/orders/"));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 11:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_12));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 12:
                        setPriceModalWindowLocator.shouldBe(visible);
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_13));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 13:
                        setPriceModalWindowLocator.shouldBe(visible);
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_14));
                        nextButton();
                        Selenide.sleep(300);
                        break;
                    case 14:
                        tipTextBoxLocator.shouldHave(text(GUIDE_Step_15));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/dispatcher"));
                        nextButton();
                        Selenide.sleep(300);
                        break;

                }
                self.shouldNotBe(visible);
            }
        });
    }
}
