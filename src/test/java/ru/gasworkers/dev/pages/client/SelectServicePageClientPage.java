package ru.gasworkers.dev.pages.client;
import com.codeborne.selenide.CollectionCondition;
import ru.gasworkers.dev.model.browser.RoleBrowser;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.pages.components.clientComponent.guideComponent.FirstMaintenanceGuideComponent;
import ru.gasworkers.dev.pages.components.clientComponent.OffersClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

public class SelectServicePageClientPage extends BaseClientPage {

    public final FocusHeaderComponent header;
    public final StepperComponent stepper;
    public final OffersClientComponent offers;
    public final FirstMaintenanceGuideComponent guideFirst;


    public SelectServicePageClientPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        offers = new OffersClientComponent(browser);
        guideFirst = new FirstMaintenanceGuideComponent(browser);
    }

    private final String
            SELECT_SERVICE_MAINTENANCE_TITLE = "Спасибо! Ваш заказ принят и обрабатывается диспетчерами",
            SELECT_SERVICE_REPAIR_TITLE = "Ваш заказ принят и обрабатывается диспетчерами";


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
    public void checkFinishMaintenanceLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            titleLocator.shouldHave(text(SELECT_SERVICE_MAINTENANCE_TITLE));
            firstServiceTabLocator.shouldBe(visible, Duration.ofSeconds(40));
            driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
            offers.checkFinishLoading();
        });
    }

    public void checkFinishRepairLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            titleLocator.shouldHave(text(SELECT_SERVICE_REPAIR_TITLE));
            firstServiceTabLocator.shouldBe(visible, Duration.ofSeconds(40));
            driver.$("[class*=zoom__plus]").as("Кнопка увеличения карты").shouldBe(visible, Duration.ofSeconds(40));
            offers.checkFinishLoading();
        });
    }

    public void checkPublishedState() {
        stepWithRole("Убедиться, что статус карты - Нет тендеров", () -> {
            reviewButtonCollection.shouldBe(CollectionCondition.empty);
            offers.noOffers();
            //TODO map component behavior
        });
    }

    public SelectServicePageClientPage waitForResponses() {
        stepWithRole("Ожидание ответов", () -> {
            reviewButtonCollection.shouldHave(CollectionCondition.sizeGreaterThan(0), Duration.ofSeconds(60));
            //TODO map component behavior
        });
        return this;
    }

    public void checkResponseState(Integer count) {
        stepWithRole("Убедиться, что статус карты - Есть " + count + " тендеров", () -> {
            reviewButtonCollection.shouldHave(CollectionCondition.size(count));
            offers.haveOffers(count);
            //TODO map component behavior
        });
    }

    public SelectServicePageClientPage toOrderCard() {
        stepWithRole("Нажать на кнопку Смотреть карточку заказа", () -> {
            toOrderButtonLocator.shouldBe(visible).click();
        });
        return this;
    }

    public SelectServicePageClientPage proceedWithFirstService() {
        stepWithRole("Нажать на кнопку Выбрать Компанию ", () -> {
            reviewButtonCollection.first().shouldBe(visible, Duration.ofSeconds(40))
                    .shouldHave(text("Выбрать")).click();
        });
        return this;
    }
}
// TODO describe map behavior
