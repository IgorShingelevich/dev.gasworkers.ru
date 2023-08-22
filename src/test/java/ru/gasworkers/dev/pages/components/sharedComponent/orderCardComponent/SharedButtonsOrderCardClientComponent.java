package ru.gasworkers.dev.pages.components.sharedComponent.orderCardComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static io.qameta.allure.Allure.step;

public class SharedButtonsOrderCardClientComponent extends BaseOrderCardComponent {
    SelenideElement
            showOnMapButtonLocator = driver.$(byTagAndText("span", "Показать на карте")).as("Кнопка Показать на карте"),
            cancelOrderButtonLocator = driver.$(byTagAndText("span", "Отменить заказ")).as("Кнопка Отменить заказ"),
            selectNewCompanyButtonLocator = driver.$(byTagAndText("span", "Выбрать новую компанию")).as("Кнопка Выбрать новую компанию"),
            payInvoiceButtonLocator = driver.$(byTagAndText("span", "Оплатить счет")).as("Кнопка Оплатить счет"),
            returnToWorkButtonLocator = driver.$(byTagAndText("span", "Вернуть в работу")).as("Кнопка Вернуть в работу");

    public SharedButtonsOrderCardClientComponent(RoleBrowser browser) {
        super(browser);
    }

    public void buttonSet(StateRepair stateRepair) {
        step("Проверить набор кнопок в состоянии " + stateRepair, () -> {
            switch (stateRepair) {
                case PUBLISHED:
                case HAS_OFFER:
                    noSelectNewCompanyButton();
                    noReturnToWorkButton();
                    noPayInvoiceButton();
                    checkShowOnMapButton();
                    checkCancelButton();
                    break;
                case SCHEDULE_DATE:
                    noShowOnMapButton();
                    noReturnToWorkButton();
                    noPayInvoiceButton();
                    checkCancelButton();
                    checkSelectNewCompanyButton();
                    break;
                case WAIT_MASTER:
                    noShowOnMapButton();
                    noReturnToWorkButton();
                    noSelectNewCompanyButton();
                    noPayInvoiceButton();
                    checkCancelButton();
                    break;
                case MASTER_START_WORK:
                    noShowOnMapButton();
                    noCancelButton();
                    noSelectNewCompanyButton();
                    noPayInvoiceButton();
                    checkReturnToWorkButton();
                    break;
                case MATERIAL_INVOICE_ISSUED:
                    noShowOnMapButton();
                    noCancelButton();
                    noSelectNewCompanyButton();
                    checkReturnToWorkButton();
                    checkPayInvoiceButton();
                    break;
                case MATERIAL_INVOICE_PAID:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noPayInvoiceButton();
                    noCancelButton();
                    checkReturnToWorkButton();
                    break;
                default:
                    throw new IllegalStateException(this.getClass().getSimpleName() + " Unexpected value: " + stateRepair);
            }
        });
    }

    public void checkShowOnMapButton() {
        stepWithRole("Убедиться, что кнопка Показать на карте присутствует", () -> {
            showOnMapButtonLocator.shouldBe(visible);
        });
    }

    public void checkCancelButton() {
        stepWithRole("Убедиться, что кнопка Отменить заказ присутствует", () -> {
            cancelOrderButtonLocator.shouldBe(visible);
        });
    }

    public void showOnMap() {
        stepWithRole("Нажать на кнопку Показать на карте", () -> {
            showOnMapButtonLocator.click();
        });
    }

    public void noShowOnMapButton() {
        stepWithRole("Убедиться, что кнопка Показать на карте отсутствует", () -> {
            showOnMapButtonLocator.shouldNotBe(visible);
        });
    }

    public void cancelOrder() {
        stepWithRole("Нажать на кнопку Отменить заказ", () -> {
            cancelOrderButtonLocator.click();
        });
    }

    public void checkSelectNewCompanyButton() {
        stepWithRole("Убедиться, что кнопка Выбрать новую компанию присутствует", () -> {
            selectNewCompanyButtonLocator.shouldBe(visible);
        });
    }

    public void selectNewCompany() {
        stepWithRole("Нажать на кнопку Выбрать новую компанию", () -> {
            selectNewCompanyButtonLocator.click();
        });
    }

    public void noSelectNewCompanyButton() {
        stepWithRole("Убедиться, что кнопка Выбрать новую компанию отсутствует", () -> {
            selectNewCompanyButtonLocator.shouldNotBe(visible);
        });
    }


    public void noCancelButton() {
        stepWithRole("Убедиться, что кнопка Отменить заказ отсутствует", () -> {
            cancelOrderButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkReturnToWorkButton() {
        stepWithRole("Убедиться, что кнопка Вернуть в работу присутствует", () -> {
            returnToWorkButtonLocator.shouldBe(visible);
        });
    }

    public void returnToWork() {
        stepWithRole("Нажать на кнопку Вернуть в работу", () -> {
            returnToWorkButtonLocator.click();
        });
    }

    public void noReturnToWorkButton() {
        stepWithRole("Убедиться, что кнопка Вернуть в работу отсутствует", () -> {
            returnToWorkButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkPayInvoiceButton() {
        stepWithRole("Убедиться, что кнопка Оплатить счет присутствует", () -> {
            payInvoiceButtonLocator.shouldBe(visible);
        });
    }

    public void payInvoice() {
        stepWithRole("Нажать на кнопку Оплатить счет", () -> {
            payInvoiceButtonLocator.click();
        });
    }

    public void noPayInvoiceButton() {
        stepWithRole("Убедиться, что кнопка Оплатить счет отсутствует", () -> {
            payInvoiceButtonLocator.shouldNotBe(visible);
        });
    }
}
