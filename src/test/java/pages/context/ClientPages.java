package pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import pages.client.HomeClientPage;
import pages.client.InfoTypeOrderClientPage;
import pages.client.maintenance.SelectDateMaintenanceClientPage;
import pages.client.maintenance.SelectObjectMaintenanceClientPage;
import pages.client.TypeOrdersClientPage;

import static model.Role.*;

@Getter
public final class ClientPages extends BaseRolePages {

    private final HomeClientPage homePage = new HomeClientPage(browser);
    private final TypeOrdersClientPage typeOrdersPage = new TypeOrdersClientPage(browser);
    private final InfoTypeOrderClientPage infoTypeOrderPage = new InfoTypeOrderClientPage(browser);
    private final SelectObjectMaintenanceClientPage selectObjectMaintenancePage = new SelectObjectMaintenanceClientPage(browser);
    private final SelectDateMaintenanceClientPage selectDateMaintenancePage = new SelectDateMaintenanceClientPage(browser);

    public ClientPages(SelenideDriver driver) {
        super(CLIENT, driver);
    }

}
