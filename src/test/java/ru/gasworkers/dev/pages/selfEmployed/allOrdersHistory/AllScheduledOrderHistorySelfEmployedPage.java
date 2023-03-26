package ru.gasworkers.dev.pages.selfEmployed.allOrdersHistory;

import com.codeborne.selenide.Condition;
import ru.gasworkers.dev.model.browser.RoleBrowser;

public class AllScheduledOrderHistorySelfEmployedPage extends AllOrdersHistorySelfEmployedBasePage {


        public AllScheduledOrderHistorySelfEmployedPage(RoleBrowser browser) {
            super(browser);

        }

    private final String
            titleText = "Список принятых заказов";



    public void checkInitialState() {
        stepWithRole("Убедиться, что страница Список принятых заказов в  начальном состоянии", () -> {
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
