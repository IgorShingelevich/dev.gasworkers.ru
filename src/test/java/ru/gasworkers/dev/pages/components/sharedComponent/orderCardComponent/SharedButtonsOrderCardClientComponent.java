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
            returnToWorkButtonLocator = driver.$(byTagAndText("span", "Вернуть в работу")).as("Кнопка Вернуть в работу"),
            signActButtonLocator = driver.$(byTagAndText("span", "Подписать акт")).as("Кнопка Подписать акт"),
            makeReviewButtonLocator = driver.$(byTagAndText("span", "Оставить отзыв")).as("Кнопка Оставить отзыв");

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
                    noSignActButton();
                    noMakeReviewButton();
                    checkShowOnMapButton();
                    checkCancelButton();
                    break;
                case SCHEDULE_DATE:
                    noShowOnMapButton();
                    noReturnToWorkButton();
                    noPayInvoiceButton();
                    checkCancelButton();
                    noSignActButton();
                    noMakeReviewButton();
                    checkSelectNewCompanyButton();
                    break;
                case WAIT_MASTER:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noReturnToWorkButton();
                    noPayInvoiceButton();
                    noSignActButton();
                    noMakeReviewButton();
                    checkCancelButton();
                    break;
                case MASTER_START_WORK:
                case MATERIAL_INVOICE_PAID:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noCancelButton();
                    noPayInvoiceButton();
                    noSignActButton();
                    noMakeReviewButton();
                    checkReturnToWorkButton();
                    break;
                case MATERIAL_INVOICE_ISSUED:
                case ACTIONS_INVOICE_ISSUED:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noCancelButton();
                    noSignActButton();
                    noMakeReviewButton();
                    checkReturnToWorkButton();
                    checkPayInvoiceButton();
                    break;
                case ACTIONS_INVOICE_PAID:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noPayInvoiceButton();
                    noCancelButton();
                    noSignActButton();
                    noMakeReviewButton();
                    checkReturnToWorkButton();
                    break;
                case MASTER_SIGN_ACT:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noCancelButton();
                    noPayInvoiceButton();
                    noMakeReviewButton();
                    checkReturnToWorkButton();
                    checkSignActButton();
                    break;
                case CLIENT_SIGN_ACT:
                    noShowOnMapButton();
                    noSelectNewCompanyButton();
                    noCancelButton();
                    noPayInvoiceButton();
                    noSignActButton();
                    noReturnToWorkButton();
                    checkMakeReviewButton();
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

    public void noSignActButton() {
        stepWithRole("Убедиться, что кнопка Подписать акт отсутствует", () -> {
            signActButtonLocator.shouldNotBe(visible);
        });
    }

    public void checkSignActButton() {
        stepWithRole("Убедиться, что кнопка Подписать акт присутствует", () -> {
            signActButtonLocator.shouldBe(visible);
        });
    }

    public void signAct() {
        stepWithRole("Нажать на кнопку Подписать акт", () -> {
            signActButtonLocator.click();
        });
    }

    public void checkMakeReviewButton() {
        stepWithRole("Убедиться, что кнопка Оставить отзыв присутствует", () -> {
            makeReviewButtonLocator.shouldBe(visible);
        });
    }

    public void makeReview() {
        stepWithRole("Нажать на кнопку Оставить отзыв", () -> {
            makeReviewButtonLocator.click();
        });
    }

    public void noMakeReviewButton() {
        stepWithRole("Убедиться, что кнопка Оставить отзыв отсутствует", () -> {
            makeReviewButtonLocator.shouldNotBe(visible);
        });
    }
}
