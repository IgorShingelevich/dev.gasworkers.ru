package ru.gasworkers.dev.pages.components.sharedComponent.orderCardTabComponent;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.OrderType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab.NavCommonMasterTabProfileComponent;

import static com.codeborne.selenide.Condition.text;

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

