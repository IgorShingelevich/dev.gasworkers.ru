package ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;

public class RecruitSidebarComponent extends BaseSidebarComponent {
    public final SupportServiceSidebarComponent support;

    public RecruitSidebarComponent(RoleBrowser browser) {
        super(browser);
        support = new SupportServiceSidebarComponent(browser);
    }
}
