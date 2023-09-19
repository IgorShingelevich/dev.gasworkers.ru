package ru.gasworkers.dev.pages.dispatcher;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.SharedHeaderComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.sidebarComponent.DispatcherSidebarComponent;

public class HomeDispatcherPage extends BaseDispatcherPage {

    public final SharedHeaderComponent header;
    public final DispatcherOrdersFilterComponent filter;
    public final ViewModeDispatcherComponent viewMode;
    public final MapDispatcherComponent map;
    private final DispatcherSidebarComponent sidebarDispatcher;
    public final OrdersDispatcherComponent orders;

    public HomeDispatcherPage(RoleBrowser browser) {
        super(browser);
        sidebarDispatcher = new DispatcherSidebarComponent(browser);
        header = new SharedHeaderComponent(browser);
        filter = new DispatcherOrdersFilterComponent(browser);
        viewMode = new ViewModeDispatcherComponent(browser);
        map = new MapDispatcherComponent(browser);
        orders = new OrdersDispatcherComponent(browser);

    }

    public void checkFinishDefaultSateLoading() {
        stepWithRole("Убедиться, что Домашняя страница загружена", () -> {
            checkUrl();
            header.checkFinishLoading();
            map.checkFinishLoading();
            filter.checkButtonLoading();
            viewMode.checkDefaultState();
            orders.checkFinishLoading();
        });
    }



    public void checkUrl() {
        urlChecker.urlContains("profile/dispatcher");
    }
}
