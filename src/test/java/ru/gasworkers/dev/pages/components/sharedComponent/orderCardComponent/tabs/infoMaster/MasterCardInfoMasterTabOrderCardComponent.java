package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.*;

public class MasterCardInfoMasterTabOrderCardComponent extends BaseOrderCardComponent {
    public MasterCardInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что карточка мастера загрузилась", () -> {
            self.shouldBe(visible);
            masterFullNameLocator.shouldBe(visible);
            masterAvatarLocator.shouldBe(visible);
            registerDateLocator.shouldBe(visible);
            ratingLocator.shouldBe(visible);
            reviewsLocator.shouldBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.master-card").as("Карточка мастера"),
            masterFullNameLocator = self.$("div.title div.name a").as("ФИО мастера"),
            masterAvatarLocator = self.$("div.profile-image div.image img").as("Аватар мастера"),
            registerDateLocator = self.$("div.register-date p.text").as("Дата регистрации мастера"),
            ratingLocator = self.$("div.rating div.rating-badge").as("Рейтинг мастера"),
            reviewsLocator = self.$("div.rating div.reviews a").as("Количество отзывов мастера");

    public void checkMasterFullName(String expectedMasterFullName) {
        stepWithRole("Убедиться, что ФИО мастера: " + expectedMasterFullName, () -> {
            masterFullNameLocator.shouldHave(text(expectedMasterFullName));
        });
    }

    public void checkMasterAvatar(String expectedMasterAvatar) {
        stepWithRole("Убедиться, что аватар мастера: " + expectedMasterAvatar, () -> {
            masterAvatarLocator.shouldHave(attribute("src", expectedMasterAvatar));
        });
    }

    public void checkRegisterDate(String expectedRegisterDate) {
        stepWithRole("Убедиться, что дата регистрации мастера: " + expectedRegisterDate, () -> {
            registerDateLocator.shouldHave(text(expectedRegisterDate));
        });
    }

    public void checkRating(String expectedRating) {
        stepWithRole("Убедиться, что рейтинг мастера: " + expectedRating, () -> {
            ratingLocator.shouldHave(text(expectedRating));
        });
    }

    public void checkReviews(String expectedReviews) {
        stepWithRole("Убедиться, что количество отзывов мастера: " + expectedReviews, () -> {
            reviewsLocator.shouldHave(text(expectedReviews));
        });
    }


}
