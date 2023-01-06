package pages.components.sharedComponents.headerComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FocusHeaderComponent {



    SelenideElement logoLocator = $("#gas__header .logo"),
            //$("#gas__header .logo"),
    // $x("//span[contains(.,'Gasworkers')]"),
    // $x("//div[@class='logo']"),
            linkSupportLocator = $x("//a[contains(text(), 'Служба поддержки')]"),
   // $x("//a[contains(@class,'link-gray')]"),
   linkExitLocator = $x("//a[contains(text(), 'Выйти')]");


public FocusHeaderComponent logo() {
    logoLocator.shouldBe(visible).click();
    return this;
}

    public FocusHeaderComponent signOut() {
        linkExitLocator.shouldBe(visible).click();
        return this;
    }

    public FocusHeaderComponent support() {
        linkSupportLocator.shouldBe(visible).click();
        return this;
    }



}
