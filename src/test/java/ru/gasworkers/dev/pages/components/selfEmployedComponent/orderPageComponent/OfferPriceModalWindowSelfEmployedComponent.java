package ru.gasworkers.dev.pages.components.selfEmployedComponent.orderPageComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.DatePickerDocumentsComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.*;

public class OfferPriceModalWindowSelfEmployedComponent extends BaseComponent {

    public final DatePickerDocumentsComponent datePicker;

    public OfferPriceModalWindowSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new DatePickerDocumentsComponent(browser);
    }

    private final String titleText = "Оборудование",
            howToGetCertificateText = "«Как получить сертификат»",
            masterIDSubtitleText = "Действующее удостоверение о квалификации",
            masterIDAttachFileDescriptionText = "Прикрепить удостоверение мастера (jpeg, pdf)",
            equipmentSubtitleText = "Оборудование:",
            equipmentCertificateAttachFileDescriptionText = "Прикрепить сертификат (jpeg, pdf)",
            attachLinkText = "Прикрепить файл",
            primaryVisitSubtitleText = "Первичный выезд мастера";

    private final SelenideElement self = driver.$("div.modal-content-wrapper").as("Модальное окно");

    private final ElementsCollection allEquipmentCollection = self.$$(".w-100.hr.mt-0.mb-3")
            .as("Коллекция  всего оборудования");
    private final ElementsCollection equipmentWithCertificateCollection = allEquipmentCollection
            .filter(Condition.text("котел"))
            .as("Коллекция оборудования с сертификатами");
    private final ElementsCollection equipmentWithoutCertificateCollection = allEquipmentCollection
            .filter(Condition.not(text("котел"))) // exclude(Condition.text("котел"))
            .as("Коллекция оборудования без сертификатов");

    ElementsCollection deleteUploadedEquipmentCertificateFileButtonCollection = self.$$("div.w-100.mb-3.mb-sm-0.pe-md-5.pe-3.d-flex .close").as("Коллекция кнопок удалить сертификат"),

    uploadFileLinkCollection = self.$$("div.photo-uploader input").as("Коллекция загрузчиков файлов"),
            uploadFileLinkTextCollection = self.$$("button.upload-button").as("Коллекция текстов загрузчиков файлов");

    SelenideElement titleLocator = self.$("span.text.bold").as("Заголовок"),
            howToGetCertificateLocator = self.$("div.order-price-title.d-flex.justify-content-between a span").as("Ссылка Как получить сертификат"),
            masterIDSubtitleLocator = self.$("div.w-100.text-center.h4").as("Подзаголовок Действующее удостоверение о квалификации"),
            masterIDAttachFileDescriptionLocator = self.$("div.d-flex.justify-content-between.flex-wrap.flex-sm-nowrap.p-2 span").as("Описание Прикрепить удостоверение мастера (jpeg, pdf)"),
            masterIDAttachFileBellLocator = self.$$("div[data-guide='certificate-notification'] img").filterBy(Condition.attributeMatching("src", ".*notification.*")).get(0).as("Значок Прикрепить удостоверение мастера"),
            uploadMasterIDFileLinkLocator = uploadFileLinkCollection.get(0).as("Ссылка Прикрепить удостоверение мастера"),
            uploadMasterIDFileLinkTextLocator = uploadFileLinkTextCollection.get(0).as("Текст ссылки Прикрепить удостоверение мастера"),
            deleteUploadedMasterIDFileLinkLocator = self.$("div[data-guide='certificate-notification'] .close").as("Ссылка удалить удостоверение мастера "),
            masterIDDatePickerComponentLocator = self.$("div.p-3.mb-32.notice-blue-light .mx-input.mx-input").as("Календарь Дата окончания действия удостоверения"),
            equipmentSubtitleLocator = self.$("div.order-details__title.medium.small.mb-20").as("Подзаголовок Оборудование:"),
            equipmentCertificateAttachFileDescriptionLocator = self.$("div div.w-100.mb-2 span.bold").as("Описание Прикрепить сертификат (jpeg, pdf)"),
            equipmentWorkPriceInputLocator = self.$("input[placeholder='Стоимость']").as("Поле Цена оборудования"),
            uploadEquipmentCertificateFileLinkLocator = uploadFileLinkCollection.get(1).as("Ссылка Прикрепить сертификат"),
            uploadEquipmentCertificateFileLinkTextLocator = uploadFileLinkTextCollection.get(1).as("Текст ссылки Прикрепить сертификат"),
            primaryVisitSubtitleLocator = self.$("div.w-100.hr-blue  .w-100.mb-3.mb-sm-0.pe-md-5.pe-3").as("Подзаголовок Первичный выезд мастера"),
            primaryVisitPriceInputLocator = self.$("input[placeholder*='от']").as("Поле Цена первичного выезда"),
            cancelButtonLocator = self.$("button.btn.btn-outline-primary").as("Кнопка Отмена"),
            saveButtonLocator = self.$("button.btn.btn-primary").as("Кнопка Сохранить");

    public void checkInitialState() {
        stepWithRole("Убедиться, что модальное окно в состоянии после регистрации", () -> {
            stepWithRole("Убедиться что модальное окно отображается", () -> {
                self.shouldBe(visible);
            });
            stepWithRole("Убедиться, что заголовок модального окна отображается", () -> {
                titleLocator.shouldHave(text(titleText));
            });
            stepWithRole("Убедиться, что ссылка Как получить сертификат отображается", () -> {
                howToGetCertificateLocator.shouldHave(text(howToGetCertificateText));
            });
            stepWithRole("Убедиться, что подзаголовок  удостоверение о квалификации отображается", () -> {
                masterIDSubtitleLocator.shouldHave(text(masterIDSubtitleText));
            });
            stepWithRole("Убедиться, что описание  Прикрепить удостоверение мастера отображается", () -> {
                masterIDAttachFileDescriptionLocator.shouldHave(text(masterIDAttachFileDescriptionText));
            });
            stepWithRole("Убедиться, что значок Прикрепить удостоверение мастера красный", () -> {
                masterIDAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
            });
            stepWithRole("Убедиться, что ссылка Прикрепить удостоверение мастера отображается", () -> {
                uploadMasterIDFileLinkTextLocator.shouldHave(text(attachLinkText));
            });
            stepWithRole("Убедиться, что календарь Дата окончания действия удостоверения отображается", () -> {
                masterIDDatePickerComponentLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что подзаголовок Оборудование: отображается", () -> {
                equipmentSubtitleLocator.shouldHave(text(equipmentSubtitleText));
            });
            //todo check  that all the equipment is displayed has its own price, every boiler has date and file and bell animation
            /*stepWithRole("Убедиться, что цена оборудования пустая", () -> {
                equipmentWorkPriceInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что календарь Дата окончания действия сертификата отображается", () -> {
                equipmentCertificateDatePickerComponentLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что описание Прикрепить сертификат отображается", () -> {
                equipmentCertificateAttachFileDescriptionLocator.shouldHave(text(equipmentCertificateAttachFileDescriptionText));
            });
            stepWithRole("Убедиться, что значок Прикрепить сертификат красный", () -> {
                equipmentCertificateAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
            });
            stepWithRole("Убедиться, что ссылка Прикрепить сертификат отображается", () -> {
                uploadEquipmentCertificateFileLinkTextLocator.shouldHave(text(attachLinkText));
            });*/
            stepWithRole("Убедиться, что подзаголовок Первичный выезд мастера отображается", () -> {
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

    public void checkOfferPriceEquipmentCount() {
        stepWithRole("Количество  наименований оборудования в предложении цены", () -> {
            stepWithRole("Коллекция всего оборудования", () -> {
                allEquipmentCollection.size();
            });
            stepWithRole("Коллекция оборудования с сертификатом", () -> {
                equipmentWithCertificateCollection.size();
            });
            stepWithRole("Коллекция оборудования без сертификата", () -> {
                equipmentWithoutCertificateCollection.size();
            });
        });

        System.out.println("Всего оборудования: " + allEquipmentCollection.size());
        System.out.println("Оборудование с сертификатом: " + equipmentWithCertificateCollection.size());
        System.out.println("Оборудование без сертификата: " + equipmentWithoutCertificateCollection.size());
    }


    public void fillCollectionPrices(String priceWOCertText, String priceWCertText, String pricePrimaryVisitText) {
        stepWithRole("Заполнить цены", () -> {
            stepWithRole("Заполнить цены сертифицированного  оборудования", () -> {
                for (SelenideElement equipment : equipmentWithCertificateCollection) {
                    fillPriceForEquipment(equipment, priceWCertText);
                }
            });

            stepWithRole("Заполнить цены не сертифицированного  оборудования", () -> {
                for (SelenideElement equipment : equipmentWithoutCertificateCollection) {
                    fillPriceForEquipment(equipment, priceWOCertText);
                }
            });

            stepWithRole("Заполнить цену первичного выезда", () -> {
                primaryVisitPriceInputLocator.setValue(pricePrimaryVisitText);
            });
        });

        System.out.println("Fill: " + equipmentWithCertificateCollection.size() + " cert items with price: " + priceWCertText);
        System.out.println("Fill: " + equipmentWithoutCertificateCollection.size() + " uncert items with price: " + priceWOCertText);
    }

    public int allEquipmentCurrentPrice() {
        return stepWithRole("Сумма цен всех оборудований", () -> {
            int sumCert = stepWithRole("Сумма цен всех сертифицированных оборудований", () ->
                    sumPrices(equipmentWithCertificateCollection, equipmentWithCertificateCollection.size())
            );
            int sumUncert = stepWithRole("Сумма цен всех не сертифицированных оборудований", () ->
                    sumPrices(equipmentWithoutCertificateCollection, equipmentWithoutCertificateCollection.size())
            );
            System.out.println("Сумма цен всех оборудований: " + sumCert + " + " + sumUncert + " = " + (sumCert + sumUncert));
            return sumCert + sumUncert;
        });
    }

    public int totalOfferPrice() {
        return stepWithRole("Сумма цен всех оборудований + цена первичного выезда", () -> {
            String currentPrimaryVisitPrice = primaryVisitPriceInputLocator.getValue();
            return allEquipmentCurrentPrice() + Integer.parseInt(currentPrimaryVisitPrice);
        });
    }

    public int sumPrices(ElementsCollection pricesCollection, int collectionSize) {
        return stepWithRole("Сумма цен  оборудований в коллекции", () -> {
            int sum = 0;
            for (int i = 0; i < collectionSize; i++) {
                String priceString = pricesCollection.get(i).find("input").getValue();
                try {
                    int priceInt = Integer.parseInt(priceString);
                    sum += priceInt;
                } catch (NumberFormatException e) {
                    // Handle invalid price string
                    // For example, you could log an error or throw an exception
                }
            }
            return sum;
        });
    }

    public void uploadMasterIDFile(File masterIDFile) {
        stepWithRole("Загрузить файл удостоверения мастера", () -> {
            uploadFileLinkCollection.get(0).uploadFile(masterIDFile);
            stepWithRole("Убедиться, что значок Прикрепить удостоверение мастера изменился на синий", () -> {
                masterIDAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-blue.svg"));
            });
            stepWithRole("Убедиться, что ссылка удалить удостоверение мастера отображается", () -> {
                deleteUploadedMasterIDFileLinkLocator.shouldBe(visible);
            });
        });
    }

    public void uploadAllBoilerEquipmentCertificateFileAndDate(File equipmentCertificateFile, String equipmentCertificateValidTillDate) {
        stepWithRole("Загрузка сертификата для каждого газового котла(анимации колокольчика, кнопка удалить) и установка даты действия сертификата", () -> {
            for (int i = 0; i < equipmentWithCertificateCollection.size(); i++) {
                SelenideElement card = equipmentWithCertificateCollection.get(i);
                stepWithRole("Карточка № " + i + 1 + ". Прикрепить сертификат для каждого газового котла", () -> {
                    card.find("input[type='file']").uploadFile(equipmentCertificateFile);

                    stepWithRole("Убедиться, что значок Прикрепить сертификат изменился на синий", () ->
                            card.find("div.w-100.mb-2 img").shouldHave(
                                    attribute("src", "https://dev.gasworkers.ru/images/notification-blue.svg")));

                    stepWithRole("Убедиться, что ссылка удалить сертификат оборудования отображается", () ->
                            card.find(".close").shouldBe(visible));

                    stepWithRole("Установить дату окончания действия сертификата оборудования", () ->
                            datePicker.setDate(card.find(".mx-input"), equipmentCertificateValidTillDate));
                });
            }
        });
    }

    public void deleteMasterId() {
        stepWithRole("Удалить файл удостоверения мастера", () -> {
            deleteUploadedMasterIDFileLinkLocator.click();
            stepWithRole("Убедиться, что значок Прикрепить удостоверение мастера изменился на красный", () -> {
                masterIDAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
            });
            stepWithRole("Убедиться, что ссылка удалить удостоверение мастера скрыта", () -> {
                deleteUploadedMasterIDFileLinkLocator.shouldNotBe(visible);
            });
        });
    }

    public void deleteEquipmentCertificate() {
        stepWithRole("Удалить файл сертификата оборудования", () -> {
            //todo fix for collection
            /*deleteUploadedEquipmentCertificateFileButtonCollection.click();
            stepWithRole("Убедиться, что значок Прикрепить сертификат изменился на красный", () -> {
                equipmentCertificateAttachFileBellCollection.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-red.svg"));
            });
            stepWithRole("Убедиться, что ссылка удалить сертификат оборудования скрыта", () -> {
                deleteUploadedEquipmentCertificateFileButtonCollection.shouldNotBe(visible);
            });*/
        });
    }

    /*public void fillAllBoilerEquipmentDocuments(File equipmentCertificateFile, String equipmentCertificateValidTillDate) {
        stepWithRole("Заполнить документы по газовым котлам", () -> {
            int count = equipmentWithCertificateCollection.size();
            stepWithRole("Загрузить файл сертификата оборудования", () -> {
                uploadAllBoilerEquipmentCertificateFile(equipmentCertificateFile);
            });
            stepWithRole("Установить дату окончания действия сертификата оборудования", () -> {
                datePicker.setDate(equipmentCertificateDatePickerComponentCollection.get(j), equipmentCertificateValidTillDate);
            });
        });

    }*/

    /* public void setAllEquipmentCertificationValidTillDate(String boilerEquipmentCertificateValidTillDatePicker) {
        stepWithRole("Установить дату окончания действия сертификата для каждого газового котла", () -> {
            datePicker.setDate(equipmentCertificateDatePickerComponentCollection, boilerEquipmentCertificateValidTillDatePicker);
        });
    }*/

    public void fillMasterIdDocuments(File masterIDFile, String masterIDValidTillDatePicker) {
        stepWithRole("Заполнить удостоверение мастера", () -> {
            stepWithRole("Загрузить файл удостоверения мастера", () -> {
                uploadMasterIDFile(masterIDFile);
            });
            stepWithRole("Установить дату окончания действия удостоверения мастера", () -> {
                datePicker.setDate(masterIDDatePickerComponentLocator, masterIDValidTillDatePicker);
            });
        });
    }

    public void checkFilledEmptyFormState() {
        stepWithRole("Убедиться, что форма предложения заполнена", () -> {
            stepWithRole("Убедиться, что значок Прикрепить удостоверение мастера изменился на синий", () -> {
                masterIDAttachFileBellLocator.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-blue.svg"));
            });
            stepWithRole("Убедиться, что ссылка удалить удостоверение мастера отображается", () -> {
                deleteUploadedMasterIDFileLinkLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что поле Дата окончания действия удостоверения мастера заполнено", () -> {
                masterIDDatePickerComponentLocator.shouldHave(attribute("value"));
            });
            //todo check  collection  bell color and close button
            /*stepWithRole("Убедиться, что значок Прикрепить сертификат изменился на синий", () -> {
                equipmentCertificateAttachFileBellCollection.shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-blue.svg"));
            });
            stepWithRole("Убедиться, что ссылка удалить сертификат оборудования отображается", () -> {
                deleteUploadedEquipmentCertificateFileButtonCollection.shouldBe(visible);
            });*/
            stepWithRole("Убедиться, что поле Цена работы с оборудованием пустое", () -> {
                equipmentWorkPriceInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что поле Цена первичного выезда пустое", () -> {
                primaryVisitPriceInputLocator.shouldBe(empty);
            });
        });
    }

    public void saveButton() {
        stepWithRole("Нажать кнопку Сохранить", () -> {
            saveButtonLocator.click();
        });
    }

    private void fillPriceForEquipment(SelenideElement equipment, String price) {
        equipment.find("input[type=number]").setValue(price);
    }

}
