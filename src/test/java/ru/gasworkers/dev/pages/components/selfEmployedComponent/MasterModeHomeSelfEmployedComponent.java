package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarSelfEmployedComponent;

public class MasterModeHomeSelfEmployedComponent extends BaseComponent {
    public final ModeSwitcherSelfEmployedComponent mode;
    public final SidebarSelfEmployedComponent sidebar;

    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;


        public MasterModeHomeSelfEmployedComponent(RoleBrowser browser) {
            super(browser);
            mode = new ModeSwitcherSelfEmployedComponent(browser);
            sidebar = new SidebarSelfEmployedComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        }

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние страницы", () -> {
            fillProfileBanner.checkFinishLoading();
        });
    }
}
