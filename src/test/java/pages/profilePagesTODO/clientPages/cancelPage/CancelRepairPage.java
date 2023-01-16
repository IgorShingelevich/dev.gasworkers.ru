package pages.profilePagesTODO.clientPages.cancelPage;

import model.browser.RoleBrowser;
import pages.client.BaseClientPage;
import pages.components.sharedComponents.headerComponents.FocusHeaderComponent;

public class CancelRepairPage  extends BaseClientPage {

    private final FocusHeaderComponent header;

    public CancelRepairPage(RoleBrowser browser) {
        super(browser);
        header = new FocusHeaderComponent(browser);
    }


}
