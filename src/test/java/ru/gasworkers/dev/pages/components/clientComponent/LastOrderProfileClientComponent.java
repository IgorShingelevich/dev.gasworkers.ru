package ru.gasworkers.dev.pages.components.clientComponent;

import com.codeborne.selenide.SelenideElement;
import lombok.Builder;
import lombok.Data;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.browser.RoleBrowser;
import ru.gasworkers.dev.pages.components.BaseComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.allRolesSharedComponent.UrlCheckerSharedComponent;
import ru.gasworkers.dev.pages.components.sharedComponent.stepperComponent.StepperComponent;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

public class LastOrderProfileClientComponent extends BaseComponent {
    public final StepperComponent stepper;
    public final UrlCheckerSharedComponent urlChecker;
    public final OffersCounterClientComponent offersCounter;
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
            lastOrderCardAddressTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_ADDRESS_TITLE)),
            lastOrderCardEquipmentTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_EQUIPMENT_TITLE)),
            lastOrderCardDateTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_DATE_TITLE)),
            lastOrderCardTimeTitleLocator = driver.$$(".section__row.row").findBy(text(LAST_ORDER_CARD_OBJECT_TIME_TITLE));

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
        lastOrderCardActionButtonLocator.shouldBe(visible).click();
        lastOrderCardOrderActionOpenLinkLocator.shouldBe(visible).click();
        return this;
    }

    public void checkFinishLoading() {
        stepWithRole("Убедиться, что секция Карточка последнего заказа отображается", () -> {
            lastOrderCardLocator.shouldBe(visible, Duration.ofSeconds(10));
        });
    }

    public void checkHasOfferRepairState(LastOrderInfoResponseDto currentOrderInfoDto) {
        LastOrderFields orderFields = LastOrderFields.builder()
                .title(LAST_ORDER_CARD_TITLE)
                .serviceType("Ремонт")
                .orderNumber(currentOrderInfoDto.getData().getNumber())
                .address(currentOrderInfoDto.getData().getAddress())
                .equipment(currentOrderInfoDto.getData().getEquipments().get(0).getComputedTitle())
//             todo   .date(currentOrderInfoDto.getObjectDate())
//                .time(currentOrderInfoDto.getObjectTime())
                .build();
        offersCounter.haveOffers(currentOrderInfoDto.getData().getClientObject().getActiveOffersCount());
        checkState(orderFields);
    }

    public void checkPublishedRepairState(LastOrderInfoResponseDto currentOrderInfoDto) {
        LastOrderFields publishedState = LastOrderFields.builder()
                .title(LAST_ORDER_CARD_TITLE)
                .serviceType("Ремонт")
                .orderNumber(currentOrderInfoDto.getData().getNumber())
                .address(currentOrderInfoDto.getData().getAddress())
                .equipment(currentOrderInfoDto.getData().getEquipments().get(0).getComputedTitle())
                .build();
        checkState(publishedState);
        offersCounter.noOffers();
    }

    public void checkState(LastOrderFields fields) {
        stepWithRole("Проверить состояние Карточки последнего заказа", () -> {
            lastOrderCardTitleLocator.shouldHave(text(fields.getTitle()));
            lastOrderCardOrderNumberLinkLocator.shouldHave(partialText(fields.getOrderNumber()));
            lastOrderCardServiceTypeTitleCollection.shouldHave(text(fields.getServiceType()));
            lastOrderCardAddressTitleLocator.shouldHave(text(fields.getAddress()));
            lastOrderCardEquipmentTitleLocator.shouldHave(text(fields.getEquipment()));
            lastOrderCardDateTitleLocator.shouldHave(text(fields.getDate()));
            lastOrderCardTimeTitleLocator.shouldHave(text(fields.getTime()));
            //todo stepper

        });
    }

    @Data
    @Builder
    private static class LastOrderFields {
        private final String title;
        private final String orderNumber;
        private final String serviceType;
        private final String address;
        private final String equipment;
        private final String date;
        private final String time;
        //todo stepper
    }
}