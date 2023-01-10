package pages.components.sharedComponents.sidebarComponents;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SidebarDispatcherComponent {

    private final String
         SUPPORT_SERVICE_PHONE = "8 800 302 89 04",
         SUPPORT_SERVICE_EMAIL = "info@gasworkers.ru",
            DISPATCHER_HOMEPAGE_TITLE = "Заказы";


    SelenideElement
            dispatcherHomePageTitleLocator = $(".page-title .h3.mb-2"),
            listViewButtonLocator = $("div.action-btn.list-type"),
            mapContainerLocator = $("div.map-wrap"),

            homeLinkLocator = $$(".sidebar .link").get(0),
            mastersListLinkLocator = $$(".sidebar .link").get(1),
            profileDispatcherLinkLocator = $$(".sidebar .link").get(2),
            supportServiceTitleLocator = $(".support-service"),
            supportServicePhoneLocator = $(".support-service__phone"),
            supportServiceEmailLocator = $(".support-service .link-dark-blue");


    public SidebarDispatcherComponent verifyLocators() {

        homeLinkLocator.shouldBe(visible);
        mastersListLinkLocator.shouldBe(visible);
        profileDispatcherLinkLocator.shouldBe(visible);
        supportServiceTitleLocator.shouldBe(visible);
        supportServicePhoneLocator.shouldBe(visible);
        supportServiceEmailLocator.shouldBe(visible);
        return this;
    }

    public SidebarDispatcherComponent home() {
        homeLinkLocator.shouldBe(visible).click();
        listViewButtonLocator.shouldBe(visible).click();
        if (mapContainerLocator.isDisplayed()) {
            mapContainerLocator.shouldBe(visible);
        }
        return this;
    }

    public SidebarDispatcherComponent allMasters() {
        mastersListLinkLocator.shouldBe(visible).click();
        return this;
    }

    public SidebarDispatcherComponent profile() {
        profileDispatcherLinkLocator.shouldBe(visible).click();
        return this;
    }
















}


/*TODO
     arhive Orders   //check that the order with this number is not visible in the list of orderNumberLinkCollection
*
* */