package ru.gasworkers.dev.pages.components.dispatcherComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.users.companies.masters.dto.CompaniesMastersResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent.MainButtonSharedComponent;
import ru.gasworkers.dev.pages.dispatcher.LoaderComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateInfo;

import java.util.List;

import static com.codeborne.selenide.Condition.*;

public class OfferPriceModalDispatcherComponent extends BaseComponent {
    public final MainButtonSharedComponent mainButton;
    public final MosOblGasBannerOfferPriceModalDispatcherComponent mosOblGasBanner;
    public final LoaderComponent loader;

    /*/*<div data-v-7e02d6b2="" class="modal-content-wrapper" style="width: 850px;"><div data-v-7e02d6b2="" class="close-btn"></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 mb-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="order-price-title text-center"><!----> <span data-v-0869c58e="" data-v-7e02d6b2="" class="text bold">
        Расценить и участвовать в заказе
      </span></div></div> <!----> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 pb-5 mb-sm-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="order-details-item"><!----> <div data-v-0869c58e="" data-v-7e02d6b2="" class="order-details__title font-sm mb-20 bold">
        Укажите стоимость выезда мастера на объект и предварительную стоимость ремонта, на которую будет ориентироваться клиент.
      </div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 hr mt-2"><div data-v-0869c58e="" data-v-7e02d6b2="" class="d-flex justify-content-between flex-wrap flex-sm-nowrap"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 mb-3 mb-sm-0 pe-md-5 pe-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 d-flex"><!----> <div data-v-0869c58e="" data-v-7e02d6b2=""><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100">Газовый котел Viessmann Vitodens 100-W WB1C104 35kW</div></div></div></div></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 hr-blue pb-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="order-details__title font-sm mb-20 bold">
        Укажите мастера для этого заказа
      </div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="order_details__master_block"><ol data-v-0869c58e="" data-v-7e02d6b2="" class="content list"><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/487" class="checked" target="_blank" data-v-7e02d6b2="">
                  МастеровСССР1 МастерСССР1 МастеровичСССР1
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      23
      отзыва
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/488" class="checked" target="_blank" data-v-7e02d6b2="">
                  Мастеров2СССР Мастер2СССР Мастерович2СССР
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/497" class="checked" target="_blank" data-v-7e02d6b2="">
                  МастерСССР3 МастерСССР3 МастеровичСССР3
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/500" class="checked" target="_blank" data-v-7e02d6b2="">
                  МастерСССР5 МастерСССР5 МастерСССР5
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/504" class="checked" target="_blank" data-v-7e02d6b2="">
                  мастер масстер мастер
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/506" class="checked" target="_blank" data-v-7e02d6b2="">
                  321321 321321 321321
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li><li data-v-0869c58e="" data-v-7e02d6b2="" class="item"><div data-v-0869c58e="" data-v-7e02d6b2="" class="col-l"><div data-v-0869c58e="" data-v-7e02d6b2="" class="name"><a data-v-0869c58e="" href="/master/4075" class="checked" target="_blank" data-v-7e02d6b2="">
                  сссрМастерЛогин сссрМастерЛогин сссрМастерЛогин
                </a></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-c"><div data-v-c614726a="" data-v-0869c58e="" class="rating" data-v-7e02d6b2=""><div data-v-c614726a="" class="rating-badge">5.00</div> <div data-v-c614726a="" class="reviews"><span data-v-c614726a="">
      0
      отзывов
    </span></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="col-r"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Выбрать
    </span> <!----> <!----></button></div></li></ol></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 hr-blue pb-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100"><!----> <div data-v-0869c58e="" data-v-7e02d6b2="" class="
              d-flex
              justify-content-between
              flex-wrap flex-sm-nowrap
              mb-30
            "><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 bold mb-3 mb-sm-0 pe-md-5 pe-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100">
                Стоимость первичного выезда мастера
              </div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 help-text light mb-3 mb-sm-0 pe-md-5 pe-3">
                Минимальная стоимость выезда мастера
                рублей
              </div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="d-flex justify-content-sm-end flex-grow-1"><div data-v-0869c58e="" data-v-7e02d6b2="" class="equipment-price"><form data-v-6cad60a8="" data-v-0869c58e="" autocomplete="off" class="gas-input text-center input-font-sm" data-v-7e02d6b2=""><!----> <div data-v-6cad60a8=""><input data-v-6cad60a8="" id="539" autocomplete="off" disabled="disabled" placeholder="от 3000 руб" type="text"> <!----> <!----></div> <!----></form></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="d-flex justify-content-between flex-wrap flex-sm-nowrap"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 bold mb-3 mb-sm-0 pe-md-5 pe-3"><div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100">
                Ориентировочная стоимость ремонта основанная на информации в заявке
              </div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="w-100 help-text light mb-3 mb-sm-0 pe-md-5 pe-3">
                Минимальная стоимость предварительной оценки ремонта
                рублей
              </div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="d-flex justify-content-sm-end flex-grow-1"><div data-v-0869c58e="" data-v-7e02d6b2="" class="equipment-price"><form data-v-6cad60a8="" data-v-0869c58e="" autocomplete="off" class="gas-input text-center input-font-sm" data-v-7e02d6b2=""><!----> <div data-v-6cad60a8=""><input data-v-6cad60a8="" id="540" autocomplete="off" disabled="disabled" placeholder="от 3000 руб." type="text"> <!----> <!----></div> <!----></form></div></div></div></div></div></div> <div data-v-0869c58e="" data-v-7e02d6b2="" class="global-btn-wrapper mb-0 justify-content-center"><button data-v-6126ebee="" data-v-0869c58e="" data-test-id="outline-primary" class="btn btn-outline-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Отменить
    </span> <!----> <!----></button> <button data-v-6126ebee="" data-v-0869c58e="" data-test-id="primary" class="btn btn-primary disable-outline" data-v-7e02d6b2=""><!----> <span data-v-6126ebee="">
      Сохранить
    </span> <!----> <!----></button></div></div>*/

