package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;

import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;
import static ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent.SharedButtonsOrderCardClientComponent.Button.*;

public class SharedButtonsOrderCardClientComponent extends BaseOrderCardComponent {

    public SharedButtonsOrderCardClientComponent(RoleBrowser browser) {
        super(browser);
    }

    public void checkButtons(StateRepair stateRepair) {
        step("Проверить набор кнопок в состоянии " + stateRepair, () -> {
            List<Button> visibleButtons;
            List<Button> notVisibleButtons;
            switch (stateRepair) {
                case PUBLISHED:
                case HAS_OFFER:
                    notVisibleButtons = List.of(SELECT_NEW_COMPANY, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(SHOW_ON_MAP, CANCEL_ORDER);
                    break;
                case SCHEDULE_DATE:
                    visibleButtons = List.of(CANCEL_ORDER, SELECT_NEW_COMPANY);
                    notVisibleButtons = List.of(SHOW_ON_MAP, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    break;
                case WAIT_MASTER:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, RETURN_TO_WORK, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(CANCEL_ORDER);
                    break;
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_PAID:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK);
                    break;
                case MATERIAL_INVOICE_ISSUED:
                case ACTIONS_INVOICE_ISSUED:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK, PAY_INVOICE);
                    break;
                case ACTIONS_INVOICE_PAID:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, PAY_INVOICE, CANCEL_ORDER, SIGN_ACT, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK);

                    break;
                case MASTER_SIGN_ACT:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, MAKE_REVIEW);
                    visibleButtons = List.of(RETURN_TO_WORK, SIGN_ACT);
                    break;
                case CLIENT_SIGN_ACT:
                    notVisibleButtons = List.of(SHOW_ON_MAP, SELECT_NEW_COMPANY, CANCEL_ORDER, PAY_INVOICE, SIGN_ACT, RETURN_TO_WORK);
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
        CANCEL_ORDER("Отменить заказ"),
        SELECT_NEW_COMPANY("Выбрать новую компанию"),
        PAY_INVOICE("Оплатить счет"),
        RETURN_TO_WORK("Вернуть в работу"),
        SIGN_ACT("Подписать акт"),
        MAKE_REVIEW("Оставить отзыв");

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