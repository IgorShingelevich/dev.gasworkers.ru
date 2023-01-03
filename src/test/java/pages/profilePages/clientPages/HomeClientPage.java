package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.LastOrderProfileClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HomeClientPage extends BaseClientPage {






    private final String OBJECTS_TITLE = "Объекты и оборудование";
    public LastOrderProfileClientComponent lastOrder = new LastOrderProfileClientComponent();
//    public SidebarClientComponent sidebar = new SidebarClientComponent();


    String ClientName = "Шингелевич Игорь Сергеевич";

// profile card
    SelenideElement
            profileCardNameLocator = $(".profile-card .title"),
            profileCardSinceDateLocator = $(".profile-card .since-date"),
            profileCardRatingLocator = $(".profile-card.rating-badge"),
            profileCardReviewsLocator = $(".profile-card .reviews"),
            profileCardImageLocator = $(".profile-card").$(".profile-image");

//objects
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

    public HomeClientPage verifyProfileClientName(String clientName) {
        profileCardNameLocator.shouldHave(text(clientName)).shouldBe(visible);
        return this;
    }

    public HomeClientPage verifyProfileClientSinceDate(String sinceDate) {
        profileCardSinceDateLocator.shouldHave(text(sinceDate)).shouldBe(visible);
        return this;
    }

    public HomeClientPage verifyProfileClientRating(String rating) {
        profileCardRatingLocator.shouldHave(text(rating)).shouldBe(visible);
        return this;
    }

    public HomeClientPage verifyProfileClientReviews(String reviews) {
        profileCardReviewsLocator.shouldHave(text(reviews)).shouldBe(visible);
        return this;
    }

    public HomeClientPage verifyProfileClientImage(String image) {
        profileCardImageLocator.shouldHave(attribute("src", image)).shouldBe(visible);
        return this;
    }

    public HomeClientPage clickLastOrderNumberLink() {
        this.lastOrder.clickLastOrderNumberLink();
        return this;
    }


    // last order

/*    public HomeClientPage verifyLastOrderTitle(String title) {
        lastOrderTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }*/

    //  objects

    public HomeClientPage verifyObjectsTitle(String title) {
        objectsTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }

    public HomeClientPage verifyObjectsPreviousButton(String previousButton) {
        objectsPreviousButtonLocator.shouldBe(visible);
        return this;
    }


    public HomeClientPage verifyObjectsNextButton(String nextButton) {
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
