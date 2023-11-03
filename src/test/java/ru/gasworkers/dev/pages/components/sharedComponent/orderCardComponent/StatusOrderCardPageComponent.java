package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.model.OrderStatus;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateBuilder;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import static com.codeborne.selenide.Condition.*;
import static io.qameta.allure.Allure.step;

public class StatusOrderCardPageComponent extends BaseOrderCardComponent {
    ElementsCollection
            paymentCollection = driver.$$("ul.order-details__prices li").as("Коллекция платежей");
    SelenideElement
            orderStatusLocator = driver.$(".item-flex p.text").as("Статус заказа"),
            activationStagePaymentBoxLocator = driver.$$("ul.order-details__prices li").findBy(text("Активация безопасной сделки")).as(" Блок Активация безопасной сделки"),
            materialsStagePaymentBoxLocator = driver.$$("ul.order-details__prices li").findBy(text("Оплата материалов")).as("Блок Оплата материалов"),
            actionsStagePaymentBoxLocator = driver.$$("ul.order-details__prices li").findBy(text("Оплата ремонтных работ")).as("Блок Оплата ремонтных работ"),
            possibleVisitPriceBoxLocator = driver.$$("ul.order-details__prices li").findBy(text("Первичный выезд мастера")).as("Блок Первичный выезд мастера"),
            possibleRepairPriceBoxLocator = driver.$$("ul.order-details__prices li").findBy(text("Ориентировочная стоимость ремонта")).as("Блок Ориентировочная стоимость ремонта"),
            possibleVisitPriceLocator = possibleVisitPriceBoxLocator.find(".order-details__prices--price strong").as("Сумма платежа за первичный выезд мастера"),
            possibleRepairPriceLocator = possibleRepairPriceBoxLocator.find(".order-details__prices--price strong").as("Сумма платежа за ориентировочную стоимость ремонта"),
            activationStatusPaymentLocator = activationStagePaymentBoxLocator.find(".order-details__prices--status .status").as("Статус платежа за активацию"),
            activationPricePaymentLocator = activationStagePaymentBoxLocator.find(".order-details__prices--price strong").as("Сумма платежа за активацию"),
            activationStatusDatePaymentLocator = activationStagePaymentBoxLocator.find(".payment-date").as("Дата платежа за активацию"),
            materialsStatusPaymentLocator = materialsStagePaymentBoxLocator.find(".order-details__prices--status .status").as("Статус платежа за материалы"),
            materialsPricePaymentLocator = materialsStagePaymentBoxLocator.find(".order-details__prices--price strong").as("Сумма платежа за материалы"),
            materialsDatePaymentLocator = materialsStagePaymentBoxLocator.find(".payment-date").as("Дата платежа за материалы"),
            actionsStatusPaymentLocator = actionsStagePaymentBoxLocator.find(".order-details__prices--status .status").as("Статус платежа за работы"),
            actionsPricePaymentLocator = actionsStagePaymentBoxLocator.find(".order-details__prices--price strong").as("Сумма платежа за работы"),
            actionsDatePaymentLocator = actionsStagePaymentBoxLocator.find(".payment-date").as("Дата платежа за работы");

    public StatusOrderCardPageComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkCurrentStatus(OrderStatus orderStatus) {
        stepWithRole("Убедиться, что статус заказа " + orderStatus.toString(), () -> {
            orderStatusLocator.shouldHave(partialText(orderStatus.toString()));
        });
    }

    public void checkCurrentStatus(StateRepair stateRepair) {
        stepWithRole("Убедиться, что статус заказа " + stateRepair.toString() + " соответствует ожидаемому", () -> {
            orderStatusLocator.shouldHave(partialText(stateRepair.toString()));
        });
    }


    public void checkActivationStatusIsPaid(Boolean expectedStatus) {
        stepWithRole("Убедиться, что статус платежа за активацию" + expectedStatus.toString(), () -> {
            isPaid(expectedStatus, activationStatusPaymentLocator);
        });
    }

    public void noActivationStagePayment() {
        stepWithRole("Убедиться, что нет платежа за активацию", () -> {
            activationStagePaymentBoxLocator.shouldNotBe(visible);
        });
    }

