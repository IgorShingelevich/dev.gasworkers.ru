package ru.gasworkers.dev.tests.web.orderProcess.maintenance;

import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.PositionBrowser;
import ru.gasworkers.dev.model.browser.SizeBrowser;
import ru.gasworkers.dev.pages.context.ClientPages;
import ru.gasworkers.dev.pages.context.SelfEmployedPages;
import ru.gasworkers.dev.tests.BaseTest;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;
import ru.gasworkers.dev.utils.userBuilder.RandomSelfEmployedAndMaster;
@Deprecated

public class ClientSelfEmployedMaintenanceTest extends BaseTest {

    @Browser(role = UserRole.CLIENT, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.FIRST_ROLE)
    ClientPages clientPages;

    @Browser(role = UserRole.SELF_EMPLOYED, browserSize = SizeBrowser.DEFAULT, browserPosition = PositionBrowser.SECOND_ROLE)
    SelfEmployedPages selfEmployedPages;

    RandomClient randomClient = new RandomClient();
    RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();


}
