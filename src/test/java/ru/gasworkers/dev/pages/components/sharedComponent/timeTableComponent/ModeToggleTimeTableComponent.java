package ru.gasworkers.dev.pages.components.sharedComponent.timeTableComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.visible;

public class ModeToggleTimeTableComponent extends BaseComponent {
    public ModeToggleTimeTableComponent(RoleBrowser browser) {
        super(browser);
    }

    private final String
            videoToggleText = "Дни проведения видеоконсультаций",
            offersToggleText = "Календарь с заказами";

    ElementsCollection
            togglePositionCollection = driver.$$("div.master-timetable-label.bold.mx-2.link-gray.pointer").as("Позиция переключателя режима расписания");

    SelenideElement
            toggleBoxLocator = driver.$("div.d-flex.justify-content-center.align-items-center.my-4").as("Компонент переключателя режима расписания"),
            toggleCheckboxLocator = driver.$("div.p-default.p-curve.p-smooth.pretty input").as("Переключатель режима расписания"),
            videoPositionLocator = togglePositionCollection.get(0).as("Позиция переключателя режима расписания: видеоконсультации"),
            offersPositionLocator = togglePositionCollection.get(1).as("Позиция переключателя режима расписания: заказы");

    public void checkInitialState() {
        stepWithRole("Убедиться, что  компонент переключателя режима расписания в начальном состоянии", () -> {
            stepWithRole("Убедиться, что компонент переключателя режима расписания виден", () -> {
                toggleBoxLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что переключатель режима расписания виден", () -> {
                toggleCheckboxLocator.shouldBe(exist);
            });
            checkOffersMode();
        });
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент переключателя режима расписания виден", () -> {
            toggleBoxLocator.shouldBe(visible);
        });
        stepWithRole("Убедиться, что переключатель режима расписания виден", () -> {
            toggleCheckboxLocator.shouldBe(visible);
        });
        checkOffersMode();
        toggleVideoMode();
        checkVideoMode();
        toggleOffersMode();
    }

    public void toggleVideoMode() {
        stepWithRole("Переключить режим расписания на видеоконсультации", () -> {
            stepWithRole("Убедиться, что переключатель режима расписания виден", () -> {
                toggleCheckboxLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что переключатель режима расписания в положении Календарь с заказами", () -> {
                offersPositionLocator.shouldHave(Condition.cssClass("text-blue"));
            });
            stepWithRole("Переключить режим расписания на видеоконсультации", () -> {
                toggleCheckboxLocator.click();
            });
            stepWithRole("Убедиться, что переключатель режима расписания в положении Дни проведения видеоконсультаций", () -> {
                videoPositionLocator.shouldHave(Condition.cssClass("text-blue"));
            });
        });
    }

    public void toggleOffersMode() {
        stepWithRole("Переключить режим расписания на заказы", () -> {
            stepWithRole("Убедиться, что переключатель режима расписания виден", () -> {
                toggleCheckboxLocator.shouldBe(visible);
            });
            stepWithRole("Убедиться, что переключатель режима расписания в положении Дни проведения видеоконсультаций", () -> {
                videoPositionLocator.shouldHave(Condition.cssClass("text-blue"));
            });
            stepWithRole("Переключить режим расписания на заказы", () -> {
                toggleCheckboxLocator.click();
            });
            stepWithRole("Убедиться, что переключатель режима расписания в положении Календарь с заказами", () -> {
                offersPositionLocator.shouldHave(Condition.cssClass("text-blue"));
            });
        });
    }

    public void checkVideoMode() {
        stepWithRole("Убедиться, что переключатель режима расписания в положении Дни проведения видеоконсультаций", () -> {
            videoPositionLocator.shouldHave(Condition.cssClass("text-blue"));
        });
    }

    public void checkOffersMode() {
        stepWithRole("Убедиться, что переключатель режима расписания в положении Календарь с заказами", () -> {
            offersPositionLocator.shouldHave(Condition.cssClass("text-blue"));
        });
    }
}
