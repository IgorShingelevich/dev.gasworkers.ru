package pages.components.sharedComponent.profileTabsComponent.navCommonTab;

import model.browser.RoleBrowser;
import pages.components.BaseComponent;
import pages.components.sharedComponent.CommonDatePickerComponent;

public class NavCommonMasterTabProfileComponent extends BaseComponent {
    public final CommonDatePickerComponent datePicker;

    public NavCommonMasterTabProfileComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new CommonDatePickerComponent(browser);
    }

}
// TODO implement CommonDataPickerComponent. Upload photo.
