package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.tests.web.orderProcess.consultation.stateHelper.StateConsultation;
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
                            CREATE_CONCLUSION,
                            START_CONSULTATION,
                            SHOW_ON_MAP,
                            SELECT_NEW_COMPANY,
                            RETURN_TO_WORK,
                            PAY_INVOICE,
                            SIGN_ACT,
                            MAKE_REVIEW);
                    visibleButtons = List.of(
                            CANCEL_ORDER,
                            SELECT_MASTER_CONSULTATION
                    );
                    break;
                case CLIENT_WAIT_MASTER:
                    notVisibleButtons = List.of(
                            CREATE_CONCLUSION,
                            START_CONSULTATION,
                            SHOW_ON_MAP,
                            SELECT_NEW_COMPANY,
                            SELECT_MASTER_CONSULTATION,
                            RETURN_TO_WORK,
                            PAY_INVOICE,
                            SIGN_ACT,
                            MAKE_REVIEW);
                    visibleButtons = List.of(CANCEL_ORDER);
                    break;
                case MASTER_START_CONSULTATION:
                case CLIENT_JOIN_CONSULTATION:
                    notVisibleButtons = List.of(
                            CREATE_CONCLUSION,
                            CANCEL_ORDER,
                            SHOW_ON_MAP,
                            SELECT_MASTER_CONSULTATION,
                            SELECT_NEW_COMPANY,
                            RETURN_TO_WORK,
                            PAY_INVOICE,
                            SIGN_ACT,
                            MAKE_REVIEW);
                    visibleButtons = List.of(
                            START_CONSULTATION
                    );
                    break;
                case MASTER_COMPLETE_CONSULTATION:
                    notVisibleButtons = List.of(
                            CREATE_CONCLUSION,
                            RETURN_TO_WORK,
                            START_CONSULTATION,
                            SHOW_ON_MAP,
                            SELECT_MASTER_CONSULTATION,
                            SELECT_NEW_COMPANY,
                            CANCEL_ORDER,
                            PAY_INVOICE,
                            SIGN_ACT,
                            MAKE_REVIEW
                    );
                    visibleButtons = List.of();
                    break;
                case MASTER_FILLED_RESUME:
                case ORDER_COMPLETED:
                    notVisibleButtons = List.of(
                            CREATE_CONCLUSION,
                            RETURN_TO_WORK,
                            START_CONSULTATION,
                            SHOW_ON_MAP,
                            SELECT_MASTER_CONSULTATION,
                            SELECT_NEW_COMPANY,
                            CANCEL_ORDER,
                            PAY_INVOICE,
                            SIGN_ACT
                    );
                    visibleButtons = List.of(
                            MAKE_REVIEW
                    );
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateConsultation);
            }
            visibleButtons.forEach(this::checkButtonIsVisible);
            notVisibleButtons.forEach(this::checkButtonIsNotVisible);
        });
    }


    public void checkStateRepair(StateRepair stateRepair) {
        step("Проверить набор кнопок в состоянии " + stateRepair, () -> {
            List<Button> visibleButtons;
            List<Button> notVisibleButtons;
            switch (stateRepair) {
                case PUBLISHED:
                case HAS_OFFER:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, START_CONSULTATION, SELECT_MASTER_CONSULTATION, SELECT_NEW_COMPANY, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(SHOW_ON_MAP, CANCEL_ORDER);
                    break;
                case CANCEL_CLIENT_PUBLISHED:
                case CANCEL_CLIENT_HAS_OFFER:
                case CANCEL_DISPATCHER_HAS_OFFER:
                    notVisibleButtons = List.of(
                            CREATE_CONCLUSION,
                            START_CONSULTATION,
                            SELECT_MASTER_CONSULTATION,
                            SHOW_ON_MAP,
                            SELECT_NEW_COMPANY,
                            RETURN_TO_WORK,
                            PAY_INVOICE,
                            SIGN_ACT,
                            MAKE_REVIEW,
                            CANCEL_ORDER);
                    visibleButtons = List.of();
                    break;
                case SCHEDULE_DATE:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(CANCEL_ORDER, SELECT_NEW_COMPANY);
                    break;
                case WAIT_MASTER:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(CANCEL_ORDER);
                    break;
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_PAID:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK);
                    break;
                case MATERIAL_INVOICE_ISSUED:
                case ACTIONS_INVOICE_ISSUED:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK, PAY_INVOICE);
                    break;
                case ACTIONS_INVOICE_PAID:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, PAY_INVOICE, CANCEL_ORDER, SIGN_ACT, MAKE_REVIEW, START_CONSULTATION);
                    visibleButtons = List.of(RETURN_TO_WORK);
                    break;
                case MASTER_SIGN_ACT:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK, SIGN_ACT);
                    break;
                case CLIENT_SIGN_ACT:
                    notVisibleButtons = List.of(CREATE_CONCLUSION, SELECT_MASTER_CONSULTATION, START_CONSULTATION, SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, SIGN_ACT, RETURN_TO_WORK);
                    visibleButtons = List.of(MAKE_REVIEW);
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
            }
            visibleButtons.forEach(this::checkButtonIsVisible);
            notVisibleButtons.forEach(this::checkButtonIsNotVisible);
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
        SHOW_ON_MAP("Показать на карте"),
        START_CONSULTATION("Начать консультацию"),
        CANCEL_ORDER("Отменить заказ"),
        SELECT_NEW_COMPANY("Выбрать новую компанию"),
        SELECT_MASTER_CONSULTATION("Выберите мастера"),
        PAY_INVOICE("Оплатить счет"),
        RETURN_TO_WORK("Вернуть в работу"),
        SIGN_ACT("Подписать акт"),
        CREATE_CONCLUSION("Создать резюме"),
        MAKE_REVIEW("Оставить отзыв"),

        //dispatcher buttons
        DISPATCHER_SELECT_MASTER("Выбрать мастера"),
        DISPATCHER_REFUSE("Отказаться");

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