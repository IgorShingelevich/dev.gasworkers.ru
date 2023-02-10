package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonMasterTabProfileComponent;

public class NavCommonTabOrderCardComponent extends NavCommonMasterTabProfileComponent {

    public final FillUpCheckListBannerComponent fillUpBanner;
    public final OrderStatusCardComponent orderState;


    public NavCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        orderState = new OrderStatusCardComponent(browser);
    }
}

