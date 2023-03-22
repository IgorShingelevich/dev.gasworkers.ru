package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navProfile;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.*;

public class NavProfileTabSelfEmployedProfilePageComponent extends BaseComponent {
    public final AddPhotoProfileTabComponent addPhoto;
    public final AddDiplomaProfileTabComponent addDiploma;

    public NavProfileTabSelfEmployedProfilePageComponent(RoleBrowser browser) {
        super(browser);
        addPhoto = new AddPhotoProfileTabComponent(browser);
        addDiploma = new AddDiplomaProfileTabComponent(browser);
    }

    private final String
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
            radiusOfWorkTitleLocator = driver.$("div.title").as("Заголовок радиус работы"),
            radiusOfWorkInputLocator = driver.$("input[placeholder='км']").as("Радиус работы"),
            doVideoCheckboxLocator = driver.$("input[type='checkbox']").as("Чекбокс провожу видеоконсультации"),
            videoPriceInputLocator = driver.$("input[placeholder='минимум 500 рублей']").as("Цена на видеоконсультацию"),
            saveButtonLocator = driver.$("button.btn.btn-primary").as("Кнопка Сохранить"),
            toOrderBottomButtonLocator = driver.$("button.mr-2.mb-3.btn.btn-warning").as("Нижняя кнопка К заказу"),
            toOrderTopButtonLocator = driver.$(".w-100 button.btn.btn-warning.disable-outline").as("Верхняя кнопка К заказу");

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
            toOrderTopButtonLocator.shouldBe(visible);
            toOrderBottomButtonLocator.shouldBe(visible);
            saveButtonLocator.shouldBe(enabled);
        });

    }
}



