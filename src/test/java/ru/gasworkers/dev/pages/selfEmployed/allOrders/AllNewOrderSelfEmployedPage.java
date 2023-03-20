package ru.gasworkers.dev.pages.selfEmployed.allOrders;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.FillProfileBannerSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.SelfEmployedSidebarComponent;
import ru.gasworkers.dev.pages.selfEmployed.BaseSelfEmployedPage;

public class AllNewOrderSelfEmployedPage extends BaseSelfEmployedPage {
    public final HeaderSelfEmployedComponent header;
    public final SelfEmployedSidebarComponent sidebar;
    public final FillProfileBannerSelfEmployedComponent fillProfileBanner;



        public AllNewOrderSelfEmployedPage(RoleBrowser browser) {
            super(browser);
            header = new HeaderSelfEmployedComponent(browser);
            sidebar = new SelfEmployedSidebarComponent(browser);
            fillProfileBanner = new FillProfileBannerSelfEmployedComponent(browser);
        }
}
