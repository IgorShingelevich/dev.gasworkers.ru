package ru.gasworkers.dev.pages.components.clientComponent.repairComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepairHelper;

import static com.codeborne.selenide.Condition.*;

public class RespondedCompaniesBoxSelectService extends BaseComponent {
    ElementsCollection
//            offerBoxCollection = driver.$$("div[id^='company-item-company-']").filterBy(Condition.partialText("Выбрать")).as("Блоки с кнопкой Выбрать");
            offerBoxCollection = driver.$$("div[id^='company-item-company-']").as("Блоки с кнопкой Выбрать");

    public RespondedCompaniesBoxSelectService(RoleBrowser browser) {
        super(browser);
    }

    public void checkAmountOfferBox(Integer offerCount) {
        stepWithRole("Убедиться, что отображается " + offerCount + " предложений", () -> {
            offerBoxCollection.shouldHave(CollectionCondition.size(offerCount));
        });
    }

    public void noBox() {
        stepWithRole("Убедиться, что отсутствуют предложения", () -> {
            offerBoxCollection.shouldHave(CollectionCondition.size(0));
        });
    }

    public int getAmountOfferBox() {
        return stepWithRole("Получить количество тендеров", () -> {
            return offerBoxCollection.size();
        });
    }

    public void noOffers() {
        stepWithRole("Убедиться, что отсутствуют предложения", () -> {
            offerBoxCollection.shouldHave(CollectionCondition.size(0));
        });
    }

