package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;

public class SignSuccessPage extends BaseClientPage {

    public final FocusHeaderComponent header;
    public final StepperComponent stepper;

    public SignSuccessPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
    }
    private final String
        TITLE_TEXT = "Поздравляем!",
        SUBTITLE_FIRST_TEXT = "Ваш договор подписан, заказ сформирован и оплачен выезд мастера.",
        SUBTITLE_SECOND_TEXT = "Ожидайте звонка диспетчера.";


    SelenideElement
        titleLocator = driver.$(byTagAndText("h3", TITLE_TEXT)),
        subtitleFirstLocator = driver.$$(".recovery-box p").get(0),
        subtitleSecondLocator = driver.$$(".recovery-box p").get(1),
        toOrderCardButtonLocator = driver.$(byTagAndText("span", "К заказу")),
        toHomePageButtonLocator = driver.$(byTagAndText("span", "На главную"));

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Подписание СМС загружена", () -> {
            titleLocator.shouldBe(visible, Duration.ofSeconds(60)).shouldHave(text(TITLE_TEXT));
            toOrderCardButtonLocator.shouldHave(text("К заказу"));
            toHomePageButtonLocator.shouldHave(text("На главную"));
        });
    }

   public void toOrderCard() {
        stepWithRole("Нажать кнопку К заказу", () ->
            toOrderCardButtonLocator.click()
        );
   }

    public void toHomePage() {
          stepWithRole("Нажать кнопку На главную", () ->
                toHomePageButtonLocator.click()
          );
    }




}
