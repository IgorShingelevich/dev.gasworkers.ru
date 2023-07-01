package ru.gasworkers.dev.api.users.client.house.getEquipments.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class GetEquipmentsResponseDto {
    private String status;
    private String message;
    private List<DataDto> data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
    @Accessors(chain = true)
    public static class DataDto {
        private Integer id;
        @JsonProperty("all_works")
        private Boolean allWorks;
        private TypeDto type;
        private BrandDto brand;
        private String title;
        @JsonProperty("computed_title")
        private String computedTitle;
        private Integer power;
        private PhotosDto photos;
        @JsonProperty("last_photo")
        private Object lastPhoto;
        private VideosDto videos;
        private ModelDto model;
        @JsonProperty("video_exists")
        private Boolean videoExists;
        private AddressDto address;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Accessors(chain = true)
        public static class AddressDto {
            private Integer id;
            private String full;
            private String type;
            @JsonProperty("full_with_zip")
            private String fullWithZip;
            private Object zip;
            private String country;
            @JsonProperty("country_code")
            private String countryCode;
            private String region;
            @JsonProperty("region_type")
            private String regionType;
        }


        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
        @Accessors(chain = true)
        public static class TypeDto {
            private Integer id;
            @JsonProperty("sort_order")
            private Integer sortOrder;
            private String title;
            @JsonProperty("additional_parameter")
            private String additionalParameter;
            private String note;
            @JsonProperty("power_required")
            private Boolean powerRequired;
            @JsonProperty("included_in_price_list")
            private Boolean includedInPriceList;
            private Object ranges;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
        @Accessors(chain = true)
        public static class BrandDto {
            private Integer id;
            private String title;
            @JsonProperty("is_foreign")
            private Boolean isForeign;
            @JsonProperty("is_custom")
            private Boolean isCustom;
            private String logo;
            @JsonProperty("created_at")
            private Integer createdAt;
            private TypesBrandDto types;

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
            @Accessors(chain = true)
            public static class TypesBrandDto {
                private Integer id;
                @JsonProperty("sort_order")
                private Integer sortOrder;
                private String title;
                @JsonProperty("additional_parameter")
                private String additionalParameter;
                private String note;
                @JsonProperty("power_required")
                private Boolean powerRequired;
                @JsonProperty("included_in_price_list")
                private Boolean includedInPriceList;
                private Object ranges;
            }
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
        @Accessors(chain = true)
        public static class VideosDto {
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
        @NoArgsConstructor
//        @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
        @Accessors(chain = true)
        public static class PhotosDto {
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
        @Accessors(chain = true)
        public static class ModelDto {
            private Integer id;
            private String title;
            private Integer power;
            private BrandDto brand;
            private BrandDto.TypesBrandDto types;
        }
    }
}
