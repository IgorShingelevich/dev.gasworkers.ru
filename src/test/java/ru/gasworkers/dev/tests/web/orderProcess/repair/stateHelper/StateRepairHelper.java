package ru.gasworkers.dev.tests.web.orderProcess.repair.stateHelper;

import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StateRepairHelper {

    public int getServiceStars(OrdersIdResponseDto.Data data, int offerIndex) {
        if (data != null && data.getMasters() != null && offerIndex >= 0 && offerIndex < data.getMasters().size()) {
            Double ratingCompanyDouble = Double.valueOf(data.getMasters().get(offerIndex).getCompany().getRating());
            if (ratingCompanyDouble != null) {
                int ratingCompany = (int) ratingCompanyDouble.doubleValue();  // Truncate the decimal part
                return ratingCompany;
            }
        }
        return 0; // Default value if masters list is empty or rating is null
    }


    public int getMasterStars(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().services != null && offerIndex >= 0 && offerIndex < dto.getData().services.size()) {
            SuggestServicesResponseDto.Service.MasterSC master = dto.getData().services.get(offerIndex).getMaster();
            if (master != null) {
                Double ratingMasterDouble = Double.valueOf(master.getRating());
                if (ratingMasterDouble != null) {
                    int ratingMaster = (int) ratingMasterDouble.doubleValue();  // Truncate the decimal part
                    return ratingMaster;
                }
            }
        }
        return 0; // Default value if services list is empty or master or rating is null
    }

    public int getMasterStarsSimple(SuggestServicesResponseDto dto, int offerIndex) {
        return (int) Double.valueOf(dto.getData().services.get(offerIndex).getMaster().getRating()).doubleValue();
    }

    public int getServiceStarsSimple(SuggestServicesResponseDto dto, int offerIndex) {
        return (int) Double.valueOf(dto.getData().services.get(offerIndex).getRating()).doubleValue();
    }





    public String getServicePageOfferPossibleVisitPrice(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size()) {
//            return dto.getData().getServices().get(offerIndex).getFirstAccept().toString();
            return dto.getData().getServices().get(offerIndex).getPossibleFirstAcceptPrice().toString();

        }
        return null; // Default value if services list is empty or index is out of bounds
    }

    public String getServicePageOfferPossibleRepairPrice(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size()) {
//            return dto.getData().getServices().get(offerIndex).getFirstAccept().toString();
            return dto.getData().getServices().get(offerIndex).getPossibleFullRepairPrice().toString();
        }
        return null; // Default value if services list is empty or index is out of bounds
    }



    public String getOfferedMasterAvatar(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getAvatar();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getOfferedMasterReviewCount(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getReviewsAsTargetCount().toString();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getOfferedMasterCompletedOrders(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getCountCompletedOrders().toString();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String desiredDateIntervalFormatter(String desiredDateStart, String desiredDateEnd) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"));

        try {
            Date startDate = inputFormat.parse(desiredDateStart);
            Date endDate = inputFormat.parse(desiredDateEnd);

            String formattedStartDate = outputFormat.format(startDate);
            String formattedEndDate = outputFormat.format(endDate);

            String startDayPart = formattedStartDate.split(" ")[0];
            String endDayPart = formattedEndDate.split(" ")[0];

            if (startDayPart.startsWith("0")) {
                startDayPart = startDayPart.substring(1);
            }

            if (endDayPart.startsWith("0")) {
                endDayPart = endDayPart.substring(1);
            }

            return startDayPart + formattedStartDate.substring(formattedStartDate.indexOf(" ")) + " - " + endDayPart + formattedEndDate.substring(formattedEndDate.indexOf(" "));
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing dates";
        }
    }

    public String desiredTimeIntervalFormatter(String desiredTimeStart, String desiredTimeEnd) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
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

    public String createdAtFormatter(String createdAt) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMMM yyyy 'год'", new Locale("ru"));

        try {
            Date date = inputFormat.parse(createdAt);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing date";
        }
    }

    public String priceFormatter(String text) {
        if (text == null) {
            return null; // or return a default value if you prefer
        }
        String amount = text.replaceAll("[^0-9.]", "");
        return amount.split("\\.")[0];
    }

    public String convertRegisterDateFromStamp(int timestamp) {
        Date date = new Date((long) timestamp * 1000L); // Convert seconds to milliseconds
        SimpleDateFormat outputFormat = new SimpleDateFormat("d MMMM yyyy 'года'", new Locale("ru"));
        return "Зарегистрирован с " + outputFormat.format(date);
    }

    public String getOfferedMasterFullName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getFullName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }


    public String getOfferMasterFirstName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getFirstName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getOfferMasterLastName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getLastName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }

    public String getOfferMasterMiddleName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getMiddleName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
    }
}
