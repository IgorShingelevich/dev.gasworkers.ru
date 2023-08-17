package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.infoMaster;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;

import static com.codeborne.selenide.Condition.partialText;
import static com.codeborne.selenide.Condition.visible;

public class RepairDetailsInfoMasterTabOrderCardComponent extends BaseOrderCardComponent {
    public RepairDetailsInfoMasterTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    /*<div data-v-0eeaa3b6="" class="order-details item hr"><div data-v-0eeaa3b6="" class="master-card-wrap"><p data-v-0eeaa3b6="" class="h4">Ваш мастер</p> <div data-v-0eeaa3b6="" class="master-card"><div data-v-0eeaa3b6="" class="header mb-0"><div data-v-0eeaa3b6="" class="profile-wrap mb-3"><div data-v-0eeaa3b6="" class="profile-image"><div data-v-0eeaa3b6="" class="image"><img data-v-0eeaa3b6="" alt="МастеровСССР1 МастерСССР1 МастеровичСССР1" src="https://gasworkers.storage.yandexcloud.net/files/2Fay9O26ZN2iC8ViKBVQHu1G1Bu0IlKvmbPPrPXR.jpg"></div></div> <div data-v-0eeaa3b6="" class="title"><div data-v-0eeaa3b6="" class="name"><a data-v-0eeaa3b6="" href="/master/487" class="checked">
                  МастеровСССР1 МастерСССР1 МастеровичСССР1
                </a></div> <div data-v-4adf3373="" data-v-0eeaa3b6="" class="rating"><div data-v-4adf3373="" class="rating-badge">5.00</div> <div data-v-4adf3373="" class="reviews"><a data-v-4adf3373="">
      23
      отзыва
    </a></div></div></div> <div data-v-0eeaa3b6="" class="register-date"><p data-v-0eeaa3b6="" class="text">Зарегистрирован с 11 января 2023 года</p></div></div></div></div></div> <!----> <div data-v-0eeaa3b6="" class="bold mb-2">
        Запасные части и&nbsp;расходные материалы
      </div> <div data-v-0eeaa3b6="" class="table-div-scroll"><div data-v-0eeaa3b6="" class="table-div"><div data-v-0eeaa3b6="" class="table-div-header"><div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Наименование</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Производитель</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Марка/модель/описание</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Кол - во</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Цена, руб</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Сумма, руб</p></div></div></div> <div data-v-0eeaa3b6="" class="table-div-body"> <div data-v-0eeaa3b6="" class="table-div-body-tr"><div data-v-0eeaa3b6="" class="table-div-body-td x-10 text-left">Итого:</div> <div data-v-0eeaa3b6="" class="table-div-body-td x-2"><p data-v-0eeaa3b6="" class="bold">0 ₽</p></div></div></div></div></div> <!----> <div data-v-0eeaa3b6="" class="bold mb-2">
        Выполненные работы и&nbsp;услуги
      </div> <div data-v-0eeaa3b6="" class="table-div-scroll"><div data-v-0eeaa3b6="" class="table-div"><div data-v-0eeaa3b6="" class="table-div-header"><div data-v-0eeaa3b6="" class="table-div-header__th x-6"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Наименование работ и услуг</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Кол - во н/час</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Цена, руб</p></div></div> <div data-v-0eeaa3b6="" class="table-div-header__th x-2"><div data-v-0eeaa3b6="" class="table-div-header__th--span x-2"><p data-v-0eeaa3b6="" class="text">Сумма, руб</p></div></div></div> <div data-v-0eeaa3b6="" class="table-div-body"> <div data-v-0eeaa3b6="" class="table-div-body-tr"><div data-v-0eeaa3b6="" class="table-div-body-td x-10 text-left">Итого:</div> <div data-v-0eeaa3b6="" class="table-div-body-td x-2"><p data-v-0eeaa3b6="" class="bold">0 ₽</p></div></div></div></div></div> <!----> <div data-v-0eeaa3b6="" class="item hr mt-3"><div data-v-0eeaa3b6="" class="w-100"><span data-v-0eeaa3b6=""></span></div></div> <!----> <!----> <!----></div>*/

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что информация о заказе загрузилась", () -> {
            self.shouldBe(visible);
            materialsTitleTextLocator.shouldBe(visible);
            actionsTitleTextLocator.shouldBe(visible);
            materialsTableLocator.shouldBe(visible);
            actionsTableLocator.shouldBe(visible);
        });
    }    SelenideElement
            self = driver.$("div.order-details").as("Информация о заказе"),
            materialsTitleTextLocator = self.$$("div.bold.mb-2").get(0).as("Запасные части и расходные материалы"),
            actionsTitleTextLocator = self.$$("div.bold.mb-2").get(1).as("Выполненные работы и услуги"),
            materialsTableLocator = self.$$("div.table-div-scroll").get(0).as("Таблица запасных частей и расходных материалов"),
            actionsTableLocator = self.$$("div.table-div-scroll").get(1).as("Таблица выполненных работ и услуг"),
            materialsTotalPriceLocator = materialsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена запасных частей и расходных материалов"),
            actionsTotalPriceLocator = actionsTableLocator.$x(".//div[@class='table-div-body-td x-2'][contains(.,'₽')][1]").as("Итоговая цена выполненных работ и услуг");

    public void checkMaterialsTotalPrice(String expectedMaterialsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена запасных частей и расходных материалов: " + expectedMaterialsTotalPrice, () -> {
            materialsTotalPriceLocator.shouldHave(partialText(expectedMaterialsTotalPrice));
        });
    }

    public void checkActionsTotalPrice(String expectedActionsTotalPrice) {
        stepWithRole("Убедиться, что итоговая цена выполненных работ и услуг: " + expectedActionsTotalPrice, () -> {
            actionsTotalPriceLocator.shouldHave(partialText(expectedActionsTotalPrice));
        });
    }


}
