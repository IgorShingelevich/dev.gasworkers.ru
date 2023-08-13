package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.mapMode.ModeDispatcherHomeSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.masterMode.ModeMasterHomeSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.guideComponent.PlayGuideComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.headerComponent.HeaderSelfEmployedComponent;

public class HomeSelfEmployedPage extends BaseSelfEmployedPage{
    public final ModeDispatcherHomeSelfEmployedComponent modeDispatcher;
    public final ModeMasterHomeSelfEmployedComponent modeMaster;
    public final HeaderSelfEmployedComponent header;
    public final PlayGuideComponent firstGuide;



    public HomeSelfEmployedPage(RoleBrowser browser) {
        super(browser);
        header = new HeaderSelfEmployedComponent(browser);
        modeDispatcher = new ModeDispatcherHomeSelfEmployedComponent(browser);
        modeMaster = new ModeMasterHomeSelfEmployedComponent(browser);
        firstGuide = new PlayGuideComponent(browser);
    }


    public void checkInitialState() {
        stepWithRole("Убедиться, что все домашняя страница в начальном состоянии ", () -> {
            mode.checkDispatcherMode();
            header.checkFinishLoading();
            header.burger.checkFinishLoading();
            //todo add messagesButtonLocator
            modeDispatcher.checkInitialState();
            mode.switchMaster();
            modeMaster.checkInitialState();
            mode.switchDispatcher();
            mode.checkDispatcherMode();
        });
    }
}
