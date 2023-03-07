package ru.gasworkers.dev.pages.client.video;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.*;

public class SuccessPaymentVideoClientPage extends BaseClientPage {

    public final FocusHeaderComponent focusHeader;
    public SuccessPaymentVideoClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
    }

    private final String
        titleText = "Оплата успешно произведена",
        subtitleText = "Вы успешно оплатили видеоконсультацию. Мастер получил заказ и начнет ее в течении 30 минут. Ожидайте СМС сообщения со ссылкой на консультацию.";


    SelenideElement
        titleLocator = driver.$("div.item-flex .h2").as("Заголовок страницы Успешная оплата"),
        bodyImageLocator = driver.$("div.item-flex img").as("Изображение страницы Успешная оплата"),
        subtitleLocator = driver.$("div.item-flex .text").as("Подзаголовок страницы Успешная оплата"),
        buttonLocator = driver.$("button.btn.btn-primary").as("Кнопка Перейти к видеоконсультации");

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Успешная оплата загрузилась", () -> {
            titleLocator.shouldHave(text(titleText));
            bodyImageLocator.shouldBe(visible);
            subtitleLocator.shouldHave(text(subtitleText));
            buttonLocator.$("span").shouldHave(text("На главную"));
        });
    }

    public void clickButton() {
        stepWithRole("Нажать на кнопку На главную", () -> {
            buttonLocator.click();
        });
    }

}
