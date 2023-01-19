package pages.context;

import com.codeborne.selenide.SelenideDriver;
import com.google.gson.internal.bind.util.ISO8601Utils;
import lombok.Getter;
import pages.client.*;
import pages.client.maintenance.SelectDateMaintenanceClientPage;
import pages.client.maintenance.SelectInsuranceClientPage;
import pages.client.maintenance.SelectObjectMaintenanceClientPage;

import static model.Role.*;

@Getter
public final class ClientPages extends BaseRolePages {

    public ClientPages(SelenideDriver driver) {
        super(CLIENT, driver);
    }

    private final HomeClientPage homePage = new HomeClientPage(browser);
    private final TypeOrdersClientPage typeOrdersPage = new TypeOrdersClientPage(browser);
    private final InfoTypeOrderClientPage infoTypeOrderPage = new InfoTypeOrderClientPage(browser);
    private final SelectObjectMaintenanceClientPage selectObjectMaintenancePage = new SelectObjectMaintenanceClientPage(browser);
    private final SelectDateMaintenanceClientPage selectDateMaintenancePage = new SelectDateMaintenanceClientPage(browser);
    private final SelectServicePageClientPage selectServicePage = new SelectServicePageClientPage(browser);
    private  final OrderCardClientPage orderCardPage = new OrderCardClientPage(browser);
    private final CancelOrderPage cancelOrderPage = new CancelOrderPage(browser);
    private final SelectInsuranceClientPage selectInsurancePage = new SelectInsuranceClientPage(browser);
    private final CheckDocumentsClientPage checkDocumentsPage = new CheckDocumentsClientPage(browser);
    private final SelectPaymentClientPage selectPaymentPage = new SelectPaymentClientPage(browser);
    private final PaymentWizardClientPage paymentWizardPage = new PaymentWizardClientPage(browser);
    private final SignSMSClientPage signSMSPage = new SignSMSClientPage(browser);
    private final SignSuccsessPage signSuccsessPage = new SignSuccsessPage(browser);

    private final AllInvoicesClientPage allInvoicesPage = new AllInvoicesClientPage(browser);
    private final AllObjectsClientPage allObjectsPage = new AllObjectsClientPage(browser);
    private final AllOrdersClientPage allOrdersPage = new AllOrdersClientPage(browser);
}
