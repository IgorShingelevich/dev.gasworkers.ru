package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.io.FileNotFoundException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class orderCardClientPage extends BaseClientPage{

    private final String
        LAST_ORDER_CARD_TITLE = "Заказ №",
        COMPLETE_ORDER_INFO = "Договор техобслуживания ВДГО необходимо предоставить в вашу газораспределительную компанию. Оставьте отзыв на работу мастера и вы сможете передать договор в вашу газораспределительную компанию";


    SelenideElement
        titleLocator = $(".h3.mb-2"),
        completeOrderInfoLocator = $(".hint-box p"),
        toMapButtonLocator = $(".col-md-12.text-right .map-ic.ms-md-auto.btn.btn-outline-primary.disable-outline"),
        cancelOrderLinkLocator = $(".col-md-12.text-right.pt-3 .btn.btn-link-dashed.disable-outline"),
        finalPriceLocator = $(".big.bold.d-flex.justify-content-between.w-100.mb-4"),
        statusOrderLocator = $(".item-flex p.text");

    ElementsCollection
        navigationBlockCollection = $$("div.navigation-block li"),
        docsDownloadCollection = $$(".link-pdf-wrap .ic");
    // $$("a.link-pdf-download")


    public orderCardClientPage isOpened() {
        titleLocator.shouldBe(visible).shouldHave(text(LAST_ORDER_CARD_TITLE));
//        toMapButtonLocator.shouldBe(visible);
//        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }

    public orderCardClientPage commonNav(){
        navigationBlockCollection.get(0).click();
        return this;
    }

    public orderCardClientPage masterNav(){
        navigationBlockCollection.get(1).click();
        return this;
    }

    public orderCardClientPage docsNav(){
        navigationBlockCollection.get(2).click();
        return this;
    }

    public orderCardClientPage docsAgreementDownload() throws FileNotFoundException {
        // href="/docs/Договор ТО ВДГО.pdf"
        docsDownloadCollection.get(1).$("a.link-pdf-download").attr("href");
        return this;
    }

    public orderCardClientPage docsCompletionActDownload() throws FileNotFoundException {
        // no href
        return this;
    }

    public orderCardClientPage docsInsuranceDownload() throws FileNotFoundException {
        // no href
        return this;
    }



    public orderCardClientPage isNewState() {
        toMapButtonLocator.shouldBe(visible);
        cancelOrderLinkLocator.shouldBe(visible);
        return this;
    }

    public orderCardClientPage isCompleteState() {
        completeOrderInfoLocator.shouldBe(visible).shouldHave(text(COMPLETE_ORDER_INFO));
        statusOrderLocator.shouldBe(visible).shouldHave(text("Завершен"));
//        finalPriceLocator.shouldBe(visible);
        docsNav();
        docsDownloadCollection.get(0).shouldBe(visible);
        docsDownloadCollection.get(1).shouldBe(visible);
        docsDownloadCollection.get(2).shouldBe(visible);
        return this;
    }



    public orderCardClientPage toMap() {
        toMapButtonLocator.scrollTo().click();
        return this;
    }

    public orderCardClientPage cancelOrder() {
        cancelOrderLinkLocator.scrollTo().click();
        return this;
    }
}
