package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.buttons;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Selectors.byTagAndText;

public class ClientButtonsOrderCardComponent extends BaseOrderCardComponent {
    public ClientButtonsOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            toMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")).as("Кнопка Показать на карте"),
            cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Кнопка Отменить заказ"),
            payBillButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")).as("Кнопка Оплатить счет"),
            signButtonLocator = driver.$(byTagAndText("span", "Подписать")).as("Кнопка Подписать"),
            submitAgreementButtonLocator = driver.$("div a.btn-link-custom").as("Кнопка Передать договор"),
            mainButtonLocator = driver.$("div button.btn.btn-primary").as("Главная кнопка"),
            submitReviewButtonLocator = mainButtonLocator.$(byTagAndText("span", "Оставить отзыв")).as("Кнопка Оставить отзыв");


    public void showOnMap() {
        stepWithRole("Нажать на кнопку Показать на карте", () -> {
            toMapButtonLocator.click();
        });
    }

    public void cancelOrder() {
        stepWithRole("Нажать на кнопку Отменить заказ", () -> {
            cancelOrderButtonLocator.click();
        });
    }


}
