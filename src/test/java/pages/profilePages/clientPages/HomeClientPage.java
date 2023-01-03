package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.LastOrderProfileClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomeClientPage extends BaseClientPage {

    public LastOrderProfileClientComponent lastOrder = new LastOrderProfileClientComponent();





    private final String OBJECTS_TITLE = "Объекты и оборудование";



// clientCardBlock
    SelenideElement
            profileCardNameLocator = $(".profile-card .title"),
            profileCardSinceDateLocator = $(".profile-card .since-date"),
            profileCardRatingLocator = $(".profile-card.rating-badge"),
            profileCardReviewsLocator = $(".profile-card .reviews"),
            profileCardImageLocator = $(".profile-card").$(".profile-image");

//objectsBlock
    SelenideElement objectsTitleLocator = $(".client-objects .title"),
            objectsPreviousButtonLocator = $(".client-objects .slick-arrow.slick-prev"),
            objectsNextButtonLocator = $(".client-objects .slick-arrow slick-next");
            ElementsCollection gotoObjectActionCollection = $$(".actions .actions__slot--link");
//            ElementsCollection actionMenuCollection = $$(".actions .actions__slot right"); //not working - menu collection is not visible
            ElementsCollection actionButtonCollection = $$(".actions .actions__btn");












    //  open
    public HomeClientPage open() {
        Selenide.open("/profile/client");
        profileCardNameLocator.shouldBe(visible);

        return this;
    }

    public HomeClientPage isOpened() {
        profileCardNameLocator.shouldBe(visible);
        return this;
    }

    //  profile card

    public HomeClientPage verifyClientCardInfo(String clientName, String sinceDate, String rating, String reviews, String image) {
        profileCardNameLocator.shouldHave(text(clientName)).shouldBe(visible);
        profileCardSinceDateLocator.shouldHave(text(sinceDate)).shouldBe(visible);
        profileCardRatingLocator.shouldHave(text(rating)).shouldBe(visible);
        profileCardReviewsLocator.shouldHave(text(reviews)).shouldBe(visible);
        profileCardImageLocator.shouldHave(attribute("src", image)).shouldBe(visible);
        return this;
    }









    public HomeClientPage clickLastOrderNumberLink() {
        this.lastOrder.clickLastOrderNumberLink();
        return this;
    }

    //  objects

    public HomeClientPage verifyObjectsBlockInfo() {
        objectsTitleLocator.shouldHave(text(OBJECTS_TITLE)).shouldBe(visible);
        objectsPreviousButtonLocator.shouldBe(visible);
        objectsNextButtonLocator.shouldBe(visible);
        return this;
    }


    public HomeClientPage clickObjectsPreviousButton() {
        objectsPreviousButtonLocator.shouldBe(visible).click();
        return this;
    }

    public HomeClientPage clickObjectsNextButton() {
        objectsNextButtonLocator.shouldBe(visible).click();
        return this;
    }


    public HomeClientPage goto1thObjectActionButtonLocator() {
        actionButtonCollection.get(3).hover();
//        actionMenuCollection.get(3).shouldBe(visible); //not working - menu collection is not visible
        actionButtonCollection.get(3).click();
        gotoObjectActionCollection.get(3).click();
        return this;
    }








}
