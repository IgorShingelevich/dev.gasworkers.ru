package pages.components.sharedComponent.profileTabsComponent.commonTab;

import model.browser.RoleBrowser;
import pages.components.BaseComponent;
import pages.components.sharedComponent.CommonDatePickerComponent;

public class CommonMasterProfileComponent extends BaseComponent {
    public final CommonDatePickerComponent datePicker;

    public CommonMasterProfileComponent(RoleBrowser browser) {
        super(browser);
        datePicker = new CommonDatePickerComponent(browser);
    }

}
// TODO implement CommonDataPickerComponent. Upload photo.
