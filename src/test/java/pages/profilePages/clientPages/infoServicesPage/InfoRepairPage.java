package pages.profilePages.clientPages.infoServicesPage;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InfoRepairPage {



    private final String INFO_PAGE_REPAIR_TITLE = "Инструкция по Ремонту";

    SelenideElement
            infoPageRepairTitleLocator = $(".page-content .h4"),
            infoPageRepairButtonLocator = $(".btn.btn-primary.disable-outline");





    public InfoRepairPage isOpened() {
        infoPageRepairTitleLocator.shouldBe(visible).shouldHave(text(INFO_PAGE_REPAIR_TITLE));
        return this;
    }

    public InfoRepairPage nextButton() {
        infoPageRepairButtonLocator.shouldBe(visible).click();
        return this;
    }


}
