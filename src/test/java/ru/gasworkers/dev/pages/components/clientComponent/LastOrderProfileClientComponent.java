package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;


import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LastOrderProfileClientComponent extends BaseComponent {

    public final StepperComponent stepper;

    public LastOrderProfileClientComponent(RoleBrowser browser) {
        super(browser);
        stepper = new StepperComponent(browser);
    }



    private final String
        LAST_ORDER_CARD_TITLE = "Информация о последнем заказе",
        LAST_ORDER_CARD_SERVICE_TYPE_TITLE = "Тип услуги",
        LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE = "Адрес объекта",
        LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE = "Оборудование",
        LAST_ORDER_CARD_OBJECT_DATE_TITLE = "Выбранная дата",
        LAST_ORDER_CARD_OBJECT_TIME_TITLE = "Выбранное время";

   SelenideElement
        lastOrderCardLocator = driver.$(".section .section.order"),
        lastOrderCardTitleLocator = driver.$(".section .header .title.d-flex.justify-content-between"),
        lastOrderCardOrderNumberLinkLocator = driver.$(".section .content .h5.link-blue.text-primary.pointer"),
        lastOrderCardActionButtonLocator = driver.$(".section .content .actions .actions__btn"),
        lastOrderCardOrderActionOpenLinkLocator = driver.$(".section .content .actions .actions__slot--link"),
        lastOrderCardServiceTypeTitleCollection = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_SERVICE_TYPE_TITLE)),
        lastOrderCardObjectAddressTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE)),
        lastOrderCardObjectEquipmentTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE)),
        lastOrderCardObjectDateTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_DATE_TITLE)),
        lastOrderCardObjectTimeTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_TIME_TITLE));


    public LastOrderProfileClientComponent lastOrderCard() {
        stepWithRole("Перейти в Карточку последнего заказа", () -> {
            lastOrderCardOrderNumberLinkLocator.click();
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
        lastOrderCardActionButtonLocator.shouldBe(visible).click();
        lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible).click();
        return this;
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что секция Карточка последнего заказа отображается", () -> {
            lastOrderCardLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
    }
}