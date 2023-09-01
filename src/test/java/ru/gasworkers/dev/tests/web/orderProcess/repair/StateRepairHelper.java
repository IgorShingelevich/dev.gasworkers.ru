package ru.gasworkers.dev.tests.web.orderProcess.repair;

import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
import ru.gasworkers.dev.api.orders.suggestedServices.dto.SuggestServicesResponseDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StateRepairHelper {

    public int getSuggestServiceSuggestedMasterCompanyRating(OrdersIdResponseDto.Data data, int masterIndex) {
        if (data != null && data.getMasters() != null && masterIndex >= 0 && masterIndex < data.getMasters().size()) {
            String ratingCompanyString = data.getMasters().get(masterIndex).getCompany().getRating();
            if (ratingCompanyString != null) {
                double ratingCompanyDouble = Double.parseDouble(ratingCompanyString);
                int ratingCompany = (int) Math.round(ratingCompanyDouble);
                if (ratingCompanyDouble >= 4.5 && ratingCompanyDouble < 5) {
                    ratingCompany = 5;
                }
                return ratingCompany;
            }
        }
        return 0; // Default value if masters list is empty or rating is null
    }

    public int getSuggestServiceSuggestedMasterCompanyRating(SuggestServicesResponseDto dto) {
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

    public String getServicePageOfferVisitPrice(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size()) {
            return dto.getData().getServices().get(offerIndex).getFirstAccept().toString();
        }
        return null; // Default value if services list is empty or index is out of bounds
    }

    public String getOfferedMasterFullName(SuggestServicesResponseDto dto, int offerIndex) {
        if (dto.getData().getServices() != null && offerIndex < dto.getData().getServices().size() &&
                dto.getData().getServices().get(offerIndex).getMaster() != null) {
            return dto.getData().getServices().get(offerIndex).getMaster().getFullName();
        }
        return null; // Default value if master is null or services list is empty or index is out of bounds
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

    /*public String desiredDateIntervalFormatter(String desiredDateStart, String desiredDateEnd) {
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
    }*/

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

    /*public String createdAtFormatter(String createdAt) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy 'год'", new Locale("ru"));

        try {
            Date date = inputFormat.parse(createdAt);
            String formattedDate = outputFormat.format(date);

            // Extract the day part from the formatted date
            String dayPart = formattedDate.split(" ")[0];

            // Remove leading zero from the day part if it exists
            if (dayPart.startsWith("0")) {
                dayPart = dayPart.substring(1);
            }

            // Reassemble the formatted date with the modified day part
            return dayPart + formattedDate.substring(formattedDate.indexOf(" "));

        } catch (ParseException e) {
            e.printStackTrace();
            return "Error parsing date";
        }
    }*/

    public String priceFormatter(String text) {
        if (text == null) {
            return null; // or return a default value if you prefer
        }
        String amount = text.replaceAll("[^0-9.]", "");
        return amount.split("\\.")[0];
    }

    public String convertRegisterDateFromStamp(int timestamp) {
        Date date = new Date((long) timestamp * 1000L); // Convert seconds to milliseconds
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy 'года'", new Locale("ru"));
        return "Зарегистрирован с " + outputFormat.format(date);
    }
}
