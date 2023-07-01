package ru.gasworkers.dev.api.users.client.house;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Define the package and imports

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Deprecated
public class AddHouseObjectModelDto {
    // Define the fields of the AddHouseObjectModelDto class

    private int id;
    private Address address;
    private String title;
    private Equipment[] equipments;

    public AddHouseObjectModelDto(Builder builder) {
        // Constructor to create an instance of AddHouseObjectModelDto from the Builder
        this.id = builder.id;
        this.address = builder.address;
        this.title = builder.title;
        this.equipments = builder.equipments;
    }

    public static Builder builder() {
        // Static method to create a new instance of the Builder class
        return new Builder();
    }

    public static class Builder {
        // Define the fields of the Builder class
        private int id;
        private Address address;
        private String title;
        private Equipment[] equipments;

        public Builder id(int id) {
            // Method to set the id field of the Builder class
            this.id = id;
            return this;
        }

        public Builder address(Address address) {
            // Method to set the address field of the Builder class
            this.address = address;
            return this;
        }

        public Builder title(String title) {
            // Method to set the title field of the Builder class
            this.title = title;
            return this;
        }

        public Builder equipments(Equipment[] equipments) {
            // Method to set the equipments field of the Builder class
            this.equipments = equipments;
            return this;
        }

        public AddHouseObjectModelDto build() {
            // Method to construct the final AddHouseObjectModelDto instance using the Builder
            return new AddHouseObjectModelDto(this);
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Address {
        // Define the fields of the Address class
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
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Type {
        // Define the fields of the Type class
        private int id;
        private int sortOrder;
        private String title;
        private String additionalParameter;
        private String note;
        private boolean powerRequired;
        private boolean includedInPriceList;
        private boolean isShowHidden;
        private Range[] ranges;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Brand {
        // Define the fields of the Brand class
        private int id;
        private String title;
        private boolean isForeign;
        private boolean isCustom;
        private boolean isShowHidden;
        private String logo;
        private long createdAt;
        private Type[] types;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Model {
        // Define the fields of the Model class
        @JsonView
        private int id;
        private String title;
        private String power;
        private Brand brand;
        private Type type;
        private boolean isCustom;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Equipment {
        // Define the fields of the Equipment class
        private int id;
        private boolean allWorks;
        private Type type;
        private Brand brand;
        private String title;
        private String computedTitle;
        private Integer power;
        private String[] photos;
        private String lastPhoto;
        private String[] videos;
        private Model model;
        private boolean videoExists;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonAutoDetect(fieldVisibility = Visibility.ANY)
    public static class Range {
        // Define the fields of the Range class
        private int id;
        private int fromValue;
        private Integer toValue;
        private String domesticPrice;
        private String foreignPrice;
    }
}
