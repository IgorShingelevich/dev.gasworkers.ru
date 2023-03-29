package ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ViewSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.MasterModeSelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.timeTableComponent.TimetableComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.modesSidebarSelfEmployedComponent.DispatcherModeSelfEmployedSidebarComponent;

public class ModeMasterHomeSelfEmployedComponent extends BaseComponent {
    public final ModeSwitcherSelfEmployedComponent mode;
    public final DispatcherModeSelfEmployedSidebarComponent sidebarDispatcher;
    public final MasterModeSelfEmployedSidebarComponent sidebarMaster;
    public final ViewSwitcherSelfEmployedComponent viewSwitcher;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final TimetableComponent timetable;


    public ModeMasterHomeSelfEmployedComponent(RoleBrowser browser) {
        super(browser);
        mode = new ModeSwitcherSelfEmployedComponent(browser);
        sidebarDispatcher = new DispatcherModeSelfEmployedSidebarComponent(browser);
        sidebarMaster = new MasterModeSelfEmployedSidebarComponent(browser);
        viewSwitcher = new ViewSwitcherSelfEmployedComponent(browser);
        fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        timetable = new TimetableComponent(browser);
    }

    public void checkInitialState() {
        stepWithRole("Убедиться, что режим мастера  СМЗ в начальном состоянии", () -> {
            stepWithRole("Убедиться что все элементы на странице загрузились", () -> {
                mode.checkMasterMode();
                fillProfileBanner.checkFinishLoading();
                timetable.checkInitialState();
                sidebarMaster.checkFinishLoading();
            });

        });
    }
}
