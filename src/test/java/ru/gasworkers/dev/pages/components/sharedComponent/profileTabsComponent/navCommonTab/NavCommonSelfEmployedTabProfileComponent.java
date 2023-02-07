package ru.gasworkers.dev.pages.components.sharedComponent.profileTabsComponent.navCommonTab;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.CommonDatePickerComponent;

public class NavCommonSelfEmployedTabProfileComponent extends BaseComponent {
    public final CommonDatePickerComponent datePicker;


    public NavCommonSelfEmployedTabProfileComponent(RoleBrowser browser){
        super(browser);
        datePicker = new CommonDatePickerComponent(browser);
    }
}
// TODO implement CommonDataPickerComponent. Upload photo.