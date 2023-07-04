package ru.gasworkers.dev.pages.components.clientComponent.conferenceComponent.consultationComponent.NavScheduleTimeTabConsultation;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class MasterListNavScheduleTimeTabConsultationVideoClientComponent extends BaseComponent {
    public MasterListNavScheduleTimeTabConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что список мастеров вкладки Консультация на выбранное время загрузился", () -> {

        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что список мастеров вкладки Консультация на выбранное время находится в состоянии по умолчанию", () -> {

        });
    }
}
