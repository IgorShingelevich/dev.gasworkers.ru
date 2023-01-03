package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.clientComponents.LastOrderProfileClientComponent;
import pages.components.sharedComponents.sidebarComponents.SidebarClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ProfileClientPage {






    private final String OBJECTS_TITLE = "Объекты и оборудование";
    public LastOrderProfileClientComponent lastOrder = new LastOrderProfileClientComponent();
    public SidebarClientComponent sidebar = new SidebarClientComponent();


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
    public ProfileClientPage open() {
        Selenide.open("/profile/client");
        profileCardNameLocator.shouldBe(visible);

        return this;
    }


    public ProfileClientPage isOpened() {
        profileCardNameLocator.shouldBe(visible);
        return this;
    }

    //  profile card

    public ProfileClientPage verifyProfileClientName(String clientName) {
        profileCardNameLocator.shouldHave(text(clientName)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientSinceDate(String sinceDate) {
        profileCardSinceDateLocator.shouldHave(text(sinceDate)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientRating(String rating) {
        profileCardRatingLocator.shouldHave(text(rating)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientReviews(String reviews) {
        profileCardReviewsLocator.shouldHave(text(reviews)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyProfileClientImage(String image) {
        profileCardImageLocator.shouldHave(attribute("src", image)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage clickLastOrderNumberLink() {
        this.lastOrder.clickLastOrderNumberLink();
        return this;
    }


    // last order

/*    public ProfileClientPage verifyLastOrderTitle(String title) {
        lastOrderTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }*/

    //  objects

    public ProfileClientPage verifyObjectsTitle(String title) {
        objectsTitleLocator.shouldHave(text(title)).shouldBe(visible);
        return this;
    }

    public ProfileClientPage verifyObjectsPreviousButton(String previousButton) {
        objectsPreviousButtonLocator.shouldBe(visible);
        return this;
    }


    public ProfileClientPage verifyObjectsNextButton(String nextButton) {
        objectsNextButtonLocator.shouldBe(visible);
        return this;
    }

    public ProfileClientPage clickObjectsPreviousButton() {
        objectsPreviousButtonLocator.shouldBe(visible).click();
        return this;
    }

    public ProfileClientPage clickObjectsNextButton() {
        objectsNextButtonLocator.shouldBe(visible).click();
        return this;
    }


    public ProfileClientPage goto1thObjectActionButtonLocator() {
        actionButtonCollection.get(3).hover();
//        actionMenuCollection.get(3).shouldBe(visible); //not working - menu collection is not visible
        actionButtonCollection.get(3).click();
        gotoObjectActionCollection.get(3).click();
        return this;
    }








}
