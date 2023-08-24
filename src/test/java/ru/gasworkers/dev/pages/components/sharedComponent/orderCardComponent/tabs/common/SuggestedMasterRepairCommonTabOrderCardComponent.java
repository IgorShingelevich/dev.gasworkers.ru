package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepairBuilder;

import static com.codeborne.selenide.Condition.*;
import static ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair.builder;

public class SuggestedMasterRepairCommonTabOrderCardComponent extends BaseOrderCardComponent {
    ElementsCollection
            selfCollection = driver.$$("div.master-card").as("Коллекция карточек предложенных мастеров");
    SelenideElement
            subtitleSuggestedMastersTextLocator = driver.$("div.master-card-wrap p.h4").as("Текст подзаголовка мастера");

    public SuggestedMasterRepairCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkSubtitleSuggestedMastersText() {
        stepWithRole("Проверить текст подзаголовка Предложенные мастера", () -> {
            subtitleSuggestedMastersTextLocator.shouldHave(text("Отклики компаний и мастеров"));
        });
    }

    public void checkCountSuggestedMaster(Integer count) {
        stepWithRole("Убедиться, что количество предложенных мастеров равно " + count, () -> {
            checkSubtitleSuggestedMastersText();
            driver.$$("div.master-card button").shouldHave(CollectionCondition.size(count));
        });
    }

    public void noSuggestedMastersCard() {
        stepWithRole("Убедиться, что нет предложенных мастеров", () -> {
            driver.$$("div.master-card button").shouldHave(CollectionCondition.size(0));
        });
    }

    public void checkFullNameSuggestedMaster(Integer masterIndex, String fullName) {
        stepWithRole("Проверить ФИО мастера под индексом: " + masterIndex + " на соответствие: " + fullName, () -> {
            selfCollection.get(masterIndex).$("div.master-card-wrap div.name").shouldHave(text(fullName));
        });
    }

    public void checkAvatarSuggestedMaster(Integer masterIndex, String avatar) {
        stepWithRole("Проверить аватар мастера под индексом: " + masterIndex + " на соответствие: " + avatar, () -> {
            selfCollection.get(masterIndex).$("div.profile-image img").shouldHave(attribute("src", avatar));
        });
    }

    public void checkRegisterDateSuggestedMaster(Integer masterIndex, String registerDate) {
        stepWithRole("Проверить дату регистрации мастера под индексом: " + masterIndex + " на соответствие: " + registerDate, () -> {
            selfCollection.get(masterIndex).$("div.register-date").shouldHave(text(registerDate));
        });
    }

    public void checkRatingSuggestedMaster(Integer masterIndex, String rating) {
        stepWithRole("Проверить рейтинг мастера под индексом: " + masterIndex + " на соответствие: " + rating, () -> {
            selfCollection.get(masterIndex).$("div.rating-badge").shouldHave(partialText(rating));
        });
    }

    public void checkReviewsSuggestedMaster(Integer masterIndex, String reviews) {
        stepWithRole("Проверить количество отзывов мастера под индексом: " + masterIndex + " на соответствие: " + reviews, () -> {
            selfCollection.get(masterIndex).$("div.reviews").shouldHave(partialText(reviews));
        });
    }

    public void checkShortWorkPlaceSuggestedMaster(Integer masterIndex, String workPlace) {
        stepWithRole("Проверить место работы мастера под индексом: " + masterIndex + " на соответствие: " + workPlace, () -> {
            selfCollection.get(masterIndex).$("div.work a").shouldHave(text(workPlace));
        });
    }

    public void checkRatingStarsCountCompanySuggestedMaster(Integer masterIndex, Integer count) {
        stepWithRole("Проверить количество звезд рейтинга компании мастера под индексом: " + masterIndex + " на соответствие: " + count, () -> {
            selfCollection.get(masterIndex).$(".gas-stars-wrap").$$(".active").shouldHave(CollectionCondition.size(count));
        });
    }

