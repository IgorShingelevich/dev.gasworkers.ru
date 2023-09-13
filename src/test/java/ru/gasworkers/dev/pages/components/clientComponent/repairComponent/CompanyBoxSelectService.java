package ru.gasworkers.dev.pages.components.clientComponent.repairComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepairHelper;

import static com.codeborne.selenide.Condition.*;

public class CompanyBoxSelectService extends BaseComponent {
    ElementsCollection
            noOfferBoxCollection = driver.$$(".grid [id^='company-item-company-']").as("Блоки без кнопки Выбрать"),
            offerBoxCollection = driver.$$(".grid [id^='company-item-company-']").filterBy(Condition.partialText("Выбрать")).as("Блоки с кнопкой Выбрать");

    public CompanyBoxSelectService(RoleBrowser browser) {
        super(browser);
    }

    public void checkAmountOfferBox(Integer offerCount) {
        stepWithRole("Убедиться, что отображается " + offerCount + " предложений", () -> {
            offerBoxCollection.shouldHave(CollectionCondition.size(offerCount));
        });
    }

    public int getAmountOfferBox() {
        return stepWithRole("Получить количество тендеров", () -> {
            return offerBoxCollection.size();
        });
    }

    public void checkNoOffers() {
        stepWithRole("Убедиться, что отсутствуют предложения", () -> {
            offerBoxCollection.shouldHave(CollectionCondition.size(0));
        });
    }

    public void checkBoxTitle(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается заголовок ", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .text-object p").shouldHave(text("Предложение  сервисной компании"));
        });
    }

    public void checkGeoTag(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается геотег", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldBe(visible);
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldHave(attribute("src", "https://dev.gasworkers.ru/_nuxt/img/location-map.4ebe60e.svg"));

        });
    }

    public void checkRatingCompany(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void getRatingCompany(Integer offerIndex) {
        stepWithRole("Получить рейтинг компании  у" + offerIndex, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-box .item-flex .gas-stars-wrap .gas-stars__star.active").size();
        });
    }

    public void checkAvatarCompany(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .photo-object-company").shouldHave(attribute("src", "https://dev.gasworkers.ru/images/default-logo.svg"));
        });
    }

    public void checkVisitPrice(Integer offerIndex, String price) {
        // round string "3100.0" to "3100"
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble; // Cast the double to an integer to remove the decimal part
        String formattedPrice = String.valueOf(priceInt); // Convert the integer to a string

        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается стоимость первоначального выезда мастера " + formattedPrice, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-success").shouldHave(partialText(formattedPrice));
        });
    }

    public void checkNotificationPaymentAfterArrival(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается уведомление " + " Оплата после приезда мастера", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-orange").shouldHave(text("Оплата после приезда мастера"));
        });
    }

    public void checkFullNameMaster(Integer offerIndex, String masterName) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается полное имя мастера " + masterName, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .text-object p").shouldHave(text(masterName));
        });
    }

    public void checkAvatarMaster(Integer offerIndex, String image) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .photo-object").shouldHave(attribute("src", image));
        });
    }

    public void checkRatingMaster(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void checkMasterReviewCount(Integer offerIndex, String reviews) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается количество отзывов " + reviews, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .item-flex.mb-2 ").shouldHave(partialText(reviews));
        });
    }

    public void checkMasterCompletedOrders(Integer offerIndex, String completedOrders) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается количество выполненных заказов " + completedOrders, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .w-100.d-flex.align-items-center.mb-2 .bag").shouldHave(partialText(completedOrders));
        });
    }

    public void checkButtonActive(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается активная кнопка Выбрать", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").shouldNotBe(disabled);
        });
    }

    public void selectButton(Integer offerIndex) {
        stepWithRole("Нажать на кнопку Выбрать у  предложения  с индексом: " + offerIndex, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").click();
        });
    }

    public void checkOfferBoxHasOfferState(int offerIndex, SuggestServicesResponseDto dto) {
        StateRepairHelper helper = new StateRepairHelper();
        int ratingCompany = helper.getSuggestServiceSuggestedMasterCompanyRating(dto);
        int ratingMaster = helper.getCalculateRatingMaster(dto);
        String visitPrice = helper.getServicePageOfferVisitPrice(dto, offerIndex),
                offeredMasterFullName = helper.getOfferedMasterFullName(dto, offerIndex),
                offeredMasterAvatar = helper.getOfferedMasterAvatar(dto, offerIndex),
                offeredMasterReviewCount = helper.getOfferedMasterReviewCount(dto, offerIndex),
                offeredMasterCompletedOrders = helper.getOfferedMasterCompletedOrders(dto, offerIndex);
        stepWithRole("Проверить детали у предложения под индексом: " + offerIndex, () -> {
            offerBoxCollection.get(offerIndex).shouldBe(visible);
            checkBoxTitle(offerIndex);
            checkGeoTag(offerIndex);
            checkRatingCompany(offerIndex, ratingCompany);
            checkAvatarCompany(offerIndex);
            checkVisitPrice(offerIndex, visitPrice);
            checkNotificationPaymentAfterArrival(offerIndex);
            checkFullNameMaster(offerIndex, offeredMasterFullName);
            checkAvatarMaster(offerIndex, offeredMasterAvatar);
            checkRatingMaster(offerIndex, ratingMaster);
            checkMasterReviewCount(offerIndex, offeredMasterReviewCount);
            checkMasterCompletedOrders(offerIndex, offeredMasterCompletedOrders);
            checkButtonActive(offerIndex);
        });
    }
}
