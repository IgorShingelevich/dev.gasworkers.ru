package ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.buttonSharedComponent;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.AllRolesSharedComponent;

public  class AllButtonSharedComponent extends BaseComponent {
    public final MainButtonSharedComponent main;
    public final SecondaryButtonSharedComponent secondary;
    public AllButtonSharedComponent(RoleBrowser browser) {
        super(browser);
        main = new MainButtonSharedComponent(browser);
        secondary = new SecondaryButtonSharedComponent(browser);
    }
}
