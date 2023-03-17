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

    private final String
            titleText = "Оборудование",
            howToGetCertificateText = "«Как получить сертификат»",
            masterIDSubtitleText = "Действующее удостоверение о квалификации",
            masterIDAttachFileDescriptionText = "Прикрепить удостоверение мастера (jpeg, pdf)",
            equipmentSubtitleText = "Оборудование:",
            equipmentCertificateAttachFileDescriptionText = "Прикрепить сертификат (jpeg, pdf)",
            attachLinkText = "Прикрепить файл",
            primaryVisitSubtitleText = "Первичный выезд мастера";


    SelenideElement
            modalWindowLocator = driver.$("div.modal-content-wrapper").as("Модальное окно");


    ElementsCollection
            allEquipmentCollection = modalWindowLocator.$$(".w-100.hr.mt-0.mb-3").as("Коллекция  всего оборудования"),
            equipmentWithCertificateCollection = allEquipmentCollection.filterBy(Condition.text("котел")).as("Коллекция оборудования с сертификатами"),
            equipmentCertificateAttachFileBellCollection = modalWindowLocator.$$("div div.w-100.mb-2 img").as("Коллекция значков Прикрепить сертификат"),
            equipmentCertificateDatePickerComponentCollection = driver.$$("div.equipment-price.d-flex  .mx-input.mx-input").as("Коллекция Календарей Дата окончания действия сертификата газового котла"),

            deleteUploadedEquipmentCertificateFileButtonCollection = modalWindowLocator.$$("div.w-100.mb-3.mb-sm-0.pe-md-5.pe-3.d-flex .close").as("Коллекция кнопок удалить сертификат"),


    equipmentWithoutCertificateCollection = allEquipmentCollection.exclude(Condition.text("котел")).as("Коллекция оборудования без сертификатов"),
            uploadFileLinkCollection = modalWindowLocator.$$("div.photo-uploader input").as("Коллекция загрузчиков файлов"),
            uploadFileLinkTextCollection = modalWindowLocator.$$("button.upload-button").as("Коллекция текстов загрузчиков файлов");

    SelenideElement
            titleLocator = modalWindowLocator.$("span.text.bold").as("Заголовок"),
            howToGetCertificateLocator = modalWindowLocator.$("div.order-price-title.d-flex.justify-content-between a span").as("Ссылка Как получить сертификат"),
            masterIDSubtitleLocator = modalWindowLocator.$("div.w-100.text-center.h4").as("Подзаголовок Действующее удостоверение о квалификации"),
            masterIDAttachFileDescriptionLocator = modalWindowLocator.$("div.d-flex.justify-content-between.flex-wrap.flex-sm-nowrap.p-2 span").as("Описание Прикрепить удостоверение мастера (jpeg, pdf)"),
            masterIDAttachFileBellLocator = modalWindowLocator.$$("div[data-guide='certificate-notification'] img").filterBy(Condition.attributeMatching("src", ".*notification.*")).get(0).as("Значок Прикрепить удостоверение мастера"),
            uploadMasterIDFileLinkLocator = uploadFileLinkCollection.get(0).as("Ссылка Прикрепить удостоверение мастера"),
            uploadMasterIDFileLinkTextLocator = uploadFileLinkTextCollection.get(0).as("Текст ссылки Прикрепить удостоверение мастера"),
            deleteUploadedMasterIDFileLinkLocator = modalWindowLocator.$("div[data-guide='certificate-notification'] .close").as("Ссылка удалить удостоверение мастера "),
            masterIDDatePickerComponentLocator = modalWindowLocator.$("div.p-3.mb-32.notice-blue-light .mx-input.mx-input").as("Календарь Дата окончания действия удостоверения"),
            equipmentSubtitleLocator = modalWindowLocator.$("div.order-details__title.medium.small.mb-20").as("Подзаголовок Оборудование:"),
            equipmentCertificateAttachFileDescriptionLocator = modalWindowLocator.$("div div.w-100.mb-2 span.bold").as("Описание Прикрепить сертификат (jpeg, pdf)"),
            equipmentWorkPriceInputLocator = modalWindowLocator.$("input[placeholder='Стоимость']").as("Поле Цена оборудования"),
            uploadEquipmentCertificateFileLinkLocator = uploadFileLinkCollection.get(1).as("Ссылка Прикрепить сертификат"),
            uploadEquipmentCertificateFileLinkTextLocator = uploadFileLinkTextCollection.get(1).as("Текст ссылки Прикрепить сертификат"),
            primaryVisitSubtitleLocator = modalWindowLocator.$("div.w-100.hr-blue  .w-100.mb-3.mb-sm-0.pe-md-5.pe-3").as("Подзаголовок Первичный выезд мастера"),
            primaryVisitPriceInputLocator = modalWindowLocator.$("input[placeholder*='от']").as("Поле Цена первичного выезда"),
            cancelButtonLocator = modalWindowLocator.$("button.btn.btn-outline-primary").as("Кнопка Отмена"),
            saveButtonLocator = modalWindowLocator.$("button.btn.btn-primary").as("Кнопка Сохранить");

    public void checkInitialState() {
        stepWithRole("Убедиться, что модальное окно в состоянии после регистрации", () -> {
            stepWithRole("Убедиться что модальное окно отображается", () -> {
                modalWindowLocator.shouldBe(visible);
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
        Integer equipmentWithCertificateCount = equipmentWithCertificateCollection.size();
        Integer equipmentWithoutCertificateCount = equipmentWithoutCertificateCollection.size();
        Integer allEquipmentCount = allEquipmentCollection.size();

        stepWithRole("Заполнить цены ", () -> {
            stepWithRole("Заполнить цены сертифицированного  оборудования", () -> {
                fillCollectionPrices(equipmentWithCertificateCollection, equipmentWithCertificateCount, priceWCertText);
            });
            stepWithRole("Заполнить цены не сертифицированного  оборудования", () -> {
                fillCollectionPrices(equipmentWithoutCertificateCollection, equipmentWithoutCertificateCount, priceWOCertText);
            });
            stepWithRole("Заполнить цену первичного выезда", () -> {
                primaryVisitPriceInputLocator.setValue(pricePrimaryVisitText);
            });
        });
        System.out.println("Fill: " + equipmentWithCertificateCount + " cert items with price: " + priceWCertText);
        System.out.println("Fill: " + equipmentWithoutCertificateCount + " uncert items with price: " + priceWOCertText);

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

    public static void fillCollectionPrices(ElementsCollection collection, Integer elementsCount, String priceText) {

        for (int i = 0; i < elementsCount; i++) {
            collection.get(i)
                    .find("input[type='number']").setValue(priceText);
        }

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
            int count = equipmentWithCertificateCollection.size();
            stepWithRole("Прикрепить сертификат для каждого газового котла", () -> {
                for (int i = 0; i < count; i++) {
                    equipmentWithCertificateCollection.get(i).find("input[type='file']").uploadFile(equipmentCertificateFile);
                    int j = i; // create a new variable j inside the loop
                    stepWithRole("Убедиться, что значок Прикрепить сертификат изменился на синий", () -> {
                        equipmentCertificateAttachFileBellCollection.get(j).shouldHave(attribute("src", "https://dev.gasworkers.ru/images/notification-blue.svg"));
                    });
                    stepWithRole("Убедиться, что ссылка удалить сертификат оборудования отображается", () -> {
                        deleteUploadedEquipmentCertificateFileButtonCollection.get(j).shouldBe(visible);
                    });
                    stepWithRole("Установить дату окончания действия сертификата оборудования", () -> {
                        datePicker.setDate(equipmentCertificateDatePickerComponentCollection.get(j), equipmentCertificateValidTillDate);
                    });

                }
            });
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


}
