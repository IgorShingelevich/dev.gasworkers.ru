package ru.gasworkers.dev.pages.components.landingComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.client.BackgroundClientRequestType;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.AddressBGRegistrationLandingComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.ContactsBGRegistrationLandingComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.CommonCodeInputModalWindowComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.EquipmentBGRegistrationLandingComponent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class BackgroundRegistrationComponent extends BaseComponent {

    // TODO public final backgroundHeaderComponent backGroundHeader;
    // TODO public final  DateBGRegistrationLandingComponent datePicker;

    public final AddressBGRegistrationLandingComponent address;

    public final EquipmentBGRegistrationLandingComponent equipment;
    public final DateBGRegistrationLandingComponent date;
    public final ContactsBGRegistrationLandingComponent contacts;
    public final CommonCodeInputModalWindowComponent codeInput;

    public BackgroundRegistrationComponent(RoleBrowser browser) {
        super(browser);
        address = new AddressBGRegistrationLandingComponent(browser);
        equipment = new EquipmentBGRegistrationLandingComponent(browser);
        date = new DateBGRegistrationLandingComponent(browser);
        contacts = new ContactsBGRegistrationLandingComponent(browser);
        codeInput = new CommonCodeInputModalWindowComponent(browser);
    }

    private final String
        BG_MAINTENANCE_TITLE = "Заключите договор на техническое обслуживание газового оборудования и системы отопления с гарантией от поломок",
        BG_MAINTENANCE_SUBTITLE = "",
//        BG_MAINTENANCE_SUBTITLE_2 = "",
        BG_MAINTENANCE_SUBTITLE_3 = "Укажите оборудование и параметры поиска",
        BG_REPAIR_TITLE = "Ремонт газового оборудования и системы отопления от 499 руб",
        BG_REPAIR_SUBTITLE = "Выезд мастера по Московской области в день обращения от 30 минут",
//        BG_REPAIR_SUBTITLE_2 = "Выезд мастера по Московской области в день обращения от 30 минут.",
        BG_REPAIR_SUBTITLE_3 = "Укажите неисправное газовое оборудование и желаемый период проведения ремонта",
        BG_VIDEO_TITLE = "Видеоконсультации по ремонту газового оборудования и системы отопления от 499 ₽",
        BG_VIDEO_SUBTITLE = "Поможем решить проблемы в удобное время по видеосвязи без выезда мастера",
//        BG_VIDEO_SUBTITLE_2 = "Вам придет смс со ссылкой на консультацию. Перейдя по ней, вы подлючетесь к видеокоференции со специалистом.",
        BG_VIDEO_SUBTITLE_3 = "Укажите ваше оборудование и найдите своего мастера";





    SelenideElement
        bgTitleLocator = driver.$("div.h3.text-center.text-white").as("Заголовок"),
        bgSubtitleLocator = driver.$("div.medium.text-center.text-white").as("Подзаголовок"),
        bgSubtitle2Locator = driver.$("div.medium.text-center.text-white span").as("Подзаголовок 2"),
        bgSubtitle3Locator = driver.$("div.h4.text-center.text-white").as("Подзаголовок 3"),
        addressFieldLocator = driver.$("div.search-option__address--title.short textarea").as("Поле ввода адреса"),
        dateFieldLocator = driver.$("div.search-option__date--title").as("Дропдаун ввода даты"),
        filledDateFieldLocator = driver.$("div.search-option__date--title-result").as("Поле заполенная  Дата"),
        credentialsDropdownLocator = driver.$("div.search-option__contacts--title").as("Дропдаун ввода данных данных"),
        credentialsContainerLocator = driver.$("div.search-option__contacts--dropdown").as("Меню ввода данных"),
        phoneFieldLocator = driver.$("input[placeholder='Номер телефона*']").as("Поле ввода телефона"),
        emailFieldLocator = driver.$("input[placeholder='Электронная почта**']").as("Поле ввода email"),
        approveCredentialsButtonLocator = credentialsContainerLocator.$("button.btn-fs-sm.btn.btn-primary.disable-outline span").as("Кнопка потвердить контактные данные"),
        findOffersButton = driver.$("button.text-primary-dark.btn.btn-warning.disable-outline").as("Найти предложения");
    ElementsCollection
        requestTypeLocator = driver.$$("div.gas-tabs-secondary__btn ").as("Тип заявки"),
        addressSuggestionsCollection = driver.$$("div.address-list li").as("Подсказки адреса"),
        filledCredentialsFieldLocator = driver.$$("div.search-option__contacts--title-result div").as("Поле заполненные данные");



    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент Фоновая регистрация загружен", () -> {
            stepWithRole("Убедиться, что отображаются компоненты Фоновой ТО", () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.MAINTENANCE.getTitle())).click();
                bgTitleLocator.shouldHave(text(BG_MAINTENANCE_TITLE));
                bgSubtitleLocator.shouldBe(empty);
