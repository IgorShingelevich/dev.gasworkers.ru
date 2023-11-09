package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.model.UserRole;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateTransitionTest.stateHelper.StateConsultation;
import ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper.StateRepair;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.SharedButtonsOrderCardComponent.Button.*;

public class SharedButtonsOrderCardComponent extends BaseOrderCardComponent {

    public SharedButtonsOrderCardComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkStateConsultation(StateConsultation stateConsultation) {
        step("Проверить набор кнопок в состоянии " + stateConsultation, () -> {
            List<Button> visibleButtons;
            List<Button> notVisibleButtons;
            switch (stateConsultation) {
                case DRAFT_ONLINE_MASTERS:
                    notVisibleButtons = List.of(
                            CLIENT_CREATE_CONCLUSION,
                            CLIENT_START_CONSULTATION,
                            CLIENT_SHOW_ON_MAP,
                            CLIENT_SELECT_NEW_COMPANY,
                            CLIENT_RETURN_TO_WORK,
                            CLIENT_PAY_INVOICE,
                            CLIENT_SIGN_ACT,
                            CLIENT_MAKE_REVIEW);
                    visibleButtons = List.of(
                            CLIENT_CANCEL_ORDER,
                            CLIENT_SELECT_MASTER_CONSULTATION
                    );
                    break;
                case CLIENT_WAIT_MASTER:
                    notVisibleButtons = List.of(
                            CLIENT_CREATE_CONCLUSION,
                            CLIENT_START_CONSULTATION,
                            CLIENT_SHOW_ON_MAP,
                            CLIENT_SELECT_NEW_COMPANY,
                            CLIENT_SELECT_MASTER_CONSULTATION,
                            CLIENT_RETURN_TO_WORK,
                            CLIENT_PAY_INVOICE,
                            CLIENT_SIGN_ACT,
                            CLIENT_MAKE_REVIEW);
                    visibleButtons = List.of(CLIENT_CANCEL_ORDER);
                    break;
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                    notVisibleButtons = List.of(
                            CLIENT_CREATE_CONCLUSION,
                            CLIENT_CANCEL_ORDER,
                            CLIENT_SHOW_ON_MAP,
                            CLIENT_SELECT_MASTER_CONSULTATION,
                            CLIENT_SELECT_NEW_COMPANY,
                            CLIENT_RETURN_TO_WORK,
                            CLIENT_PAY_INVOICE,
                            CLIENT_SIGN_ACT,
                            CLIENT_MAKE_REVIEW);
                    visibleButtons = List.of(
                            CLIENT_START_CONSULTATION
                    );
                    break;
                case MASTER_COMPLETE_CONSULTATION:
                    notVisibleButtons = List.of(
                            CLIENT_CREATE_CONCLUSION,
                            CLIENT_RETURN_TO_WORK,
                            CLIENT_START_CONSULTATION,
                            CLIENT_SHOW_ON_MAP,
                            CLIENT_SELECT_MASTER_CONSULTATION,
                            CLIENT_SELECT_NEW_COMPANY,
                            CLIENT_CANCEL_ORDER,
                            CLIENT_PAY_INVOICE,
                            CLIENT_SIGN_ACT,
                            CLIENT_MAKE_REVIEW
                    );
                    visibleButtons = List.of();
                    break;
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    notVisibleButtons = List.of(
                            CLIENT_CREATE_CONCLUSION,
                            CLIENT_RETURN_TO_WORK,
                            CLIENT_START_CONSULTATION,
                            CLIENT_SHOW_ON_MAP,
                            CLIENT_SELECT_MASTER_CONSULTATION,
                            CLIENT_SELECT_NEW_COMPANY,
                            CLIENT_CANCEL_ORDER,
                            CLIENT_PAY_INVOICE,
                            CLIENT_SIGN_ACT
                    );
                    visibleButtons = List.of(
                            CLIENT_MAKE_REVIEW
                    );
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateConsultation);
            }
            visibleButtons.forEach(this::checkButtonIsVisible);
            notVisibleButtons.forEach(this::checkButtonIsNotVisible);
        });
    }


    public void checkStateRepair(UserRole userRole, StateRepair stateRepair) {
        step("Проверить набор кнопок в состоянии " + stateRepair, () -> {

            List<Button> visibleButtons;
            List<Button> notVisibleButtons;
            switch (userRole) {
                case CLIENT:
                    checkNoDispatcherButtons();
                    switch (stateRepair) {
                        case PUBLISHED:
                        case HAS_SUPER_OFFER_SD_PROCESS:
                        case HAS_SERVICE_OFFER:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_START_CONSULTATION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_SELECT_NEW_COMPANY, CLIENT_RETURN_TO_WORK, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_SHOW_ON_MAP, CLIENT_CANCEL_ORDER);
                            break;
                        case CANCEL_CLIENT_PUBLISHED:
                        case CANCEL_CLIENT_HAS_OFFER:
                        case CANCEL_DISPATCHER_HAS_OFFER:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_START_CONSULTATION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_RETURN_TO_WORK, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW, CLIENT_CANCEL_ORDER);
                            visibleButtons = List.of();
                            break;
                        case CLIENT_PAID_SUPER_ACTIVATION_SD_PROCESS:
                        case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                        case SERVICE_SCHEDULED_MASTER_SD_PROCESS:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_RETURN_TO_WORK, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_CANCEL_ORDER, CLIENT_SELECT_NEW_COMPANY);
                            break;
                        case WAIT_SERVICE_MASTER_SD_PROCESS:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_RETURN_TO_WORK, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_CANCEL_ORDER);
                            break;
                        case MASTER_START_WORK:
                        case MATERIAL_INVOICE_PAID:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_CANCEL_ORDER, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_RETURN_TO_WORK);
                            break;
                        case MATERIAL_INVOICE_ISSUED:
                        case ACTIONS_INVOICE_ISSUED:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_CANCEL_ORDER, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_RETURN_TO_WORK, CLIENT_PAY_INVOICE);
                            break;
                        case ACTIONS_INVOICE_PAID:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_PAY_INVOICE, CLIENT_CANCEL_ORDER, CLIENT_SIGN_ACT, CLIENT_MAKE_REVIEW, CLIENT_START_CONSULTATION, CLIENT_RETURN_TO_WORK);
                            visibleButtons = List.of(CLIENT_MAKE_REVIEW);
                            break;
                        case MASTER_SIGN_ACT:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_CANCEL_ORDER, CLIENT_PAY_INVOICE, CLIENT_MAKE_REVIEW);
                            visibleButtons = List.of(CLIENT_RETURN_TO_WORK, CLIENT_SIGN_ACT);
                            break;
                        case CLIENT_SIGN_ACT:
                            notVisibleButtons = List.of(CLIENT_CREATE_CONCLUSION, CLIENT_SELECT_MASTER_CONSULTATION, CLIENT_START_CONSULTATION, CLIENT_SHOW_ON_MAP, CLIENT_SELECT_NEW_COMPANY, CLIENT_CANCEL_ORDER, CLIENT_PAY_INVOICE, CLIENT_SIGN_ACT, CLIENT_RETURN_TO_WORK);
                            visibleButtons = List.of(CLIENT_MAKE_REVIEW);
                            break;
                        default:
                            throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
                    }
                    break;
                case DISPATCHER: {
                    checkNoClientButtons();
                    switch (stateRepair) {
                        case PUBLISHED:
                            visibleButtons = List.of(DISPATCHER_SELECT_MASTER, DISPATCHER_REFUSE);
                            notVisibleButtons = List.of(DISPATCHER_ALREADY_SELECTED_MASTER, DISPATCHER_ALREADY_REFUSED, DISPATCHER_CANCEL);
                            break;
                        case REFUSED_OFFER_DISPATCHER:
                            visibleButtons = List.of(DISPATCHER_ALREADY_REFUSED);
                            notVisibleButtons = List.of(DISPATCHER_SELECT_MASTER, DISPATCHER_ALREADY_SELECTED_MASTER, DISPATCHER_CANCEL, DISPATCHER_REFUSE);
                            break;
                        case HAS_SERVICE_OFFER:
                            visibleButtons = List.of(DISPATCHER_ALREADY_SELECTED_MASTER);
                            notVisibleButtons = List.of(DISPATCHER_SELECT_MASTER, DISPATCHER_ALREADY_REFUSED, DISPATCHER_CANCEL, DISPATCHER_REFUSE);
                            break;
                        case CANCEL_CLIENT_PUBLISHED:
                        case CANCEL_CLIENT_HAS_OFFER:
                        case CANCEL_DISPATCHER_HAS_OFFER:
                        case SUPER_DISPATCHER_ASSIGN_SERVICE_SD_PROCESS:
                        case WAIT_SERVICE_MASTER_SD_PROCESS:
                        case MASTER_START_WORK:
                        case MATERIAL_INVOICE_PAID:
                        case MATERIAL_INVOICE_ISSUED:
                        case ACTIONS_INVOICE_ISSUED:
                        case ACTIONS_INVOICE_PAID:
                        case MASTER_SIGN_ACT:
                        case CLIENT_SIGN_ACT:
                            notVisibleButtons = List.of();
                            visibleButtons = List.of();
                            break;
                        default:
                            throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
                    }
                }
                break;
                default:
                    throw new IllegalArgumentException("Unexpected user role: " + userRole);
            }
            visibleButtons.forEach(this::checkButtonIsVisible);
            notVisibleButtons.forEach(this::checkButtonIsNotVisible);
        });
    }

    public void checkNoDispatcherButtons() {
        step("Проверить, что кнопки диспетчера отсутствуют", () -> {
            List<Button> buttons = List.of(
                    DISPATCHER_SELECT_MASTER,
                    DISPATCHER_ALREADY_SELECTED_MASTER,
                    DISPATCHER_REFUSE,
                    DISPATCHER_ALREADY_REFUSED,
                    DISPATCHER_CANCEL
            );
            buttons.forEach(this::checkButtonIsNotVisible);
        });
    }

    public void checkNoClientButtons() {
        step("Проверить, что кнопки клиента отсутствуют", () -> {
            List<Button> buttons = List.of(
                    CLIENT_CREATE_CONCLUSION,
                    CLIENT_START_CONSULTATION,
                    CLIENT_SHOW_ON_MAP,
                    CLIENT_SELECT_NEW_COMPANY,
                    CLIENT_SELECT_MASTER_CONSULTATION,
                    CLIENT_RETURN_TO_WORK,
                    CLIENT_PAY_INVOICE,
                    CLIENT_SIGN_ACT,
                    CLIENT_MAKE_REVIEW
            );
            buttons.forEach(this::checkButtonIsNotVisible);
        });
    }

    public void checkButtonIsVisible(Button button) {
       /* if (button == NO_BUTTON) {
            return;
        }*/
        stepWithRole("Убедиться, что  присутствует кнопка " + button, () ->
                button.asSelenideElement(driver).shouldBe(visible));
    }

    public void checkButtonIsNotVisible(Button button) {
        stepWithRole("Убедиться, что отсутствует кнопка " + button, () ->
                button.asSelenideElement(driver).shouldNotBe(visible));
    }

    public void clickOnButton(Button button) {
        stepWithRole("Нажать на кнопку " + button, () ->
                button.asSelenideElement(driver).click());
    }

    @AllArgsConstructor
    public enum Button {
        CLIENT_SHOW_ON_MAP("Показать на карте"),
        CLIENT_START_CONSULTATION("Начать консультацию"),
        CLIENT_CANCEL_ORDER("Отменить заказ"),
        CLIENT_SELECT_NEW_COMPANY("Выбрать новую компанию"),
        CLIENT_SELECT_MASTER_CONSULTATION("Выберите мастера"),
        CLIENT_PAY_INVOICE("Оплатить счет"),
        CLIENT_RETURN_TO_WORK("Вернуть в работу"),
        CLIENT_SIGN_ACT("Подписать акт"),
        CLIENT_CREATE_CONCLUSION("Создать резюме"),
        CLIENT_MAKE_REVIEW("Оставить отзыв"),

        //dispatcher buttons
        DISPATCHER_SELECT_MASTER("Назначить мастера"),
        DISPATCHER_ALREADY_SELECTED_MASTER("Уже участвуете"), // only  disabled
        DISPATCHER_REFUSE("Отказаться"),
        DISPATCHER_ALREADY_REFUSED("Отказались"),  // only  disabled
        DISPATCHER_CANCEL("Отменить"),
        DISPATCHER_SAVE("Сохранить");

        private final String title;

        SelenideElement asSelenideElement(SelenideDriver driver) {
            return driver.$(byTagAndText("span", title)).as(String.format("Кнопка %s", title));
        }

        @Override
        public String toString() {
            return title;
        }

    }

}