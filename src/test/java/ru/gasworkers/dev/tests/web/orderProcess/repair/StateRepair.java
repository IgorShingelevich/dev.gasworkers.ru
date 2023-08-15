package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.pages.client.SelectServicePageClientPage;
import ru.gasworkers.dev.pages.components.clientComponent.LastOrderProfileClientComponent;

@AllArgsConstructor
public enum StateRepair {

    PUBLISHED("Опубликован"),
    HAS_OFFER("Есть предложения"),
    SCHEDULE_TIME("Согласование времени"),
    WAIT_MASTER("Ожидание мастера"),
    MASTER_START_WORK("Мастер приступил к работе"),
    MATERIAL_INVOICE_ISSUED("Выставлен счет на материалы"),
    MATERIAL_INVOICE_PAID("Оплачен счет на материалы"),
    ACTIONS_INVOICE_ISSUED("Выставлен счет на работы"),
    ACTIONS_INVOICE_PAID("Оплачен счет на работы"),
    MASTER_CREATE_ACT("Мастер создал акт"),
    CLIENT_SIGN_ACT("Клиент подписал акт");

    private final String state;


    public void checkLastOrderComponent(LastOrderProfileClientComponent page, LastOrderInfoResponseDto dto) {
        String
                orderNumber = dto.getData().getNumber(),
                serviceType = "Ремонт",
                address = dto.getData().getAddress(),
                equipments0 = dto.getData().getEquipments().get(0).getComputedTitle();
//        todo time and stepper
        Integer offersCount = dto.getData().getOffersCount();

        switch (this) {
            case PUBLISHED:
                page.checkOrderNumber(orderNumber);
                page.checkServiceType(serviceType);
                page.checkAddress(address);
                page.checkEquipment(equipments0);
                page.offersCounter.noOffers();
//        todo time and stepper
                break;
            case HAS_OFFER:
                page.checkOrderNumber(orderNumber);
                page.checkServiceType(serviceType);
                page.checkAddress(address);
                page.checkEquipment(equipments0);
                page.offersCounter.amount(offersCount);
//        todo time and stepper
                break;
            // ... other cases ...
        }
    }

    public void checkSelectServicePage(SelectServicePageClientPage page, SuggestServicesResponseDto dto) {
        int offerIndex = 0;
        int ratingCompany = (int) Math.round(Double.parseDouble(dto.getData().services.get(0).getRating()));
        int ratingMaster = (int) Math.round(Double.parseDouble(dto.getData().services.get(0).getMaster().getRating()));

// Apply custom rounding logic for ratingCompany
        double ratingCompanyDouble = Double.parseDouble(dto.getData().services.get(0).getRating());
        if (ratingCompanyDouble >= 4.5 && ratingCompanyDouble < 5) {
            ratingCompany = 5;
            //todo computedRatingStars
        }

// Apply custom rounding logic for ratingMaster
        double ratingMasterDouble = Double.parseDouble(dto.getData().services.get(0).getMaster().getRating());
        if (ratingMasterDouble >= 4.5 && ratingMasterDouble < 5) {
            ratingMaster = 5;
            //todo computedRatingStars
        }
        String visitPrice = dto.getData().getServices().get(offerIndex).getFirstAccept().toString(),
                fullMasterName = dto.getData().getServices().get(offerIndex).getMaster().getFullName(),
                masterAvatar = dto.getData().getServices().get(offerIndex).getMaster().getAvatar(),
                masterReviewCount = dto.getData().getServices().get(offerIndex).getMaster().getReviewsAsTargetCount().toString(),
                masterCompletedOrders = dto.getData().getServices().get(offerIndex).getMaster().getCountCompletedOrders().toString();


        //todo offerCount
        switch (this) {
            case PUBLISHED:
                page.offersCounter.noOffers();
                page.companyBoxRepair.checkNoOffers();
                break;
            case HAS_OFFER:
                page.offersCounter.amount(page.companyBoxRepair.getAmountOfferBox());
                page.companyBoxRepair.checkBoxTitle(offerIndex);
                page.companyBoxRepair.checkGeoTag(offerIndex);
                page.companyBoxRepair.checkRatingCompany(offerIndex, ratingCompany);
                page.companyBoxRepair.checkAvatarCompany(offerIndex);
                page.companyBoxRepair.checkVisitPrice(offerIndex, visitPrice);
                page.companyBoxRepair.checkNotificationPaymentAfterArrival(offerIndex);
                page.companyBoxRepair.checkFullNameMaster(offerIndex, fullMasterName);
                page.companyBoxRepair.checkAvatarMaster(offerIndex, masterAvatar);
                page.companyBoxRepair.checkRatingMaster(offerIndex, ratingMaster);
                page.companyBoxRepair.checkMasterReviewCount(offerIndex, masterReviewCount);
                page.companyBoxRepair.checkMasterCompletedOrders(offerIndex, masterCompletedOrders);
                page.companyBoxRepair.checkButtonActive(offerIndex);
                break;
            // ... other cases ...
        }
    }

    @Override
    public String toString() {
        return state;
    }
}
