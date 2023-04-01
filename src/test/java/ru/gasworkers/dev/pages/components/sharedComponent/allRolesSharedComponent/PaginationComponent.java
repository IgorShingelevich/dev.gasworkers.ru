package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class PaginationComponent extends AllRolesSharedComponent {
    public PaginationComponent(RoleBrowser browser) {
        super(browser);
    }

    SelenideElement
            self = driver.$("div.gas-pagination").as("Компонент пагинации");

    ElementsCollection
            buttonCollection = self.$$("button").as("Кнопки пагинации"),
            pageCollection = self.$$("ul.gas-pagination__nav li a").as("Страницы пагинации");

    SelenideElement
    prevButton = buttonCollection.get(0).as("Кнопка 'Назад'"),
    nextButton = buttonCollection.get(1).as("Кнопка 'Вперед'");

    public void checkInitialState() {
        stepWithRole("Убедиться, что компонент пагинации  в исходном состоянии", () -> {
            stepWithRole("Убедиться, что кнопки 'Назад' и 'Вперед' неактивны", () -> {
                prevButton.shouldBe(disabled);
                nextButton.shouldBe(disabled);
            });
            stepWithRole("Убедиться, что отображается только  первая страница", () -> {
                pageCollection.shouldHave(size(1));
            });
            checkActivePageNumber(1);
        });
    }

    public void checkActivePageNumber(int pageNumber) {
        stepWithRole("Убедиться, что активна страница " + pageNumber, () -> {
            pageCollection.get(pageNumber - 1).shouldHave(cssClass("active"));
        });
    }

    public void checkNotVisible() {
        stepWithRole("Убедиться, что компонент пагинации не отображается", () -> {
            self.shouldNot(exist);
        });
    }


}
