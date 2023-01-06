package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectServicePage {

    public FocusHeaderComponent header = new FocusHeaderComponent();


    private final String
        SELECT_SERVICE_TITLE = "Спасибо! Ваш заказ принят и обрабатывается диспетчерами";


    SelenideElement
        titleLocator = $(".col-12.col-md-6 .text-center"),
        toOrderButtonLocator = $(".fix-row.w-100.d-flex.flex-column-reverse.flex-md-row .mb-3.btn-sm.ms-md-auto.btn.btn-primary.disable-outline"),
        backLinkLocator = $(".col-12.col-md-3 .link-dark-blue.mr-32.medium"),
        spinnerScrollbarLocator = $(".scrollbar.mb-3.col-lg-5 .d-flex.justify-content-center.pb-5"),
        servicesColumnBLockLocator = $(".row.columns-list");//.shouldBe(visible);



    public SelectServicePage isOpened() {
        titleLocator.shouldHave(text(SELECT_SERVICE_TITLE));
        spinnerScrollbarLocator.should(disappear);
        servicesColumnBLockLocator.shouldBe(appear);



        return this;
    }

    public SelectServicePage toOrder() {
        spinnerScrollbarLocator.should(disappear);
        servicesColumnBLockLocator.shouldBe(appear);
        toOrderButtonLocator.shouldBe(visible).click();
        return this;
    }

    public SelectServicePage back() {
        backLinkLocator.click();
        return this;
    }


}
