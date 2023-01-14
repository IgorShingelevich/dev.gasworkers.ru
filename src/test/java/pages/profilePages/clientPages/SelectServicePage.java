package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelectServicePage {

    public FocusHeaderComponent header = new FocusHeaderComponent();


    private final String
        SELECT_SERVICE_TITLE = "Спасибо! Ваш заказ принят и обрабатывается диспетчерами";


    SelenideElement
        titleLocator = $(".col-12.col-md-6 .text-center"),
        toOrderButtonLocator = $(".fix-row.w-100.d-flex.flex-column-reverse.flex-md-row .mb-3.btn-sm.ms-md-auto.btn.btn-primary.disable-outline"),
        backLinkLocator = $(".col-12.col-md-3 .link-dark-blue.mr-32.medium"),
        spinnerScrollbarLocator = $(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5"),
        firstServiceTabLocator = $$("div.col-xl-6.mb-3").first(),
        firstServiceButtonLocator = $$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline").get(0),
        servicesColumnBLockLocator = $(".row.columns-list"),
        mapContainerLocator = $(".ymap-container div.map-into"),
        selectInsurancePageTitleLocator = $(".page-content .h2.text-center.mb-4");

    ElementsCollection
        servicesTabsCollection = $$("div.col-xl-6.mb-3"),
        reviewButtonCollection = $$(".row.columns-list button.btn.btn-primary.btn-sm.disable-outline");


    public SelectServicePage isOpened() {
//        spinnerScrollbarLocator.should(disappear);
        firstServiceTabLocator.should(appear, Duration.ofSeconds(15));
//        mapContainerLocator.shouldBe(appear).shouldBe(visible);
        mapContainerLocator.shouldBe(appear, Duration.ofSeconds(15));
        return this;
    }

    public SelectServicePage waitForResponses() {
        firstServiceButtonLocator.should(appear, Duration.ofSeconds(40));

     /*   try {
            reviewButtonCollection.wait(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        // isolate titleLocator.wait(6000L) in try-catch block
//        try {
//            titleLocator.wait(6000L);
//        } catch (Exception e) {
//            System.out.println("titleLocator.wait(6000L) failed");
//        }
//        titleLocator.shouldHave(text(SELECT_SERVICE_TITLE));
//        try {
//            spinnerScrollbarLocator.wait(6000L);
//        } catch (Exception e) {
//            System.out.println("spinnerScrollbarLocator.wait(6000L).should(disappear) failed");
//        }
//        spinnerScrollbarLocator.should(disappear);
        return this;
    }

    public SelectServicePage toOrder() {
        toOrderButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectServicePage backLink() {
        backLinkLocator.click();
        return this;
    }

    public SelectServicePage reviewFirstService() {
        reviewButtonCollection.first().click();
        selectInsurancePageTitleLocator.shouldBe(visible);

        return this;
    }

    public SelectServicePage reviewSecondService() {
        reviewButtonCollection.get(1).click();
        selectInsurancePageTitleLocator.shouldBe(visible);
        return this;
    }


}
