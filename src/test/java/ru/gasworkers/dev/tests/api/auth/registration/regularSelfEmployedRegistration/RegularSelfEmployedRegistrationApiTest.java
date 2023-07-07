//package ru.gasworkers.dev.tests.api.auth.registration.regularSelfEmployedRegistration;
//
//public class RegularSelfEmployedRegistrationApiTest {
//    @Browser(role = SELF_EMPLOYED)
//    SelfEmployedPages selfEmployedPages;
//
//    @BeforeEach
//    public void setUpRestAssured() {
//        ApiTestConfig.configureRestAssured();
//    }
//
//    public final OLDRegularStartRegistrationApi OLDRegularStartRegistrationApi = new OLDRegularStartRegistrationApi();
//    public final CheckRegistrationCodeApi checkRegularRegistrationCodeApi = new CheckRegistrationCodeApi();
//    public final FinishRegistrationApi regularFinishRegistrationApi = new FinishRegistrationApi();
//    public final SelfEmployedRegistrationApi configureSelfEmployedRegistrationApi = new SelfEmployedRegistrationApi();
//    private final RandomSelfEmployedAndMaster randomSelfEmployedAndMaster = new RandomSelfEmployedAndMaster();
//    public final ServiceCompanyStaff staff = new ServiceCompanyStaff();
//
//    private final String
//            email = randomSelfEmployedAndMaster.getEmail(),
//            phone = randomSelfEmployedAndMaster.getPhone(),
//            firstName = randomSelfEmployedAndMaster.getFirstName(),
//            lastName = randomSelfEmployedAndMaster.getLastName(),
//            patronymicName = randomSelfEmployedAndMaster.getPatronymicName(),
//            password = randomSelfEmployedAndMaster.getPassword(),
//            code = randomSelfEmployedAndMaster.getCode(),
//            gender = Gender.MALE.toString();
//    private final Integer
//            serviceId = staff.EGIDA.ID;
//
//    @Test
//    @Owner("Igor Shingelevich")
//    @Epic(AllureEpic.REGISTRATION)
//    @Feature(AllureFeature.REGULAR_REGISTRATION)
//    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_INDIVIDUAL), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
//    @DisplayName(" Регистрация самозанятого Api ( без конфигурации) - получается Рекрут? Чем отличается от регистрации мастера в ск? " )
//    public void noConfigSelfEmployedRegistration() {
//        OLDRegularStartRegistrationApi.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
//        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
//        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.SELF_EMPLOYED.toString(), null);
//
//        selfEmployedPages.getLoginPage().open();
//        selfEmployedPages.getLoginPage().loginEmail(email, password);
//        selfEmployedPages.getHomePage().urlChecker.urlContains("profile/master");
//    }
//    @Test
//    @Owner("Igor Shingelevich")
//    @Epic(AllureEpic.REGISTRATION)
//    @Feature(AllureFeature.REGULAR_REGISTRATION)
//    @Tags({@Tag(AllureTag.REGRESSION), @Tag(AllureTag.SE_INDIVIDUAL), @Tag(AllureTag.REGISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
//    @DisplayName(" Регистрация самозанятого Api ( c конфигурацией ")
//    public void configSelfEmployedRegistration() {
//        OLDRegularStartRegistrationApi.regularStartRegistration(UserType.MASTER.toString(), email, phone, false);
//        checkRegularRegistrationCodeApi.checkRegularRegistrationCode(code, UserType.MASTER.toString(), email, phone);
//        regularFinishRegistrationApi.regularFinishRegistration(UserType.MASTER.toString(), email, phone, firstName, lastName, patronymicName, password, gender, true, true, Employed_status.SELF_EMPLOYED.toString(), null);
//        configureSelfEmployedRegistrationApi.configureSelfEmployedRegistration(false, true);
//
//
//        selfEmployedPages.getLoginPage().open();
//        selfEmployedPages.getLoginPage().loginEmail(email, password);
//        selfEmployedPages.getHomePage().urlChecker.urlContains("profile/master");
//    }
//
//
//}
