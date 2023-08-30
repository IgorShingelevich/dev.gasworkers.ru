package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.helpers.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepairBuilder;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;

public class ApprovedMasterCardInfoMasterTabOrderCardComponent extends BaseOrderCardComponent {
    public ApprovedMasterCardInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что карточка мастера назначенного загрузилась", () -> {
            self.shouldBe(visible);
            subtitleApprovedMastersTextLocator.shouldHave(text("Ваш мастер"));
            approvedMasterFullNameLocator.shouldBe(visible);
            approvedMasterAvatarLocator.shouldBe(visible, Duration.ofSeconds(3));
            approvedMasterRegisterDateLocator.shouldBe(visible);
            approvedMasterRatingLocator.shouldBe(visible);
            approvedMasterReviewsLocator.shouldBe(visible);
        });
    }

    public void checkApprovedMasterCardExists() {
        stepWithRole("Убедиться, что карточка мастера назначенного существует", () -> {
            self.shouldHave(text("Ваш мастер"));
        });
    }

    public void noApprovedMasterCard() {
        stepWithRole("Убедиться, что карточка назначенного мастера отсутствует", () -> {
            self.$(byTagAndText("p", "Ваш мастер")).shouldNotBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.master-card-wrap").as("Карточка назначенного мастера"),
            subtitleApprovedMastersTextLocator = driver.$("div.master-card-wrap p.h4").as("Текст подзаголовка назначенного мастера"),
            approvedMasterFullNameLocator = self.$("div.title div.name a").as("ФИО назначенного мастера"),
            approvedMasterAvatarLocator = self.$("div.profile-image div.image img").as("Аватар назначенного мастера"),
            approvedMasterRegisterDateLocator = self.$("div.register-date p.text").as("Дата регистрации назначенного мастера"),
            approvedMasterRatingLocator = self.$("div.rating div.rating-badge").as("Рейтинг назначенного мастера"),
            approvedMasterReviewsLocator = self.$("div.rating div.reviews a").as("Количество отзывов назначенного мастера");

    public void checkMasterFullName(String expectedMasterFullName) {
        stepWithRole("Убедиться, что ФИО мастера: " + expectedMasterFullName, () -> {
            approvedMasterFullNameLocator.shouldHave(text(expectedMasterFullName));
        });
    }

    public void checkMasterAvatar(String expectedMasterAvatar) {
        stepWithRole("Убедиться, что аватар мастера: " + expectedMasterAvatar, () -> {
            approvedMasterAvatarLocator.shouldHave(attribute("src", expectedMasterAvatar));
        });
    }

    public void checkRegisterDate(String expectedRegisterDate) {
        stepWithRole("Убедиться, что дата регистрации мастера: " + expectedRegisterDate, () -> {
            approvedMasterRegisterDateLocator.shouldHave(text(expectedRegisterDate));
        });
    }

    public void checkRating(String expectedRating) {
        stepWithRole("Убедиться, что рейтинг мастера: " + expectedRating, () -> {
            approvedMasterRatingLocator.shouldHave(text(expectedRating));
        });
    }

    public void checkReviews(String expectedReviews) {
        stepWithRole("Убедиться, что количество отзывов мастера: " + expectedReviews, () -> {
            approvedMasterReviewsLocator.shouldHave(text(expectedReviews));
        });
    }

    public void checkStateRepair(StateRepair stateRepair, StateRepairBuilder.OrderIdData dto) {
        step("Проверить, что карточка назначенного мастера в состоянии " + stateRepair, () -> {
            switch (stateRepair) {
                case PUBLISHED:
                case HAS_OFFER:
                    noApprovedMasterCard();
                    break;
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                case MASTER_SIGN_ACT:
                case CLIENT_SIGN_ACT:
                    checkApprovedMasterCardExists();
                    checkFinishLoading();
                    checkMasterFullName(dto.getMasterFullName());
                    checkMasterAvatar(dto.getMasterAvatar());
                    checkRegisterDate(dto.getMasterRegisterDate());
                    checkReviews(dto.getMasterReviewCount());
                    checkRating(dto.getMasterRating());
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkStateConsultation(StateConsultation stateConsultation, StateRepairBuilder.OrderIdData dto) {
        step("Проверить, что карточка назначенного мастера в состоянии " + stateConsultation, () -> {
            switch (stateConsultation) {
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case MASTER_FILLED_CONCLUSION:
                case COMPLETED:
                    checkApprovedMasterCardExists();
                    checkFinishLoading();
                    checkMasterFullName(dto.getMasterFullName());
                    checkMasterAvatar(dto.getMasterAvatar());
                    checkRegisterDate(dto.getMasterRegisterDate());
                    checkReviews(dto.getMasterReviewCount());
                    checkRating(dto.getMasterRating());
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }


}
