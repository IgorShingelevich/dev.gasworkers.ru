package ru.gasworkers.dev.tests.web.orderProcess.repair;

import lombok.Builder;
import lombok.Getter;
import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;
import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
import ru.gasworkers.dev.model.ServiceType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StateRepairHelper {

    public LastOrderInfoData extractLastOrderInfoData(LastOrderInfoResponseDto dto) {
        return LastOrderInfoData.builder()
                .orderNumber(dto.getData().getNumber())
                .serviceType(ServiceType.REPAIR.toString())
                .address(dto.getData().getAddress())
                .equipments0(dto.getData().getEquipments().get(0).getComputedTitle())
                .offersCount(dto.getData().getOffersCount())
                .build();
    }

    public OrderIdData extractOrdersIdData(OrdersIdResponseDto dto) {
        return OrderIdData.builder()
                .orderNumber(dto.getData().getNumber())
                .activationIsPaid(dto.getData().getReceipts().get(0).getPaid())
                .activationAmount(amountFormatter(String.valueOf(dto.getData().getReceipts().get(0).getAmount())))
                .activationDate(createdAtFormatter(dto.getData().getReceipts().get(0).getCreatedAt()))
                .serviceType(ServiceType.REPAIR.toString())
                .clientFullName(dto.getData().getClient().getFullName())
                .address(dto.getData().getClientObject().getAddress().getFull())
                .phone(dto.getData().getClient().getPhone().toString())
                .equipments0(dto.getData().getEquipments().get(0).getComputedTitle())
                .desiredDate(desiredDateIntervalFormatter(dto.getData().getDesiredDateIntervalStartedAt(), dto.getData().getDesiredDateIntervalEndedAt()))
                .desiredTime(desiredTimeIntervalFormatter(dto.getData().getDesiredTimeStarted(), dto.getData().getDesiredTimeEnded()))
                .description(dto.getData().getDescription())
                .build();
    }

    public int getCalculateRatingCompany(SuggestServicesResponseDto dto) {
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

    public int getCalculateRatingMaster(SuggestServicesResponseDto dto) {
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

    private String desiredDateIntervalFormatter(String desiredDateStart, String desiredDateEnd) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));
        try {
            Date startDate = inputFormat.parse(desiredDateStart);
            Date endDate = inputFormat.parse(desiredDateEnd);

            String formattedStartDate = outputFormat.format(startDate);
            String formattedEndDate = outputFormat.format(endDate);

            return formattedStartDate + " - " + formattedEndDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing dates";
        }
    }

    private String desiredTimeIntervalFormatter(String desiredTimeStart, String desiredTimeEnd) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

        try {
            Date startTime = inputFormat.parse(desiredTimeStart);
            Date endTime = inputFormat.parse(desiredTimeEnd);

            String formattedStartTime = outputFormat.format(startTime);
            String formattedEndTime = outputFormat.format(endTime);

            return formattedStartTime + " - " + formattedEndTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing times";
        }
    }

    private String createdAtFormatter(String createdAt) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy 'год'", new Locale("ru"));

        try {
            Date date = inputFormat.parse(createdAt);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing date";
        }
    }

    private String amountFormatter(String text) {
        String amount = text.replaceAll("[^0-9.]", "");
        return amount.split("\\.")[0];
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
        private final String activationAmount;
        private final String activationDate;
        private final Boolean materialsIsPaid;
        private final String materialsAmount;
        private final String materialsDate;
        private final Boolean actionsIsPaid;
        private final String actionsAmount;
        private final String actionsDate;
        private final String serviceType;
        private final String clientFullName;
        private final String address;
        private final String phone;
        private final String equipments0;
        private final String desiredTime;
        private final String desiredDate;
        private final String description;
    }

}
