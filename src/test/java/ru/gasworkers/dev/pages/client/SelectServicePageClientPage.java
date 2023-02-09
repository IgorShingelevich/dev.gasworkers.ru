package ru.gasworkers.dev.pages.client;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

public class SelectServicePageClientPage extends BaseClientPage {

    private final FocusHeaderComponent header;

    public SelectServicePageClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }

    private final String
        SELECT_SERVICE_TITLE = "Спасибо! Ваш заказ принят и обрабатывается диспетчерами";


    SelenideElement
        titleLocator = driver.$("div h4.text-center").as("Заголовок страницы Выбор СК"),
        toOrderButtonLocator = driver.$("button.mb-3.me-sm-3 span").as("Кнопка Смотреть  заказ"),
        backButtonLocator = driver.$(".col-12.col-md-3 .link-dark-blue.mr-32.medium").as("Кнопка Назад"),
        spinnerScrollbarLocator = driver.$(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5").as("Спиннер загрузки"),
        firstServiceTabLocator = driver.$("[id^=company-item]").as("Первая вкладка Сервисного предложения"),
        firstServiceButtonLocator = driver.$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline").as("Кнопка Выбрать первой вкладки Сервисного предложения"),
        servicesColumnBLockLocator = driver.$(".row.columns-list").as("Левый блок Сервисных предложений"),
        mapContainerLocator = driver.$("[id^=yandexMap]").as("Контейнер карты"),
        mapZoomPlusButtonLocator =  driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты");

    ElementsCollection
        servicesTabsCollection = driver.$$("div.col-xl-6.mb-3"),
        reviewButtonCollection = driver.$$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline").as("Кнопки Выбрать Сервисное предложение");

    public SelectServicePageClientPage backLink() {
        backButtonLocator.click();
        return this;
    }

//    @DisplayName("Убедиться, что страница Выбор СК загружена")
    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            titleLocator.shouldHave(text(SELECT_SERVICE_TITLE));
            firstServiceTabLocator.shouldBe(visible, Duration.ofSeconds(40));
            driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
//            sleep(3_000);
        });
    }

    public SelectServicePageClientPage waitForResponses() {
        stepWithRole("Ожидание ответов", () -> {
            firstServiceButtonLocator.should(appear, Duration.ofSeconds(60));
        });
        return this;
    }

    public SelectServicePageClientPage toOrderCard() {
        stepWithRole("Нажать на кнопку Смотреть Заказ", () -> {
            toOrderButtonLocator.shouldBe(visible).click();
        });
        return this;
    }

    public SelectServicePageClientPage proceedWithFirstService() {
        stepWithRole("Нажать на кнопку Выбрать Компанию ", () -> {
//            reviewButtonCollection.first().shouldBe(visible, Duration.ofSeconds(40)).shouldHave(text("Выбрать")).click();
            reviewButtonCollection.first().shouldBe(visible, Duration.ofSeconds(40))
                    .shouldHave(text("Выбрать")).click();
        });
        return this;
    }
}
