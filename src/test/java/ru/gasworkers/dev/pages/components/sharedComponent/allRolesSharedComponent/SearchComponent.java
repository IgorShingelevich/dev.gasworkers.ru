package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.empty;

public class SearchComponent extends BaseComponent {
    public SearchComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            box = driver.$("div.gas-search-box").as("Строка поиска"),
            searchButtonLocator = box.$("button.search-btn").as("Кнопка поиска"),
            inputLocator = box.$("input[placeholder]").as("Поле ввода");


    public void checkInitialState(String placeholder) {
        stepWithRole("Убедиться, что строка поиска: " + placeholder + " в начальном состоянии", () -> {
//            SelenideElement closestSearchComponent = box.find("input[placeholder='" + placeholder + "']").closest("div.gas-search-box");
            SelenideElement
                    searchField = driver.$("input[placeholder='" + placeholder + "']"),
                    closestSearchComponent = searchField.ancestor(".gas-search-box"),
                    searchButton = closestSearchComponent.$("button.search-btn");


            stepWithRole("Убедиться, что поле ввода пустое", () -> {
               searchField.shouldBe(empty);
            });
            stepWithRole("Убедиться, что кнопка поиска отображается", () -> {
                searchButton.shouldBe(Condition.visible);
            });
        });
    }
}
