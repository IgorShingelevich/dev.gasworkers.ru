package ru.gasworkers.dev.pages.components.clientComponent.repairComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class CompanyBoxSelectService extends BaseComponent {
    public CompanyBoxSelectService(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            noOfferBoxCollection = driver.$$(".grid [id^='company-item-company-']").as("Блоки без кнопки Выбрать"),
            offerBoxCollection = driver.$$(".grid [id^='company-item-company-']").filterBy(Condition.partialText("Выбрать")).as("Блоки с кнопкой Выбрать");

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
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается заголовок " + "Предложение  сервисной компании", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .text-object p").shouldHave(text("Предложение  сервисной компании"));
        });
    }

    public void checkGeoTag(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается геотег", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldBe(visible);
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldHave(attribute("src", "https://dev.gasworkers.ru/_nuxt/img/location-map.4ebe60e.svg"));

        });
    }

    public void checkRatingCompany(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения компании отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void getRatingCompany(Integer offerIndex) {
        stepWithRole("Получить рейтинг компании  у" + (offerIndex + 1) + " предложения", () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-box .item-flex .gas-stars-wrap .gas-stars__star.active").size();
        });
    }

    public void checkAvatarCompany(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .photo-object-company").shouldHave(attribute("src", "https://dev.gasworkers.ru/images/default-logo.svg"));
        });
    }

    public void checkVisitPrice(Integer offerIndex, String price) {
        // round string "3100.0" to "3100"
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble; // Cast the double to an integer to remove the decimal part
        String formattedPrice = String.valueOf(priceInt); // Convert the integer to a string

        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается стоимость первоначального выезда мастера " + formattedPrice, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-success").shouldHave(partialText(formattedPrice));
        });
    }

    public void checkNotificationPaymentAfterArrival(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается уведомление " + " Оплата после приезда мастера", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-orange").shouldHave(text("Оплата после приезда мастера"));
        });
    }

    public void checkFullNameMaster(Integer offerIndex, String masterName) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается полное имя мастера " + masterName, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .text-object p").shouldHave(text(masterName));
        });
    }

    public void checkAvatarMaster(Integer offerIndex, String image) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения мастера отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .photo-object").shouldHave(attribute("src", image));
        });
    }

    public void checkRatingMaster(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения мастера отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void checkMasterReviewCount(Integer offerIndex, String reviews) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения мастера отображается количество отзывов " + reviews, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .item-flex.mb-2 ").shouldHave(partialText(reviews));
        });
    }

    public void checkMasterCompletedOrders(Integer offerIndex, String completedOrders) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения мастера отображается количество выполненных заказов " + completedOrders, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .w-100.d-flex.align-items-center.mb-2 .bag").shouldHave(partialText(completedOrders));
        });
    }

    public void checkButtonActive(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + (offerIndex + 1) + " предложения отображается активная кнопка Выбрать", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").shouldNotBe(disabled);
        });
    }

    public void selectButton(Integer offerIndex) {
        stepWithRole("Нажать на кнопку Выбрать у " + (offerIndex + 1) + " предложения", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").click();
        });
    }

}
