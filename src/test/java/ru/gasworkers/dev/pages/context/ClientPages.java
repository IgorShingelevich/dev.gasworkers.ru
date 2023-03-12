package ru.gasworkers.dev.pages.context;

import com.codeborne.selenide.SelenideDriver;
import lombok.Getter;
import ru.gasworkers.dev.pages.client.*;
import ru.gasworkers.dev.pages.client.maintenance.SelectDateMaintenanceClientPage;
import ru.gasworkers.dev.pages.client.maintenance.SelectInsuranceClientPage;
import ru.gasworkers.dev.pages.client.maintenance.SelectObjectMaintenanceClientPage;
import ru.gasworkers.dev.pages.client.RegistrationClientPage;
import ru.gasworkers.dev.pages.client.maintenance.SelectPaymentMaintenanceClientPage;
import ru.gasworkers.dev.pages.client.video.ApproveDetailsVideoClientPage;
import ru.gasworkers.dev.pages.client.video.ConsultationVideoClientPage;
import ru.gasworkers.dev.pages.client.video.SelectPaymentVideoClientPage;
import ru.gasworkers.dev.pages.client.video.SuccessPaymentVideoClientPage;
import ru.gasworkers.dev.pages.sharedPages.AllNotificationsPage;
import ru.gasworkers.dev.pages.sharedPages.LandingPage;

import static ru.gasworkers.dev.model.Role.*;

@Getter
public final class ClientPages extends BaseRolePages {

    public ClientPages(SelenideDriver driver) {
        super(CLIENT, driver);
    }

    private final LandingPage landingPage = new LandingPage(browser);
    private final RegistrationClientPage registrationPage = new RegistrationClientPage(browser);
    private final HomeClientPage homePage = new HomeClientPage(browser);
    private final ProfileClientPage profilePage = new ProfileClientPage(browser);
    private final TypeOrdersClientPage typeOrdersPage = new TypeOrdersClientPage(browser);
    private final InfoTypeOrderClientPage infoTypeOrderPage = new InfoTypeOrderClientPage(browser);
    private final SelectObjectMaintenanceClientPage selectObjectMaintenancePage = new SelectObjectMaintenanceClientPage(browser);
    private final SelectDateMaintenanceClientPage selectDateMaintenancePage = new SelectDateMaintenanceClientPage(browser);
    private final SelectServicePageClientPage selectServicePage = new SelectServicePageClientPage(browser);
    private  final OrderCardClientPage orderCardPage = new OrderCardClientPage(browser);
    private final ObjectCardClientPage objectCardPage = new ObjectCardClientPage(browser);
    private final CancelOrderPage cancelOrderPage = new CancelOrderPage(browser);
    private final ConsultationVideoClientPage consultationVideoPage = new ConsultationVideoClientPage(browser);
    private final ApproveDetailsVideoClientPage approveMasterVideoPage = new ApproveDetailsVideoClientPage(browser);
    private final SelectInsuranceClientPage selectInsurancePage = new SelectInsuranceClientPage(browser);
    private final CheckDocumentsClientPage checkDocumentsPage = new CheckDocumentsClientPage(browser);
    private final SelectPaymentMaintenanceClientPage selectPaymentMaintenancePage = new SelectPaymentMaintenanceClientPage(browser);
    private final SelectPaymentVideoClientPage selectPaymentVideoPage = new SelectPaymentVideoClientPage(browser);
    private final  SuccessPaymentVideoClientPage successPaymentVideoPage = new SuccessPaymentVideoClientPage(browser);
    private final PaymentWizardClientPage paymentWizardPage = new PaymentWizardClientPage(browser);
    private final SignSMSClientPage signSMSPage = new SignSMSClientPage(browser);
    private final SignSuccessPage signSuccessPage = new SignSuccessPage(browser);

    private final AllInvoicesClientPage allInvoicesPage = new AllInvoicesClientPage(browser);
    private final AllObjectsClientPage allObjectsPage = new AllObjectsClientPage(browser);
    private final AllOrdersClientPage allOrdersPage = new AllOrdersClientPage(browser);
    private final AllNotificationsPage allNotificationsPage = new AllNotificationsPage(browser);
}
