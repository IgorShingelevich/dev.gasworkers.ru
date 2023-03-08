package ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class NavPasswordTabProfileComponent extends BaseComponent {
    public NavPasswordTabProfileComponent(RoleBrowser browser) {
        super(browser);
    }


    SelenideElement
        currentPasswordInputLocator = driver.$("input[placeholder*=Текущий]").as("Текущий пароль"),
        newPasswordInputLocator = driver.$("input[placeholder*=Новый]").as("Новый пароль"),
        repeatNewPasswordInputLocator = driver.$("input[placeholder*=еще]").as("Повторите новый пароль"),
        generatePasswordButtonLocator = driver.$(byTagAndText("span", "Сгенерировать надежный пароль")).as("Сгенерировать пароль"),
        suggestedPasswordLocator = driver.$("input[readonly=readonly]").as("Предложенный пароль"),
        saveButtonLocator = driver.$(".footer button.mb-3.btn.btn-primary").as("Кнопка Сохранить");

    ElementsCollection
    interractionElementsCollection = driver.$$("div.item").as("Элементы взаимодействия"),
        eyeIconCollection = driver.$$("div.eye-icon").as("Коллекция иконок отображения пароля");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Пароль загружена" , () -> {
            driver.$$("div.title").get(0).shouldHave(text("Смена пароля")).as("Заголовок вкладки Смена пароля");
            driver.$("div.description").shouldHave(text("Пароль должен содержать не менее 4 знаков")).as("Описание вкладки Смена пароля");
            driver.$$("div.title").get(1).shouldHave(text("Подтвердите свой текущий пароль")).as("Подтвердите свой текущий пароль");
            driver.$$("div.title").get(2).shouldHave(text("Введите свой новый пароль")).as("Введите свой новый пароль");
            currentPasswordInputLocator.shouldBe(Condition.visible);
            newPasswordInputLocator.shouldBe(Condition.visible);
            repeatNewPasswordInputLocator.shouldBe(Condition.visible);
            generatePasswordButtonLocator.shouldBe(Condition.visible);
            saveButtonLocator.shouldHave(text("Сохранить"));
        });
        stepWithRole("Убедиться, что кнопка Сохранить не активна", () -> {
            saveButtonLocator.shouldBe(disabled);
        });
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что вкладка Пароль не содержит пароль в форме на вкладке Пароль", () -> {
            driver.$$("div.title").get(0).shouldHave(text("Смена пароля")).as("Заголовок вкладки Смена пароля");
            driver.$("div.description").shouldHave(text("Пароль должен содержать не менее 4 знаков")).as("Описание вкладки Смена пароля");
            driver.$$("div.title").get(1).shouldHave(text("Подтвердите свой текущий пароль")).as("Подтвердите свой текущий пароль");
            driver.$$("div.title").get(2).shouldHave(text("Введите свой новый пароль")).as("Введите свой новый пароль");
            newPasswordInputLocator.shouldBe(Condition.empty);
            repeatNewPasswordInputLocator.shouldBe(Condition.empty);
        });
        stepWithRole("Убедиться, что кнопка Сохранить неактивна", () -> {
            saveButtonLocator.shouldBe(disabled);
        });
    }

    public void generatePassword() {
        stepWithRole("Сгенерировать пароль" , () -> {
            stepWithRole("Нажать на кнопку Сгенерировать пароль" , () -> {
                generatePasswordButtonLocator.click();
            });
            stepWithRole("Убедиться, что появился подзаголовок поля Надежный пароль: " + driver.$("p.small.mb-20").getText() , () -> {
                driver.$("p.small.mb-20").as("подзаголовок поля Надежный пароль").shouldHave(text("Мы сгенерировали вам пароль"));
            });
            stepWithRole("Убедиться, что форма генерации пароля отображается" , () -> {
                suggestedPasswordLocator.shouldBe(Condition.visible).as("Предложенный пароль");
            });
            stepWithRole("Убедиться, что сгенерирован Надежный  пароль: " + suggestedPasswordLocator.val().toString() , () -> {
                suggestedPasswordLocator.shouldNotBe(Condition.empty).as("Предложенный пароль");
                System.out.println("suggestedPassword: " + suggestedPasswordLocator.val().toString());
            });
            stepWithRole("Убедиться, что поле Новый пароль и Подтверждение пароля заполнены сгенерированным паролем: " + suggestedPasswordLocator.val().toString() , () -> {
                stepWithRole("Нажать на иконку Показать  скрытый пароль в поле Пароль и Подтверждение Пароля" , () -> {
                    stepWithRole("Убедиться, что иконки переходят из состояния Скрытый пароль в состояние Пароль Отображается" , () -> {
                        eyeIconCollection.get(1).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(1).shouldHave(cssClass("visible"));
                        eyeIconCollection.get(2).shouldNotHave(cssClass("visible")).click();
                        eyeIconCollection.get(2).shouldHave(cssClass("visible"));
                    });
                });
                stepWithRole("Убедиться, что в поле  Новый пароль и Повторите пароль отображается сгенерированный пароль: " + newPasswordInputLocator.val().toString() , () -> {
                    newPasswordInputLocator.shouldNotBe(Condition.empty).as("Новый пароль");
                    repeatNewPasswordInputLocator.shouldNotBe(Condition.empty).as("Повторите пароль");
                });
                stepWithRole("Нажать на иконку Скрыть пароль в поле Пароль и Подтверждение Пароля" , () -> {
                    eyeIconCollection.get(1).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(1).shouldNotHave(cssClass("visible"));
                    eyeIconCollection.get(2).shouldHave(cssClass("visible")).click();
                    eyeIconCollection.get(2).shouldNotHave(cssClass("visible"));
                });
            });
            stepWithRole("Убедиться, что кнопка Сохранить активна" , () -> {
                saveButtonLocator.shouldBe(enabled);
            });
        });
    }

    public void applyNewPassword (String currentPassword){
        //TODO applyNewPassword
    }

}
