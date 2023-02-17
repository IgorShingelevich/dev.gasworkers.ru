package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class CheckDocumentsClientPage  extends BaseClientPage {
    private final FocusHeaderComponent header;
     public CheckDocumentsClientPage(RoleBrowser browser) {
         super(browser);
         header = new FocusHeaderComponent(browser);
     }

    private final String
        CHECK_DOCUMENTS_TITLE = "Для заключения договора ТО заполните недостающие данные";

    SelenideElement
        pageTitleLocator = driver.$("div p.h3").as("Заголовок страницы Проверка документов"),
        pageSubtitleLocator = driver.$(".h4.mb-32"),
        inputPassportIssuedByFieldLocator = driver.$("input[placeholder='Кем выдан']").as("Поле Кем выдан"),
        makeContractButton = driver.$(byTagAndText("span", "Оформить договор")).as("Кнопка Оформить договор"),
        spinnerLoaderLocator = driver.$("div.gas-loader").as("Спинер загрузки");

    public CheckDocumentsClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Проверка документов загружена", () -> {
            pageTitleLocator.shouldHave(text(CHECK_DOCUMENTS_TITLE));
        });
        return this;
    }

    public CheckDocumentsClientPage makeContract()  {
        stepWithRole("Нажать кнопку Заключить договор", () -> {
            inputPassportIssuedByFieldLocator.shouldNot(empty);
            makeContractButton.shouldBe(visible, Duration.ofSeconds(60)).hover().click();
            spinnerLoaderLocator.shouldNotBe(visible, Duration.ofSeconds(60));
        });
        return this;
    }






}

