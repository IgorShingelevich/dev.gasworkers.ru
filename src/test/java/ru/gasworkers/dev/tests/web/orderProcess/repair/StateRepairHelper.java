package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;

public class StateRepairHelper {

    public OrderData extractOrderData(LastOrderInfoResponseDto dto) {
        String orderNumber = dto.getData().getNumber();
        String serviceType = "Ремонт";
        String address = dto.getData().getAddress();
        String equipments0 = dto.getData().getEquipments().get(0).getComputedTitle();
        Integer offersCount = dto.getData().getOffersCount();

        return new OrderData(orderNumber, serviceType, address, equipments0, offersCount);
    }

    public int calculateRatingCompany(SuggestServicesResponseDto dto) {
        if (dto.getData().services != null && !dto.getData().services.isEmpty()) {
            String ratingCompanyString = dto.getData().services.get(0).getRating();
            if (ratingCompanyString != null) {
                double ratingCompanyDouble = Double.parseDouble(ratingCompanyString);
                int ratingCompany = (int) Math.round(ratingCompanyDouble);
                if (ratingCompanyDouble >= 4.5 && ratingCompanyDouble < 5) {
                    ratingCompany = 5;
                }
                return ratingCompany;
            }
        }
        return 0; // Default value if services list is empty or rating is null
    }

    public int calculateRatingMaster(SuggestServicesResponseDto dto) {
        if (dto.getData().services != null && !dto.getData().services.isEmpty()) {
            SuggestServicesResponseDto.Service.MasterSC master = dto.getData().services.get(0).getMaster();
            if (master != null) {
                String ratingMasterString = master.getRating();
                if (ratingMasterString != null) {
                    double ratingMasterDouble = Double.parseDouble(ratingMasterString);
                    int ratingMaster = (int) Math.round(ratingMasterDouble);
                    if (ratingMasterDouble >= 4.5 && ratingMasterDouble < 5) {
                        ratingMaster = 5;
                    }
                    return ratingMaster;
                }
            }
        }
        return 0; // Default value if services list is empty or master or rating is null
    }

    public String getVisitPrice(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size()) {
            return dto.getData().getServices().get(offerIndex).getFirstAccept().toString();
        }
        return null; // Default value if services list is empty or index is out of bounds
    }

    public String getMasterFullName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getFullName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getMasterAvatar(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getAvatar();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getMasterReviewCount(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getReviewsAsTargetCount().toString();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getMasterCompletedOrders(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getCountCompletedOrders().toString();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    @Getter
    @AllArgsConstructor
    public static class OrderData {
        private final String orderNumber;
        private final String serviceType;
        private final String address;
        private final String equipments0;
        private final Integer offersCount;
    }
}