    public void checkActivationPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за активацию " + expectedPrice + " соответствует ожидаемой", () -> {
            activationPricePaymentLocator.shouldHave(partialText(expectedPrice));
        });
    }

    public void checkActivationDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за активацию " + expectedDate + " соответствует ожидаемой", () -> {
            activationStatusDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    public void noPossibleVisitPrice() {
        stepWithRole("Убедиться, что нет платежа за первичный выезд мастера", () -> {
            possibleVisitPriceBoxLocator.shouldNotBe(visible);
        });
    }

    public void checkPossibleVisitPrice(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за первичный выезд мастера " + expectedPrice + " соответствует ожидаемой", () -> {
            possibleVisitPriceLocator.shouldHave(partialText(expectedPrice));
        });
    }

    public void noPossibleRepairPrice() {
        stepWithRole("Убедиться, что нет платежа за ориентировочную стоимость ремонта", () -> {
            possibleRepairPriceBoxLocator.shouldNotBe(visible);
        });
    }

    public void checkPossibleRepairPrice(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за ориентировочную стоимость ремонта " + expectedPrice + " соответствует ожидаемой", () -> {
            possibleRepairPriceLocator.shouldHave(partialText(expectedPrice));
        });
    }

    public void checkMaterialsStatusIsPaid(Boolean expectedStatus) {
        stepWithRole("Убедиться, что статус платежа за материалы" + expectedStatus.toString(), () -> {
            isPaid(expectedStatus, materialsStatusPaymentLocator);
        });
    }

    public void noMaterialsStagePayment() {
        stepWithRole("Убедиться, что нет платежа за материалы", () -> {
            materialsStagePaymentBoxLocator.shouldNotBe(visible);
        });
    }

    public void checkMaterialsPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за материалы " + expectedPrice + " соответствует ожидаемой", () -> {
            materialsPricePaymentLocator.shouldHave(text(expectedPrice));
        });
    }

    public void checkMaterialsDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за материалы " + expectedDate + " соответствует ожидаемой", () -> {
            materialsDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    public void checkActionsStatusIsPaid(Boolean expectedStatus) {
        isPaid(expectedStatus, actionsStatusPaymentLocator);
    }

    public void noActionsStagePayment() {
        stepWithRole("Убедиться, что нет платежа за работы", () -> {
            actionsStagePaymentBoxLocator.shouldNotBe(visible);
        });
    }

    public void checkActionsPricePayment(String expectedPrice) {
        stepWithRole("Убедиться, что сумма платежа за работы " + expectedPrice + " соответствует ожидаемой", () -> {
            actionsPricePaymentLocator.shouldHave(text(expectedPrice));
        });
    }

    public void checkActionsDatePayment(String expectedDate) {
        stepWithRole("Убедиться, что дата платежа за работы " + expectedDate + " соответствует ожидаемой", () -> {
            actionsDatePaymentLocator.shouldHave(text(expectedDate));
        });
    }

    private void isPaid(Boolean expectedStatus, SelenideElement statusPaymentLocator) {
        stepWithRole("Убедиться, что статус платежа " + expectedStatus.toString(), () -> {
            if (expectedStatus) {
                statusPaymentLocator.shouldHave(Condition.cssClass("green"));
            } else {
                statusPaymentLocator.shouldHave(Condition.cssClass("red"));
            }
        });
    }

    public void checkStateConsultation(StateConsultation stateConsultation, StateBuilder.OrderIdData data, OrdersIdResponseDto dto) {

        step("Проверить статус заказа и оплаты в состоянии " + stateConsultation, () -> {
            switch (stateConsultation) {
                case DRAFT_ONLINE_MASTERS:
                    checkCurrentStatus(OrderStatus.DRAFT);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    break;
                case CLIENT_WAIT_MASTER:
                    checkCurrentStatus(OrderStatus.CLIENT_WAIT_MASTER);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    // todo calculatedTime
                    break;
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                    checkCurrentStatus(OrderStatus.MASTER_START_CONSULTATION);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    // todo calculatedTime
                    break;
                case MASTER_COMPLETE_CONSULTATION:
                    checkCurrentStatus(OrderStatus.MASTER_FILLING_CONCLUSION);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    // todo calculatedTime
                    break;
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    checkCurrentStatus(OrderStatus.ORDER_COMPLETED);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    // todo stepper
                    // todo calculatedTime
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + this);

            }
        });
    }

    public void checkStateRepair(UserRole role, StateRepair stateRepair, StateBuilder.OrderIdData data, OrdersIdResponseDto dto) {

        step("Проверить статус заказа и оплаты в состоянии " + stateRepair, () -> {
            switch (stateRepair) {
                case PUBLISHED:
                    noActivationStagePayment();
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    switch (role) {
                        case CLIENT:
                            checkCurrentStatus(StateRepair.PUBLISHED);
                            break;
                        case DISPATCHER:
                            checkCurrentStatus(OrderStatus.NEW_TENDER);
                            break;
                        default:
                            throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + role);
                    }
                    break;
                case HAS_SUPER_OFFER_SD_PROCESS:
                case HAS_SERVICE_OFFER:
                    checkCurrentStatus(StateRepair.HAS_SERVICE_OFFER);
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    break;
                case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                    checkCurrentStatus(StateRepair.SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkPossibleVisitPrice(String.valueOf(data.getPossibleFirstAcceptPrice()));
                    checkPossibleRepairPrice(String.valueOf(data.getPossibleFullRepairPrice()));
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    break;
                case WAIT_SERVICE_MASTER_SD_PROCESS:
                    checkCurrentStatus(OrderStatus.WAIT_MASTER);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkPossibleVisitPrice(String.valueOf(data.getPossibleFirstAcceptPrice()));
                    checkPossibleRepairPrice(String.valueOf(data.getPossibleFullRepairPrice()));
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    break;
                case MASTER_START_WORK:
                    checkCurrentStatus(OrderStatus.MASTER_START_WORK);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkPossibleVisitPrice(String.valueOf(data.getPossibleFirstAcceptPrice()));//?
                    checkPossibleRepairPrice(String.valueOf(data.getPossibleFullRepairPrice()));//?
                    noMaterialsStagePayment();
                    noActionsStagePayment();
               /*     noMaterialsStagePayment();
                    noActionsStagePayment();
                    noPossibleVisitPrice();//!
                    noPossibleRepairPrice();//!*/
                    break;
                case MATERIAL_INVOICE_ISSUED:
                    checkCurrentStatus(OrderStatus.MATERIAL_INVOICE_ISSUED);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(false);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    noActionsStagePayment();
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case MATERIAL_INVOICE_PAID:
                    checkCurrentStatus(OrderStatus.MATERIAL_INVOICE_PAID);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(true);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    noActionsStagePayment();
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case ACTIONS_INVOICE_ISSUED:
                    checkCurrentStatus(OrderStatus.ACTIONS_INVOICE_ISSUED);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(true);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    checkActionsStatusIsPaid(false);
                    checkActionsPricePayment(data.getActionsPrice());
                    checkActionsDatePayment(data.getActionsDate());
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case ACTIONS_INVOICE_PAID:
                    checkCurrentStatus(OrderStatus.ACTIONS_INVOICE_PAID);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(true);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    checkActionsStatusIsPaid(true);
                    checkActionsPricePayment(data.getActionsPrice());
                    checkActionsDatePayment(data.getActionsDate());
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case MASTER_SIGN_ACT:
                    checkCurrentStatus(OrderStatus.MASTER_SIGN_ACT);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(true);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    checkActionsStatusIsPaid(true);
                    checkActionsPricePayment(data.getActionsPrice());
                    checkActionsDatePayment(data.getActionsDate());
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case CLIENT_SIGN_ACT:
                    checkCurrentStatus(OrderStatus.CLIENT_SIGN_ACT);
                    checkActivationStatusIsPaid(true);
                    checkActivationPricePayment(data.getActivationPrice());
                    checkActivationDatePayment(data.getActivationDate());
                    checkMaterialsStatusIsPaid(true);
                    checkMaterialsPricePayment(data.getMaterialsPrice());
                    checkMaterialsDatePayment(data.getMaterialsDate());
                    checkActionsStatusIsPaid(true);
                    checkActionsPricePayment(data.getActionsPrice());
                    checkActionsDatePayment(data.getActionsDate());
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                    checkCurrentStatus(OrderStatus.CANCELED);
                    noActivationStagePayment();
                    noMaterialsStagePayment();
                    noActionsStagePayment();
                    noPossibleVisitPrice();
                    noPossibleRepairPrice();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
            }
        });
    }
}