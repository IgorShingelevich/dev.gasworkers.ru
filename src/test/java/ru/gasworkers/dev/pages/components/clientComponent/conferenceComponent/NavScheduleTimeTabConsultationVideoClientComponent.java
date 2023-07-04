package ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent.consultationComponent.FilterConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent.consultationComponent.NavScheduleTimeTabConsultation.MasterDatePickerScheduleTimeTabConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent.consultationComponent.NavScheduleTimeTabConsultation.MasterListNavScheduleTimeTabConsultationVideoClientComponent;

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
        stepWithRole("Убедиться, что вкладка Консультация на выбранное время загрузилась TODO", () -> {
        filter.checkFinishLoading();
        masterList.checkFinishLoading();
        masterDatePicker.checkFinishLoading();
        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что вкладка Консультация на выбранное время находится в состоянии по умолчанию TODO", () -> {
        filter.checkDefaultState();
        masterList.checkDefaultState();
        masterDatePicker.checkDefaultState();
        });
    }
}
