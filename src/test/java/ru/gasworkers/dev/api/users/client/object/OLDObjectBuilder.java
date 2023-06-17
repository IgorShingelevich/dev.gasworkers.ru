package ru.gasworkers.dev.api.users.client.object;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Deprecated
public class OLDObjectBuilder {
    private int id;
    private String title;
    private int activeOffersCount;
    private List<Integer> photos;
    private Integer accountNumber;
    private AddressDTO address;
    private String area;
    private String areaType;
    private String areaWithType;
    private String block;
    private String capitalMarker;
    private String city;
    private String cityType;
    private String cityWithType;
    private String country;
    private String countryCode;
    private String flat;
    private String full;
    private String fullWithZip;
    private String house;
    private String houseType;
    private String houseWithType;
    private double latitude;
    private double longitude;
    private String region;
    private String regionType;
    private String regionWithType;
    private String settlement;
    private String settlementType;
    private String settlementWithType;
    private String street;
    private String streetType;
    private String streetWithType;
    private String type;
    private String zip;
    private BranchDTO branch;
    private CompanyDTO company;
    private List<BranchDTO> branches;
    private long createdAt;
    private List<EquipmentDTO> equipments;
    private List<OrderDTO> orders;
    private boolean videoExists;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressDTO {
        private int id;
        private String full;
        private String type;
        @JsonProperty("full_with_zip")
        private String fullWithZip;
        private String zip;
        private String country;
        @JsonProperty("country_code")
        private String countryCode;
        private String region;
        @JsonProperty("region_type")
        private String regionType;
        @JsonProperty("region_with_type")
        private String regionWithType;
        private String area;
        @JsonProperty("area_type")
        private String areaType;
        @JsonProperty("area_with_type")
        private String areaWithType;
        private String city;
        @JsonProperty("city_type")
        private String cityType;
        @JsonProperty("city_with_type")
        private String cityWithType;
        private String settlement;
        @JsonProperty("settlement_type")
        private String settlementType;
        @JsonProperty("settlement_with_type")
        private String settlementWithType;
        private String street;
        @JsonProperty("street_type")
        private String streetType;
        @JsonProperty("street_with_type")
        private String streetWithType;
        private String house;
        @JsonProperty("house_type")
        private String houseType;
        @JsonProperty("house_with_type")
        private String houseWithType;
        private String block;
        private String flat;
        @JsonProperty("capital_marker")
        private String capitalMarker;
        private double longitude;
        private double latitude;
    }


    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BranchDTO {
        private int id;
        private String title;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyDTO {
        private int id;
        private String title;

        public static class CompanyDTOBuilder {
            // Other fields and methods
            private List<BranchDTO> branches;

            public CompanyDTOBuilder branches(List<BranchDTO> branches) {
                this.branches = branches;
                return this;
            }
        }
    }


    @Data
    @Builder
    @NoArgsConstructor
    public static class EquipmentDTO {
        // EquipmentDTO fields go here...
    }

    @Data
    @Builder
    @NoArgsConstructor
    public static class OrderDTO {
        // OrderDTO fields go here...
    }
}
