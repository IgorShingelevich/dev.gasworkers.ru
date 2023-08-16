package ru.gasworkers.dev.pages.components.sharedComponent.profileComponent.navCommon;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;

public class NavCommonTabMasterProfilePageComponent extends BaseNavCommonTabProfilePageComponent {
    public final DateBGRegistrationLandingComponent datePicker;

    public NavCommonTabMasterProfilePageComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new DateBGRegistrationLandingComponent(browser);
    }

}
// TODO implement CommonDataPickerComponent. Upload photo.
