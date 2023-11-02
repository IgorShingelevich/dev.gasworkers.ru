package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateBuilder;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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
            approvedMasterAvatarBoxLocator.shouldBe(visible, Duration.ofSeconds(10));
//            approvedMasterAvatarLocator.shouldBe(visible, Duration.ofSeconds(10));  // not all the users have avatar
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
    }

    public void checkMasterAvatarBox() {
        stepWithRole("Убедиться, что  бокс аватара мастера отображается", () -> {
            approvedMasterAvatarBoxLocator.shouldBe(visible);
        });
    }

    public void checkMasterFullName(String expectedMasterFullName) {
        stepWithRole("Убедиться, что ФИО мастера: " + expectedMasterFullName, () -> {
            approvedMasterFullNameLocator.shouldHave(text(expectedMasterFullName));
        });
    }

    public void checkStateRepair(StateRepair stateRepair, StateBuilder.OrderIdData dto) {
        step("Проверить, что карточка назначенного мастера в состоянии " + stateRepair, () -> {
            switch (stateRepair) {
                case PUBLISHED:
                case HAS_SUPER_OFFER:
                case HAS_SERVICE_OFFER:
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                case SUPER_DISPATCHER_ASSIGN_SERVICE:
                    noApprovedMasterCard();
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION:
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
                    checkMasterAvatarBox();
                    checkRegisterDate(dto.getMasterRegisterDate());
                    checkReviews(dto.getMasterReviewCount());
                    checkRating(dto.getMasterRating());
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);
            }
        });
    }

    public void checkRegisterDate(String expectedRegisterDate) {
        stepWithRole("Убедиться, что дата регистрации мастера: " + expectedRegisterDate, () -> {
            approvedMasterRegisterDateLocator.shouldHave(text(expectedRegisterDate));
        });
    }    SelenideElement
            self = driver.$("div.master-card-wrap").as("Карточка назначенного мастера"),
            subtitleApprovedMastersTextLocator = driver.$("div.master-card-wrap p.h4").as("Текст подзаголовка назначенного мастера"),
            approvedMasterFullNameLocator = self.$("div.title div.name a").as("ФИО назначенного мастера"),
            approvedMasterAvatarLocator = self.$("div.profile-image div.image img").as("Аватар назначенного мастера"),
            approvedMasterAvatarBoxLocator = self.$("div.profile-image").as("Блок аватара назначенного мастера"),
            approvedMasterRegisterDateLocator = self.$("div.register-date p.text").as("Дата регистрации назначенного мастера"),
            approvedMasterRatingLocator = self.$("div.rating div.rating-badge").as("Рейтинг назначенного мастера"),
            approvedMasterReviewsLocator = self.$("div.rating div.reviews a").as("Количество отзывов назначенного мастера");

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

    public void checkStateConsultation(StateConsultation stateConsultation, StateBuilder.OrderIdData dto) {
        step("Проверить, что карточка назначенного мастера в состоянии " + stateConsultation, () -> {
            switch (stateConsultation) {
                case DRAFT_ONLINE_MASTERS:
                    noApprovedMasterCard();
                    break;
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                case MASTER_COMPLETE_CONSULTATION:
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    checkApprovedMasterCardExists();
                    checkFinishLoading();
                    checkMasterFullName(dto.getMasterFullName());
                    checkMasterAvatarBox();
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
