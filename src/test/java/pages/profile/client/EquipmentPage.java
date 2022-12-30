package pages.profile.client;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.SidebarClientComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class EquipmentPage {
    // exemplar of pages.profile.client.ClientProfilePage
    ClientProfilePage clientProfilePage = new ClientProfilePage();


    /**<div class="page-content" data-v-35550176=""> <button class="mb-4 btn btn-primary disable-outline" data-v-6d08f792="" data-v-ef75ca32="" data-v-35550176="">
     Создать объект
     </button> <div class="objects" data-v-35550176="" data-v-ef75ca32=""><div class="row" data-v-35550176="" data-v-ef75ca32=""><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><div class="object-image" data-v-9e3fa99c=""><img alt="Дом3_Клиента" src="https://gasworkers.storage.yandexcloud.net/files/Vo5qsLLzMxTmXYlST0vqGlzketebC8xAyNIJftEv.png" data-v-9e3fa99c=""></div></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     Дом3_Клиента
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/299" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/299#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <div class="content" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Оборудование:</div> <div class="equipment scrollbar" data-v-9e3fa99c=""><div class="equipment-item" data-v-9e3fa99c="">
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </div></div></div> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Пушкино, микрорайон Чистые Пруды</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><div class="object-image" data-v-9e3fa99c=""><img alt="Объект1_Клиента1 комплекс Люберцы Парк" src="https://gasworkers.storage.yandexcloud.net/files/NMJk7SrLEqrMZTVTKYitdGZDBSh0ZAdZYDRuWNjc.png" data-v-9e3fa99c=""></div></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     Объект1_Клиента1 комплекс Люберцы Парк
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/289" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/289#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <div class="content" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Оборудование:</div> <div class="equipment scrollbar" data-v-9e3fa99c=""><div class="equipment-item" data-v-9e3fa99c="">
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </div><div class="equipment-item" data-v-9e3fa99c="">
     Плита газовая BEON BN-554
     </div><div class="equipment-item" data-v-9e3fa99c="">
     11111111111111111111111111111111111111111111111111111111111111111111111
     </div></div></div> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><!----></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     Хогвартс
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/287" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/287#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <div class="content" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Оборудование:</div> <div class="equipment scrollbar" data-v-9e3fa99c=""><div class="equipment-item" data-v-9e3fa99c="">
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </div><div class="equipment-item" data-v-9e3fa99c="">
     Плита газовая Darina NGM 811-01
     </div></div></div> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><!----></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     234
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/279" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/279#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <!----> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><!----></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     123
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/276" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/276#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <!----> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><div class="object-image" data-v-9e3fa99c=""><img alt="замок Хогвартс" src="https://gasworkers.storage.yandexcloud.net/files/HK43EZEljGCTsAe34BhuMtk2SNc0F9RGZjxOx5PW.jpg" data-v-9e3fa99c=""></div></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     замок Хогвартс
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/275" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/275#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <div class="content" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Оборудование:</div> <div class="equipment scrollbar" data-v-9e3fa99c=""><div class="equipment-item" data-v-9e3fa99c="">
     Газгольдер Сделай сам 3
     </div></div></div> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020</div></div></div></div><div class="col-lg-6 object-item" data-v-35550176="" data-v-ef75ca32=""><div class="object" data-v-9e3fa99c="" data-v-ef75ca32="" data-v-35550176=""><div class="header" data-v-9e3fa99c=""><div class="image" data-v-9e3fa99c=""><!----></div> <div class="title link-blue text-primary pointer" data-v-9e3fa99c="">
     названиеВашегоОбъекта_3
     </div> <div class="actions" data-v-9e3fa99c=""><button type="button" class="actions__btn" data-v-9e3fa99c=""><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div class="actions__slot right" data-v-9e3fa99c=""><a href="/equipment/270" class="actions__slot--link" data-v-9e3fa99c="">
     Перейти
     </a> <a href="/equipment/270#documents" class="actions__slot--btn" data-v-9e3fa99c="">
     Документы
     </a> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Добавить оборудование
     </button> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button type="button" class="actions__slot--btn" data-v-9e3fa99c="">
     Удалить объект
     </button></div></div></div> <div class="content" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Оборудование:</div> <div class="equipment scrollbar" data-v-9e3fa99c=""><div class="equipment-item" data-v-9e3fa99c="">
     Плита газовая Centek CT-1522
     </div><div class="equipment-item" data-v-9e3fa99c="">
     Плита газовая Centek CT-1521
     </div><div class="equipment-item" data-v-9e3fa99c="">
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </div><div class="equipment-item" data-v-9e3fa99c="">
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </div><div class="equipment-item" data-v-9e3fa99c="">
     234 234
     </div><div class="equipment-item" data-v-9e3fa99c="">
     1 1
     </div></div></div> <div class="address" data-v-9e3fa99c=""><div class="title" data-v-9e3fa99c="">Адрес объекта:</div> <div class="address-string" data-v-9e3fa99c="">Россия, Московская область, Люберцы</div></div></div></div></div></div></div>
     * */

    SidebarClientComponent sidebarClientComponent = new SidebarClientComponent();

    private final String EQUIPMENT_PAGE_TITLE_TEXT = "Объекты и оборудование";

    SelenideElement equipmentPageTitleLocator = $(".page-title"),

    createNewObjectButtonLocator = $x("//button[@class='mb-4 btn btn-primary disable-outline'][contains(.,'Создать объект')]"),
                            //$(".button"),
                            //$x("//button[@class='mb-4 btn btn-primary disable-outline'][contains(.,'Создать объект')]"),
                            //$(".mb-4 btn btn-primary disable-outline"),
      object1thCollectionLocator = $$(".objects .object").get(0),
     object$$ActionsDropdownCollectionLocator = $(".actions"),
      pickGasDistributorDropdownLocator = $(".gas-select .gas-select__header");







    public EquipmentPage open() {

        Selenide.open("/equipment");
        checkEquipmentPageTitle();

        return this;
    }

    public void checkEquipmentPageTitle() {
        equipmentPageTitleLocator.shouldHave(text(EQUIPMENT_PAGE_TITLE_TEXT));
    }

    public void clickCreateNewObject() {
//        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        createNewObjectButtonLocator.shouldHave(text("Создать объект")).shouldNotBe(disabled).click();
        pickGasDistributorDropdownLocator.shouldBe(visible);
    }





}
