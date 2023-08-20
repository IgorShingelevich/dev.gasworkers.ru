package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.Builder;
import lombok.Getter;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.ServiceType;

import java.util.List;

public class StateRepairBuilder {
    public StateRepairHelper helper = new StateRepairHelper();

    public LastOrderInfoData extractLastOrderInfoData(LastOrderInfoResponseDto dto) {
        return LastOrderInfoData.builder()
                .orderNumber(dto.getData().getNumber())
                .serviceType(ServiceType.REPAIR.toString())
                .address(dto.getData().getAddress())
                .equipments0(dto.getData().getEquipments().get(0).getComputedTitle())
                .offersCount(dto.getData().getOffersCount())
                .build();
    }

    public void extractMasterOrdersId(OrdersIdResponseDto.Data.Master master) {
        String masterFullName = master.getFullName();
        String masterAvatar = master.getAvatar();
        String masterCreatedAt = master.getCreatedAt();
        String masterReviewCount = String.valueOf(master.getReviewsCount());
        String masterRating = master.getRating();
    }

    public SuggestedMasterRepairCommonTabOrderCardComponent extractMastersOrdersId(OrdersIdResponseDto.Data.Masters suggestedMaster) {
        String suggestedMasterFullName = suggestedMaster.getFullName();
        String suggestedMasterAvatar = suggestedMaster.getAvatar();
        String suggestedMasterRegisterDate = suggestedMaster.getCreatedAt();
        String suggestedMasterRating = suggestedMaster.getRating();
        String suggestedMasterReviewCount = String.valueOf(suggestedMaster.getReviewsCount());
        String suggestedMasterShortWorkPlace = suggestedMaster.getCompany().getShortTitle();
        String suggestedMasterSkills = suggestedMaster.getSkills();
        Double suggestedMasterCompanyRatingStarsCount = Double.parseDouble(suggestedMaster.getCompany().getRating());
        Integer convertDoubleToInt = suggestedMasterCompanyRatingStarsCount.intValue();
        Integer suggestedMasterCertifiedEquipmentCount = suggestedMaster.getBrands().size();
        String suggestedMasterCompletedOrdersCount = String.valueOf(suggestedMaster.getCompletedOrdersCount());
        String suggestedMasterVisitPrice = suggestedMaster.getCompany().getFirstAccept().toString();

        return SuggestedMasterRepairCommonTabOrderCardComponent.builder()
                .suggestedMasterFullName(suggestedMasterFullName)
                .suggestedMasterAvatar(suggestedMasterAvatar)
                .suggestedMasterRegisterDate(helper.convertRegisterDateFromStamp(Integer.parseInt(suggestedMasterRegisterDate)))
                .suggestedMasterRating(suggestedMasterRating)
                .suggestedMasterReviewCount(suggestedMasterReviewCount)
                .suggestedMasterShortWorkPlace(suggestedMasterShortWorkPlace)
                .suggestedMasterSkills(suggestedMasterSkills)
                .suggestedMasterCompanyRatingStarsCount(convertDoubleToInt)
                .suggestedMasterCertifiedEquipmentCount(suggestedMasterCertifiedEquipmentCount)
                .suggestedMasterCompletedOrdersCount(suggestedMasterCompletedOrdersCount)
                .suggestedMasterVisitPrice(helper.priceFormatter(suggestedMasterVisitPrice))
                .build();
    }

