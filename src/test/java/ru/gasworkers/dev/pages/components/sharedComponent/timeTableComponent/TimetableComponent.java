package ru.gasworkers.dev.pages.components.sharedComponent.timeTableComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;

public class TimetableComponent extends BaseComponent {
    public final ModeToggleTimeTableComponent modeToggler;
    public TimetableComponent(RoleBrowser browser) {
        super(browser);
        modeToggler = new ModeToggleTimeTableComponent(browser);
    }

    SelenideElement
            timetableLocator = driver.$("div.timetable").as("Расписание");

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние расписания", () -> {
            modeToggler.checkInitialState();
            timetableLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
    }
}
