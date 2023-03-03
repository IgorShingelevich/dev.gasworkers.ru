package ru.gasworkers.dev.pages.client.video;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

public class ApproveMasterVideoClientPage extends BaseClientPage {
    public final FocusHeaderComponent focusHeader;
    public final StepperComponent stepper;
    public ApproveMasterVideoClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
    }
}
