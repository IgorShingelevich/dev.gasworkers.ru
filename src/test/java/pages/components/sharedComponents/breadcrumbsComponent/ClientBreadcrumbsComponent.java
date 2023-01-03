package pages.components.sharedComponents.breadcrumbsComponent;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ClientBreadcrumbsComponent {

    SelenideElement homePage = $(".breadcrumb .nuxt-link-active"),
    //$(".breadcrumb li:nth-child(2)"), - learn how to use :nth-child
    OrdersList = $x("//li[@aria-current='page'][contains(.,'Список заказов')]");
    //$(".breadcrumb .breadcrumb-item active"); -not working
    //$(".breadcrumb li:nth-child(3)");



    public ClientBreadcrumbsComponent home() {
        homePage.shouldBe(visible).click();
        return this;
    }

}
