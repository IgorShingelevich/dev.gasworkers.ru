package ru.gasworkers.dev.pages.selfEmployed.orderPage;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SidebarSelfEmployedComponent;
import ru.gasworkers.dev.pages.selfEmployed.BaseSelfEmployedPage;

public class NewOrderSelfEmployedPage extends BaseSelfEmployedPage {
    public final HeaderSelfEmployedComponent header;
    public final SidebarSelfEmployedComponent sidebar;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;



        public NewOrderSelfEmployedPage(RoleBrowser browser) {
            super(browser);
            header = new HeaderSelfEmployedComponent(browser);
            sidebar = new SidebarSelfEmployedComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        }
}
