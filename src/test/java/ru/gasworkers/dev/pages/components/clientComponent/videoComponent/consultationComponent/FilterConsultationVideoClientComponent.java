package ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;

public class FilterConsultationVideoClientComponent extends BaseComponent {
    public FilterConsultationVideoClientComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что фильтр вкладки Консультация прямо сейчас загрузился", () -> {

        });
    }
}
