package ru.gasworkers.dev.pages.client.maintenance;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SelectPaymentMaintenanceClientPage extends BaseClientPage {

    private final FocusHeaderComponent header;
    public SelectPaymentMaintenanceClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }
    private final String
            SELECT_PAYMENT_TITLE = "Заключение договора ТО и оплата выезда мастера";

    SelenideElement
        pageTitleLocator = driver.$("div p.h3").as("Заголовок страницы Выбор Способа Оплаты"),
        secondBankComissionsTextLocator = driver.$$("div.logo-wrap__text").get(1).as("Текст второго банковского комиссионного сбора"),
        pagePaymentWizardTitleLocator = driver.$("span.part"),
        payImgLocator = driver.$("div.logo-wrap img").as("Картинка оплаты"),
        docAgreementMaintenanceLocator = driver.$("div .link-pdf").as("Ссылка на договор ТО");

    ElementsCollection
        paymentMethodsCollection = driver.$$("div.logo-wrap").as("Способы оплаты"),
        bankComissionCollection = driver.$$("div.logo-wrap__text").as("Комиссионные сборы банков");

    public SelectPaymentMaintenanceClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что страница Выбор Способа Оплаты загружена", () -> {
            pageTitleLocator.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text(SELECT_PAYMENT_TITLE));
            docAgreementMaintenanceLocator.shouldBe(visible);
            secondBankComissionsTextLocator.shouldBe(visible);
            // get text from second bank comission and sout it
            String secondBankComission = secondBankComissionsTextLocator.getText();
            payImgLocator.shouldBe(visible, Duration.ofSeconds(20));
        });

        return this;
    }

    public SelectPaymentMaintenanceClientPage payMIR() {
        paymentMethodsCollection.get(0).click();
        return this;
    }

    public SelectPaymentMaintenanceClientPage paySPB() {
        stepWithRole("Нажать кнопку Оплатить через SPB", () -> {
            paymentMethodsCollection.get(1).click();
        });
//        pagePaymentWizardTitleLocator.shouldBe(visible);
        return this;
    }
}
