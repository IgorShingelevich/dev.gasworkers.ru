package ru.gasworkers.dev.pages.components.clientComponent.videoComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.FilterConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.NavScheduleTimeTabConsultation.MasterDatePickerScheduleTimeTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.NavScheduleTimeTabConsultation.MasterListNavScheduleTimeTabConsultationVideoClientComponent;

public class NavScheduleTimeTabConsultationVideoClientComponent extends BaseComponent {
    public final FilterConsultationVideoClientComponent filter;
    public final MasterListNavScheduleTimeTabConsultationVideoClientComponent masterList;
    public final MasterDatePickerScheduleTimeTabConsultationVideoClientComponent masterDatePicker;

    public NavScheduleTimeTabConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
        filter = new FilterConsultationVideoClientComponent(browser);
        masterList = new MasterListNavScheduleTimeTabConsultationVideoClientComponent(browser);
        masterDatePicker = new MasterDatePickerScheduleTimeTabConsultationVideoClientComponent(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Консультация на выбранное время загрузилась", () -> {
        filter.checkFinishLoading();
        masterList.checkFinishLoading();
        masterDatePicker.checkFinishLoading();
        });
    }
}
