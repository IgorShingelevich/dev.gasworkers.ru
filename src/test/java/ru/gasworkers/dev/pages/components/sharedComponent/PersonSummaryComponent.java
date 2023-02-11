package ru.gasworkers.dev.pages.components.sharedComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.text;

public class PersonSummaryComponent extends BaseComponent {
    // TODO include  to  reviewPage, chatPage, clientHomePage
    //TODO Master rating with description
    public PersonSummaryComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
        // clientBlock
        profileBlockFullNameLocator = driver.$(".profile-card .title").as("Full name"),
        profileBlockSinceDateLocator = driver.$(".profile-card .since-date").as("Registration date"),
        profileBlockRatingLocator = driver.$("span.rating-badge").as("Rating"),
        profileBlockReviewsCountLocator = driver.$(".profile-card .reviews").as("Reviews count"),
        profileBlockImageLocator = driver.$(".profile-card").$(".profile-image");

    public void checkFullName(String fullName) {
        stepWithRole("Убедиться, что  полное имя: " + fullName, () -> {
            profileBlockFullNameLocator.shouldHave(Condition.text(fullName));
        });
    }

    public void checkSinceProfileDate(String sinceProfileDate) {
        stepWithRole("Убедиться, что дата регистрации: " + sinceProfileDate, () -> {
            profileBlockSinceDateLocator.shouldHave(Condition.text(sinceProfileDate));
        });
    }

    public void checkRating(String rating) {
        stepWithRole("Убедиться, что рейтинг: " + rating, () ->
                profileBlockRatingLocator.shouldHave(Condition.text(rating))
        );
    }

    public void checkReviewsCount(String reviewsCount) {
        stepWithRole("Убедиться, что количество отзывов: " + reviewsCount, () ->
                profileBlockReviewsCountLocator.shouldHave(Condition.text(reviewsCount))
        );
    }

    public void checkInitialState(String fullName, String sinceProfileDate) {
        stepWithRole("Убедиться, секция персональных данных содержит: ", () -> {
            checkFullName(fullName);
            stepWithRole("Дата регистрации: " + sinceProfileDate, () -> {
                profileBlockSinceDateLocator.as("Profile since date").shouldHave(text("Зарегистрирован с " + sinceProfileDate));
            });
            checkRating("5.00");
            checkReviewsCount("0");
        });
    }

    public void checkPersonSummary(String fullName, String sinceProfileDate, String rating, String reviewsCount) {
        stepWithRole("Убедиться, секция персональных данных содержит: ", () -> {
            checkFullName(fullName);
            stepWithRole("Дата регистрации: " + sinceProfileDate, () -> {
                profileBlockSinceDateLocator.as("Profile since date").shouldHave(text("Зарегистрирован с " + sinceProfileDate));
            });
            checkRating(rating);
            checkReviewsCount(reviewsCount);
        });
    }

    public void checkFinishLoading(String fullName, String sinceDate) {
        stepWithRole("Убедиться, что секция персональных данных загрузилась", () -> {
            checkFullName(fullName);
            checkSinceProfileDate(sinceDate);
            profileBlockFullNameLocator.shouldBe(Condition.visible);
            profileBlockSinceDateLocator.shouldBe(Condition.visible);
            profileBlockRatingLocator.shouldBe(Condition.visible);
            profileBlockReviewsCountLocator.shouldBe(Condition.visible);
            profileBlockImageLocator.shouldBe(Condition.visible);
        });
    }
}