//                bgSubtitle2Locator.shouldHave(exactText(BG_MAINTENANCE_SUBTITLE_2));
                bgSubtitle3Locator.shouldHave(text(BG_MAINTENANCE_SUBTITLE_3));
                findOffersButton.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображаются компоненты Фонового ремонта", () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.REPAIR.getTitle())).click();
                bgTitleLocator.shouldHave(text(BG_REPAIR_TITLE));
                bgSubtitleLocator.shouldHave(text(BG_REPAIR_SUBTITLE));
//                bgSubtitle2Locator.shouldHave(text(BG_REPAIR_SUBTITLE_2));
                bgSubtitle3Locator.shouldHave(text(BG_REPAIR_SUBTITLE_3));
                findOffersButton.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображаются компоненты Фоновой видеоконсультации", () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.VIDEO.getTitle())).click();
                bgTitleLocator.shouldHave(text(BG_VIDEO_TITLE));
                bgSubtitleLocator.shouldHave(text(BG_VIDEO_SUBTITLE));
//                bgSubtitle2Locator.shouldHave(text(BG_VIDEO_SUBTITLE_2));
                bgSubtitle3Locator.shouldHave(text(BG_VIDEO_SUBTITLE_3));
                findOffersButton.shouldBe(visible);
            });
            requestTypeLocator.findBy(text(BackgroundClientRequestType.MAINTENANCE.getTitle())).click();
            //TODO check form components presence
        });
    }

    public void fillBGMaintenanceRequest(String objectAddress, EquipmentType type, Integer mark, Integer brand, Integer power, String phone, String email) {
        stepWithRole("Заполнить заявку на ТО в форме фоновой регистрации", () -> {
            stepWithRole("Выбрать тип заявки: " + BackgroundClientRequestType.MAINTENANCE.getTitle(), () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.MAINTENANCE.getTitle())).click();
            });
            stepWithRole("Убедиться, что выбран тип заявки: " + BackgroundClientRequestType.MAINTENANCE.getTitle(), () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.MAINTENANCE.getTitle())).shouldHave(Condition.cssClass("active"));
            });
           /* stepWithRole("Указать адрес", () -> {
                stepWithRole("начать вводить адрес: " + objectAddress, () -> {
                    addressFieldLocator.setValue(objectAddress);
                    System.out.println("start input address: " + addressFieldLocator.getValue());
                });
                stepWithRole("Выбрать первую подсказку: " + addressSuggestionsCollection.get(0).getText(), () -> {
                    addressSuggestionsCollection.get(0).click();
                    System.out.println("selected suggestion: " + addressSuggestionsCollection.get(0).getText());
                });
                stepWithRole("Убедиться, что скрыты подсказки адреса", () -> {
                    addressSuggestionsCollection.shouldHave(size(0));
                });
                stepWithRole("Убедиться, что искомый запрос: " + objectAddress + " содержится в выбранном адресе: " + addressFieldLocator.getValue(), () -> {
                    addressFieldLocator.shouldHave(partialValue(objectAddress));
                });
            });*/
        address.fillAddressField(objectAddress);
        equipment.fillMaintenanceEquipment(type, mark, brand, power);
        stepWithRole("Указать дату регистрации", () -> {
            stepWithRole("Нажать на поле дата", () -> {
                dateFieldLocator.click();
            });
            date.checkFinishLoading();
            date.setTodayDate();
        });
        stepWithRole("Указать контактные данные", () -> {
                stepWithRole("Нажать на поле контактне данне", () -> {
                    credentialsDropdownLocator.click();
                });
                stepWithRole("Проверить, что открылось меню ввода контактных данных", () -> {
                    credentialsContainerLocator.shouldBe(visible);
                });
                stepWithRole("Ввести телефон: " + phone, () -> {
                    phoneFieldLocator.setValue(phone);
                });
                stepWithRole("Ввести email: " + email, () -> {
                    emailFieldLocator.setValue(email);
                });
            });
            stepWithRole("Подтвердить контактные данные", () -> {
            approveCredentialsButtonLocator.click();
            });
        });
        stepWithRole("Проверить заполненные данные", () -> {
            stepWithRole("Убедиться, что выбран адрес: " + addressFieldLocator.getValue()  , () -> {
                addressFieldLocator.shouldNotBe(empty);
                System.out.println("result address: " + addressFieldLocator.getValue());
            });
            stepWithRole("Убедиться, что дата сегодняшняя  выбрана: " + filledDateFieldLocator.getValue(), () -> {
                filledDateFieldLocator.shouldHave(Condition.text(LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"))));
                System.out.println("result date: " + filledDateFieldLocator.getText());
            });
            String formattedPhone = "+7(" + phone.toString().substring(1, 4) + ")-" + phone.toString().substring(4, 7) + "-" + phone.toString().substring(7);
            stepWithRole("Убедиться, что заполнены  почта: " + filledCredentialsFieldLocator.get(0).getText() + ", и телефон: " + filledCredentialsFieldLocator.get(1).getText(), () -> {
                filledCredentialsFieldLocator.get(0).shouldHave(text(email));
                filledCredentialsFieldLocator.get(1).shouldHave(text(formattedPhone));
                System.out.println("result email: " + filledCredentialsFieldLocator.get(0).getText());
                System.out.println("result phone: " + filledCredentialsFieldLocator.get(1).getText());
            });
        });

    }

    public void fillBGRepairRequest(String objectAddress, EquipmentType type, Integer mark, Integer brand, Integer power, String phone, String email) {
        stepWithRole("Заполнить заявку на ремонт в форме фоновой регистрации", () -> {
            stepWithRole("Выбрать тип заявки: " + BackgroundClientRequestType.REPAIR.getTitle(), () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.REPAIR.getTitle())).click();
            });
            address.fillAddressField(objectAddress);
            equipment.fillRepairEquipment(type, mark, brand, power);
            stepWithRole("Указать дату регистрации", () -> {
                stepWithRole("Нажать на дропдаун даты", () -> {
                    dateFieldLocator.click();
                });
                date.checkFinishLoading();
                date.setTodayDate();
            });
            stepWithRole("Указать контактные данные", () -> {
                stepWithRole("Нажать на дропдаун контактных данных", () -> {
                    credentialsDropdownLocator.click();
                });
                stepWithRole("Убедиться, что открылось меню ввода контактных данных", () -> {
                    credentialsContainerLocator.shouldBe(visible);
                });
                stepWithRole("Ввести телефон: " + phone, () -> {
                    phoneFieldLocator.setValue(phone);
                });
                stepWithRole("Ввести email: " + email, () -> {
                    emailFieldLocator.setValue(email);
                });
            });
            stepWithRole("Подтвердить контактные данные", () -> {
                approveCredentialsButtonLocator.click();
            });
            stepWithRole("Убедиться, что  меню контактных данных закрылось", () -> {
                credentialsContainerLocator.shouldNotBe(visible);
            });
        });
    }

    public void fillBGVideoRequest(String objectAddress, EquipmentType type, Integer mark, Integer brand, Integer power, String phone, String email) {
        stepWithRole("Заполнить заявку на Видео в форме фоновой регистрации", () -> {
            stepWithRole("Выбрать тип заявки: " + BackgroundClientRequestType.VIDEO.getTitle(), () -> {
                requestTypeLocator.findBy(text(BackgroundClientRequestType.VIDEO.getTitle())).click();
            });
            address.fillAddressField(objectAddress);
            equipment.fillVideoEquipment(type, mark, brand, power);
            stepWithRole("Указать контактные данные", () -> {
                stepWithRole("Нажать на дропдаун контактных данных", () -> {
                    credentialsDropdownLocator.click();
                });
                stepWithRole("Убедиться, что открылось меню ввода контактных данных", () -> {
                    credentialsContainerLocator.shouldBe(visible);
                });
                stepWithRole("Ввести телефон: " + phone, () -> {
                    phoneFieldLocator.setValue(phone);
                });
                stepWithRole("Ввести email: " + email, () -> {
                    emailFieldLocator.setValue(email);
                });
            });
            stepWithRole("Подтвердить контактные данные", () -> {
                approveCredentialsButtonLocator.click();
            });
            stepWithRole("Убедиться, что  меню контактных данных закрылось", () -> {
                credentialsContainerLocator.shouldNotBe(visible);
            });
        });
    }



    public void findOffers() {
        stepWithRole("Нажать кнопку найти предложения", () -> {
            findOffersButton.shouldHave(text("Найти предложения")).click();
        });
    }
}
// todo pick random address suggestion and equipment