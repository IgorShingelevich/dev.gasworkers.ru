package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class OfferPriceModalWindowSelfEmployedComponent extends BaseComponent {
    public OfferPriceModalWindowSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            titleText = "Оборудование",
            howToGetCertificateText = "\n" + "          «Как получить сертификат»\n" + "        ",
            masterIDSubtitleText = "Действующее удостоверение о квалификации",
            masterIDAttachFileDescriptionText = "Прикрепить удостоверение мастера (jpeg, pdf)",
            equipmentSubtitleText = "Оборудование:",
            equipmentCertificateAttachFileDescriptionText = "Прикрепить сертификат (jpeg, pdf)",
            attachLinkText = "Прикрепить файл",
            primaryVisitSubtitleText = "Первичный выезд мастера";


    SelenideElement
            modalWindowLocator = driver.$("div.modal-content-wrapper").as("Модальное окно");


    ElementsCollection
            fileUploaderCollection = modalWindowLocator.$$(".photo-uploader").as("Коллекция загрузчиков файлов");

    SelenideElement
            titleLocator = modalWindowLocator.$("span.text.bold").as("Заголовок"),
            howToGetCertificateLocator = modalWindowLocator.$("div.order-price-title.d-flex.justify-content-between a span").as("Ссылка Как получить сертификат"),
            masterIDSubtitleLocator = modalWindowLocator.$("div.w-100.text-center.h4").as("Подзаголовок Действующее удостоверение о квалификации"),
            masterIDAttachFileDescriptionLocator = modalWindowLocator.$("div.d-flex.justify-content-between.flex-wrap.flex-sm-nowrap.p-2 span").as("Описание Прикрепить удостоверение мастера (jpeg, pdf)"),
            masterIDAttachFileBellLocator = modalWindowLocator.$("div.d-flex.justify-content-between.flex-wrap.flex-sm-nowrap.p-2 img").as("Значок Прикрепить удостоверение мастера"),
            masterIDAttachFileLinkLocator = fileUploaderCollection.get(0).as("Ссылка Прикрепить удостоверение мастера"),
            masterIDDatePickerComponentLocator = modalWindowLocator.$("div.p-3.mb-32.notice-blue-light .mx-input.mx-input").as("Календарь Дата окончания действия удостоверения"),
            equipmentSubtitleLocator = modalWindowLocator.$("div.order-details__title.medium.small.mb-20").as("Подзаголовок Оборудование:"),
            equipmentCertificateAttachFileDescriptionLocator = modalWindowLocator.$("div div.w-100.mb-2 span.bold").as("Описание Прикрепить сертификат (jpeg, pdf)"),
            equipmentCertificateDatePickerComponentLocator = modalWindowLocator.$("div.equipment-price.d-flex  .mx-input.mx-input").as("Календарь Дата окончания действия сертификата"),
            equipmentPriceInputLocator = modalWindowLocator.$("input[placeholder='Стоимость']").as("Поле Цена оборудования"),
            equipmentCertificateAttachFileBellLocator = modalWindowLocator.$("div div.w-100.mb-2 img").as("Значок Прикрепить сертификат"),
            equipmentCertificateAttachFileLinkLocator = fileUploaderCollection.get(1).as("Ссылка Прикрепить сертификат"),
            primaryVisitSubtitleLocator = modalWindowLocator.$("div.w-100.hr-blue  .w-100.mb-3.mb-sm-0.pe-md-5.pe-3").as("Подзаголовок Первичный выезд мастера"),
            primaryVisitPriceInputLocator = modalWindowLocator.$("input[placeholder*='от']").as("Поле Цена первичного выезда"),
            cancelButtonLocator = modalWindowLocator.$("button.btn.btn-outline-primary").as("Кнопка Отмена"),
            saveButtonLocator = modalWindowLocator.$("button.btn.btn-primary").as("Кнопка Сохранить");

    public void checkInitialState(){
        stepWithRole("Убедиться, что модальное окно в состоянии после регистрации", () -> {
            stepWithRole("Убедиться что модальное окно отображается", () -> {
                modalWindowLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что заголовок модального окна отображается", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что ссылка Как получить сертификат отображается", () ->{
                howToGetCertificateLocator.shouldHave(text(howToGetCertificateText));
            });
            stepWithRole("Убедиться, что подзаголовок  удостоверение о квалификации отображается", () ->{
                masterIDSubtitleLocator.shouldHave(text(masterIDSubtitleText));
            });
            stepWithRole("Убедиться, что описание  Прикрепить удостоверение мастера отображается", () ->{
                masterIDAttachFileDescriptionLocator.shouldHave(text(masterIDAttachFileDescriptionText));
            });
            stepWithRole("Убедиться, что значок Прикрепить удостоверение мастера отображается", () ->{
                masterIDAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
                //todo red or blue
            });
            stepWithRole("Убедиться, что ссылка Прикрепить удостоверение мастера отображается", () -> {
                masterIDAttachFileLinkLocator.shouldHave(text(attachLinkText));
            });
            stepWithRole("Убедиться, что календарь Дата окончания действия удостоверения отображается", () -> {
                masterIDDatePickerComponentLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что подзаголовок Оборудование: отображается", () -> {
                equipmentSubtitleLocator.shouldHave(text(equipmentSubtitleText));
            });
            stepWithRole("Убедиться, что цена оборудования пустая", () -> {
                equipmentPriceInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что календарь Дата окончания действия сертификата отображается", () -> {
            equipmentCertificateDatePickerComponentLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что описание Прикрепить сертификат отображается", () -> {
                equipmentCertificateAttachFileDescriptionLocator.shouldHave(text(equipmentCertificateAttachFileDescriptionText));
            });
            stepWithRole("Убедиться, что значок Прикрепить сертификат отображается", () -> {
                equipmentCertificateAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
                //todo red or blue
            });
            stepWithRole("Убедиться, что ссылка Прикрепить сертификат отображается", () -> {
                equipmentCertificateAttachFileLinkLocator.shouldHave(text(attachLinkText));
            });
            stepWithRole("Убедиться, что подзаголовок Первичный выезд мастера отображается", () ->{
            primaryVisitSubtitleLocator.shouldHave(text(primaryVisitSubtitleText));
            });
            stepWithRole("Убедиться, что поле Цена первичного выезда пустое", () -> {
            primaryVisitPriceInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что кнопка Отмена отображается", () -> {
            cancelButtonLocator.shouldHave(text("Отменить"));
            });
            stepWithRole("Убедиться, что кнопка Сохранить активна", () -> {
                saveButtonLocator.shouldHave(text("Сохранить")).shouldBe(enabled);
            });

        });
    }


}
