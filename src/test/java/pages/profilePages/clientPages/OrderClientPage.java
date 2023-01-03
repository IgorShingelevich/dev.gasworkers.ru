package pages.profilePages.clientPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class OrderClientPage extends BaseClientPage{

    SelenideElement
        toMapButtonLocator = $(".col-md-12.text-right .map-ic.ms-md-auto.btn.btn-outline-primary.disable-outline"),
        cancelOrderLinkLocator = $(".col-md-12.text-right.pt-3 .btn.btn-link-dashed.disable-outline");

    public OrderClientPage toMap() {
        toMapButtonLocator.click();
        return this;
    }

    public OrderClientPage cancelOrder() {
        cancelOrderLinkLocator.click();
        return this;
    }
}
