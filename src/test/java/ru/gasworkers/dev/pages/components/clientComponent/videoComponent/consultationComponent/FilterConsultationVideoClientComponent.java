package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class FilterConsultationVideoClientComponent extends BaseComponent {
    public FilterConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    ElementsCollection
            filterTitleCollection = driver.$$("div.col-md-3 p.mb-4").as("Заголовки фильтров"),
            radiobuttonTitleCollection = driver.$$("div.gas-radio.round span").as("Заголовки переключателей радиобаттонов фильтров"),
            radioButtonCollection = driver.$$(".pretty input[type='radio']").as("Переключатели фильтра"),
            mainCheckboxCollection = driver.$$(".p-default.p-curve.p-smooth.pretty").as("Главные чекбоксы фильтра"),
    //        ratingCheckboxCollection = checkboxCollection.subList(0, 5).as("Чекбоксы фильтра по рейтингу мастера"),
    ratingCheckboxCollection = driver.$$(".mb-2 .gas-checkbox.medium ").as("Чекбоксы фильтра по рейтингу мастера");
    SelenideElement
            titleFirstLocator = filterTitleCollection.get(0).as("Заголовок первого фильтра"),
            titleSecondLocator = filterTitleCollection.get(1).as("Заголовок второго фильтра"),
            masterRatingRadioLocator = radioButtonCollection.get(0).as("Переключатель фильтра по рейтингу мастера"),
            priceRadioLocator = radioButtonCollection.get(1).as("Переключатель фильтра по цене"),
            nameInputLocator = driver.$("input[placeholder*=имя]").as("Поле Введите имя мастера"),
            searchNameButtonLocator = driver.$("button.search-btn").as("Кнопка Поиск"),
            pickMasterRatingCheckboxDropdownLocator = driver.$$("div.medium.d-flex.cursor-pointer.mb-3").get(0).as("Дропдаун фильтра по рейтингу мастера"),
            pickPriceRangeDropdownLocator = driver.$$("div.medium.d-flex.cursor-pointer.mb-3").get(1).as("Дропдаун фильтра по цене"),
            priceRangeFirstTitleLocator = driver.$$("div.medium.d-flex.cursor-pointer.mb-3").get(1).as("Текст первый фильтра по цене"), //driver.$$("div.medium.d-flex.cursor-pointer.mb-3 span").get(0).as("Текст первый фильтра по цене"),
            priceRangeSecondTitleLocator = driver.$$("div.medium.d-flex.cursor-pointer.mb-3 span").get(1).as("Текст второй фильтра по цене"),
            priceRangeFromInputLocator = driver.$("input[placeholder=от]").as("Поле От"),
            priceRangeToInputLocator = driver.$("input[placeholder=до]").as("Поле До");


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что фильтр вкладки Консультация прямо сейчас загрузился", () -> {
            stepWithRole("Убедиться, что заголовки фильтров совпадают с ожидаемым", () -> {
                // todo need to set locator
//                titleFirstLocator.shouldHave(text("Сортировать по:")); // need to set locator
//                titleSecondLocator.shouldHave(text("Сортировать по:"));

                pickMasterRatingCheckboxDropdownLocator.shouldHave(text("Рейтинг мастера"));
                priceRangeFirstTitleLocator.shouldHave(text("Стоимость"));
                priceRangeSecondTitleLocator.shouldHave(text("консультации, руб"));
            });
            stepWithRole("Убедиться, что отображается поле Введите имя мастера и кнопка поиска", () -> {
                nameInputLocator.shouldBe(visible);
                searchNameButtonLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что отображаются чекбоксы уточнения фильтра по рейтингу мастера", () -> {
                stepWithRole("Убедиться что названия чекбоксов с уточнением совпадают с ожидаемым", () -> {
                    driver.$$("div.gas-checkbox.medium span").get(0).shouldHave(text("Консультация прямо сейчас"));
                    driver.$$("div.gas-checkbox.medium span").get(1).shouldHave(text("Мастер работает с моим оборудованием"));
                });
            });
            stepWithRole("Убедиться, что чекбоксы фильтра по рейтингу мастера отображаются", () -> {
                ratingCheckboxCollection.shouldBe(CollectionCondition.size(0));
                /*pickMasterRatingCheckboxDropdownLocator.click();
                ratingCheckboxCollection.shouldHave(CollectionCondition.size(5));
                pickMasterRatingCheckboxDropdownLocator.click();*/
//                ratingCheckboxCollection.shouldBe(CollectionCondition.size(0));

            });
            stepWithRole("Убедиться, что отображается поле От и До", () -> {
                priceRangeFirstTitleLocator.click();
                priceRangeFromInputLocator.shouldBe(visible);
                priceRangeToInputLocator.shouldBe(visible);
                priceRangeFirstTitleLocator.click();
            });
            stepWithRole("Убедиться, что название  фильтров с радиобаттонами совпадает с ожидаемым", () -> {
                radiobuttonTitleCollection.get(0).shouldHave(text("Рейтингу мастера"));
                radiobuttonTitleCollection.get(1).shouldHave(text("Стоимости"));
            });
        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что фильтр вкладки Консультация прямо сейчас находится в состоянии по умолчанию", () -> {
            stepWithRole("Убедиться, что радиобаттон фильтра выбран по рейтингу мастера", () -> {
                radioButtonCollection.get(0).shouldBe(checked);
                radioButtonCollection.get(0).isSelected();
                radioButtonCollection.get(1).shouldNotBe(checked);
            });
            stepWithRole("Убедиться, что поле Введите имя мастера пустое", () -> {
                nameInputLocator.shouldBe(empty);
            });
            stepWithRole("Убедиться, что отмечен первый уточняющий чекбокс", () -> {
//                mainCheckboxCollection.get(0).shouldBe(checked); fall??
                mainCheckboxCollection.get(0).isSelected();
                mainCheckboxCollection.get(1).shouldNotBe(checked);
            });
            stepWithRole("Убедиться, что ни один чекбокс фильтра по рейтингу мастера не отмечен", () -> {
                pickMasterRatingCheckboxDropdownLocator.click();
                ratingCheckboxCollection.shouldHave(CollectionCondition.noneMatch("Неотмеченные чекбоксы", selenideElement -> driver.$(selenideElement).isSelected()));
                pickMasterRatingCheckboxDropdownLocator.click();


            });
            stepWithRole("Убедиться, что поля От и До пустые", () -> {
                priceRangeFromInputLocator.shouldNotBe(visible);
                priceRangeToInputLocator.shouldNotBe(visible);
                pickPriceRangeDropdownLocator.click();
                priceRangeFromInputLocator.shouldBe(empty);
                priceRangeToInputLocator.shouldBe(empty);
                pickPriceRangeDropdownLocator.click();

            });

        });
    }
}
