package ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navProfile;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.profilePageComponent.BaseProfileSelfEmployedComponent;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;

import static com.codeborne.selenide.Condition.*;

public class NavProfileTabSelfEmployedProfilePageComponent extends BaseProfileSelfEmployedComponent {
    public final AddPhotoProfileTabComponent addPhoto;
    public final AddDiplomaProfileTabComponent addDiploma;

    public NavProfileTabSelfEmployedProfilePageComponent(RoleBrowser browser) {
        super(browser);
        addPhoto = new AddPhotoProfileTabComponent(browser);
        addDiploma = new AddDiplomaProfileTabComponent(browser);
    }

    private final String
            mainButtonText = "Сохранить",
            radiusOfWorkTitleText = "Радиус работы, км",
            doVideoTitleText = "Провожу видеоконсультации",
            specialityCounterDefaultText = "0 символов из 50 доступных",
            skillsCounterDefaultText = "0 символов из 1000 доступных",
            toOrderButtonText = "К заказу";

    SelenideElement
            specialityInputLocator = driver.$("input[placeholder*='Ваша специальность']").as("Специальность"),
            specialitySymbolCounterLocator = specialityInputLocator.sibling(0).as("Счетчик символов специальности"),
            skillsInputLocator = driver.$("textarea[placeholder*='Ваши навыки']").as("Навыки"),
            skillsSymbolCounterLocator = skillsInputLocator.parent().sibling(0).as("Счетчик символов навыков"),
            addressInputLocator = driver.$("input[placeholder*='Адрес']").as("Адрес"),
            apartmentNumberLocator = driver.$("input[placeholder*='Номер квартиры']").as("Номер квартиры"),
            radiusOfWorkBoxLocator = driver.$$("div.col-md-7.col-lg-4").get(0).as("Бокс радиус работы"),
            radiusOfWorkTitleLocator = driver.$("div.title").as("Заголовок радиус работы"),
            radiusOfWorkInputLocator = driver.$("input[placeholder='км']").as("Радиус работы"),
            doVideoCheckboxLocator = driver.$("input[type='checkbox']").as("Чекбокс провожу видеоконсультации"),
            videoPriceBoxLocator = driver.$$("div.col-md-7.col-lg-4").get(1).as("Бокс цена на видеоконсультацию"),
            videoPriceInputLocator = driver.$("input[placeholder='минимум 500 рублей']").as("Цена на видеоконсультацию");

    public void fillRandomData(RandomSelfEmployedAndMaster randomSelfEmployedAndMaster) {
        stepWithRole("Заполнить случайными данными все элементы вкладки профиль ", () -> {
//            todo
        });
    }

    public void checkFirsOfferEvaluatedInitialState() {
        stepWithRole("Убедиться, что вкладка профиль в начальном состоянии", () -> {
            addPhoto.checkInitialState();
            addDiploma.checkInitialState();
            specialityInputLocator.shouldBe(empty);
            specialitySymbolCounterLocator.shouldHave(text(specialityCounterDefaultText));
            skillsInputLocator.shouldBe(empty);
            skillsSymbolCounterLocator.shouldHave(text(skillsCounterDefaultText));
            addressInputLocator.shouldBe(empty);
            apartmentNumberLocator.shouldBe(empty);
            radiusOfWorkTitleLocator.shouldHave(text(radiusOfWorkTitleText));
            radiusOfWorkInputLocator.shouldBe(empty);
            doVideoCheckboxLocator.shouldNotBe(checked);
            videoPriceInputLocator.shouldBe(empty);
            button.main.isEnabled();
            toOrderContextButtons.checkToOrderContextButtonsPresenceState();
        });
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что вкладка профиль в начальном состоянии", () -> {
            addPhoto.checkInitialState();
            addDiploma.checkInitialState();
            specialityInputLocator.shouldBe(empty);
            specialitySymbolCounterLocator.shouldHave(text(specialityCounterDefaultText));
            skillsInputLocator.shouldBe(empty);
            skillsSymbolCounterLocator.shouldHave(text(skillsCounterDefaultText));
            addressInputLocator.shouldBe(empty);
            apartmentNumberLocator.shouldBe(empty);
            radiusOfWorkTitleLocator.shouldHave(text(radiusOfWorkTitleText));
            radiusOfWorkInputLocator.shouldBe(empty);
            doVideoCheckboxLocator.shouldNotBe(checked);
            videoPriceInputLocator.shouldBe(empty);
            radiusOfWorkBoxLocator.shouldBe(visible);
            videoPriceBoxLocator.shouldBe(visible);
            button.main.isEnabled();
            validation.checkTotalNoErrors();
        });
        toOrderContextButtons.checkNoToOrderContextButtonsPresenceState();
    }

    public void saveButton() {
        button.main.clickActive(mainButtonText);
    }

    public void checkEmptyFormValidationTriggeredState() {

    }
}



