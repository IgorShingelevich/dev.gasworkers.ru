package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;
import ru.gasworkers.dev.tests.web.orderProcess.repair.StateRepair;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class LastOrderProfileClientComponent extends BaseComponent {
    public final StepperComponent stepper;
    public final UrlCheckerSharedComponent urlChecker;
    public final OffersCounterClientComponent offersCounter;
    private final String
            LAST_ORDER_CARD_TITLE = "Информация о последнем заказе",
            LAST_ORDER_CARD_SERVICE_TYPE_TITLE = "Тип заказа",
            LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE = "Адрес объекта",
            LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE = "Оборудование",
            LAST_ORDER_CARD_OBJECT_DESIRED_DATE_TITLE = "Выбранная дата",
            LAST_ORDER_CARD_OBJECT_DESIRED_TIME_TITLE = "Выбранное время",
            MASTER_VISIT_DATE_AND_TIME = "Дата и время приезда мастера";
    SelenideElement
            self = driver.$(".section .section.order").as("Секция Карточка последнего заказа"),
            lastOrderCardTitleLocator = driver.$(".section .header .title.d-flex.justify-content-between").as("Заголовок Карточки последнего заказа"),
            lastOrderCardOrderNumberLinkLocator = driver.$(".section .content .h5.link-blue.text-primary.pointer").as("Ссылка на номер заказа"),
            lastOrderCardActionButtonLocator = driver.$(".section .content .actions .actions__btn").as("Кнопка открытия меню действий карточки последнего заказа"),
            lastOrderCardOrderActionOpenLinkLocator = driver.$(".section .content .actions .actions__slot--link").as("Кнопка открытия карточки заказа"),
            lastOrderCardServiceTypeTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_SERVICE_TYPE_TITLE)).as("Тип заказа"),
            lastOrderCardAddressTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE)).as("Адрес объекта"),
            lastOrderCardEquipmentTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE)).as("Оборудование"),
            lastOrderCardDesiredDateTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_DESIRED_DATE_TITLE)).as("Выбранная дата"),
            lastOrderCardDesiredTimeTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_DESIRED_TIME_TITLE)).as("Выбранное время"),
            lastOrderCardMasterVisitDateAndTimeTitleLocator = driver.$$(".section__row.row").findBy(text(MASTER_VISIT_DATE_AND_TIME)).as("Дата и время приезда мастера");


    public LastOrderProfileClientComponent(RoleBrowser browser) {
        super(browser);
        stepper = new StepperComponent(browser);
        urlChecker = new UrlCheckerSharedComponent(browser);
        offersCounter = new OffersCounterClientComponent(browser);
    }

    public LastOrderProfileClientComponent lastOrderCard() {
        stepWithRole("Перейти в Карточку последнего заказа", () -> {
            lastOrderCardOrderNumberLinkLocator.click();
            urlChecker.urlStartsWith("https://gasworkers.ru/client/orders/");
        });
        return this;
    }

    public LastOrderProfileClientComponent hoverOverLastOrderActionButton() {
        lastOrderCardActionButtonLocator.hover();
        lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible);
        return this;
    }

    public LastOrderProfileClientComponent clickLastOrderActionButton() {
        lastOrderCardActionButtonLocator.shouldBe(visible).click();
        return this;
    }

    public LastOrderProfileClientComponent open() {
        lastOrderCardActionButtonLocator.shouldBe(visible)
                .click();
        lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible)
                .click();
        return this;
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что секция Карточка последнего заказа отображается", () -> {
            self.shouldBe(visible, Duration.ofSeconds(10));
            checkTitle();
        });
    }

    public void checkTitle() {
        stepWithRole("Убедиться, что заголовок Карточки последнего заказа отображается", () -> {
            lastOrderCardTitleLocator.shouldHave(text(LAST_ORDER_CARD_TITLE));
        });
    }

    public void checkOrderNumber(String expectedOrderNumber) {
        stepWithRole("Убедиться, что номер заказа " + expectedOrderNumber + " соответствует ожидаемому", () -> {
            lastOrderCardOrderNumberLinkLocator.shouldHave(partialText(expectedOrderNumber));
        });
    }

    public void checkServiceType(String expectedServiceType) {
        stepWithRole("Убедиться, что тип заказа " + expectedServiceType + " соответствует ожидаемому", () -> {
            lastOrderCardServiceTypeTitleLocator.shouldHave(text(expectedServiceType));
        });
    }

    public void checkAddress(String expectedAddress) {
        stepWithRole("Убедиться, что адрес " + expectedAddress + " соответствует ожидаемому", () -> {
            lastOrderCardAddressTitleLocator.shouldHave(text(expectedAddress));
        });
    }

    public void checkEquipment(String expectedEquipment) {
        stepWithRole("Убедиться, что оборудование " + expectedEquipment + " соответствует ожидаемому", () -> {
            lastOrderCardEquipmentTitleLocator.shouldHave(text(expectedEquipment));
        });
    }

    public void checkDesiredDate(String expectedDate) {
        stepWithRole("Убедиться, что дата " + expectedDate + " соответствует ожидаемой", () -> {
            lastOrderCardDesiredDateTitleLocator.shouldHave(text(expectedDate));
        });
    }

    public void noDesiredDate() {
        stepWithRole("Убедиться, что дата отсутствует", () -> {
            lastOrderCardDesiredDateTitleLocator.shouldNotBe(visible);
        });
    }

    public void checkDesiredTime(String expectedTime) {
        stepWithRole("Убедиться, что время " + expectedTime + " соответствует ожидаемому", () -> {
            lastOrderCardDesiredTimeTitleLocator.shouldHave(text(expectedTime));
        });
    }

    public void noDesiredTime() {
        stepWithRole("Убедиться, что время отсутствует", () -> {
            lastOrderCardDesiredTimeTitleLocator.shouldNotBe(visible);
        });
    }

    public void checkMasterVisitDateAndTime(String expectedDateAndTime) {
        stepWithRole("Убедиться, что дата и время " + expectedDateAndTime + " соответствует ожидаемому", () -> {
            lastOrderCardMasterVisitDateAndTimeTitleLocator.shouldHave(text(expectedDateAndTime));
        });
    }

    public void noMasterVisitDateAndTime() {
        stepWithRole("Убедиться, что дата и время отсутствует", () -> {
            lastOrderCardMasterVisitDateAndTimeTitleLocator.shouldNotBe(visible);
        });
    }

    public void checkState(StateRepair state, LastOrderInfoResponseDto currentLastOrderInfoDto) {
        stepWithRole("Убедиться, что статус " + state + " соответствует ожидаемому", () -> {
            state.checkLastOrderComponent(this, currentLastOrderInfoDto);
        });
    }
}