package ru.gasworkers.dev.pages.selfEmployed;

import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.guideComponent.FirstSelfEmployedGuideComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.HeaderSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MapModeHomeSelfEmployedComponent;
import ru.gasworkers.dev.pages.components.selfEmployedComponent.MasterModeHomeSelfEmployedComponent;

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
        stepWithRole("Проверить начальное состояние страницы", () -> {
            mapMode.checkInitialState();
            masterMode.checkInitialState();
        });
    }
}
