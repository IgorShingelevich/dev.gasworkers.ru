package ru.gasworkers.dev.api.users.client.object.addObject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddHouseObjectResponseDTO {
    private Integer status;
    private String message;
    private DataDto data;
    private ErrorsDto errors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
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
    @Builder
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
    @Builder
    public static class CompanyDto {
        private Integer id;
        private String title;
        private BranchDto[] branches;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BranchDto {
        private Integer id;
        private String title;

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ErrorsDto {
        @JsonProperty("address_id")
        private String[] addressIdErrors;
        @JsonProperty("company_id")
        private String[] companyIdErrors;
        @JsonProperty("title")
        private String[] titleErrors;
        @JsonProperty("branch_id")
        private String[] branchIdErrors;
    }

    public static AddHouseObjectResponseDTO missingAllFields() {
        ErrorsDto errors = ErrorsDto.builder()
                .addressIdErrors(new String[]{"Поле address id обязательно для заполнения."})
                .companyIdErrors(new String[]{"Поле company id обязательно для заполнения."})
                .titleErrors(new String[]{"Поле Название обязательно для заполнения."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Поле address id обязательно для заполнения. (и еще 2 ошибки)")
                .build();
    }

    public static AddHouseObjectResponseDTO missingAddressId() {
        ErrorsDto errors = ErrorsDto.builder()
                .addressIdErrors(new String[]{"Поле address id обязательно для заполнения."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Поле address id обязательно для заполнения.")
                .build();
    }

    public static AddHouseObjectResponseDTO missingCompanyId() {
        ErrorsDto errors = ErrorsDto.builder()
                .companyIdErrors(new String[]{"Поле company id обязательно для заполнения."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Поле company id обязательно для заполнения.")
                .build();
    }

    public static AddHouseObjectResponseDTO missingTitle() {
        ErrorsDto errors = ErrorsDto.builder()
                .titleErrors(new String[]{"Поле Название обязательно для заполнения."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Поле Название обязательно для заполнения.")
                .build();
    }

    public static AddHouseObjectResponseDTO missingBranchId() {
        return AddHouseObjectResponseDTO.builder()
                .message("Не указан филиал")
                .build();
    }

    public static AddHouseObjectResponseDTO missingAccountNumber() {
        return AddHouseObjectResponseDTO.builder()
                .message("Не указан лицевой счет")
                .build();
    }

    public static AddHouseObjectResponseDTO invalidAddressId() {
        ErrorsDto errors = ErrorsDto.builder()
                .addressIdErrors(new String[]{"Выбранное значение для address id некорректно."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Выбранное значение для address id некорректно.")
                .build();
    }

    public static AddHouseObjectResponseDTO invalidCompanyId() {
        ErrorsDto errors = ErrorsDto.builder()
                .companyIdErrors(new String[]{"Выбранное значение для company id некорректно."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Выбранное значение для company id некорректно.")
                .build();
    }

    public static AddHouseObjectResponseDTO invalidBranchId() {
        ErrorsDto errors = ErrorsDto.builder()
                .branchIdErrors(new String[]{"Выбранное значение для branch id некорректно."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Выбранное значение для branch id некорректно.")
                .build();
    }

    public static AddHouseObjectResponseDTO invalidAccountNumber() {
        return AddHouseObjectResponseDTO.builder()
                .message("Неверный лицевой счет")
                .build();
    }

    public static AddHouseObjectResponseDTO invalidTitle() {
        ErrorsDto errors = ErrorsDto.builder()
                .titleErrors(new String[]{"Поле Название обязательно для заполнения."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Поле Название обязательно для заполнения.")
                .build();
    }

    public static AddHouseObjectResponseDTO successResponse() {
        AddressDto address = AddressDto.builder()
                .id(2121)
                .full("Москва, Московская улица")
                .type("street")
                .fullWithZip("Россия, Москва, Московская улица")
                .zip(null)
                .country("Россия")
                .countryCode("RU")
                .region("Москва")
                .regionType("Москва")
                .longitude(37.416161)
                .latitude(55.671229)
                .build();

        BranchDto[] branches = {
                BranchDto.builder().id(1).title("Юг").build(),
                BranchDto.builder().id(2).title("Север").build(),
                BranchDto.builder().id(3).title("Восток").build(),
                BranchDto.builder().id(4).title("Запад").build(),
                BranchDto.builder().id(13).title("Юго-Восток").build(),
                BranchDto.builder().id(14).title("Северо-Запад").build()
        };

        CompanyDto company = CompanyDto.builder()
                .id(1)
                .title("МособлГаз")
                .branches(branches)
                .build();

        DataDto data = DataDto.builder()
                .id(null)
                .title("my house")
                .activeOffersCount(0)
                .photos(new Object[]{})
                .address(address)
                .equipments(new Object[]{})
                .company(company)
                .accountNumber(null)
                .videoExists(false)
                .createdAt(null)
                .build();

        return AddHouseObjectResponseDTO.builder()
                .status(0)
                .message("Объект добавлен")
                .data(data)
                .build();
    }

    public static AddHouseObjectResponseDTO invalidExceedSymbolsLimitTitle() {
        ErrorsDto errors = ErrorsDto.builder()
                .titleErrors(new String[]{"Количество символов в поле Название не может превышать 255."})
                .build();
        return AddHouseObjectResponseDTO.builder()
                .errors(errors)
                .message("Количество символов в поле Название не может превышать 255.")
                .build();
    }

}
