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
            private Integer equipmentsId;
            @JsonProperty("all_works")
            private Boolean equipmentsAllWorks;
            @JsonProperty("equipment_type_id")
            private EquipmentBrandDto.EquipmentBrandTypeDto equipmentsType;
            @JsonProperty("brand")
            private EquipmentBrandDto equipmentsBrand;
            @JsonProperty("title")
            private String equipmentsTitle;
            @JsonProperty("computed_title")
            private String equipmentsComputedTitle;
            @JsonProperty("power")
            private Integer equipmentsPower;
            @JsonProperty("photos")
            private List<String> equipmentsPhotos;
            @JsonProperty("last_photo")
            private String equipmentsLastPhoto;
            @JsonProperty("videos")
            private List<VideoDto> equipmentsVideos;
            @JsonProperty("model")
            private EquipmentModelDto equipmentsModel;
            @JsonProperty("video_exists")
            private Boolean equipmentsVideoExists;
            @JsonProperty("type")
            private EquipmentsTypeDto equipmentsTypeDto;

            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class EquipmentsTypeDto {
                /*"type": {
                    "id": 1,
                    "sort_order": 100,
                    "title": "Газовый котел",
                    "additional_parameter": "Мощность",
                    "note": null,
                    "power_required": true,
                    "included_in_price_list": true,
                    "is_show_hidden": false,
                    "ranges": [
                        {
                            "id": 1,
                            "from_value": 0,
                            "to_value": 20,
                            "domestic_price": null,
                            "foreign_price": null
                        },
                        {
                            "id": 2,
                            "from_value": 21,
                            "to_value": 50,
                            "domestic_price": null,
                            "foreign_price": null
                        },
                        {
                            "id": 3,
                            "from_value": 51,
                            "to_value": 100,
                            "domestic_price": null,
                            "foreign_price": null
                        },
                        {
                            "id": 4,
                            "from_value": 100,
                            "to_value": null,
                            "domestic_price": null,
                            "foreign_price": null
                        }
                    ]
                },*/
                @JsonProperty("id")
                private Integer typeId;
                @JsonProperty("sort_order")
                private Integer typeSortOrder;
                @JsonProperty("title")
                private String typeTitle;
                @JsonProperty("additional_parameter")
                private String typeAdditionalParameter;
                @JsonProperty("note")
                private String typeNote;
                @JsonProperty("power_required")
                private Boolean typePowerRequired;
                @JsonProperty("included_in_price_list")
                private Boolean typeIncludedInPriceList;
                @JsonProperty("is_show_hidden")
                private Boolean typeIsShowHidden;
                @JsonProperty("ranges")
                private List<RangesDto> typeRanges;

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                public static class RangesDto {
                    @JsonProperty("id")
                    private Integer rangesId;
                    @JsonProperty("from_value")
                    private Integer rangesFromValue;
                    @JsonProperty("to_value")
                    private Integer rangesToValue;
                    @JsonProperty("domestic_price")
                    private Integer rangesDomesticPrice;
                    @JsonProperty("foreign_price")
                    private Integer rangesForeignPrice;
                }

            }


            @Data
            @Builder
            @AllArgsConstructor
            @NoArgsConstructor
            public static class EquipmentBrandDto {
                @JsonProperty("id")
                private Integer brandId;
                @JsonProperty("is_show_hidden")
                private Boolean brandIsShowHidden;
                @JsonProperty("title")
                private String brandTitle;
                @JsonProperty("is_foreign")
                private Boolean brandIsForeign;
                @JsonProperty("is_custom")
                private Boolean brandIsCustom;
                @JsonProperty("logo")
                private String brandLogo;
                @JsonProperty("created_at")
                private Long brandCreatedAt;
                @JsonProperty("types")
                private List<EquipmentBrandTypeDto> brandTypes;

                @Data
                @Builder
                @AllArgsConstructor
                @NoArgsConstructor
                public static class EquipmentBrandTypeDto {
                    @JsonProperty("id")
                    private Integer typeId;
                    @JsonProperty("sort_order")
                    private Integer typeSortOrder;
                    @JsonProperty("title")
                    private String typeTitle;
                    @JsonProperty("additional_parameter")
                    private String typeAdditionalParameter;
                    @JsonProperty("note")
                    private String typeNote;
                    @JsonProperty("power_required")
                    private Boolean typePowerRequired;
                    @JsonProperty("included_in_price_list")
                    private Boolean typeIncludedInPriceList;
                    @JsonProperty("is_show_hidden")
                    private Boolean typeIsShowHidden;
                    @JsonProperty("ranges")
                    private List<Object> typeRanges;
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
