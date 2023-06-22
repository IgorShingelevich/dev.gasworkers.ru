package ru.gasworkers.dev.api.users.client.object.addObject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddHouseObjectResponseDTO {
    private Integer status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        private Integer id;
        private String title;
        @JsonProperty("active_offers_count")
        private Integer activeOffersCount;
        private Object[] photos;
        private AddressDto address;
        private Object[] equipments;
        private Object branch;
        private CompanyDto company;
        @JsonProperty("account_number")
        private Object accountNumber;
        @JsonProperty("video_exists")
        private Boolean videoExists;
        @JsonProperty("created_at")
        private Integer createdAt;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
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
        @JsonProperty("region_with_type")
        private Object regionWithType;
        private Object area;
        @JsonProperty("area_type")
        private Object areaType;
        @JsonProperty("area_with_type")
        private Object areaWithType;
        private Object city;
        @JsonProperty("city_type")
        private Object cityType;
        @JsonProperty("city_with_type")
        private Object cityWithType;
        private Object settlement;
        @JsonProperty("settlement_type")
        private Object settlementType;
        @JsonProperty("settlement_with_type")
        private Object settlementWithType;
        private Object street;
        @JsonProperty("street_type")
        private Object streetType;
        @JsonProperty("street_with_type")
        private Object streetWithType;
        private Object house;
        @JsonProperty("house_type")
        private Object houseType;
        @JsonProperty("house_with_type")
        private Object houseWithType;
        private Object block;
        private Object flat;
        @JsonProperty("capital_marker")
        private Object capitalMarker;
        private Double longitude;
        private Double latitude;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyDto {
        private Integer id;
        private String title;
        private BranchDto[] branches;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BranchDto {
        private Integer id;
        private String title;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorsDto {
        @JsonProperty("address_id")
        private String[] addressIdErrors;
        @JsonProperty ("company_id")
        private String[] companyIdErrors;
        private String[] titleErrors;
        @JsonProperty("branch_id")
        private String[] branchIdErrors;

    }

    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO missingAddressId() {
        /*{
  "message": "Поле address id обязательно для заполнения.",
  "errors": {
    "address_id": [
      "Поле address id обязательно для заполнения."
    ]
  }
}*/
        ErrorsDto errors = new ErrorsDto();
        errors.setAddressIdErrors(new String[]{"Поле address id обязательно для заполнения."});

        return new AddHouseObjectResponseDTO(1, "Поле address id обязательно для заполнения.", null, errors);
    }
    @JsonIgnoreProperties(value = {"status"})

    public static AddHouseObjectResponseDTO missingCompanyId() {
        /*{
            "message": "Поле company id обязательно для заполнения.",
            "errors": {
                "company_id": [
                    "Поле company id обязательно для заполнения."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setCompanyIdErrors(new String[]{"Поле company id обязательно для заполнения."});
        return new AddHouseObjectResponseDTO(1, "Поле company id обязательно для заполнения.", null, errors);
    }
    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO missingTitle() {
        /*{
            "message": "Поле Название обязательно для заполнения.",
            "errors": {
                "title": [
                    "Поле Название обязательно для заполнения."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setTitleErrors(new String[]{"Поле Название обязательно для заполнения."});
        return new AddHouseObjectResponseDTO(1, "Поле Название обязательно для заполнения.", null, errors);
    }
    public static AddHouseObjectResponseDTO missingBranchId() {
        return new AddHouseObjectResponseDTO(1, "Не указан филиал", null, null);
    }
    public static AddHouseObjectResponseDTO missingAccountNumber() {
        return new AddHouseObjectResponseDTO(1, "Не указан лицевой счет", null, null);
    }
    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO invalidAddressId() {
        /*{
            "message": "Выбранное значение для address id некорректно.",
            "errors": {
                "address_id": [
                    "Выбранное значение для address id некорректно."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setAddressIdErrors(new String[]{"Выбранное значение для address id некорректно."});
        return new AddHouseObjectResponseDTO(1, "Выбранное значение для address id некорректно.", null, errors);
    }
    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO invalidCompanyId() {
        /*{
            "message": "Выбранное значение для company id некорректно.",
            "errors": {
                "company_id": [
                    "Выбранное значение для company id некорректно."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setCompanyIdErrors(new String[]{"Выбранное значение для company id некорректно."});
        return new AddHouseObjectResponseDTO(1, "Выбранное значение для company id некорректно.", null, errors);
    }
    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO invalidBranchId() {
        /*{
            "message": "Выбранное значение для branch id некорректно.",
            "errors": {
                "branch_id": [
                    "Выбранное значение для branch id некорректно."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setBranchIdErrors(new String[]{"Выбранное значение для branch id некорректно."});
        return new AddHouseObjectResponseDTO(1, "Выбранное значение для branch id некорректно.", null, errors);
    }
    public static AddHouseObjectResponseDTO invalidAccountNumber() {
        return new AddHouseObjectResponseDTO(1, "Неверный лицевой счет", null, null);
    }
    @JsonIgnoreProperties(value = {"status"})
    public static AddHouseObjectResponseDTO invalidTitle() {
        /*{
            "message": "Поле Название обязательно для заполнения.",
            "errors": {
                "title": [
                    "Поле Название обязательно для заполнения."
                ]
            }
        }*/
        ErrorsDto errors = new ErrorsDto();
        errors.setTitleErrors(new String[]{"Поле Название обязательно для заполнения."});
        return new AddHouseObjectResponseDTO(1, "Поле Название обязательно для заполнения.", null, errors);
    }

    public static AddHouseObjectResponseDTO successResponse() {
        DataDto data = new DataDto();
        data.setId(null);
        data.setTitle("my house");
        data.setActiveOffersCount(0);
        data.setPhotos(new Object[]{});

        AddressDto address = new AddressDto();
        address.setId(2121);
        address.setFull("Москва, Московская улица");
        address.setType("street");
        address.setFullWithZip("Россия, Москва, Московская улица");
        address.setZip(null);
        address.setCountry("Россия");
        address.setCountryCode("RU");
        address.setRegion("Москва");
        address.setRegionType("Москва");
        address.setLongitude(37.416161);
        address.setLatitude(55.671229);

        data.setAddress(address);
        data.setEquipments(new Object[]{});

        CompanyDto company = new CompanyDto();
        company.setId(1);
        company.setTitle("МособлГаз");

        BranchDto[] branches = {
                new BranchDto(1, "Юг"),
                new BranchDto(2, "Север"),
                new BranchDto(3, "Восток"),
                new BranchDto(4, "Запад"),
                new BranchDto(13, "Юго-Восток"),
                new BranchDto(14, "Северо-Запад")
        };
        company.setBranches(branches);

        data.setCompany(company);
        data.setAccountNumber(null);
        data.setVideoExists(false);
        data.setCreatedAt(null);

        return new AddHouseObjectResponseDTO(0, "Объект добавлен", data, null);
    }
}
