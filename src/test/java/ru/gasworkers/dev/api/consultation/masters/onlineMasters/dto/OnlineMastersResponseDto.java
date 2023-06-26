package ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class OnlineMastersResponseDto {
    private List<MasterDto> data;
    private Boolean refunded;
    @JsonProperty("last_price")
    private Integer lastPrice;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Builder
    public static class MasterDto {
        private Integer id;
        @JsonProperty("full_name")
        private String fullName;
        private String avatar;
        private Double rating;
        @JsonProperty("count_reviews")
        private Integer countReviews;
        @JsonProperty("consultation_price")
        private Integer consultationPrice;
        private CompanyDto company;
        private List<BrandDto> brands;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CompanyDto {
        private Integer id;
        private String title;
        @JsonProperty("short_title")
        private String shortTitle;
        private Double rating;
        private String logo;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BrandDto {
        private Integer id;
        private String title;
        private String logo;
    }
}

