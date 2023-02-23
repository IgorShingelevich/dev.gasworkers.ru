package ru.gasworkers.dev.pages.components.sharedComponent.tabOrderCardComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;

public class NavCommonTabOrderCardComponent extends BaseTabOrderCardComponent {

    public final FillUpCheckListBannerComponent fillUpBanner;
    public final OrderStatusCardComponent orderStatus;
    public final OrderDetailsCommonTabCardComponent orderDetails;


    public NavCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        orderStatus = new OrderStatusCardComponent(browser);
        orderDetails = new OrderDetailsCommonTabCardComponent(browser);
    }




}

