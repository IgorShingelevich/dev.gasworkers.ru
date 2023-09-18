package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.masterComponent.FillUpCheckListBannerComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.SharedButtonsOrderCardComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.StatusOrderCardPageComponent;

public class CommonTabOrderCardComponent extends BaseOrderCardComponent {

    public final FillUpCheckListBannerComponent fillUpBanner;
    public final StatusOrderCardPageComponent status;
    public final DetailsCommonTabOrderCardComponent details;
    public final SharedButtonsOrderCardComponent buttons;
    public final SuggestedMasterRepairCommonTabOrderCardComponent suggestedMasterRepair;
    //todo add buttons


    public CommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
        fillUpBanner = new FillUpCheckListBannerComponent(browser);
        status = new StatusOrderCardPageComponent(browser);
        details = new DetailsCommonTabOrderCardComponent(browser);
        buttons = new SharedButtonsOrderCardComponent(browser);
        suggestedMasterRepair = new SuggestedMasterRepairCommonTabOrderCardComponent(browser);
    }

}

