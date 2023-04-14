package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.pages.components.clientComponent.guideComponent.FirstMaintenanceGuideComponent;
import ru.gasworkers.dev.pages.components.clientComponent.OffersClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.SpinnerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SelectServicePageClientPage extends BaseClientPage {

    public final FocusHeaderComponent header;
    public final StepperComponent stepper;
    public final SpinnerComponent spinner;
    public final OffersClientComponent offers;
    public final FirstMaintenanceGuideComponent guideFirst;


    public SelectServicePageClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        spinner = new SpinnerComponent(browser);
        offers = new OffersClientComponent(browser);
        guideFirst = new FirstMaintenanceGuideComponent(browser);
    }

    private final String
            startMaintenancePrefixText = "Ваш заказ",
            endMaintenancePrefixText = "и обрабатывается диспетчерами",
            startRepairPrefixText = "Ожидайте подтверждение заказа диспетчером. Номер заказа ",
            endRepairPrefixText = "Ремонтные работы и запчасти оплачиваются дополнительно к указанной стоимости.";

    SelenideElement
            titleLocator = driver.$(".align-items-center .text-center").as("Заголовок страницы Выбор СК"), // changed driver.$("div h4.text-center")
            toOrderButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Смотреть  заказ"),
            backButtonLocator = driver.$(".col-12.col-md-3 .link-dark-blue.mr-32.medium").as("Кнопка Назад"),
            spinnerServicesContainerLocator = driver.$(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5").as("Спиннер загрузки контейнера с Сервисными компаниями"),
            firstServiceTabLocator = driver.$("[id^=company-item]").as("Первая вкладка Сервисного предложения"),
            mapContainerLocator = driver.$("[id^=yandexMap]").as("Контейнер карты");

    ElementsCollection
            serviceBoxCollection = driver.$$("[id^='company-item-company-']").as("Блоки Сервисных компаний"),
            boxesWithButtonCollection = serviceBoxCollection.filterBy(cssClass("btn-primary")).as("Блоки с кнопкой Выбрать");

    public SelectServicePageClientPage backLink() {
        backButtonLocator.click();
        return this;
    }

    //    @DisplayName("Убедиться, что страница Выбор СК загружена")
    public void checkFinishMaintenanceLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/orders/maintenance/");
            spinner.checkPresence();
            assertThat(titleLocator.getText(), startsWith(startMaintenancePrefixText));
            assertThat(titleLocator.getText(), endsWith(endMaintenancePrefixText)); //changed title again
//            titleLocator.shouldHave(text("Спасибо! Ваш заказ принят и обрабатывается диспетчерами")); // title changed
            /*stepWithRole("Убедиться что спиннер появился", () -> {
                spinnerServicesContainerLocator.should(appear);
            });*/
            stepWithRole("Убедиться что спиннер исчез", () -> {
                spinnerServicesContainerLocator.should(disappear, Duration.ofSeconds(40));
            });
            stepWithRole("Убедиться что появился первый таб", () -> {
                firstServiceTabLocator.shouldBe(visible, Duration.ofSeconds(40));
            });
           /* stepWithRole("Убедиться что компонент карты загрузился", () -> {
                // todo map loading optimization
                driver.refresh();
                Selenide.sleep(1000);
                mapContainerLocator.shouldBe(visible, Duration.ofSeconds(40));
                driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
            });*/
            offers.checkFinishLoading();
            getOrderNumber();
        });
    }

    public void checkFinishRepairLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
            spinner.checkPresence();
//            spinnerScrollbarLocator.should(disappear);
            assertThat(titleLocator.getText(), startsWith(startRepairPrefixText));
            assertThat(titleLocator.getText(), endsWith(endRepairPrefixText));
//            titleLocator.shouldHave(text("Ваш заказ принят и обрабатывается диспетчерами"));
            stepWithRole("Убедиться что появился первый таб", () -> {
                firstServiceTabLocator.shouldBe(visible, Duration.ofSeconds(40));
            });
            /*stepWithRole("Убедиться что компонент карты загрузился", () -> {
                driver.refresh();
                mapContainerLocator.shouldBe(visible, Duration.ofSeconds(40));
                driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
            });*/
            offers.checkFinishLoading();
            getOrderNumber();
        });
    }

    public void checkPublishedState() {
        stepWithRole("Убедиться, что статус карты - Нет тендеров", () -> {
            boxesWithButtonCollection.shouldBe(CollectionCondition.empty);
            offers.noOffers();
            //TODO map component behavior
        });
    }

    public SelectServicePageClientPage waitForResponses() {
        stepWithRole("Ожидание ответов", () -> {
            boxesWithButtonCollection.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(60));
            //TODO map component behavior
        });
        return this;
    }

    public void checkResponseState(Integer count) {
        stepWithRole("Убедиться, что статус карты - Есть " + count + " тендеров", () -> {
            boxesWithButtonCollection.shouldHave(CollectionCondition.size(count));
            //TODO map component behavior
        });
    }

    public SelectServicePageClientPage toOrderCard() {
        stepWithRole("Нажать на кнопку Смотреть карточку заказа", () -> {
            toOrderButtonLocator.shouldBe(visible).click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client/orders/");
        });
        return this;
    }

    public SelectServicePageClientPage proceedWithFirstService() {
        stepWithRole("Нажать на кнопку Выбрать Компанию ", () -> {
            boxesWithButtonCollection.first().shouldBe(visible, Duration.ofSeconds(40))
                    .shouldHave(text("Выбрать")).click();
        });
        return this;
    }

    public String getOrderNumber() {
        return stepWithRole("Получить номер заказа", () -> {
            String text = titleLocator.getText();
            String[] words = text.split("\\s+");
            for (String word : words) {
                if (word.matches("\\d{2}/\\d{2}/\\d{3}/\\d{6}")) {
                    String orderNumber = word.replaceAll("/", "");
                    System.out.println("Order number on map: " + orderNumber.substring(orderNumber.length() - 4));
                    return orderNumber.substring(orderNumber.length() - 4);
                }
            }
            return null;
        });
    }
}


// TODO describe map behavior