    public OrderIdData extractOrdersIdData(OrdersIdResponseDto dto) {
        OrdersIdResponseDto.Data.Master master = dto.getData().getMaster();
        String masterFullName = (master != null) ? master.getFullName() : null;
        String masterAvatar = (master != null) ? master.getAvatar() : null;
        String masterCreatedAt = (master != null) ? master.getCreatedAt() : null;
        String masterRating = (master != null) ? master.getRating() : null;
        String masterReviewCount = (master != null) ? String.valueOf(master.getReviewsCount()) : null;


        List<OrdersIdResponseDto.Data.Receipts> receipts = dto.getData().getReceipts();
        Boolean activationIsPaid = (receipts != null && !receipts.isEmpty()) ? receipts.get(0).getPaid() : null;
        String activationPrice = (receipts != null && !receipts.isEmpty()) ? helper.priceFormatter(String.valueOf(receipts.get(0).getAmount())) : null;
        String activationDate = (receipts != null && !receipts.isEmpty()) ? helper.createdAtFormatter(receipts.get(0).getCreatedAt()) : null;

        OrdersIdResponseDto.Data.ServiceCenter serviceCenter = dto.getData().getServiceCenter();
        String companyFullName = (serviceCenter != null) ? serviceCenter.getTitle() : null;

        OrdersIdResponseDto.Data.Client client = dto.getData().getClient();
        String clientFullName = (client != null) ? client.getFullName() : null;


        return OrderIdData.builder()
                .orderNumber(dto.getData().getNumber())
                .activationIsPaid(activationIsPaid)
                .activationPrice(activationPrice)
                .activationDate(activationDate)
                .serviceType(ServiceType.REPAIR.toString())
                .clientFullName(clientFullName)
                .address(dto.getData().getClientObject().getAddress().getFull())
                .phone(dto.getData().getClient().getPhone().toString())
                .equipments0(dto.getData().getEquipments().get(0).getComputedTitle())
                .desiredDate(helper.desiredDateIntervalFormatter(dto.getData().getDesiredDateIntervalStartedAt(), dto.getData().getDesiredDateIntervalEndedAt()))
                .desiredTime(helper.desiredTimeIntervalFormatter(dto.getData().getDesiredTimeStarted(), dto.getData().getDesiredTimeEnded()))
                .description(dto.getData().getDescription())
                .masterFullName(masterFullName)
                .masterAvatar(masterAvatar)
                .masterRegisterDate(masterCreatedAt != null ? helper.convertRegisterDateFromStamp(Integer.parseInt(masterCreatedAt)) : null)
                .masterReviewCount(masterReviewCount)
                .masterRating(masterRating)
                .companyFullName(companyFullName)
                // todo .scheduledTime(desiredTimeIntervalFormatter(dto.getData().getScheduledTimeStarted(), dto.getData().getScheduledTimeEnded()))
                .build();
    }



    @Getter
    @Builder
    public static class SuggestedMasterRepairCommonTabOrderCardComponent {
        private final String suggestedMasterFullName;
        private final String suggestedMasterRegisterDate;
        private final String suggestedMasterAvatar;
        private final String suggestedMasterRating;
        private final String suggestedMasterReviewCount;
        private final String suggestedMasterShortWorkPlace;
        private final String suggestedMasterSkills;
        private final Integer suggestedMasterCompanyRatingStarsCount;
        private final Integer suggestedMasterCertifiedEquipmentCount;
        private final String suggestedMasterCompletedOrdersCount;
        private final String suggestedMasterVisitPrice;
    }

    @Getter
    @Builder
    public static class SelectedMasterRepairCommonTabOrderCardComponent {
        private final String selectedMasterFullName;
        private final String selectedMasterAvatar;
        private final String selectedMasterRegisterDate;
        private final String selectedMasterRating;
        private final String selectedMasterReviewCount;
        private final String selectedMasterShortWorkPlace;
        private final String selectedMasterSkills;
        private final Integer selectedMasterCompanyRatingStarsCount;
        private final Integer selectedMasterCertifiedEquipmentCount;
        private final String selectedMasterCompletedOrdersCount;
        private final String selectedMasterVisitPrice;
    }

    @Getter
    @Builder
    public static class LastOrderInfoData {
        private final String orderNumber;
        private final String serviceType;
        private final String address;
        private final String equipments0;
        private final Integer offersCount;

    }

    @Getter
    @Builder
    public static class OrderIdData {
        private final Integer offersCount;
        private final String orderNumber;
        private final Boolean activationIsPaid;
        private final String activationPrice;
        private final String activationDate;
        private final Boolean materialsIsPaid;
        private final String materialsPrice;
        private final String materialsDate;
        private final Boolean actionsIsPaid;
        private final String actionsPrice;
        private final String actionsDate;
        private final String serviceType;
        private final String clientFullName;
        private final String address;
        private final String phone;
        private final String equipments0;
        private final String desiredTime;
        private final String desiredDate;
        private final String description;
        private final String masterFullName;
        private final String masterAvatar;
        private final String masterRegisterDate;
        private final String masterReviewCount;
        private final String masterRating;
        private final String companyFullName;
        private final String scheduledTime;
    }
}
