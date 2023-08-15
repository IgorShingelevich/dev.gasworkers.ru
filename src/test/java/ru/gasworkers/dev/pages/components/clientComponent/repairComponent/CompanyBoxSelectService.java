package ru.gasworkers.dev.pages.components.clientComponent.repairComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class CompanyBoxSelectService extends BaseComponent {



    /*<div data-v-519a7d66="" id="company-item-company-39" class="company-item-common mb-4 stamp" style="position: absolute; left: 0%; top: 0px;"><div data-v-519a7d66="" class="
                    gas-box
                    p-10
                    border-light-blue
                    direction-column
                    w-100
                    mb-0
                    h-100
                  "><div data-v-519a7d66="" class="item-flex flex-nowrap"><img data-v-519a7d66="" alt="Предложение  сервисной компании" src="/images/default-logo.svg" class="photo-object-company"> <div data-v-519a7d66="" class="text-object w-100 align-self-start ps-2"><p data-v-519a7d66="" class="h5 w-100">
                        Предложение  сервисной компании
                      </p></div> <img data-v-519a7d66="" alt="Gasworkers" src="/_nuxt/img/location-map.4ebe60e.svg" class="small-icon cursor-pointer ms-2"></div> <div data-v-519a7d66=""><!----></div> <div data-v-519a7d66="" class="item-flex"><div data-v-5c500570="" data-v-519a7d66="" class="gas-stars w-100 justify-content-between text-sm no-pointer"><div data-v-5c500570="" class="gas-stars__label">Рейтинг компании:</div> <div data-v-5c500570="" class="gas-stars-wrap"><div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div></div></div></div> <!----> <div data-v-519a7d66="" class="item-flex"><p data-v-519a7d66="" class="medium text-11 m-0 align-self-center">
                        Стоимость первоначального  выезда мастера:
                      </p> <p data-v-519a7d66="" class="bag bag-success small ms-auto mb-0">
                        3100 ₽
                      </p></div> <div data-v-519a7d66="" class="item-flex justify-content-center"><p data-v-519a7d66="" class="bag bag-orange small mx-auto mb-0">
                        Оплата после приезда мастера
                      </p></div> <div data-v-519a7d66="" class="gas-card__item"><div data-v-519a7d66="" class="item-flex flex-nowrap"><img data-v-519a7d66="" alt="МастеровСССР1 МастерСССР1 МастеровичСССР1" src="https://gasworkers.storage.yandexcloud.net/files/2Fay9O26ZN2iC8ViKBVQHu1G1Bu0IlKvmbPPrPXR.jpg" class="photo-object"> <div data-v-519a7d66="" class="text-object w-100 align-self-start ps-2"><p data-v-519a7d66="" class="h5 w-100">
                            МастеровСССР1 МастерСССР1 МастеровичСССР1
                          </p></div></div> <div data-v-519a7d66="" class="item-flex"><div data-v-5c500570="" data-v-519a7d66="" class="gas-stars w-100 justify-content-between text-sm"><div data-v-5c500570="" class="gas-stars__label">Рейтинг мастера:</div> <div data-v-5c500570="" class="gas-stars-wrap"><div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div> <div data-v-5c500570="" class="gas-stars__star active"></div></div></div></div> <div data-v-519a7d66="" class="item-flex mb-2">
                        23 отзыва
                      </div> <div data-v-519a7d66="" class="w-100 d-flex align-items-center mb-2"><p data-v-519a7d66="" class="small medium me-auto mb-2">
                          Выполненные заказы
                        </p> <div data-v-519a7d66="" class="bag badge-primary small mb-2">
                          223
                        </div></div></div> <div data-v-519a7d66="" class="gas-card__item mb-4"><!----> <!----></div> <div data-v-519a7d66="" class="
                      item-flex
                      justify-content-center
                      flex-column
                      align-items-center
                    "><button data-v-6126ebee="" data-v-519a7d66="" data-test-id="primary" class="btn btn-primary disable-outline btn-fs-sm"><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button> <!----></div></div></div>*/


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
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается заголовок " + "Предложение  сервисной компании", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .text-object p").shouldHave(text("Предложение  сервисной компании"));
        });
    }

    public void checkGeoTag(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается геотег", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldBe(visible);
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldHave(attribute("src", "https://dev.gasworkers.ru/_nuxt/img/location-map.4ebe60e.svg"));

        });
    }

    public void checkRatingCompany(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения компании отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void getRatingCompany(Integer offerIndex) {
        stepWithRole("Получить рейтинг компании  у" + offerIndex + " предложения", () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-box .item-flex .gas-stars-wrap .gas-stars__star.active").size();
        });
    }

    public void checkAvatarCompany(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .photo-object-company").shouldHave(attribute("src", "https://dev.gasworkers.ru/images/default-logo.svg"));
        });
    }

    public void checkVisitPrice(Integer offerIndex, String price) {
        // round string "3100.0" to "3100"
        double priceDouble = Double.parseDouble(price);
        int priceInt = (int) priceDouble; // Cast the double to an integer to remove the decimal part
        String formattedPrice = String.valueOf(priceInt); // Convert the integer to a string

        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается стоимость первоначального выезда мастера " + price, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-success").shouldHave(partialText(formattedPrice));
        });
    }

    public void checkNotificationPaymentAfterArrival(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается уведомление " + " Оплата после приезда мастера", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .item-flex .bag-orange").shouldHave(text("Оплата после приезда мастера"));
        });
    }

    public void checkFullNameMaster(Integer offerIndex, String masterName) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается полное имя мастера " + masterName, () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .text-object p").shouldHave(text(masterName));
        });
    }

    public void checkAvatarMaster(Integer offerIndex, String image) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения мастера отображается изображение", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .gas-card__item .item-flex .photo-object").shouldHave(attribute("src", image));
        });
    }

    public void checkRatingMaster(Integer offerIndex, Integer rating) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения мастера отображается рейтинг " + rating, () -> {
            offerBoxCollection.get(offerIndex).$$(".gas-stars-wrap").get(0).$$(".active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void checkMasterReviewCount(Integer offerIndex, String reviews) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения мастера отображается количество отзывов " + reviews, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .item-flex.mb-2 ").shouldHave(partialText(reviews));
        });
    }

    public void checkMasterCompletedOrders(Integer offerIndex, String completedOrders) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения мастера отображается количество выполненных заказов " + completedOrders, () -> {
            offerBoxCollection.get(offerIndex).$(" .gas-card__item .w-100.d-flex.align-items-center.mb-2 .bag").shouldHave(partialText(completedOrders));
        });
    }

    public void checkButtonActive(Integer offerIndex) {
        stepWithRole("Убедиться, что у " + offerIndex + " предложения отображается активная кнопка Выбрать", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").shouldNotBe(disabled);
        });
    }

    public void selectButton(Integer offerIndex) {
        stepWithRole("Нажать на кнопку Выбрать у " + offerIndex + " предложения", () -> {
            offerBoxCollection.get(offerIndex).$(".gas-box .justify-content-center.flex-column.align-items-center button").click();
        });
    }

}
