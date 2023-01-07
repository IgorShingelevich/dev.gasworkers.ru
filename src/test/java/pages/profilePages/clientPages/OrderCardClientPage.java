package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class OrderCardClientPage extends BaseClientPage{

    SelenideElement
        toMapButtonLocator = $(".col-md-12.text-right .map-ic.ms-md-auto.btn.btn-outline-primary.disable-outline"),
        cancelOrderLinkLocator = $(".col-md-12.text-right.pt-3 .btn.btn-link-dashed.disable-outline");

    public OrderCardClientPage toMap() {
        toMapButtonLocator.click();
        return this;
    }

    public OrderCardClientPage cancelOrder() {
        cancelOrderLinkLocator.click();
        return this;
    }

    public OrderCardClientPage isOpened() {
        toMapButtonLocator.shouldBe(visible);
        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }
}
