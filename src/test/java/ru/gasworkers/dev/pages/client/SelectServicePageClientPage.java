package ru.gasworkers.dev.pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.clientComponent.OffersCounterClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.repairComponent.CompanyBoxSelectService;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.spinner.MapSpinnerSelectServiceComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.spinner.ServiceSpinnerSelectServiceComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.guideComponent.PlayGuideComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;

public class SelectServicePageClientPage extends BaseClientPage {

    public final FocusHeaderComponent header;
    public final StepperComponent stepper;
    public final ServiceSpinnerSelectServiceComponent serviceSpinner;
    public final MapSpinnerSelectServiceComponent mapSpinner;
    public final OffersCounterClientComponent offersCounter;
    public final PlayGuideComponent guide;
    public final CompanyBoxSelectService companyBoxRepair;
    private final String
            startMaintenancePrefixText = "Ваш заказ",
            endMaintenancePrefixText = "и обрабатывается диспетчерами",
            startRepairPrefixText = "Ожидайте подтверждение заказа диспетчером. Номер заказа ",
            endRepairPrefixText = "Ремонтные работы и запчасти оплачиваются дополнительно к указанной стоимости.",
            publishedText = "Ожидайте подтверждение заказа диспетчером. После подтверждения цвет заказа изменится на зелёный и отобразится стоимость выезда мастера.",
            hasOffer1Text = "После подтверждения цвет заказа изменится на зелёный и отобразится стоимость выезда мастера.",
            hasOffer2Text = "Сервис работает через безопасную сделку, необходимо оплатить 10 руб. для активации сделки и принятия мастера на заказ.";
    SelenideElement
            titleLocator = driver.$(".align-items-center .text-center").as("Заголовок страницы Выбор СК"), // changed driver.$("div h4.text-center")
            banner1Locator = driver.$("div.gas-tip__text .first-text").as("Баннер 1"),
            hasOfferRepairBanner2Locator = driver.$("div.gas-tip__text .second-text").as("Есть отклик Ск - Баннер 2"),
            toOrderButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Смотреть  заказ"),
            backButtonLocator = driver.$(".col-12.col-md-3 .link-dark-blue.mr-32.medium").as("Кнопка Назад"),
            spinnerServicesContainerLocator = driver.$(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5").as("Спиннер загрузки контейнера с Сервисными компаниями"),
            firstServiceTabLocator = driver.$("[id^=company-item]").as("Первая вкладка Сервисного предложения"),
            mapContainerLocator = driver.$("[id^=yandexMap]").as("Контейнер карты");

        public SelectServicePageClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        serviceSpinner = new ServiceSpinnerSelectServiceComponent(browser);
        mapSpinner = new MapSpinnerSelectServiceComponent(browser);
        offersCounter = new OffersCounterClientComponent(browser);
        guide = new PlayGuideComponent(browser);
        companyBoxRepair = new CompanyBoxSelectService(browser);
    }    ElementsCollection
            serviceBoxCollection = driver.$$("[id^='company-item-company-']").as("Блоки Сервисных компаний"),
            boxesWithButtonCollectionOld = serviceBoxCollection.filterBy(cssClass("btn-primary")).as("Блоки с кнопкой Выбрать"),
            boxesWithButtonCollection = serviceBoxCollection.filterBy(attribute("data-test-id", "primary")).as("Блоки с кнопкой Выбрать");

public void checkFinishLoadingRepair() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
            serviceSpinner.checkPresence();
//            spinnerScrollbarLocator.should(disappear);
            assertThat(titleLocator.getText(), startsWith(startRepairPrefixText));
            assertThat(titleLocator.getText(), endsWith(endRepairPrefixText));
            mapContainerLocator.shouldBe(visible, Duration.ofSeconds(40));
            stepWithRole("Убедиться что появился первый таб", () -> {
                firstServiceTabLocator.shouldBe(exist, Duration.ofSeconds(40));
            });
            /*stepWithRole("Убедиться что компонент карты загрузился", () -> {
                driver.refresh();
                mapContainerLocator.shouldBe(visible, Duration.ofSeconds(40));
                driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
            });*/
            offersCounter.checkFinishLoading();
            getOrderNumber();
        });
    }

    public SelectServicePageClientPage backLink() {
        backButtonLocator.click();
        return this;
    }

    //    @DisplayName("Убедиться, что страница Выбор СК загружена")
    public void checkFinishLoadingMaintenance() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/orders/maintenance/");
            serviceSpinner.checkPresence();
            mapSpinner.checkPresence();
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
            offersCounter.checkFinishLoading();
            getOrderNumber();
        });
    }

    public void checkHasOfferBanner() {
        stepWithRole("Убедиться, что отображается баннер Есть отклик СК", () -> {
            banner1Locator.shouldHave(text(hasOffer1Text));
            hasOfferRepairBanner2Locator.shouldHave(text(hasOffer2Text));
        });
    }

    public void checkPublishedBanner() {
        stepWithRole("Убедиться, что отображается баннер Опубликован", () -> {
            banner1Locator.shouldHave(text(publishedText));
        });
    }



    public void checkPublishedState() {
        stepWithRole("Убедиться, что статус карты - Нет тендеров", () -> {
            boxesWithButtonCollectionOld.shouldBe(CollectionCondition.empty);
            offersCounter.noOffers();
            //TODO map component behavior
        });
    }

    public SelectServicePageClientPage waitForResponses() {
        stepWithRole("Ожидание ответов", () -> {
            boxesWithButtonCollectionOld.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(60));
            //TODO map component behavior
        });
        return this;
    }

    public void checkOrderNumber(String orderNumber) {
        stepWithRole("Убедиться, что номер заказа " + orderNumber + " соответствует ожидаемому", () -> {
            titleLocator.shouldHave(partialText(orderNumber));
        });
    }

    public void checkAmountOfferBox(Integer count) {
        stepWithRole("Убедиться, что статус карты - Есть " + count + " тендеров", () -> {
            boxesWithButtonCollection.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1));
            boxesWithButtonCollection.shouldHave(CollectionCondition.size(count));
            //TODO map component behavior
        });
    }


    public SelectServicePageClientPage proceedWithFirstService() {
        stepWithRole("Нажать на кнопку Выбрать Компанию ", () -> {
            boxesWithButtonCollectionOld.first().shouldBe(visible, Duration.ofSeconds(40))
                    .shouldHave(text("Выбрать")).click();
        });
        return this;
    }

    public SelectServicePageClientPage toOrderCard() {
        stepWithRole("Нажать на кнопку Смотреть карточку заказа", () -> {
            toOrderButtonLocator.shouldBe(visible).click();
            urlChecker.urlStartsWith("https://dev.gasworkers.ru/profile/client/orders/");
        });
        return this;
    }

    public void checkState(StateRepair stateRepair, SuggestServicesResponseDto dto) {
        stepWithRole("Убедиться, что статус карты - " + stateRepair, () -> {
            stateRepair.checkSelectServicePage(this, dto);
        });
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

    public void checkUrl() {
        stepWithRole("Убедиться, что url страницы соответствует шаблону", () -> {
            urlChecker.urlContains("select-service");
        });
    }


    public void notAvailable() {
        stepWithRole("Убедиться, что страница не доступна", () -> {
            urlChecker.urlContains("select-service");
            titleLocator.shouldHave(text("Страница не доступна"));
        });
    }

    public void open(String orderId) {
        stepWithRole("Открыть страницу Выбор СК", () -> {
            driver.open("/orders/" + orderId + "/select-service");
        });
    }
}


// TODO describe map behavior
