package pages.client;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.browser.RoleBrowser;
import pages.components.clientComponents.LastOrderProfileClientComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public final class HomeClientPage extends BaseClientPage {

    private final LastOrderProfileClientComponent lastOrderProfileClientComponent;



    public HomeClientPage(RoleBrowser browser) {
        super(browser);
        lastOrderProfileClientComponent = new LastOrderProfileClientComponent(browser);
    }



    private final String OBJECTS_TITLE = "Объекты и оборудование";

    // clientCardBlock
    SelenideElement
        profileCardNameLocator = driver.$(".profile-card .title"),
        profileCardSinceDateLocator = driver.$(".profile-card .since-date"),
        profileCardRatingLocator = driver.$(".profile-card.rating-badge"),
        profileCardReviewsLocator = driver.$(".profile-card .reviews"),
        profileCardImageLocator = driver.$(".profile-card").$(".profile-image");

    //objectsBlock
    SelenideElement
        objectsTitleLocator = driver.$(".client-objects .title"),
        firstObjectLinkLocator = driver.$$x("(//div[contains(@class,'title link-blue text-primary pointer')])").get(4), // 0-3 out of visibility
        objectsPreviousButtonLocator = driver.$(".client-objects .slick-arrow.slick-prev"),
        objectsNextButtonLocator = driver.$(".client-objects .slick-arrow.slick-next");
    ElementsCollection
        gotoObjectActionCollection = driver.$$(".actions .actions__slot--link"),
        actionButtonCollection = driver.$$(".actions .actions__btn");

//    @Step("Open client home page")
    public HomeClientPage open() {
        stepWithRole("Открыть домашнюю страницу", () -> {
            driver.open("/profile/client");
            profileCardNameLocator.shouldBe(visible);
        });
        return this;
    }

//    @Step("Убедиться, что Домашняя страница загружена")
    public HomeClientPage checkFinishLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            driver.$(".client-objects [data-index='0']")
                    .shouldBe(visible, Duration.ofSeconds(10));
        });
        return this;
    }

    @Step("Check that client name is {fio}")
    public HomeClientPage checkFio(String fio) {
        stepWithRole("Проверка  ФИО {fio}", () ->
                driver.$(".profile-card .title")
                        .as("fio")
                        .shouldHave(Condition.text(fio)));
        return this;
    }

//    @Step("Click [Place order] button")
    public void clickPlaceOrderButton() {
        stepWithRole("Нажать кнопку Создать заказ", () ->
                driver.$("#gas__content-header .btn-block")
                        .as("place order button")
                        .shouldBe(interactable)
                        .click()
        );

    }

//    public HomeClientPage verifyClientCardInfo(String clientName, String sinceDate, String rating, String reviews, String image) {
//        profileCardNameLocator.shouldHave(text(clientName)).shouldBe(visible);
//        profileCardSinceDateLocator.shouldHave(text(sinceDate)).shouldBe(visible);
//        profileCardRatingLocator.shouldHave(text(rating)).shouldBe(visible);
//        profileCardReviewsLocator.shouldHave(text(reviews)).shouldBe(visible);
//        profileCardImageLocator.shouldHave(attribute("src", image)).shouldBe(visible);
//        return this;
//    }


    //  objects

//    public HomeClientPage verifyObjectsBlockInfo() {
//        objectsTitleLocator.shouldHave(text(OBJECTS_TITLE)).shouldBe(visible);
//        objectsPreviousButtonLocator.shouldBe(visible);
//        objectsNextButtonLocator.shouldBe(visible);
//        return this;
//    }


//    public HomeClientPage clickObjectsPreviousButton() {
//        objectsPreviousButtonLocator.shouldBe(visible).click();
//        return this;
//    }

//    public HomeClientPage clickObjectsNextButton() {
//        objectsNextButtonLocator.shouldBe(visible).click();
//        return this;
//    }


//    public HomeClientPage goto1thObjectActionButtonLocator() {
//        actionButtonCollection.get(3).hover();
//        actionMenuCollection.get(3).shouldBe(visible); //not working - menu collection is not visible
//        actionButtonCollection.get(3).click();
//        gotoObjectActionCollection.get(3).click();
//        return this;
//    }


//    public HomeClientPage partialTextSearch() {
//        objectsTitleLocator.shouldHave(text("Объекты и"));
//        //sout all the text from the locator objectsTitleLocator.shouldHave(text("Объекты и"))
//        System.out.println("objectsTitleLocator.getText" + objectsTitleLocator.getText());
//        return this;
//    }

}
