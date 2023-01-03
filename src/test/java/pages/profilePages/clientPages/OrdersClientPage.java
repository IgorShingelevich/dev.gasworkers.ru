package pages.profilePages.clientPages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class OrdersClientPage {

    /**<div data-v-7575453c="" class="content"><main data-v-7575453c=""><div data-v-7575453c="" class="page-content"><div data-v-fa438d6c="" data-v-7575453c="" class="w-100 d-flex justify-content-center flex-wrap"><div data-v-fa438d6c="" class="w-100"><!----></div> <!----> <!----></div> <!----> <div data-v-0e0ed747="" data-v-7575453c="" id="orders"><div data-v-35550176="" data-v-0e0ed747="" class="page-wrapper"><div data-v-35550176="" class="page-title"><div data-v-35550176="" class="row"><div data-v-35550176="" class="col-md-12"></div> <div data-v-35550176="" class="col-md-12"><h1 data-v-35550176="" class="h3 mb-2">Список заказов</h1> <div data-v-35550176="" class="nav"><nav data-v-35550176="" aria-label="breadcrumb"><ol data-v-35550176="" class="breadcrumb"><li data-v-35550176="" class="breadcrumb-item"><a data-v-35550176="" href="/profile/client" class="nuxt-link-active"><span data-v-35550176="" role="link">
     Главная
     </span></a></li> <li data-v-0e0ed747="" data-v-35550176="" aria-current="page" class="breadcrumb-item active">
     Список заказов
     </li></ol></nav></div></div> <!----> <div data-v-35550176="" class="col-md-12"> </div></div></div> <div data-v-35550176="" class="buttons"></div> <div data-v-35550176="" class="page-content"> <div data-v-0e0ed747="" data-v-35550176="" class="top-filter"><!----> <!----> <!----> <!----> <!----> <hr data-v-0e0ed747="" data-v-35550176="" class="hr mb-20"> <!----></div> <div data-v-0e0ed747="" data-v-35550176="" class="order-cards"><div data-v-0e0ed747="" data-v-35550176="" class="row"><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/002178
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/2178" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">29 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Пушкино, микрорайон Чистые Пруды
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">30/12/2022 04:04</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/002165
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/2165" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/maintenance/2165/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">28 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Пушкино, микрорайон Чистые Пруды
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Согласование даты заказа
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     30 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     09:00 - 15:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001940
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1940" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">15 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Пушкино, микрорайон Чистые Пруды
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">15/12/2022 11:29</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001938
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1938" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">15 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Пушкино, микрорайон Чистые Пруды
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">16/12/2022 16:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001922
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1922" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">14 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Пушкино, микрорайон Чистые Пруды
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Talia Green Evo System HP 150
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">14/12/2022 19:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001894
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1894" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">13 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li><li>
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </li><li>
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </li><li>
     234 234
     </li><li>
     1 1
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">14/12/2022 16:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001890
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1891" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">13 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     14 декабря 2022 - 21 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     18:50 - 18:50
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001889
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1889" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/maintenance/1889/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">13 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </li><li>
     Плита газовая BEON BN-554
     </li><li>
     11111111111111111111111111111111111111111111111111111111111111111111111
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Согласование даты заказа
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     14 декабря 2022 - 20 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     09:00 - 15:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001888
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1888" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">13 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     13 декабря 2022 - 22 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     09:00 - 15:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001824
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1824" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">8 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li><li>
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </li><li>
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </li><li>
     234 234
     </li><li>
     1 1
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     8 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     09:00 - 15:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001811
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1811" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">7 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </li><li>
     Плита газовая BEON BN-554
     </li><li>
     11111111111111111111111111111111111111111111111111111111111111111111111
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">23/12/2022 05:04</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001809
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1809" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">7 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газгольдер Сделай сам 3
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">08/12/2022 11:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001789
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1789" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">5 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </li><li>
     Плита газовая BEON BN-554
     </li><li>
     11111111111111111111111111111111111111111111111111111111111111111111111
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">05/12/2022 12:02</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001785
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1785" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </li><li>
     Плита газовая BEON BN-554
     </li><li>
     11111111111111111111111111111111111111111111111111111111111111111111111
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 14:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001783
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1783" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1783/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 13:10</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001782
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1782" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1782/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 13:10</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001778
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1778" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1778/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 12:56</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001777
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1777" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1777/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 12:55</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001776
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1776" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер приступил к работе
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 19:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001773
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1773" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1773/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:23</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001772
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1772" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1772/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:23</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001771
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1771" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1771/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:21</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001770
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1770" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1770/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:21</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001767
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1767" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1767/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:19</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001766
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1766" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1766/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:19</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001764
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1764" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1764/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:18</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001763
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1763" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1763/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:18</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001762
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1762" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1762/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:16</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001761
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1761" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1761/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 11:16</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001760
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1760" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">2 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">02/12/2022 18:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001756
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1756" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1756/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">1 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">01/12/2022 18:09</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001755
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1755" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1755/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">1 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">01/12/2022 18:08</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001754
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1754" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/repair/1754/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">1 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Ремонт</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">01/12/2022 18:08</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001753
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1753" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">1 декабря 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">01/12/2022 20:00</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001737
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1737" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">30 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     30 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001686
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1686" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">25 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, жилой комплекс Люберцы Парк
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Chaffoteaux Pigma Ultra 25 FF
     </li><li>
     Плита газовая BEON BN-554
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     25 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001665
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1665" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">24 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     25 ноября 2022 - 1 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001664
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1664" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">24 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газовый котел Bosch Gaz 2500 F ZSA 24 – 2 K
     </li><li>
     Плита газовая Darina NGM 811-01
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     25 ноября 2022 - 8 декабря 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001600
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1600" class="actions__slot--link">
     Открыть
     </a> <hr> <a href="/orders/maintenance/1600/cancel?from=list" class="actions__slot--link">
     Отменить
     </a> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">23 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы, микрорайон Зенино, жилой комплекс Люберцы 2020
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Газгольдер Сделай сам 3
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Мастер в пути
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">23/11/2022 15:38</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001598
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1598" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">23 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li><li>
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </li><li>
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </li><li>
     234 234
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     25 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001550
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1550" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">22 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li><li>
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </li><li>
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </li><li>
     234 234
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     30 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001410
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1410" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">16 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Видеоконсультация</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Завершен
     </p></div> <!----> <!----> <!----> <!----> <div class="order-card__item"><div class="order-card__item--title">Дата и время приезда мастера</div> <div class="order-card__item--text">16/11/2022 09:14</div></div></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001409
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1409" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">16 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li><li>
     Газовый конвектор BaltGaz 11111111111111111111111111111111111111111111111111
     </li><li>
     Проточный ёмкостный газовый водонагреватель Gorenje 1111111111111111111111111111111111111111111111111111111111111111111111111111111111
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     16 ноября 2022 - 18 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     15:00 - 20:00
     </p></div> <!----></div></div><div data-v-0e0ed747="" data-v-35550176="" class="col-lg-6 mb-30"><div data-v-0e0ed747="" class="order-card" data-v-35550176=""><div class="order-card__item hr"><p class="h5 link-blue pointer">
     Заказ №45/22/077/001405
     </p> <!----> <div class="actions"><button type="button" class="actions__btn"><span></span> <span></span> <span></span></button> <div class="actions__slot right"><a href="/profile/client/orders/1405" class="actions__slot--link">
     Открыть
     </a> <!----> <!----> <!----></div></div></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Дата заказа:
     </p> <p class="order-card__item--text">15 ноября 2022</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Тип заказа
     </p> <p class="order-card__item--text">Техническое обслуживание</p></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Адрес объекта:
     </p> <p class="order-card__item--text">
     Россия, Московская область, Люберцы
     </p></div> <div class="order-card__item hr flex-wrap"><p class="order-card__item--title">
     Оборудование:
     </p> <ul class="order-card__item--list scrollbar"><li>
     Плита газовая Centek CT-1522
     </li><li>
     Плита газовая Centek CT-1521
     </li></ul></div> <div class="order-card__item hr"><p class="order-card__item--title">
     Статус заказа:
     </p> <p class="order-card__item--text">
     Заказ отменен
     </p></div> <!----> <!----> <div class="order-card__item"><p class="order-card__item--title">
     Желаемая дата:
     </p> <p class="order-card__item--text">
     16 ноября 2022 - 29 ноября 2022
     </p></div> <div class="order-card__item"><p class="order-card__item--title">
     Желаемое время:
     </p> <p class="order-card__item--text">
     09:00 - 15:00
     </p></div> <!----></div></div></div></div></div></div></div></div></main></div>
     * */

private final String ORDER_PAGE_TITLE = "Список заказов";

    SelenideElement
    orderPageTitle = $(".page-title .h3.mb-2"),
    breadcrumbProfileClient = $(".breadcrumb .nuxt-link-active"),
            //$(".breadcrumb li:nth-child(2)"), - learn how to use :nth-child
    breadcrumbOrdersList = $x("//li[@aria-current='page'][contains(.,'Список заказов')]");
                    //$(".breadcrumb .breadcrumb-item active"); -not working
            //$(".breadcrumb li:nth-child(3)");

    ElementsCollection
    orderCardActionButtonCollection = $$x("(//button[contains(@type,'button')])"),
    orderCardOpenCollection = $$x("(//a[contains(@class,'actions__slot--link')])");





    public OrdersClientPage openPage() {
        open("/profile/client/orders");
        isOpened();
        return this;
    }

    public OrdersClientPage isOpened() {
        orderPageTitle.shouldHave(text(ORDER_PAGE_TITLE));
        breadcrumbProfileClient.shouldHave(text("Главная"));
        breadcrumbOrdersList.shouldHave(text("Список заказов"));
        return this;
    }

    public OrdersClientPage clickOrderCardActionButton(int orderCardNumber) {
        orderCardActionButtonCollection.get(orderCardNumber+1).click();
        return this;
    }

    public OrdersClientPage openOrder(int orderCardNumber) {
        orderCardOpenCollection.get(orderCardNumber-1).click();
        return this;
    }

    public OrdersClientPage toProfile() {
        breadcrumbProfileClient.shouldBe(visible).click();
        return this;
    }



}
