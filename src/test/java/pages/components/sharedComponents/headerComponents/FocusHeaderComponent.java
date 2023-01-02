package pages.components.sharedComponents.headerComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FocusHeaderComponent {


    /**
     * <header data-v-669fe65c="" data-v-eb791b80="" id="gas__header" class="container"><div data-v-669fe65c="" class="logo"><a data-v-669fe65c="" href="/" class="nuxt-link-active"><img data-v-669fe65c="" src="/_ipx/_/images/logo-blue.svg" alt="Gasworkers"> <span data-v-669fe65c="">Gasworkers</span></a></div> <div data-v-669fe65c="" class="link-gray-wrap"><a data-v-669fe65c="" class="link-gray">
     *       Служба поддержки
     *     </a> <a data-v-669fe65c="" href="" class="link-dark-blue">
     *       Выйти
     *     </a></div></header>*/

    SelenideElement logoLocator = $("#gas__header .logo"),
            //$("#gas__header .logo"),
    // $x("//span[contains(.,'Gasworkers')]"),
    // $x("//div[@class='logo']"),
            linkSupportLocator = $x("//a[contains(text(), 'Служба поддержки')]"),
   // $x("//a[contains(@class,'link-gray')]"),
   linkExitLocator = $x("//a[contains(text(), 'Выйти')]");


public FocusHeaderComponent clickLogo() {
    logoLocator.shouldBe(visible).click();
    return this;
}

    public FocusHeaderComponent clickLinkExit() {
        linkExitLocator.shouldBe(visible).click();
        return this;
    }

    public FocusHeaderComponent clickLinkSupport() {
        linkSupportLocator.shouldBe(visible).click();
        return this;
    }



}
