package ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory;

import com.codeborne.selenide.Condition;
import ru.gasworkers.dev.model.browser.RoleBrowser;

public class AllNewOrderHistorySelfEmployedPage extends AllOrdersHistorySelfEmployedBasePage {
    public AllNewOrderHistorySelfEmployedPage(RoleBrowser browser) {
        super(browser);
    }

    private final String
            titleText = "Список новых заказов";

    public void checkInitialState() {
        stepWithRole("Убедиться, что страница Список новых заказов в  начальном состоянии", () -> {
            titleLocator.shouldHave(Condition.text(titleText));
            fillProfileBanner.checkFinishLoading();
            mosOblGasBanner.checkFinishLoading();
            modeSwitcher.checkFinishLoading();
            viewSwitcher.checkFinishLoading();
            viewSwitcher.checkTableActive();
            checkEmptyState();

        });
    }
}