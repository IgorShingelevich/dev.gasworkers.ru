package ru.gasworkers.dev.api.users.client.lastOrderInfo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gasworkers.dev.api.users.client.house.dto.HouseResponseDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LastOrderInfoResponseDto {

    private Integer status;
    private String message;
    private DataDto data;

    public static LastOrderInfoResponseDto noLastOrderResponse() {
        return LastOrderInfoResponseDto.builder()
                .status(0)
                .message("Информация о последнем заказе")
                .data(null)
                .build();
    }


    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        private Integer id;
        private String number;
        private String type;
        private String status;
        private String address;
        private List<EquipmentsDto> equipments;
        @JsonProperty("desired_date_interval_started_at")
        private String desiredDateIntervalStartedAt;
        @JsonProperty("desired_date_interval_ended_at")
        private String desiredDateIntervalEndedAt;
        @JsonProperty("desired_time_started")
        private String desiredTimeStarted;
        @JsonProperty("desired_time_ended")
        private String desiredTimeEnded;
        @JsonProperty("selected_date")
        private String selectedDate;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("creation_status")
        private String creationStatus;
        @JsonProperty("review_left")
        private Boolean reviewLeft;
        private HouseResponseDto.DataDto clientObject;
        @JsonProperty("offers_count")
        private Integer offersCount;
        @JsonProperty("approved_offer")
        private Boolean approvedOffer;
        @JsonProperty("not_want_to_pay")
        private Boolean notWantToPay;
        @JsonProperty("not_want_to_sign")
        private Boolean notWantToSign;
        @JsonProperty("additional_status")
        private String additionalStatus;
        @JsonProperty("is_insurance_case")
        private Boolean isInsuranceCase;
        private List<HistoryDto> history;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class HistoryDto {
            @JsonProperty("id")
            private Integer historyId;
            @JsonProperty("action")
            private String historyAction;
            @JsonProperty("src_title")
            private String historySrcTitle;
            @JsonProperty("title")
            private String historyTitle;
            @JsonProperty("description")
            private String historyDescription;
            @JsonProperty("set_at")
            private String historySetAt;
            @JsonProperty("completed_at")
            private String historyCompletedAt;
            @JsonProperty("completed")
            private Boolean historyCompleted;
            @JsonProperty("is_current")
            private Boolean historyIsCurrent;
        }

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class EquipmentsDto {
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("all_works")
            private Boolean allWorks;
            @JsonProperty("equipment_type_id")
            private EquipmentBrandDto.EquipmentBrandTypeDto type;
            @JsonProperty("brand")
            private EquipmentBrandDto brand;
            @JsonProperty("title")
            private String title;
            @JsonProperty("computed_title")
            private String computedTitle;
            @JsonProperty("power")
            private Integer power;
            @JsonProperty("photos")
            private List<String> photos;
            @JsonProperty("last_photo")
            private String lastPhoto;
            @JsonProperty("videos")
            private List<VideoDto> videos;
            @JsonProperty("model")
            private EquipmentModelDto model;
            @JsonProperty("video_exists")
            private Boolean videoExists;
            @JsonProperty("type")
            private EquipmentsTypeDto typeDto;

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class EquipmentsTypeDto {

                @JsonProperty("id")
                private Integer id;
                @JsonProperty("sort_order")
                private Integer sortOrder;
                @JsonProperty("title")
                private String title;
                @JsonProperty("additional_parameter")
                private String additionalParameter;
                @JsonProperty("note")
                private String note;
                @JsonProperty("power_required")
                private Boolean powerRequired;
                @JsonProperty("included_in_price_list")
                private Boolean includedInPriceList;
                @JsonProperty("is_show_hidden")
                private Boolean isShowHidden;
                @JsonProperty("ranges")
                private List<RangesDto> ranges;

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                public static class RangesDto {
                    @JsonProperty("id")
                    private Integer id;
                    @JsonProperty("from_value")
                    private Integer fromValue;
                    @JsonProperty("to_value")
                    private Integer toValue;
                    @JsonProperty("domestic_price")
                    private Integer domesticPrice;
                    @JsonProperty("foreign_price")
                    private Integer foreignPrice;
                }

            }


            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class EquipmentBrandDto {
                @JsonProperty("id")
                private Integer id;
                @JsonProperty("is_show_hidden")
                private Boolean isShowHidden;
                @JsonProperty("title")
                private String title;
                @JsonProperty("is_foreign")
                private Boolean isForeign;
                @JsonProperty("is_custom")
                private Boolean isCustom;
                @JsonProperty("logo")
                private String logo;
                @JsonProperty("created_at")
                private Long createdAt;
                @JsonProperty("types")
                private List<EquipmentBrandTypeDto> types;

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                public static class EquipmentBrandTypeDto {
                    @JsonProperty("id")
                    private Integer id;
                    @JsonProperty("sort_order")
                    private Integer sortOrder;
                    @JsonProperty("title")
                    private String title;
                    @JsonProperty("additional_parameter")
                    private String additionalParameter;
                    @JsonProperty("note")
                    private String note;
                    @JsonProperty("power_required")
                    private Boolean powerRequired;
                    @JsonProperty("included_in_price_list")
                    private Boolean includedInPriceList;
                    @JsonProperty("is_show_hidden")
                    private Boolean isShowHidden;
                    @JsonProperty("ranges")
                    private List<Object> ranges;
                }
            }

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class VideoDto {
                private Integer id;
                @JsonProperty("original_name")
                private String originalName;
                private String title;
                private String path;
                private String extension;
                private String preview;
            }

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class EquipmentModelDto {
                private Integer id;
                private String title;
                private Integer power;
                private EquipmentBrandDto brand;
                private EquipmentBrandDto.EquipmentBrandTypeDto type;
                @JsonProperty("is_custom")
                private Boolean isCustom;
            }
        }
    }
}
