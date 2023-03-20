package ru.gasworkers.dev.pages.components.sharedComponent.tabsOrderCardPageComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavCommonTabOrderCardPageComponent extends BaseTabOrderCardComponent {

    public final FillUpCheckListBannerComponent fillUpBanner;
    public final OrderStatusOrderCardPageComponent orderStatus;
    public final OrderDetailsCommonTabComponent orderDetails;


    public NavCommonTabOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        orderStatus = new OrderStatusOrderCardPageComponent(browser);
        orderDetails = new OrderDetailsCommonTabComponent(browser);
    }




}

