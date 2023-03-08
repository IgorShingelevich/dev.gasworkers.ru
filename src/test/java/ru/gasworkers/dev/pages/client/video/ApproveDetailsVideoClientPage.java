package ru.gasworkers.dev.pages.client.video;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.approveMasterComponent.ErrorAttachmentsOrderVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.approveMasterComponent.DetailsOrderVideoClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;

public class ApproveDetailsVideoClientPage extends BaseClientPage {
    public final FocusHeaderComponent focusHeader;
    public final StepperComponent stepper;
    public final ErrorAttachmentsOrderVideoClientComponent errorAttachments;
    public final DetailsOrderVideoClientComponent details;
    public ApproveDetailsVideoClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        errorAttachments = new ErrorAttachmentsOrderVideoClientComponent(browser);
        details = new DetailsOrderVideoClientComponent(browser);
    }

    public final String
            titleText = "Опишите вашу проблему";

    SelenideElement
        titleLocator = driver.$("div p.h3").as("Заголовок страницы Подтверждение мастера"),
        payButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Оплатить");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Подтверждение мастера загрузилась", () -> {
            focusHeader.checkFinishLoading();
            stepper.checkFinishLoading();
            titleLocator.shouldHave(text(titleText), Duration.ofSeconds(10));
            errorAttachments.checkFinishLoading();
            details.checkFinishLoading();
            payButtonLocator.shouldBe(enabled);
        });
    }

    public void clickPayButton() {
        stepWithRole("Нажать на кнопку Оплатить", () -> {
            payButtonLocator.click();
        });
    }



}
