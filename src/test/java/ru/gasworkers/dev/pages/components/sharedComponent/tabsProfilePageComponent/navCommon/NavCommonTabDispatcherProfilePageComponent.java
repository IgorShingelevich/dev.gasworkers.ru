package ru.gasworkers.dev.pages.components.sharedComponent.tabsProfilePageComponent.navCommon;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;

public class NavCommonTabDispatcherProfilePageComponent extends BaseNavCommonTabProfilePageComponent {
    public final DateBGRegistrationLandingComponent datePicker;

    public NavCommonTabDispatcherProfilePageComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new DateBGRegistrationLandingComponent(browser);
    }

}
// TODO implement CommonDataPickerComponent. Upload photo.
