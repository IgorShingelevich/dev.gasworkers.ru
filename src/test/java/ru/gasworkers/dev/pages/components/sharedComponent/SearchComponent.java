package ru.gasworkers.dev.pages.components.sharedComponent;

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
    self =driver.$("div.gas-search-box").as("Строка поиска"),
    searchButtonLocator = self.$("button.search-btn").as("Кнопка поиска"),
    inputLocator = self.$("input[placeholder]").as("Поле ввода");


    public void checkInitialState(String placeholder) {
        stepWithRole("Убедиться, что строка поиска: " + placeholder + " в начальном состоянии", () -> {
            SelenideElement closestSearchComponent = self.find("input[placeholder='" + placeholder + "']").closest("div.gas-search-box");
            stepWithRole("Убедиться, что поле ввода пустое", () -> {
                closestSearchComponent.$("input[placeholder]").shouldHave(empty);
            });
            stepWithRole("Убедиться, что кнопка поиска отображается", () -> {
                closestSearchComponent.$("button.search-btn").shouldBe(Condition.visible);
            });
        });
    }
}