    ElementsCollection
            mastersCollection = driver.$$("div.order_details__master_block li.row").as("коллекция мастеров");

    SelenideElement
            selfLocator = driver.$("div.modal-content-wrapper").as("Модальное окно расценка оборудования"),
            equipmentSectionTextLocator = driver.$("div.order-details-item")
                    .closest("div.w-100").as("Секция оборудование");
    public OfferPriceModalDispatcherComponent(RoleBrowser browser) {
        super(browser);
        mainButton = new MainButtonSharedComponent(browser);
        mosOblGasBanner = new MosOblGasBannerOfferPriceModalDispatcherComponent(browser);
        loader = new LoaderComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что модальное окно расценка оборудования загрузилось", () -> {
            loader.handleLoaderPage();
            selfLocator.shouldBe(visible);
//            mosOblGasBanner.checkFinishLoading(); todo ask   mosoblgas  here
//            mainButton.checkButtonText("Сохранить", selfLocator);
            driver.$("div.order-price-title").shouldHave(text("Расценить и участвовать в заказе"));
            //add second button check
            //add price calculation check
            //add toggle check
        });
    }

    public void saveButton() {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            mainButton.clickActive("Сохранить", selfLocator);
        });
    }


    private void checkMasters(List<CompaniesMastersResponseDto.DataDto> masters) {
        stepWithRole("Убедиться, что отображается  список мастеров", () -> {
            mastersCollection.shouldHave(CollectionCondition.size(masters.size()));
            // check size
            for (int i = 0; i < masters.size(); i++) {
                mastersCollection.get(i).shouldHave(partialText(masters.get(i).getFullName()));
            }
            //check names
            for (int i = 0; i < masters.size(); i++) {
                mastersCollection.get(i).shouldHave(partialText(masters.get(i).getFullName()));
            }
            //check rating
            for (int i = 0; i < masters.size(); i++) {
                mastersCollection.get(i).shouldHave(partialText(masters.get(i).getRating()));
            }
            // check reviews
            for (int i = 0; i < masters.size(); i++) {
                mastersCollection.get(i).shouldHave(partialText(String.valueOf(masters.get(i).getReviewsCount())));
            }
        });
    }

    private void checkEquipmentModal(StateInfo stateInfo) {
        stepWithRole("Убедиться, что отображается оборудавание", () -> {
            String equipment0 = stateInfo.getOrdersIdResponseDto().getData().getClientObject().getEquipments().get(0).getComputedTitle();
            equipmentSectionTextLocator.shouldHave(partialText(equipment0));
        });
    }

    public void checkOffer(StateInfo stateInfo) {

        stepWithRole("Убедиться  что  модальное окно расценка оборудования содержит нужные поля", () -> {
            stepWithRole("Убедиться, что  отображается оборудавание", () -> {
                checkEquipmentModal(stateInfo);
            });
            stepWithRole("Убедиться,  что отображается  список мастеров", () -> {
                checkMasters(stateInfo.getDesignatedCompaniesMastersResponseDto().getData());
            });
            stepWithRole("Убедиться, что отображается  поле  стоимость выезда мастера", () -> {

            });
            stepWithRole("Убедиться, что отображается  поле  стоимость ремонта", () -> {

            });
            stepWithRole("Убедиться, что отображается  кнопка  Отменить", () -> {

            });
            stepWithRole("Убедиться, что отображается  кнопка  Сохранить", () -> {

            });
        });

    }

}
// add savePrice toggle check