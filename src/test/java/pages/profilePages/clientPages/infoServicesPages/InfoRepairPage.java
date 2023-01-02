package pages.profilePages.clientPages.infoServicesPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InfoRepairPage {

    /**<div data-v-eb791b80="" class="page-content"><div data-v-7be6fd26="" data-v-eb791b80="" class="page-wrapper"><div data-v-7be6fd26="" class="d-flex justify-content-md-end mb-4"><button type="button" class="link-dark-blue mr-32 medium"><svg fill="none" height="8" viewBox="0 0 21 8" width="21" xmlns="http://www.w3.org/2000/svg"><path d="M0.646446 3.59176C0.451185 3.78702 0.451185 4.1036 0.646446 4.29887L3.82843 7.48085C4.02369 7.67611 4.34027 7.67611 4.53553 7.48085C4.7308 7.28558 4.7308 6.969 4.53553 6.77374L1.70711 3.94531L4.53553 1.11689C4.7308 0.921623 4.7308 0.605041 4.53553 0.409779C4.34027 0.214516 4.02369 0.214516 3.82843 0.409779L0.646446 3.59176ZM21 3.44531H1V4.44531H21V3.44531Z"></path></svg>
     Назад
     </button> <button type="button" class="link-dark-blue medium">
     Отменить
     </button></div> <!----> <div data-v-7be6fd26="" class="gas-box mw-540"><p data-v-7be6fd26="" class="h4">Инструкция по Ремонту</p> <ol data-v-7be6fd26=""><li data-v-7be6fd26="">Выберите объект или создайте новый</li> <li data-v-7be6fd26="">Укажите неисправное оборудование</li> <li data-v-7be6fd26="">Опишите поломку, приложите видео/фото неисправного оборудования</li> <li data-v-7be6fd26="">Разместите заказ, ожидайте предложения сервисных компаний</li></ol> <div data-v-7be6fd26="" class="btn-wrap"><button data-v-6d08f792="" data-v-7be6fd26="" class="btn btn-primary disable-outline">
     Далее
     </button></div></div> <!----></div></div>
     * */

    private final String INFO_PAGE_REPAIR_TITLE = "Инструкция по Ремонту";

    SelenideElement
            infoPageRepairTitleLocator = $(".page-content .h4"),
            infoPageRepairButtonLocator = $(".btn btn-primary disable-outline");





    public InfoRepairPage isOpened() {
        infoPageRepairTitleLocator.shouldBe(visible).shouldHave(text(INFO_PAGE_REPAIR_TITLE));
        return this;
    }

    public InfoRepairPage clickRepairButton() {
        infoPageRepairButtonLocator.shouldBe(visible).click();
        return this;
    }


}
