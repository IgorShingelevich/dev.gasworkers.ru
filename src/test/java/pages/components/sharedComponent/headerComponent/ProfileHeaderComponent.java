package pages.components.sharedComponent.headerComponent;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ProfileHeaderComponent {


SelenideElement
            logoLocator = $("#gas__content-header .logo"),
//          $(".logo-wrap .logo"),


            orderButtonLocator = $(".btn-block .small.btn.btn-warning.disable-outline");


    public ProfileHeaderComponent clickLogo() {
        logoLocator.shouldBe(visible).click();
        return this;
    }

    public ProfileHeaderComponent clickOrderButton() {
        orderButtonLocator.shouldBe(visible).click();
        return this;
    }

}
