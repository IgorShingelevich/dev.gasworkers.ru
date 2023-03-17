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
        stepWithRole("Убедиться, что компонент расписание в начальном состоянии", () -> {
            modeToggler.checkInitialState();
            timetableLocator.shouldBe(visible, Duration.ofSeconds(10));
            // todo check that timetable is empty
        });
    }
    public void checkFinishLoading() {
        stepWithRole("Убедиться, что компонент расписание загрузился", () -> {
            modeToggler.checkInitialState();
            timetableLocator.shouldBe(visible, Duration.ofSeconds(10));
            //todo add check timetable elements
        });
    }


}
