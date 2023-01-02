package pages.profilePages.clientPages.infoServicesPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class InfoMaintenancePage {


    /**<div data-v-7be6fd26="" data-v-eb791b80="" class="page-wrapper"><div data-v-7be6fd26="" class="d-flex justify-content-md-end mb-4"><button type="button" class="link-dark-blue mr-32 medium"><svg fill="none" height="8" viewBox="0 0 21 8" width="21" xmlns="http://www.w3.org/2000/svg"><path d="M0.646446 3.59176C0.451185 3.78702 0.451185 4.1036 0.646446 4.29887L3.82843 7.48085C4.02369 7.67611 4.34027 7.67611 4.53553 7.48085C4.7308 7.28558 4.7308 6.969 4.53553 6.77374L1.70711 3.94531L4.53553 1.11689C4.7308 0.921623 4.7308 0.605041 4.53553 0.409779C4.34027 0.214516 4.02369 0.214516 3.82843 0.409779L0.646446 3.59176ZM21 3.44531H1V4.44531H21V3.44531Z"></path></svg>
     Назад
     </button> <button type="button" class="link-dark-blue medium">
     Отменить
     </button></div> <div data-v-7be6fd26="" class="gas-box mw-540"><p data-v-7be6fd26="" class="h4">Инструкция</p> <ol data-v-7be6fd26=""><li data-v-7be6fd26="">Выберите объект или создайте новый</li> <li data-v-7be6fd26="">Выберите сервисную компанию&nbsp;— исполнителя</li> <li data-v-7be6fd26="">Выберите дату приезда мастера, оплатите выезд и&nbsp;дождитесь звонка от&nbsp;диспетчера</li> <li data-v-7be6fd26="">После&nbsp;проведения ТО&nbsp;подпишите акт&nbsp;и&nbsp;оплатите счёт в&nbsp;личном кабинете</li></ol> <div data-v-7be6fd26="" class="btn-wrap"><button data-v-6d08f792="" data-v-7be6fd26="" class="btn btn-primary disable-outline">
     Далее
     </button></div></div> <!----> <!----></div>
     * */


    private final String INFO_MAINTENANCE_PAGE_TITLE = "Инструкция";
    SelenideElement
            infoPageMaintenanceTitleLocator = $("p.h4"),
            infoPageMaintenanceButtonLocator = $x("//button[contains(@class,'btn btn-primary disable-outline')]");
    //$(".btn btn-primary disable-outline"); -not work


    public InfoMaintenancePage isOpened() {
        infoPageMaintenanceTitleLocator.shouldBe(visible).shouldHave(text(INFO_MAINTENANCE_PAGE_TITLE));
        return this;
    }

    public InfoMaintenancePage clickMaintenanceButton() {
        infoPageMaintenanceButtonLocator.shouldHave(text("Далее")).shouldBe(enabled).click();
        return this;
    }




}

