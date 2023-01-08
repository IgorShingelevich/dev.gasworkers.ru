package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LastOrderCardClientPage extends BaseClientPage{

    SelenideElement
        toMapButtonLocator = $(".col-md-12.text-right .map-ic.ms-md-auto.btn.btn-outline-primary.disable-outline"),
        cancelOrderLinkLocator = $(".col-md-12.text-right.pt-3 .btn.btn-link-dashed.disable-outline");

    public LastOrderCardClientPage isOpened() {
        toMapButtonLocator.shouldBe(visible);
        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }

    public LastOrderCardClientPage toMap() {
        toMapButtonLocator.scrollTo().click();
        return this;
    }

    public LastOrderCardClientPage cancelOrder() {
        cancelOrderLinkLocator.scrollTo().click();
        return this;
    }
}
