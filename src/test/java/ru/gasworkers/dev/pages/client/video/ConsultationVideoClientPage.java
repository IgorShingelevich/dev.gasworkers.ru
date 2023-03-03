package ru.gasworkers.dev.pages.client.video;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.client.BaseClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.NavRightNowTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.NavScheduleTimeTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.FocusHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

public class ConsultationVideoClientPage extends BaseClientPage {

    public final FocusHeaderComponent focusHeader;
    public final StepperComponent stepper;
    public final NavRightNowTabConsultationVideoClientComponent rightNowTab;
    public final NavScheduleTimeTabConsultationVideoClientComponent scheduleTimeTab;
    public ConsultationVideoClientPage(RoleBrowser browser) {
        super(browser);
        focusHeader = new FocusHeaderComponent(browser);
        stepper = new StepperComponent(browser);
        rightNowTab = new NavRightNowTabConsultationVideoClientComponent(browser);
        scheduleTimeTab = new NavScheduleTimeTabConsultationVideoClientComponent(browser);

    }


    public void checkFinishLoading() {
        stepWithRole("Убедиться, что страница Консультация по видео загрузилась", () -> {
            focusHeader.checkFinishLoading();
            stepper.checkFinishLoading();
            rightNowTab.checkFinishLoading();
            scheduleTimeTab.checkFinishLoading();
        });
    }
}
