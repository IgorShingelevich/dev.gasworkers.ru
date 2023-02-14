package ru.gasworkers.dev.pages.components.sharedComponent.backgroundRegistrationComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.model.equipment.EquipmentType;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.CommonCodeInputModalWindowComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.CommonDatePickerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.equipmentPicker.EquipmentBackgroundPicker;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class BackgroundRegistrationComponent extends BaseComponent {

    // TODO public final backgroundHeaderComponent backGroundHeader;
    // TODO public final  CommonDatePickerComponent datePicker;
    public final EquipmentBackgroundPicker equipmentPicker;
    public final CommonDatePickerComponent datePicker;
    public final CommonCodeInputModalWindowComponent codeInput;

    public BackgroundRegistrationComponent(RoleBrowser browser) {
        super(browser);
        equipmentPicker = new EquipmentBackgroundPicker(browser);
        datePicker = new CommonDatePickerComponent(browser);
        codeInput = new CommonCodeInputModalWindowComponent(browser);
    }

    private final String
        BG_MAINTENANCE_TITLE = "Заключить договор на техническое обслуживание дёшево и с гарантией",
        BG_MAINTENANCE_SUBTITLE = "Подбери лучшую компанию для проведения технического обслуживания газового оборудования с гарантией.",
        BG_MAINTENANCE_SUBTITLE_2 = "Укажите ваше оборудование и получите предложения.",
        BG_MAINTENANCE_SUBTITLE_3 = "Укажите оборудование и параметры поиска",
        BG_REPAIR_TITLE = "Провести ремонт газового оборудования профессиональным мастером",
        BG_REPAIR_SUBTITLE = "Подберем квалифицированного мастера для ремонта газового котла, установки и настройки оборудования.",
        BG_REPAIR_SUBTITLE_2 = "Выезд мастера по Московской области в день обращения от 30 минут.",
        BG_REPAIR_SUBTITLE_3 = "Укажите неисправное газовое оборудование и желаемый период проведения ремонта.",
        BG_VIDEO_TITLE = "Видеоконсультации по ремонту газового оборудования от 499 ₽",
        BG_VIDEO_SUBTITLE = "Поможем решить проблемы в удобное время по видеосвязи без выезда мастера.",
        BG_VIDEO_SUBTITLE_2 = "Вам придет смс со ссылкой на консультацию. Перейдя по ней, вы подлючетесь к видеокоференции со специалистом.",
        BG_VIDEO_SUBTITLE_3 = "Укажите ваше оборудование и найдите своего мастера.";





    SelenideElement
        requestTypeLocator = driver.$("div.gas-tabs-secondary__btn").as("Тип заявки"),
        bgTitleLocator = driver.$("div.h3.text-center.text-white").as("Заголовок"),
        bgSubtitleLocator = driver.$("div.medium.text-center.text-white").as("Подзаголовок"),
        bgSubtitle2Locator = driver.$("div.medium.text-center.text-white span").as("Подзаголовок 2"),
        bgSubtitle3Locator = driver.$("div.h4.text-center.mb-4.text-white").as("Подзаголовок 3"),
        addressFieldLocator = driver.$("div.search-option__address--title.short textarea").as("Поле ввода адреса"),
        dateDropdownLocator = driver.$("div.search-option__date--title").as("Дропдаун ввода даты"),
        credentialsDropdownLocator = driver.$("div.search-option__contacts--title").as("Дропдаун ввода данных данных"),
        credentialsContainerLocator = driver.$("div.search-option__contacts--dropdown").as("Меню ввода данных"),
        phoneFieldLocator = driver.$("input[placeholder='Номер телефона*']").as("Поле ввода телефона"),
        emailFieldLocator = driver.$("input[placeholder='Электронная почта**']").as("Поле ввода email"),
        approveCredentialsButtonLocator = credentialsContainerLocator.$("button.btn-fs-sm.btn.btn-primary.disable-outline span").as("Кнопка потвердить контактные данные"),
        findOffersButton = driver.$("button.text-primary-dark.btn.btn-warning.disable-outline").as("Найти предложения");
    ElementsCollection
        addressSuggestionsLocator = driver.$$("div.address-list").as("Подсказки адреса");

    public void fillBGMaintenanceRequest(String objectAddress, EquipmentType type, Integer mark, Integer brand, Integer power, String registrationDate, String phone, String email) {
        stepWithRole("Заполнить заявку на ТО в форме фоновой регистрации", () -> {
            stepWithRole("Указать адрес", () -> {
                stepWithRole("начать вводить адрес: " + objectAddress, () -> {
                    addressFieldLocator.setValue(objectAddress);
                });
                stepWithRole("Выбрать первую подсказку: " + addressSuggestionsLocator.get(0).getText(), () -> {
                    addressSuggestionsLocator.get(0).click();
                });
            });
        equipmentPicker.fillEquipment(type, mark, brand, power);
        stepWithRole("Указать дату регистрации", () -> {
            stepWithRole("Нажать на дропдаун даты", () -> {
                dateDropdownLocator.click();
            });
            datePicker.checkFinishLoading();
            datePicker.setTodayDate();
        });
        stepWithRole("Указать контактные данные", () -> {
                stepWithRole("Нажать на дропдаун контактных данных", () -> {
                    credentialsDropdownLocator.click();
                });
                stepWithRole("Проверить, что открылось меню ввода данных", () -> {
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
    }

    public void findOffers() {
        stepWithRole("Нажать кнопку найти предложения", () -> {
            findOffersButton.shouldHave(text("Найти предложения")).click();
        });
    }


}
