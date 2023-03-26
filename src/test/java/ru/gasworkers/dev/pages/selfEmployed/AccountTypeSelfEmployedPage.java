package ru.gasworkers.dev.pages.selfEmployed;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.registrationComponent.selfEmployedRegistrationStepComponent.BannerTypeSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AccountTypeSelfEmployedPage extends BaseSelfEmployedPage {
    public final FocusHeaderComponent header;
    public final BannerTypeSelfEmployedComponent banner;

    public AccountTypeSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        banner = new BannerTypeSelfEmployedComponent(browser);
    }

    private final String
            titleText = "Выберите подходящий вам вариант",
            alreadySERadioText = "Я уже самозанятый, хочу подключиться к платформе",
            alreadySEDescriptionText = "Вам доступны заказы на ремонт и видеоконсультации , соглашаясь с условиями Вы получаете доступ к заказам ТО ВДГО.",
            individualEntrepreneurSERadioText = "У меня есть ИП и я хочу получать заказы",
            individualEntrepreneurSEDescriptionText = "Вам доступны заказы на ремонт и видеоконсультации , соглашаясь с условиями Вы получаете доступ к заказам ТО ВДГО.",
            checkBoxText = "Нажимая кнопку «Далее», вы подтверждаете, что ознакомлены с",
            termsOfUseLinkText = "Условиями пользования сервиса «Gasworkers»",
            errorText = "Требуется подтвердить согласие с условиями пользования сервиса";


    ElementsCollection
            radioButtons = driver.$$("div.pretty.p-default.p-round ").as("Радио кнопки"),
            descriptionsCollection = driver.$$("div.checkbox-list__item--text:not(.mt-2)").as("Описания");

    SelenideElement
            titleLocator = driver.$("div .h4").as("Заголовок"),
            alreadySERadioLocator = radioButtons.get(0).as("Радио кнопка 'Я уже самозанятый, хочу подключиться к платформе'"),
            alreadySEDescriptionLocator = descriptionsCollection.get(0).as("Описание 'Я уже самозанятый, хочу подключиться к платформе'"),
            individualEntrepreneurSERadioLocator = radioButtons.get(1).as("Радио кнопка 'У меня есть ИП и я хочу получать заказы'"),
            individualEntrepreneurSEDescriptionLocator = descriptionsCollection.get(1).as("Описание 'У меня есть ИП и я хочу получать заказы'"),
            checkBoxLocator = driver.$("div.p-default.p-curve.p-smooth.pretty input").as("Чекбокс"),
                                                    //div.p-default.p-curve.p-smooth.pretty:nth-child(1) input
            checkBoxTextLocator = driver.$("span .mx-1").as("Текст чекбокса"),
            termsOfUseLinkLocator = driver.$("div.checkbox-list__item--text.mt-2 a").as("Ссылка на условия пользования"),
            errorTextLocator = driver.$("div.invalid-feedback").as("Текст ошибки"),
            forwardButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка перехода к следующему шагу"),
            backButtonLocator = driver.$("button.btn.btn-outline-primary").as("Кнопка перехода к предыдущему шагу");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что представлены все компоненты  страницы выбора типа смз", () -> {
            header.checkFinishLoading();
            banner.checkFinishLoading();
            stepWithRole("Убедиться, что заголовок страницы загрузился", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что радио кнопки и их описания загрузились", () -> {
                alreadySERadioLocator.sibling(0).shouldHave(text(alreadySERadioText));
                descriptionsCollection.shouldHave(size(2));
                alreadySEDescriptionLocator.shouldHave(text(alreadySEDescriptionText));
                individualEntrepreneurSERadioLocator.sibling(0).shouldHave(text(individualEntrepreneurSERadioText));
                individualEntrepreneurSEDescriptionLocator.shouldHave(text(individualEntrepreneurSEDescriptionText));
            });
            stepWithRole("Убедиться что первый радио кнопка выбрана", () -> {
                //todo: Проверить, что радио кнопка выбрана
            });
            stepWithRole("Убедиться, что чекбокс и его описание загрузились", () -> {
                checkBoxLocator.shouldNotBe(checked);
                checkBoxTextLocator.shouldHave(text(checkBoxText));
                termsOfUseLinkLocator.shouldHave(text(termsOfUseLinkText));
            });
            stepWithRole("Убедиться, что кнопка перехода далее активна", () -> {
                forwardButtonLocator.shouldHave(text("Далее")).shouldBe(enabled);
            });
            stepWithRole("Убедиться, что кнопка перехода назад отображается", () -> {
                backButtonLocator.shouldHave(text("Назад"));
            });
            stepWithRole("Убедиться, что текст ошибки не отображается", () -> {
                errorTextLocator.shouldNotBe(visible);
            });
        });
    }

    public void forwardButton() {
        stepWithRole("Нажать кнопку 'Далее'", () -> {
            forwardButtonLocator.click();
        });
    }

    public void backButton () {
        stepWithRole("Нажать кнопку 'Назад'", () -> {
            backButtonLocator.click();
        });
    }

    public void selectAlreadySE() {
        stepWithRole("Выбрать радио кнопку 'Я уже самозанятый, хочу подключиться к платформе'", () -> {
            alreadySERadioLocator.click();
        });
        stepWithRole("Убедиться, что чекбокс не выбран", () -> {
            checkBoxLocator.shouldNotBe(checked);
        });
        stepWithRole("Отметить чекбокс", () -> {
            checkBoxLocator.click();
        });
        stepWithRole("Убедиться, что чекбокс выбран", () -> {
            checkBoxLocator.shouldBe(checked);
        });
    }

    public void selectIndividualEntrepreneurSE() {
        stepWithRole("Выбрать радио кнопку 'У меня есть ИП и я хочу получать заказы'", () -> {
            individualEntrepreneurSERadioLocator.click();
        });
        stepWithRole("Убедиться, что чекбокс не выбран", () -> {
            checkBoxLocator.shouldNotBe(checked);
        });
        stepWithRole("Отметить чекбокс", () -> {
            checkBoxLocator.click();
        });
        stepWithRole("Убедиться, что чекбокс выбран", () -> {
            checkBoxLocator.shouldBe(checked);
        });
    }




}
