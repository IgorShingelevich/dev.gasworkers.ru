package ru.gasworkers.dev.pages.components.selfEmployedComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class MasterModeHomeSelfEmployedComponent extends BaseComponent {
    public final SidebarSelfEmployedComponent sidebar;

    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;


        public MasterModeHomeSelfEmployedComponent(RoleBrowser browser) {
            super(browser);
            sidebar = new SidebarSelfEmployedComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        }
}
