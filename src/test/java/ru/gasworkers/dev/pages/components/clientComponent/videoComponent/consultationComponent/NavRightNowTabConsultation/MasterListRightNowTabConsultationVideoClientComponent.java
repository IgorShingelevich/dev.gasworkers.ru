package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.NavRightNowTabConsultation;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class MasterListRightNowTabConsultationVideoClientComponent extends BaseComponent {
    public MasterListRightNowTabConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что список мастеров вкладки Консультация прямо сейчас загрузился", () -> {

        });
    }
}
