package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.tabs.common;

import com.codeborne.selenide.ElementsCollection;
import ru.gasworkers.dev.model.ServiceType;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.BaseOrderCardComponent;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateBuilder;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static io.qameta.allure.Allure.step;

public class DetailsCommonTabOrderCardComponent extends BaseOrderCardComponent {

    ElementsCollection
            selfCollection = driver.$$("div.order-details-item").as("Элементы информация о заказе");

    public DetailsCommonTabOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkServiceType(ServiceType serviceType) {
        stepWithRole("Убедиться, что тип заказа: " + serviceType.toString(), () -> {
            selfCollection.findBy(text("Тип заказа")).shouldHave(text(serviceType.toString()));
        });
    }

    public void checkCompanyFullName(String expectedServiceCompany) {
        stepWithRole("Убедиться, что сервисная компания: " + expectedServiceCompany + " соответствует ожидаемой", () -> {
            selfCollection.findBy(text("Сервисная компания:")).shouldHave(text(expectedServiceCompany));
        });
    }

    public void checkNoCompany() {
        stepWithRole("Убедиться, что сервисная компания отсутствует", () -> {
            selfCollection.findBy(text("Сервисная компания:")).shouldNotBe(visible);
        });
    }

    public void checkClientFullName(String expectedPersonalData) {
        stepWithRole("Убедиться, что личные данные " + expectedPersonalData + " соответствуют ожидаемым", () -> {
            selfCollection.findBy(text("Личные данные:")).shouldHave(text(expectedPersonalData));
        });
    }

    public void checkAddress(String expectedAddress) {
        stepWithRole("Убедиться, что адрес " + expectedAddress + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Адрес объекта:")).shouldHave(text(expectedAddress));
        });
    }

    public void checkClientPhone(String expectedPhoneNumber) {
        stepWithRole("Убедиться, что номер телефона " + expectedPhoneNumber + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Номер телефона:")).shouldHave(text(expectedPhoneNumber));
        });
    }

    public void checkEquipment(String expectedEquipment) {
        stepWithRole("Убедиться, что оборудование " + expectedEquipment + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Оборудование:")).shouldHave(text(expectedEquipment));
        });
    }

    public void checkDesiredDate(String expectedDate) {
        stepWithRole("Убедиться, что  желаемая дата " + expectedDate + " соответствует ожидаемой", () -> {
            selfCollection.findBy(text("Желаемая дата:")).shouldHave(text(expectedDate));
        });
    }

    public void noDesiredDate() {
        stepWithRole("Убедиться, что  желаемая дата отсутствует", () -> {
            selfCollection.findBy(text("Желаемая дата:")).shouldNotBe(visible);
        });
    }

    public void checkDesiredTime(String expectedTime) {
        stepWithRole("Убедиться, что желаемое время " + expectedTime + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Желаемое время:")).shouldHave(text(expectedTime));
        });
    }

    public void noDesiredTime() {
        stepWithRole("Убедиться, что желаемое время отсутствует", () -> {
            selfCollection.findBy(text("Желаемое время:")).shouldNotBe(visible);
        });
    }

    public void checkAssignedDateAndTime(String expectedDateAndTime) {
        stepWithRole("Убедиться, что дата и время приезда мастера " + expectedDateAndTime + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Дата и время приезда мастера")).shouldHave(text(expectedDateAndTime));
        });
    }

    public void noAssignedDateAndTime() {
        stepWithRole("Убедиться, что дата и время приезда мастера отсутствует", () -> {
            selfCollection.findBy(text("Дата и время приезда мастера")).shouldNotBe(visible);
        });
    }

    public void checkDescription(String expectedDescription) {
        stepWithRole("Убедиться, что описание " + expectedDescription + " соответствует ожидаемому", () -> {
            selfCollection.findBy(text("Описание проблемы:")).shouldHave(text(expectedDescription));
        });
    }


    public void detailsRepair(StateRepair stateRepair, StateBuilder.OrderIdData data) {
        step("Убедиться, что  детали заказа соответствуют ожидаемым в состоянии " + stateRepair, () -> {
            checkCommonDetails(data, ServiceType.REPAIR);
            switch (stateRepair) {

                case PUBLISHED:
                case CANCEL_CLIENT_PUBLISHED:
                    checkDesiredDate(data.getDesiredDate());
                    checkDesiredTime(data.getDesiredTime());
                    checkNoCompany();
                    noAssignedDateAndTime();
                    break;
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                    checkNoCompany();
                    noAssignedDateAndTime();
                    checkClientFullName(data.getClientFullName());
                    checkDesiredDate(data.getDesiredDate());
                    checkDesiredTime(data.getDesiredTime());
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                    checkNoCompany();
                    noAssignedDateAndTime();
                    checkClientFullName(data.getClientFullName());
                    checkClientPhone(data.getPhone());
                    checkDesiredDate(data.getDesiredDate());
                    checkDesiredTime(data.getDesiredTime());
                    break;
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_ISSUED:
                case MATERIAL_INVOICE_PAID:
                case ACTIONS_INVOICE_ISSUED:
                case ACTIONS_INVOICE_PAID:
                case MASTER_SIGN_ACT:
                case CLIENT_SIGN_ACT:
                    checkClientFullName(data.getClientFullName());
                    checkClientPhone(data.getPhone());
                    checkCompanyFullName(data.getCompanyFullName());
//                 todo checkAssignedDateAndTime(data.getAssignedDateAndTime());
                    noDesiredDate();
                    noDesiredTime();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
            }
        });
    }

    private void checkCommonDetails(StateBuilder.OrderIdData data, ServiceType serviceType) {
        step("Убедиться, что общие детали заказа соответствуют ожидаемым", () -> {
            checkServiceType(serviceType);
            checkAddress(data.getAddress());
            checkEquipment(data.getEquipments0());
            checkDescription(data.getDescription());
        });
    }

    public void checkStateDetailsConsultation(StateConsultation stateConsultation, StateBuilder.OrderIdData data) {
        step("Убедиться, что  детали заказа соответствуют ожидаемым в состоянии " + stateConsultation, () -> {
            switch (stateConsultation) {
                case DRAFT_ONLINE_MASTERS:
                    checkServiceType(ServiceType.CONSULTATION);
                    checkClientFullName(data.getClientFullName());
                    checkAddress(data.getAddress());
                    checkEquipment(data.getEquipments0());
//                    checkNoCompany(); //todo selfemployed or master SC - need to determine
//                    checkCompanyFullName(data.getCompanyFullName());
//                 todo checkAssignedDateAndTime(data.getAssignedDateAndTime());
                    noDesiredDate();
                    noDesiredTime();
                    noDescription();
                    break;
                case CLIENT_WAIT_MASTER:
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                case MASTER_COMPLETE_CONSULTATION:
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    checkCommonDetails(data, ServiceType.CONSULTATION);
                    checkClientFullName(data.getClientFullName());
                    checkClientPhone(data.getPhone());
//                    checkNoCompany(); //todo selfemployed or master SC - need to determine
//                    checkCompanyFullName(data.getCompanyFullName());
//                 todo checkAssignedDateAndTime(data.getAssignedDateAndTime());
                    noDesiredDate();
                    noDesiredTime();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateConsultation);
            }
        });
    }

    private void noDescription() {
        stepWithRole("Убедиться, что описание отсутствует", () -> {
            selfCollection.findBy(text("Описание проблемы:")).shouldNotBe(visible);
        });
    }
}
