package ru.gasworkers.dev.pages.components.clientComponent.videoComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.FilterConsultationVideoClientComponent;
import ru.gasworkers.dev.pages.components.clientComponent.videoComponent.consultationComponent.NavRightNowTabConsultation.MasterListRightNowTabConsultationVideoClientComponent;

public class NavRightNowTabConsultationVideoClientComponent extends BaseComponent {
    public final FilterConsultationVideoClientComponent filter;
    public final MasterListRightNowTabConsultationVideoClientComponent masterList;

            public NavRightNowTabConsultationVideoClientComponent(RoleBrowser browser) {
            super(browser);
            filter = new FilterConsultationVideoClientComponent(browser);
            masterList = new MasterListRightNowTabConsultationVideoClientComponent(browser);
        }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что вкладка Консультация прямо сейчас загрузилась", () -> {
            filter.checkFinishLoading();
            masterList.checkFinishLoading();

        });
    }

    public void checkDefaultState() {
        stepWithRole("Убедиться, что вкладка Консультация прямо сейчас находится в состоянии по умолчанию", () -> {
            filter.checkDefaultState();
            masterList.checkDefaultState();
        });
    }
}