    public void checkBoxTitle(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается заголовок ", () -> {
            offerBoxCollection.get(offerIndex).$(".h5").shouldHave(text("Предложение  сервисной компании"));
        });
    }

    public void checkGeoTag(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается геотег", () -> {
            offerBoxCollection.get(offerIndex).$(".maintenance-offer-list_map-placemark-icon").shouldBe(visible);
        });
    }

    public void checkServiceRating(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается рейтинг компании " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").as("Количество активных звезд у компании")
                    .shouldHave(CollectionCondition.size(rating));
        });
    }

    public void checkMasterRating(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается рейтинг мастера" + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(1).$$(".active").as("Количество активных звезд у мастера")
                    .shouldHave(CollectionCondition.size(rating));
        });
    }


    public void checkAvatarMaster(Integer offerIndex, String offeredMasterAvatar) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".ava img").shouldHave(attribute("src", offeredMasterAvatar));

        });
    }

    public void checkPossibleVisitPrice(Integer offerIndex, String price) {
        // round string "3100.0" to "3100"
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble; // Cast the double to an integer to remove the decimal part
        String formattedPrice = String.valueOf(priceInt); // Convert the integer to a string

        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается стоимость первоначального выезда мастера " + formattedPrice, () -> {
            offerBoxCollection.get(offerIndex).$$(".item-flex").findBy(text("Выезд мастера:"))
                    .shouldHave(partialText(formattedPrice));
        });
    }

    public void checkPossibleRepairPrice(Integer offerIndex, String price) {
        // round string "3100.0" to "3100"
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble; // Cast the double to an integer to remove the decimal part
        String formattedPrice = String.valueOf(priceInt); // Convert the integer to a string

        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается ориентировочная стоимость ремонта: " + formattedPrice, () -> {
            offerBoxCollection.get(offerIndex).$$(".item-flex").findBy(text("Ориентировочная стоимость ремонта:"))
                    .shouldHave(partialText(formattedPrice));
        });
    }

    public void noFullRepairPrice(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " нет ориентировочной стоимости ремонта", () -> {
            offerBoxCollection.get(offerIndex).$$(".item-flex").findBy(text("Ориентировочная стоимость ремонта:"))
                    .shouldHave(partialText("не определено"));
        });
    }

    public void checkNotificationPaymentAfterArrival(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается уведомление " + " Оплата после приезда мастера", () -> {
            offerBoxCollection.get(offerIndex).$(".bag-orange").shouldHave(text("Оплата после приезда мастера"));
        });
    }

    public void checkFirstNameMaster(Integer offerIndex, String firstName) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается  имя мастера " + firstName, () -> {
            offerBoxCollection.get(offerIndex).$$(" .text-wrap .small").get(1).shouldHave(text(firstName));
        });
    }

    public void checkMiddleNameMaster(Integer offerIndex, String middleName) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается  отчество мастера " + middleName, () -> {
            offerBoxCollection.get(offerIndex).$$(" .text-wrap .small").get(2).shouldHave(text(middleName));
        });
    }

    public void checkLastNameMaster(Integer offerIndex, String lastName) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + " отображается  фамилия мастера " + lastName, () -> {
            offerBoxCollection.get(offerIndex).$$(" .text-wrap .small").get(0).shouldHave(text(lastName));
        });
    }

    public void checkMasterReviewCount(Integer offerIndex, String reviews) {
        String stepName = reviews.equals("0") ?
                "Убедиться, что у предложения с индексом: " + offerIndex + " отображается текст 'Нет отзывов'" :
                "Убедиться, что у предложения с индексом: " + offerIndex + " отображается количество отзывов " + reviews;

        stepWithRole(stepName, () -> {
            SelenideElement reviewCountLocator = offerBoxCollection.get(offerIndex).$("div.medium.text-sm.w-100");
            int reviewCount = Integer.parseInt(reviews);
            if (reviewCount == 0) {
                reviewCountLocator.shouldHave(partialText("Нет отзывов"));
            } else {
                reviewCountLocator.shouldHave(partialText(reviews));
            }
        });
    }


    public void checkMasterCompletedOrders(Integer offerIndex, String completedOrders) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается количество выполненных заказов " + completedOrders, () -> {
            offerBoxCollection.get(offerIndex).$(" div.bag.small.ms-auto.px-3").shouldHave(partialText(completedOrders));
        });
    }

    public void checkButtonActive(Integer offerIndex) {
        stepWithRole("Убедиться, что у  предложения  с индексом: " + offerIndex + "  отображается активная кнопка Выбрать", () -> {
            offerBoxCollection.get(offerIndex).$("button").shouldNotBe(disabled);
        });
    }

    public void selectButton(Integer offerIndex) {
        stepWithRole("Нажать на кнопку Выбрать у  предложения  с индексом: " + offerIndex, () -> {
            offerBoxCollection.get(offerIndex).$("button").click();
        });
    }

    public void checkOfferBoxHasOfferState(int offerIndex, SuggestServicesResponseDto dto) {
        StateRepairHelper helper = new StateRepairHelper();
        int ratingService = helper.getServiceStarsSimple(dto, offerIndex);
        int ratingMaster = helper.getMasterStarsSimple(dto, offerIndex);
        String possibleVisitPrice = helper.getServicePageOfferPossibleVisitPrice(dto, offerIndex),
                possibleRepairPrice = helper.getServicePageOfferPossibleRepairPrice(dto, offerIndex),
                offerMasterFirstName = helper.getOfferMasterFirstName(dto, offerIndex),
                offerMasterMiddleName = helper.getOfferMasterMiddleName(dto, offerIndex),
                offerMasterLastName = helper.getOfferMasterLastName(dto, offerIndex),
                offeredMasterAvatar = helper.getOfferedMasterAvatar(dto, offerIndex),
                offeredMasterReviewCount = helper.getOfferedMasterReviewCount(dto, offerIndex),
                offeredMasterCompletedOrders = helper.getOfferedMasterCompletedOrders(dto, offerIndex);
        stepWithRole("Проверить детали у предложения под индексом: " + offerIndex, () -> {
            offerBoxCollection.get(offerIndex).shouldBe(visible);
            checkBoxTitle(offerIndex);
            checkGeoTag(offerIndex);
            checkServiceRating(offerIndex, ratingService);
            checkAvatarMaster(offerIndex, offeredMasterAvatar);
            checkPossibleVisitPrice(offerIndex, possibleVisitPrice);
            checkPossibleRepairPrice(offerIndex, possibleRepairPrice);
            checkNotificationPaymentAfterArrival(offerIndex);
            checkFirstNameMaster(offerIndex, offerMasterFirstName);
            checkMiddleNameMaster(offerIndex, offerMasterMiddleName);
            checkLastNameMaster(offerIndex, offerMasterLastName);
            checkMasterRating(offerIndex, ratingMaster);
            checkMasterReviewCount(offerIndex, offeredMasterReviewCount);
            checkMasterCompletedOrders(offerIndex, offeredMasterCompletedOrders);
            checkButtonActive(offerIndex);
        });
    }
}
