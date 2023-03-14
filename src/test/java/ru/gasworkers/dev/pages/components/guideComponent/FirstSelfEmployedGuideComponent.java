package ru.gasworkers.dev.pages.components.guideComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FirstSelfEmployedGuideComponent extends BaseComponent {
    public FirstSelfEmployedGuideComponent(RoleBrowser browser) {
        super(browser);
    }


    private final String
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
            GUIDE_Step_14 = "Платформа Gasworkers формирует полную сумму Вашего предложения исходя из: стоимости работ по оборудованию и первичного выезда мастера. Сохранить расценку и предложить ее клиенту можно нажав на кнопку «Принять заказ».";

    SelenideElement
            guideFrameLocator = driver.$("div.gas-guide-main").as("Гайд"),
            guideTipTextBoxLocator = driver.$("div.gas-guide-tip-light").as("Баннер с информацией"),
            guideButtonLocator = driver.$("button.btn.btn-secondary.disable-outline.small").as("Кнопка Ok баннера"),
            guideSkipButtonLocator = driver.$(".gas-guide-footer button.btn.btn-secondary.disable-outline").as("Кнопка баннера Пропустить"),
            guideNavPrevButtonLocator = driver.$("button.gas-screen-toggler__btn.prev").as("Кнопка гида Назад"),
            guideNavNextButtonLocator = driver.$("button.gas-screen-toggler__btn.next").as("Кнопка гида Вперед"),
            setPriceModalWindowLocator = driver.$("div.modal-content-wrapper").as("Модальное окно Установить цену");

//    ElementsCollection
//            qrCodeDescriptionLocator = driver.$$("div.mt-2.small").as("Коллекция Описание QR кодов");


    public void playSequence() {
        stepWithRole("Проиграть гайд", () -> {
            guideFrameLocator.shouldBe(visible, Duration.ofSeconds(10)); // added 10 seconds
            for (int i = 0; i < 14; i++) {
                switch (i) {
                    case 0:
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/dispatcher"));
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_1));
                        nextStepGuide();
                        break;
                    case 1:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_2));
                        nextStepGuide();
                        break;
                    case 2:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_3));
                        nextStepGuide();
                        break;
                    case 3:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_4));
                        nextStepGuide();
                        break;
                    case 4:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_5));
                        nextStepGuide();
                        break;
                    case 5:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_6));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/edit?tab=common"));
                        nextStepGuide();
                        break;
                    case 6:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_7));
                        nextStepGuide();
                        break;
                    case 7:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_8));
                        nextStepGuide();
                        break;
                    case 8:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_9));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/edit?tab=profile"));
                        nextStepGuide();
                        break;
                    case 9:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_10));
                        nextStepGuide();
                        break;
                    case 10:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_11));
                        assertThat(driver.url(), containsString("https://dev.gasworkers.ru/profile/dispatcher/orders/"));
                        nextStepGuide();
                        break;
                    case 11:
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_12));
                        nextStepGuide();
                        break;
                    case 12:
                        setPriceModalWindowLocator.shouldBe(visible);
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_13));
                        nextStepGuide();
                        break;
                    case 13:
                        setPriceModalWindowLocator.shouldBe(visible);
                        guideTipTextBoxLocator.shouldHave(text(GUIDE_Step_14));
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
