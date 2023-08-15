package ru.gasworkers.dev.pages.components.clientComponent.repairComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class CompanyBoxSelectService extends BaseComponent {

    ElementsCollection
            self = driver.$$("[id^='company-item-company-']").filterBy(attribute("data-test-id", "primary")).as("Блоки с кнопкой Выбрать");

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

    public void checkAmountOfferBox(Integer offerCount) {
        stepWithRole("Проверить, что отображается " + offerCount + " предложений", () -> {
            self.shouldHave(CollectionCondition.size(offerCount));
        });
    }

    public void checkBoxTitle(Integer offerNumber, String title) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается заголовок " + title, () -> {
            self.get(offerNumber).$(".gas-box .item-flex .text-object p").shouldHave(text(title));
        });
    }

    public void checkGeoTag(Integer offerNumber) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается геотег", () -> {
            self.get(offerNumber).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldBe(visible);
            self.get(offerNumber).$(".gas-box .item-flex .small-icon.cursor-pointer.ms-2").shouldHave(attribute("src", "/_nuxt/img/location-map.4ebe60e.svg"));

        });
    }

    public void checkRatingCompany(Integer offerNumber, Integer rating) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения компании отображается рейтинг " + rating, () -> {
            self.get(offerNumber).$$(".gas-box .item-flex .gas-stars-wrap .gas-stars__star.active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void getRatingCompany(Integer offerNumber) {
        stepWithRole("Получить рейтинг компании  у" + offerNumber + " предложения", () -> {
            self.get(offerNumber).$$(".gas-box .item-flex .gas-stars-wrap .gas-stars__star.active").size();
        });
    }

    public void checkImageCompany(Integer offerNumber) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается изображение", () -> {
            self.get(offerNumber).$(".gas-box .item-flex .photo-object-company").shouldHave(attribute("src", "/images/default-logo.svg"));
        });
    }

    public void checkVisitPrice(Integer offerNumber, String price) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается стоимость первоначального выезда мастера " + price, () -> {
            self.get(offerNumber).$(".gas-box .item-flex .bag-success").shouldHave(text(price));
        });
    }

    public void checkNotificationPaymentAfterArrival(Integer offerNumber) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается уведомление " + " Оплата после приезда мастера", () -> {
            self.get(offerNumber).$(".gas-box .item-flex .bag-orange").shouldHave(text("Оплата после приезда мастера"));
        });
    }

    public void checkFullMasterName(Integer offerNumber, String masterName) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения отображается полное имя мастера " + masterName, () -> {
            self.get(offerNumber).$(".gas-box .gas-card__item .item-flex .text-object p").shouldHave(text(masterName));
        });
    }

    public void checkMasterImage(Integer offerNumber, String image) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения мастера отображается изображение", () -> {
            self.get(offerNumber).$(".gas-box .gas-card__item .item-flex .photo-object").shouldHave(attribute("src", image));
        });
    }

    public void checkRatingMaster(Integer offerNumber, Integer rating) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения мастера отображается рейтинг " + rating, () -> {
            self.get(offerNumber).$$(".gas-box .gas-card__item .item-flex .gas-stars-wrap .gas-stars__star.active").shouldHave(CollectionCondition.size(rating));
        });
    }

    public void checkMasterReviewCount(Integer offerNumber, String reviews) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения мастера отображается количество отзывов " + reviews, () -> {
            self.get(offerNumber).$(" .gas-card__item .item-flex.mb-2 ").shouldHave(partialText(reviews));
        });
    }

    public void checkMasterCompletedOrders(Integer offerNumber, String completedOrders) {
        stepWithRole("Проверить, что у " + offerNumber + " предложения мастера отображается количество выполненных заказов " + completedOrders, () -> {
            self.get(offerNumber).$(" .gas-card__item .w-100.d-flex.align-items-center.mb-2 .bag").shouldHave(partialText(completedOrders));
        });
    }

    public void selectButton(Integer offerNumber) {
        stepWithRole("Нажать на кнопку Выбрать у " + offerNumber + " предложения", () -> {
            self.get(offerNumber).$(".gas-box .justify-content-center.flex-column.align-items-center button").click();
        });
    }

}
