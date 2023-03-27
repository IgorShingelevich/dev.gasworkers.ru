package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.guideComponent.FirstSelfEmployedGuideComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.ModeSwitcherSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.mapMode.MapModeHomeSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.MasterModeHomeSelfEmployedComponent;

public class HomeSelfEmployedPage extends BaseSelfEmployedPage{
    public final MapModeHomeSelfEmployedComponent mapMode;
    public final MasterModeHomeSelfEmployedComponent masterMode;
    public final HeaderSelfEmployedComponent header;
    public final FirstSelfEmployedGuideComponent firstGuide;



    public HomeSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        mapMode = new MapModeHomeSelfEmployedComponent(browser);
        masterMode = new MasterModeHomeSelfEmployedComponent(browser);
        firstGuide = new FirstSelfEmployedGuideComponent(browser);
    }


    public void checkInitialState() {
        stepWithRole("Убедиться, что все домашняя страница в начальном состоянии ", () -> {
            mode.checkDispatcherMode();
            header.checkFinishLoading();
            header.burger.checkFinishLoading();
            //todo add messagesButtonLocator
            mapMode.checkInitialState();
            mode.switchMaster();
            masterMode.checkInitialState();
            mode.switchDispatcher();
            mode.checkDispatcherMode();
        });
    }
}
