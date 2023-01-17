package pages.client;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import model.browser.RoleBrowser;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class OrderCardClientPage extends BaseClientPage {

    public OrderCardClientPage (RoleBrowser browser) {
        super(browser);
    }

    private final String
        LAST_ORDER_CARD_TITLE = "Заказ №",
        COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию";


    SelenideElement
        titleNumberLocator = driver.$("h1.h3.mb-2"),
        completeOrderInfoLocator = driver.$(".hint-box p"),
        toMapButtonLocator = driver.$(".col-md-12.text-right .map-ic.ms-md-auto.btn.btn-outline-primary.disable-outline"),
        cancelOrderLinkLocator = driver.$(".col-md-12.text-right.pt-3 .btn.btn-link-dashed.disable-outline"),
        finalPriceLocator = driver.$(".big.bold.d-flex.justify-content-between.w-100.mb-4"),
        statusOrderLocator = driver.$(".item-flex p.text");

    ElementsCollection
        navigationBlockCollection = driver.$$("div.navigation-block li"),
        docsDownloadCollection = driver.$$(".link-pdf-wrap .ic");
    // $$("a.link-pdf-download")


    public OrderCardClientPage checkFinishLoading() {
        titleNumberLocator.shouldBe(visible).shouldHave(text(LAST_ORDER_CARD_TITLE));
//        toMapButtonLocator.shouldBe(visible);
//        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }

    public OrderCardClientPage commonNav(){
        navigationBlockCollection.get(0).click();
        return this;
    }

    public OrderCardClientPage masterNav(){
        navigationBlockCollection.get(1).click();
        return this;
    }

    public OrderCardClientPage docsNav(){
        navigationBlockCollection.get(2).click();
        return this;
    }

    public OrderCardClientPage docsAgreementDownload() throws FileNotFoundException {
        // href="/docs/Договор ТО ВДГО.pdf"
        docsDownloadCollection.get(1).$("a.link-pdf-download").attr("href");
        return this;
    }

    public OrderCardClientPage docsCompletionActDownload() throws FileNotFoundException {
        // no href
        return this;
    }

    public OrderCardClientPage docsInsuranceDownload() throws FileNotFoundException {
        // no href
        return this;
    }



    public OrderCardClientPage isNewState() {
        toMapButtonLocator.shouldBe(visible);
        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }

    public String getOrderNumber() {
        // get last 4 digits from titleNumberLocator text size - 4
        return titleNumberLocator.getText().substring(titleNumberLocator.getText().length() - 4);


    }

    public OrderCardClientPage isCompleteState() {
        completeOrderInfoLocator.shouldBe(visible).shouldHave(text(COMPLETE_ORDER_INFO));
        statusOrderLocator.shouldBe(visible).shouldHave(text("Завершен"));
//        finalPriceLocator.shouldBe(visible);
        docsNav();
        docsDownloadCollection.get(0).shouldBe(visible);
        docsDownloadCollection.get(1).shouldBe(visible);
        docsDownloadCollection.get(2).shouldBe(visible);
        return this;
    }



    public OrderCardClientPage showOnMap() {
        toMapButtonLocator.scrollTo().click();
        return this;
    }

    public OrderCardClientPage cancelOrder() {
        cancelOrderLinkLocator.scrollTo().click();
        return this;
    }
}
