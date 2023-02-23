package ru.gasworkers.dev.pages.components.clientComponent.tabObjectCardClientComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class DistributorTabObjectCardClientComponent extends BaseComponent {
    public final NotEditableStateBannerObjectCardClientComponent noEditBanner;

    public  DistributorTabObjectCardClientComponent (RoleBrowser browser) {
        super(browser);
        noEditBanner = new NotEditableStateBannerObjectCardClientComponent(browser);

    }

}
