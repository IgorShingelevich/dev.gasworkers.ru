package pages.profilePages;

import com.codeborne.selenide.SelenideElement;
import pages.client.BaseClientPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ObjectCardClientPage {

    private final String OBJECT_CARD_TITLE = "Объекты и оборудование";

    SelenideElement objectCardTitleLocator = $(".page-title .h3.mb-2");

    public ObjectCardClientPage isOpened() {
        objectCardTitleLocator.shouldHave(text(OBJECT_CARD_TITLE));
        return this;
    }


}
