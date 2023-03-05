package ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.landingComponent.bgRegistrationComponent.DateBGRegistrationLandingComponent;

public class NavCommonDispatcherTabProfileComponent extends BaseComponent {
    public final DateBGRegistrationLandingComponent datePicker;

    public NavCommonDispatcherTabProfileComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new DateBGRegistrationLandingComponent(browser);
    }

}
// TODO implement CommonDataPickerComponent. Upload photo.
