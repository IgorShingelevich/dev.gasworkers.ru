package ru.gasworkers.dev.api.users.client.house.getClientObjects.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetClientObjectResponseDto {
    private DataDto[] data;
    private Links links;
    private Meta meta;
    private Integer status;
    private String message;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class DataDto {
        private Integer id;
        private Address address;
        private String title;
        private Equipment[] equipments;
        private Object[] photos;
        private Object last_photo;
        private DistributionCompany distribution_company;
        private DistributionCompanyBranch distribution_company_branch;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Address {
        private Integer id;
        private String full;
        private String type;
        private String full_with_zip;
        private Object zip;
        private String country;
        private String country_code;
        private String region;
        private String region_type;
        private Object region_with_type;
        private Object area;
        private Object area_type;
        private Object area_with_type;
        private Object city;
        private Object city_type;
        private Object city_with_type;
        private Object settlement;
        private Object settlement_type;
        private Object settlement_with_type;
        private Object street;
        private Object street_type;
        private Object street_with_type;
        private Object house;
        private Object house_type;
        private Object house_with_type;
        private Object block;
        private Object flat;
        private Object capital_marker;
        private double longitude;
        private double latitude;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Equipment {
        private Integer id;
        private boolean all_works;
        private EquipmentType type;
        private Brand brand;
        private String title;
        private String computed_title;
        private Integer power;
        private Object[] photos;
        private Object last_photo;
        private Object[] videos;
        private Model model;
        private boolean video_exists;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EquipmentType {
        private Integer id;
        private Integer sort_order;
        private String title;
        private String additional_parameter;
        private Object note;
        private boolean power_required;
        private boolean included_in_price_list;
        private boolean is_show_hidden;
        private Range[] ranges;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Brand {
        private Integer id;
        private String title;
        private boolean is_foreign;
        private boolean is_custom;
        private boolean is_show_hidden;
        private String logo;
        private Integer created_at;
        private EquipmentType[] types;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class Range {
        private Integer id;
        private Integer from_value;
        private Integer to_value;
        private Object domestic_price;
        private Object foreign_price;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)

    public static class Model {
        private Integer id;
        private String title;
        private String power;
        private Brand brand;
        private EquipmentType type;
        private boolean is_custom;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class DistributionCompany {
        private Integer id;
        private String title;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class DistributionCompanyBranch {
        private Object id;
        private Object title;
        private Object account_number;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class Links {
        private String first;
        private String last;
        private Object prev;
        private Object next;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class Meta {
        private Integer current_page;
        private Integer from;
        private Integer last_page;
        private Link[] links;
        private String path;
        private Integer per_page;
        private Integer to;
        private Integer total;
    }

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    @AllArgsConstructor
    public static class Link {
        private Object url;
        private String label;
        private boolean active;
    }
}
