package pages.client;
import model.browser.RoleBrowser;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponent.headerComponent.FocusHeaderComponent;

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
        titleLocator = driver.$(".col-12.col-md-6 .text-center"),
        toOrderButtonLocator = driver.$(".fix-row.w-100.d-flex.flex-column-reverse.flex-md-row .mb-3.btn-sm.ms-md-auto.btn.btn-primary.disable-outline"),
        backLinkLocator = driver.$(".col-12.col-md-3 .link-dark-blue.mr-32.medium"),
        spinnerScrollbarLocator = driver.$(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5"),
        firstServiceTabLocator =driver.$$("div.col-xl-6.mb-3").first(),
        firstServiceButtonLocator = driver.$$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline").get(0),
        servicesColumnBLockLocator = driver.$(".row.columns-list"),
        mapContainerLocator = driver.$(".ymap-container div.map-into"),
        selectInsurancePageTitleLocator = driver.$(".page-content .h2.text-center.mb-4");

    ElementsCollection
        servicesTabsCollection = driver.$$("div.col-xl-6.mb-3"),
        reviewButtonCollection = driver.$$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline");

    public SelectServicePageClientPage backLink() {
        backLinkLocator.click();
        return this;
    }

//    @DisplayName("Убедиться, что страница Выбор СК загружена")
    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Выбор СК загружена", () -> {
//            spinnerScrollbarLocator.should(disappear);
            firstServiceTabLocator.should(appear, Duration.ofSeconds(40));
//        mapContainerLocator.shouldBe(appear).shouldBe(visible);
            mapContainerLocator.shouldBe(appear, Duration.ofSeconds(40));
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
            reviewButtonCollection.first().shouldBe(visible, Duration.ofSeconds(40)).click();
            selectInsurancePageTitleLocator.shouldBe(visible, Duration.ofSeconds(40));
        });

        return this;
    }

    public SelectServicePageClientPage reviewSecondService() {
        reviewButtonCollection.get(1).click();
        selectInsurancePageTitleLocator.shouldBe(visible);
        return this;
    }


}
