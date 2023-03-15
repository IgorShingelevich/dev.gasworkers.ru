package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.selfEmployed.BaseSelfEmployedPage;

public class MapModeHomeSelfEmployedComponent extends BaseComponent {
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;
    public final ModeSwitcherSelfEmployedComponent mode;


        public MapModeHomeSelfEmployedComponent(RoleBrowser browser) {
            super(browser);
            mode = new ModeSwitcherSelfEmployedComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        }

    public void checkInitialState() {
        stepWithRole("Проверить начальное состояние страницы", () -> {
            fillProfileBanner.checkFinishLoading();
        });
    }
}
