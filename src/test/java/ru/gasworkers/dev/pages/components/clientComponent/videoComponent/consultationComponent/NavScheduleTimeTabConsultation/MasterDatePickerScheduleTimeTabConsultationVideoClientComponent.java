package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.NavScheduleTimeTabConsultation;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class MasterDatePickerScheduleTimeTabConsultationVideoClientComponent extends BaseComponent {
    public MasterDatePickerScheduleTimeTabConsultationVideoClientComponent (RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что календарь вкладки Консультация на выбранное время загрузился", () -> {

        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что календарь вкладки Консультация на выбранное время находится в состоянии по умолчанию", () -> {

        });
    }
}
