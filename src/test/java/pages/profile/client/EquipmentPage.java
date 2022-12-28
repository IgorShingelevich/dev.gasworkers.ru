package pages.profile.client;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import pages.components.SidebarClientComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class EquipmentPage {


    /**<main data-v-7575453c=""><div data-v-7575453c="" class="page-content"><div data-v-fa438d6c="" data-v-7575453c="" class="w-100 d-flex justify-content-center flex-wrap"><div data-v-fa438d6c="" class="w-100"><!----></div> <div data-v-fa438d6c="" class="notice-list-fixed"><div data-v-fa438d6c="" class="w-100 d-flex mb-2 justify-content-end"><button data-v-6d08f792="" data-v-fa438d6c="" class="btn btn-secondary disable-outline small">
     Прочитать все
     </button></div> <div data-v-fa438d6c="" class="notice-list-fixed-content gas-scrollbar-inline"><div data-v-a02b3682="" data-v-fa438d6c="" class="item item"><div data-v-a02b3682="" class="w-100"><div data-v-a02b3682="" class="gas-notice-warning"><div data-v-a02b3682="" class="item-header d-flex justify-content-between w-100 gas-notice-warning__text"><div data-v-a02b3682="" class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer"><span data-v-a02b3682="" class="ic"></span> <div data-v-a02b3682="" class="d-flex flex-wrap text-break"><span data-v-a02b3682="" class="me-1">
     Заказ №
     </span> <span data-v-a02b3682="">
     45/22/077/002150
     </span></div></div> <div data-v-a02b3682="" class="gas-notice-warning__text--close"></div></div> <div data-v-a02b3682="" class="item-flex w-100"><div data-v-a02b3682="" class="row"><div data-v-a02b3682="" class="col-md-12"><div data-v-a02b3682="" class="text w-100 text-left gas-notice-warning__text">
     Ваш заказ №45/22/077/002150 опубликован на платформе gasworkers.ru. Вы будете получать уведомления и письма о согласии сервисных компаний и мастеров выполнить ваш заказ
     </div></div> <div data-v-a02b3682="" class="col-md-12"><div data-v-a02b3682="" class="d-flex justify-content-end w-100"><a data-v-a02b3682="" href="#" class="small-button bold link-dark-blue mt-md-1">
     Перейти
     </a></div></div></div></div></div></div></div><div data-v-a02b3682="" data-v-fa438d6c="" class="item item"><div data-v-a02b3682="" class="w-100"><div data-v-a02b3682="" class="gas-notice-warning"><div data-v-a02b3682="" class="item-header d-flex justify-content-between w-100 gas-notice-warning__text"><div data-v-a02b3682="" class="h4 w-100 pe-2 flex-nowrap align-items-start gas-notice-warning__text--title cursor-pointer"><span data-v-a02b3682="" class="ic"></span> <div data-v-a02b3682="" class="d-flex flex-wrap text-break"><span data-v-a02b3682="" class="me-1">
     Заказ №
     </span> <span data-v-a02b3682="">
     45/22/077/002146
     </span></div></div> <div data-v-a02b3682="" class="gas-notice-warning__text--close"></div></div> <div data-v-a02b3682="" class="item-flex w-100"><div data-v-a02b3682="" class="row"><div data-v-a02b3682="" class="col-md-12"><div data-v-a02b3682="" class="text w-100 text-left gas-notice-warning__text">
     Выставлен счет на 3000 руб. за оплату выезда мастера по заказу 45/22/077/002146
     </div></div> <div data-v-a02b3682="" class="col-md-12"><div data-v-a02b3682="" class="d-flex justify-content-end w-100"><a data-v-a02b3682="" href="#" class="small-button bold link-dark-blue mt-md-1">
     Оплатить
     </a></div></div></div></div></div></div></div></div></div> <!----></div> <!----> <div data-v-ef75ca32="" data-v-7575453c="" id="equipments"><div data-v-35550176="" data-v-ef75ca32="" class="page-wrapper"><div data-v-35550176="" class="page-title"><div data-v-35550176="" class="row"><div data-v-35550176="" class="col-md-12"></div> <div data-v-35550176="" class="col-md-12"><h1 data-v-35550176="" class="h3 mb-2">Объекты и оборудование</h1> <div data-v-35550176="" class="nav"><nav data-v-35550176="" aria-label="breadcrumb"><ol data-v-35550176="" class="breadcrumb"><li data-v-35550176="" class="breadcrumb-item"><a data-v-35550176="" href="/profile/client" class=""><span data-v-35550176="" role="link">
     Главная
     </span></a></li> <li data-v-ef75ca32="" data-v-35550176="" aria-current="page" class="breadcrumb-item active">
     Объекты и оборудование
     </li></ol></nav></div></div> <!----> <div data-v-35550176="" class="col-md-12"> </div></div></div> <div data-v-35550176="" class="buttons"></div> <div data-v-35550176="" class="page-content"> <button data-v-6d08f792="" data-v-ef75ca32="" class="mb-4 btn btn-primary disable-outline" data-v-35550176="">
     Создать объект
     </button> <div data-v-ef75ca32="" data-v-35550176="" class="objects"><div data-v-ef75ca32="" data-v-35550176="" class="row"><div data-v-ef75ca32="" data-v-35550176="" class="col-lg-6 object-item"><div data-v-9e3fa99c="" data-v-ef75ca32="" class="object" data-v-35550176=""><div data-v-9e3fa99c="" class="header"><div data-v-9e3fa99c="" class="image"><!----></div> <div data-v-9e3fa99c="" class="title link-blue text-primary pointer">
     Дом3
     </div> <div data-v-9e3fa99c="" class="actions"><button data-v-9e3fa99c="" type="button" class="actions__btn"><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div data-v-9e3fa99c="" class="actions__slot right"><a data-v-9e3fa99c="" href="/equipment/339" class="actions__slot--link">
     Перейти
     </a> <a data-v-9e3fa99c="" href="/equipment/339#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-9e3fa99c="" class="content"><div data-v-9e3fa99c="" class="title">Оборудование:</div> <div data-v-9e3fa99c="" class="equipment scrollbar"><div data-v-9e3fa99c="" class="equipment-item">
     Газовый котел Bosch Gaz 2500 F 25
     </div><div data-v-9e3fa99c="" class="equipment-item">
     Плита газовая BEON BN-554
     </div><div data-v-9e3fa99c="" class="equipment-item">
     Духовой шкаф Beko BIG 22100 X
     </div></div></div> <div data-v-9e3fa99c="" class="address"><div data-v-9e3fa99c="" class="title">Адрес объекта:</div> <div data-v-9e3fa99c="" class="address-string">Россия, Москва, Московская улица квартира 3311</div></div></div></div><div data-v-ef75ca32="" data-v-35550176="" class="col-lg-6 object-item"><div data-v-9e3fa99c="" data-v-ef75ca32="" class="object" data-v-35550176=""><div data-v-9e3fa99c="" class="header"><div data-v-9e3fa99c="" class="image"><div data-v-9e3fa99c="" class="object-image"><img data-v-9e3fa99c="" alt="Дом2Клиента1" src="https://gasworkers.storage.yandexcloud.net/files/vdsFEPRDz9Lh1XStgtcYYHToGych1JeRkMgUbxsY.png"></div></div> <div data-v-9e3fa99c="" class="title link-blue text-primary pointer">
     Дом2Клиента1
     </div> <div data-v-9e3fa99c="" class="actions"><button data-v-9e3fa99c="" type="button" class="actions__btn"><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div data-v-9e3fa99c="" class="actions__slot right"><a data-v-9e3fa99c="" href="/equipment/338" class="actions__slot--link">
     Перейти
     </a> <a data-v-9e3fa99c="" href="/equipment/338#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-9e3fa99c="" class="content"><div data-v-9e3fa99c="" class="title">Оборудование:</div> <div data-v-9e3fa99c="" class="equipment scrollbar"><div data-v-9e3fa99c="" class="equipment-item">
     Газовый котел Bosch Gaz 2500 F 25
     </div><div data-v-9e3fa99c="" class="equipment-item">
     Плита газовая BEON BN-554
     </div></div></div> <div data-v-9e3fa99c="" class="address"><div data-v-9e3fa99c="" class="title">Адрес объекта:</div> <div data-v-9e3fa99c="" class="address-string">Россия, Москва, Лермонтовский проспект квартира 22</div></div></div></div><div data-v-ef75ca32="" data-v-35550176="" class="col-lg-6 object-item"><div data-v-9e3fa99c="" data-v-ef75ca32="" class="object" data-v-35550176=""><div data-v-9e3fa99c="" class="header"><div data-v-9e3fa99c="" class="image"><div data-v-9e3fa99c="" class="object-image"><img data-v-9e3fa99c="" alt="Дом1Клиента1" src="https://gasworkers.storage.yandexcloud.net/files/guqPjOzW7YbzQAPEtZTCVLvhu84wBRKgSnpKNyWS.png"></div></div> <div data-v-9e3fa99c="" class="title link-blue text-primary pointer">
     Дом1Клиента1
     </div> <div data-v-9e3fa99c="" class="actions"><button data-v-9e3fa99c="" type="button" class="actions__btn"><span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span> <span data-v-9e3fa99c=""></span></button> <div data-v-9e3fa99c="" class="actions__slot right"><a data-v-9e3fa99c="" href="/equipment/337" class="actions__slot--link">
     Перейти
     </a> <a data-v-9e3fa99c="" href="/equipment/337#documents" class="actions__slot--btn">
     Документы
     </a> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Добавить оборудование
     </button> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Редактировать объект
     </button> <hr data-v-9e3fa99c=""> <button data-v-9e3fa99c="" type="button" class="actions__slot--btn">
     Удалить объект
     </button></div></div></div> <div data-v-9e3fa99c="" class="content"><div data-v-9e3fa99c="" class="title">Оборудование:</div> <div data-v-9e3fa99c="" class="equipment scrollbar"><div data-v-9e3fa99c="" class="equipment-item">
     Газовый котел Bosch Gaz 2500 F 25
     </div></div></div> <div data-v-9e3fa99c="" class="address"><div data-v-9e3fa99c="" class="title">Адрес объекта:</div> <div data-v-9e3fa99c="" class="address-string">Россия, Москва, Лермонтовский проспект квартира 11</div></div></div></div></div></div></div></div></div></div></main>
     * */
    SidebarClientComponent sidebarClientComponent = new SidebarClientComponent();

    private final String EQUIPMENT_PAGE_TITLE_TEXT = "Объекты и оборудование";

    SelenideElement equipmentPageTitleLocator = $(".page-title");

    public void open() {
      //  open("profile/client");
        Selenide.open("/equipment");
        equipmentPageTitleLocator.shouldHave(text(EQUIPMENT_PAGE_TITLE_TEXT)).shouldBe(visible);
    }





}