    public void certifiedEquipmentsSuggestedMaster(Integer masterIndex, Integer count) {
        stepWithRole("Проверить количество сертификатов оборудования у мастера под индексом: " + masterIndex + " на соответствие: " + count, () -> {
            selfCollection.get(masterIndex).$$("li.equipment-item").shouldHave(CollectionCondition.size(count));
        });
    }

    public void checkSkillsSuggestedMaster(Integer masterIndex, String skills) {
        stepWithRole("Проверить опыт работы мастера под индексом: " + masterIndex + " на соответствие: " + skills, () -> {
            selfCollection.get(masterIndex).$("div.section.work-with").shouldHave(text(skills)).as("Опыт работы мастера");
        });
    }

    public void checkCompletedOrdersSuggestedMaster(Integer masterIndex, String completedOrders) {
        stepWithRole("Проверить количество выполненных заказов мастера под индексом: " + masterIndex + " на соответствие: " + completedOrders, () -> {
            selfCollection.get(masterIndex).$("div.section.order-count").shouldHave(text(completedOrders)).as("Количество выполненных заказов мастера");
        });
    }

    public void checkVisitPriceSuggestedMaster(Integer masterIndex, String visitPrice) {
        stepWithRole("Проверить цену выезда мастера под индексом: " + masterIndex + " на соответствие: " + visitPrice, () -> {
            selfCollection.get(masterIndex).$("p.bag").shouldHave(partialText(visitPrice)).as("Цена выезда мастера");
        });
    }

    public void checkSelectMasterButtonSuggestedMaster(Integer masterIndex) {
        stepWithRole("Проверить кнопку Выбрать мастера под индексом: " + masterIndex, () -> {
            selfCollection.get(masterIndex).$("button.btn span").shouldHave(text("Выбрать мастера"));
        });
    }

    public void clickSelectMasterButton(Integer masterIndex) {
        stepWithRole("Нажать на кнопку Выбрать мастера под индексом: " + masterIndex, () -> {
            selfCollection.get(masterIndex).$("button.btn span").click();
        });
    }

    public void checkSuggestedMastersDetails(SuggestedMasterRepairCommonTabOrderCardComponent tab, StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster, Integer masterIndex) {
        stepWithRole("Проверить детали предложенного мастера под индексом: " + masterIndex, () -> {
            tab.checkSubtitleSuggestedMastersText();
            tab.checkFullNameSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterFullName());
            tab.checkAvatarSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterAvatar());
            tab.checkRegisterDateSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterRegisterDate());
            tab.checkRatingSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterRating());
            tab.checkReviewsSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterReviewCount());
            tab.checkShortWorkPlaceSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterShortWorkPlace());
            tab.checkRatingStarsCountCompanySuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterCompanyRatingStarsCount());
            tab.certifiedEquipmentsSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterCertifiedEquipmentCount());
            tab.checkSkillsSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterSkills());
            tab.checkCompletedOrdersSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterCompletedOrdersCount());
            tab.checkVisitPriceSuggestedMaster(masterIndex, dataSuggestedMaster.getSuggestedMasterVisitPrice());
            tab.checkSelectMasterButtonSuggestedMaster(masterIndex);
        });
    }

    public void statusSet(StateRepair stateRepair, OrdersIdResponseDto dto, Integer masterIndex) {
        stepWithRole("Проверить предложенного мастера под индексом: " + masterIndex, () -> {
            switch (stateRepair) {
                case PUBLISHED:
                case SCHEDULE_DATE:
                case WAIT_MASTER:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                case MASTER_SIGN_ACT:
                    noSuggestedMastersCard();
                    break;
                case HAS_OFFER:
                    StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster = builder.extractMastersOrdersId(dto.getData().getMasters().get(0));
                    checkCountSuggestedMaster(dto.getData().getMasters().size());
                    checkSuggestedMastersDetails(this, dataSuggestedMaster, 0);
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
            }
        });
    }
}
