package ru.gasworkers.dev.pages.components.registrationStepComponent.selfEmployedRegistrationStepComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;


public class FirstStepSelfEmployedRegistrationComponent  extends BaseComponent {
    public FirstStepSelfEmployedRegistrationComponent(RoleBrowser browser) {
        super(browser);
    }
    private final String
        alreadyRegisteredText = "Уже зарегистрированы?",
        titleText = "Выберете подходящий для Вас вариант.",
        seSubtitleText = "Я хочу работать на себя",
        seDescriptionText = "Вам будет открыт доступ к заказам по ТО, ремонту ВДГО и видеоконсультациям.",
        masterSubtitleText = "Я уже работаю в сервисной компании",
        masterDescriptionText = "Выберите сервисную компанию, в которой вы работаете и дождитесь подтверждения. Регистрация возможна только для мастеров сервисных компаний, зарегистрированных на платформе.",
        recruitSubtitleText = "Я хочу найти работу в сервисной компании",
        recruitDescriptionText = "В личном кабинете создайте резюме, отправьте его в сервисные компании и ожидайте отклика.";

    ElementsCollection
        stepsCollection = driver.$$("div.stage").as("Коллекция шагов регистрации"),
        subtitleCollection = driver.$$("span.small.bold").as("Подзаголовки"),
        descriptionCollection = driver.$$("div.small.mt-2 span").as("Описания"),
        buttonCollection = driver.$$("button").as("Кнопки"),
        selectOptionButtonCollection = driver.$$("div.section.mb-3 button.btn-sm.mt-2.btn").as("Кнопки выбора варианта");
    SelenideElement
        alreadyRegisteredLinkLocator = driver.$("a.link-gray").as("Ссылка уже зарегистрированы"),
        titleLocator = driver.$("p.mb-20.medium").as("Заголовок"),
        seSubtitleLocator = subtitleCollection.get(0).as("Подзаголовок для смз"),
        seDescriptionLocator = descriptionCollection.get(0).as("Описание для смз"),
        masterSubtitleLocator = subtitleCollection.get(1).as("Подзаголовок для мастера"),
        masterDescriptionLocator = descriptionCollection.get(1).as("Описание для мастера"),
        recruitSubtitleLocator = subtitleCollection.get(2).as("Подзаголовок для рекрута"),
        recruitDescriptionLocator = descriptionCollection.get(2).as("Описание для рекрута"),
        nextButtonLocator = driver.$("div.mt-auto button.btn.btn-primary").as("Кнопка далее"),
        backButtonLocator = driver.$("div.mt-auto button.btn.btn-outline-primary").as("Кнопка назад");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что представлены компоненты первого шага регистрации: ", () -> {
            stepWithRole("Убедиться, что на таймлайне выделен первый шаг", () -> {
                stepsCollection.get(0).shouldHave(cssClass("active"));
            });
            stepWithRole("Убедиться, что ссылка уже зарегистрированы отображается", () -> {
                alreadyRegisteredLinkLocator.shouldHave(text(alreadyRegisteredText));
            });
            stepWithRole("Убедиться, что заголовок отображается", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что подзаголовок для смз отображается", () -> {
                seSubtitleLocator.shouldHave(text(seSubtitleText));
            });
            stepWithRole("Убедиться, что описание для смз отображается", () -> {
                seDescriptionLocator.shouldHave(text(seDescriptionText));
            });
            stepWithRole("Убедиться, что подзаголовок для мастера отображается", () -> {
                masterSubtitleLocator.shouldHave(text(masterSubtitleText));
            });
            stepWithRole("Убедиться, что описание для мастера отображается", () -> {
                masterDescriptionLocator.shouldHave(text(masterDescriptionText));
            });
            stepWithRole("Убедиться, что подзаголовок для рекрута отображается", () -> {
                recruitSubtitleLocator.shouldHave(text(recruitSubtitleText));
            });
            stepWithRole("Убедиться, что описание для рекрута отображается", () -> {
                recruitDescriptionLocator.shouldHave(text(recruitDescriptionText));
            });
            stepWithRole("Убедиться, что кнопки выбора варианта отображаются", () -> {
                selectOptionButtonCollection.shouldHave(CollectionCondition.size(3));
            });
            stepWithRole("Убедиться что кнопка выбора варианта для смз неактивна", () -> {
                selectOptionButtonCollection.get(0).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
            });
            stepWithRole("Убедиться что кнопка выбора варианта для мастера неактивна", () -> {
                selectOptionButtonCollection.get(1).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
            });
            stepWithRole("Убедиться что кнопка выбора варианта для рекрута неактивна", () -> {
                selectOptionButtonCollection.get(2).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
            });
            stepWithRole("Убедиться что кнопка выбора варианта для всех типов неактивна", () -> {
                for (SelenideElement button : selectOptionButtonCollection) {
                    button.shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
                }
            });
            stepWithRole("Убедиться, что кнопка далее отображается и неактивна", () -> {
                nextButtonLocator.shouldBe(disabled).shouldHave(text("Далее"));
            });
            stepWithRole("Убедиться, что кнопка назад отображается", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
            selectSE();
            selectMaster();
            selectRecruit();
            selectSE();
        });
    }

    public void selectSE (){
        stepWithRole("Выбрать вариант для смз", () -> {
            selectOptionButtonCollection.get(0).click();
            stepWithRole("Убедиться, что активна  только кнопка выбора варианта для смз", () -> {
                selectOptionButtonCollection.get(0).shouldHave(cssClass("btn-primary")).shouldNotHave(cssClass("btn-outline-primary"));
                selectOptionButtonCollection.get(1).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
                selectOptionButtonCollection.get(2).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
            });
            stepWithRole("Убедиться, что кнопка далее отображается и активна", () -> {
                nextButtonLocator.shouldBe(enabled).shouldHave(text("Далее"));
            });

        });
    }

    public void selectMaster (){
        stepWithRole("Выбрать вариант для мастера", () -> {
            selectOptionButtonCollection.get(1).click();
            stepWithRole("Убедиться, что активна  только кнопка выбора варианта для мастера", () -> {
                selectOptionButtonCollection.get(0).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
                selectOptionButtonCollection.get(1).shouldHave(cssClass("btn-primary")).shouldNotHave(cssClass("btn-outline-primary"));
                selectOptionButtonCollection.get(2).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
            });
            stepWithRole("Убедиться, что кнопка далее отображается и активна", () -> {
                nextButtonLocator.shouldBe(enabled).shouldHave(text("Далее"));
            });
        });
    }

    public void selectRecruit () {
        stepWithRole("Выбрать вариант для рекрута", () -> {
            selectOptionButtonCollection.get(2).click();
            stepWithRole("Убедиться, что активна  только кнопка выбора варианта для рекрута", () -> {
                selectOptionButtonCollection.get(0).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
                selectOptionButtonCollection.get(1).shouldHave(cssClass("btn-outline-primary")).shouldNotHave(cssClass("btn-primary"));
                selectOptionButtonCollection.get(2).shouldHave(cssClass("btn-primary")).shouldNotHave(cssClass("btn-outline-primary"));
            });
            stepWithRole("Убедиться, что кнопка Создать резюме отображается и активна", () -> {
                nextButtonLocator.shouldBe(enabled).shouldHave(text("Создать резюме"));
            });
        });
    }

    public void nextButton () {
        stepWithRole("Нажать кнопку Далее", () -> {
            nextButtonLocator.click();
        });
    }

}
