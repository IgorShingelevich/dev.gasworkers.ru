package pages.client;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;

import java.util.List;

import static com.codeborne.selenide.Condition.*;

public final class InfoTypeOrderClientPage extends BaseClientPage {

    public InfoTypeOrderClientPage(RoleBrowser browser) {
        super(browser);
    }

    @Step("Check type order title")
    public InfoTypeOrderClientPage checkTitle(String expectedTitle) {
        driver.$("p.h4").shouldHave(Condition.text(expectedTitle));
        return this;
    }

    @Step("Check step sequence")
    public InfoTypeOrderClientPage checkStepSequence(List<String> expectedSteps) {
        driver.$$(".gas-box li").shouldHave(CollectionCondition.texts(expectedSteps));
        return this;
    }

    @Step("Click next button")
    public void clickNextButton() {
        driver.$(".btn-wrap button").shouldHave(text("Далее")).click();
    }

}

