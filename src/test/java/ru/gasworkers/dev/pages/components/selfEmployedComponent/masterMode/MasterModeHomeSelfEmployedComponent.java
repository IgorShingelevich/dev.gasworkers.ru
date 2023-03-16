package ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ViewSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.timeTableComponent.TimetableComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SelfEmployedSidebarComponent;

public class MasterModeHomeSelfEmployedComponent extends BaseComponent {
    public final ModeSwitcherSelfEmployedComponent mode;
    public final SelfEmployedSidebarComponent sidebar;
    public final ViewSwitcherSelfEmployedComponent viewSwitcher;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final TimetableComponent timetable;


        public MasterModeHomeSelfEmployedComponent(RoleBrowser browser) {
            super(browser);
            mode = new ModeSwitcherSelfEmployedComponent(browser);
            sidebar = new SelfEmployedSidebarComponent(browser);
            viewSwitcher = new ViewSwitcherSelfEmployedComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
            timetable = new TimetableComponent(browser);
        }

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние страницы", () -> {
            fillProfileBanner.checkFinishLoading();
            mode.checkMasterMode();
            timetable.checkInitialState();
        });
    }
}
