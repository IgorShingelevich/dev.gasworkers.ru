package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepairBuilder;

import static com.codeborne.selenide.Condition.*;

public class SuggestedMasterRepairCommonTabOrderCardComponent extends BaseOrderCardComponent {
    ElementsCollection
            selfCollection = driver.$$("div.master-card").as("Коллекция карточек предложенных мастеров");
    SelenideElement
            subtitleSuggestedMastersTextLocator = driver.$("div.master-card-wrap p.h4").as("Текст подзаголовка Предложенные мастера");

    public SuggestedMasterRepairCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkSubtitleSuggestedMastersText() {
        stepWithRole("Проверить текст подзаголовка Предложенные мастера", () -> {
            subtitleSuggestedMastersTextLocator.shouldHave(text("Отклики компаний и мастеров"));
        });
    }

    public void checkMasterCountSuggestedMaster(Integer count) {
        stepWithRole("Убедиться, что количество предложенных мастеров равно " + count, () -> {
            checkSubtitleSuggestedMastersText();
            selfCollection.shouldHave(CollectionCondition.size(count));
        });
    }

    public void checkNoSuggestedMastersRepair() {
        stepWithRole("Убедиться, что нет предложенных мастеров", () -> {
            subtitleSuggestedMastersTextLocator.shouldNotBe(visible);
            selfCollection.shouldHave(CollectionCondition.size(0));
        });
    }

    public void checkFullNameSuggestedMaster(Integer masterIndex, String fullName) {
        stepWithRole("Проверить ФИО мастера", () -> {
            selfCollection.get(masterIndex).$("div.master-card-wrap div.name").shouldHave(text(fullName));
        });
    }

    public void checkAvatarSuggestedMaster(Integer masterIndex, String avatar) {
        stepWithRole("Проверить аватар мастера", () -> {
            selfCollection.get(masterIndex).$("div.profile-image img").shouldHave(attribute("src", avatar));
        });
    }

    public void checkRegisterDateSuggestedMaster(Integer masterIndex, String registerDate) {
        stepWithRole("Проверить дату регистрации мастера", () -> {
            selfCollection.get(masterIndex).$("div.register-date").shouldHave(text(registerDate));
        });
    }

    public void checkRatingSuggestedMaster(Integer masterIndex, String rating) {
        stepWithRole("Проверить рейтинг мастера", () -> {
            selfCollection.get(masterIndex).$("div.rating-badge").shouldHave(partialText(rating));
        });
    }

    public void checkReviewsSuggestedMaster(Integer masterIndex, String reviews) {
        stepWithRole("Проверить количество отзывов мастера", () -> {
            selfCollection.get(masterIndex).$("div.reviews").shouldHave(partialText(reviews));
        });
    }

    public void checkShortWorkPlaceSuggestedMaster(Integer masterIndex, String workPlace) {
        stepWithRole("Проверить место работы мастера", () -> {
            selfCollection.get(masterIndex).$("div.work a").shouldHave(text(workPlace));
        });
    }

    public void checkRatingStarsCountCompanySuggestedMaster(Integer masterIndex, Integer count) {
        stepWithRole("Проверить количество звезд рейтинга компании", () -> {
            selfCollection.get(masterIndex).$(".gas-stars-wrap").$$(".active").shouldHave(CollectionCondition.size(count));
        });
    }

    public void certifiedEquipmentsSuggestedMaster(Integer masterIndex, Integer count) {
        stepWithRole("Проверить наличие сертифицированного оборудования", () -> {
            selfCollection.get(masterIndex).$$("li.equipment-item").shouldHave(CollectionCondition.size(count));
        });
    }

    public void checkSkillsSuggestedMaster(Integer masterIndex, String skills) {
        stepWithRole("Проверить опыт работы мастера", () -> {
            selfCollection.get(masterIndex).$("div.section.work-with").shouldHave(text(skills)).as("Опыт работы мастера");
        });
    }

    public void checkCompletedOrdersSuggestedMaster(Integer masterIndex, String completedOrders) {
        stepWithRole("Проверить количество выполненных заказов мастера", () -> {
            selfCollection.get(masterIndex).$("div.section.order-count").shouldHave(text(completedOrders)).as("Количество выполненных заказов мастера");
        });
    }

    public void checkVisitPriceSuggestedMaster(Integer masterIndex, String visitPrice) {
        stepWithRole("Проверить цену выезда мастера", () -> {
            selfCollection.get(masterIndex).$("p.bag").shouldHave(partialText(visitPrice)).as("Цена выезда мастера");
        });
    }

    public void checkSelectMasterButtonSuggestedMaster(Integer masterIndex) {
        stepWithRole("Проверить кнопку Выбрать мастера", () -> {
            selfCollection.get(masterIndex).$("button.btn span").shouldHave(text("Выбрать мастера"));
        });
    }

    public void clickSelectMasterButton(Integer masterIndex) {
        stepWithRole("Нажать на кнопку Выбрать мастера", () -> {
            selfCollection.get(masterIndex).$("button.btn span").click();
        });
    }

    public void checkHasOfferMastersDetails(SuggestedMasterRepairCommonTabOrderCardComponent tab, StateRepairBuilder.SuggestedMasterRepairCommonTabOrderCardComponent dataSuggestedMaster, Integer masterIndex) {
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
    }
}
