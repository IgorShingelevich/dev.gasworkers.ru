package ru.gasworkers.dev.api.users.client.object.addObject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

        private Integer active_offers_count;

        private Object[] photos;

        private AddressDto address;

        private Object[] equipments;

        private Object branch;

        private CompanyDto company;
        private Object account_number;
        private Boolean video_exists;
        private Integer created_at;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressDto {
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
        private String[] errors;

    }
    // Success Response
    public static AddHouseObjectResponseDTO successResponse() {
        DataDto data = new DataDto();
        data.setId(null); // Set id field to null
        data.setTitle("my house");
        data.setActive_offers_count(0);
        data.setPhotos(new Object[]{});

        AddressDto address = new AddressDto();
        address.setId(2121);
        address.setFull("Москва, Московская улица");
        address.setType("street");
        address.setFull_with_zip("Россия, Москва, Московская улица");
        address.setZip(null);
        address.setCountry("Россия");
        address.setCountry_code("RU");
        address.setRegion("Москва");
        address.setRegion_type("Москва");
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
        data.setAccount_number(null);
        data.setVideo_exists(false);
        data.setCreated_at(null); // Set created_at field to null

        return new AddHouseObjectResponseDTO(0, "Объект добавлен", data, null);
    }
    public static AddHouseObjectResponseDTO missingAddressId() {
        return new AddHouseObjectResponseDTO(1, "Не указан адрес", null, null);
    }
    public static AddHouseObjectResponseDTO missingCompanyId() {
        return new AddHouseObjectResponseDTO(1, "Не указана компания", null, null);
    }
    public static AddHouseObjectResponseDTO missingTitle() {
        return new AddHouseObjectResponseDTO(1, "Не указано название объекта", null, null);
    }
    public static AddHouseObjectResponseDTO missingBranchId() {
        return new AddHouseObjectResponseDTO(1, "Не указан филиал", null, null);
    }
    public static AddHouseObjectResponseDTO missingAccountNumber() {
        return new AddHouseObjectResponseDTO(1, "Не указан лицевой счет", null, null);
    }
    public static AddHouseObjectResponseDTO invalidAddressId() {
        return new AddHouseObjectResponseDTO(1, "Неверный адрес", null, null);
    }

    public static AddHouseObjectResponseDTO invalidCompanyId() {
        return new AddHouseObjectResponseDTO(1, "Неверная компания", null, null);
    }
    public static AddHouseObjectResponseDTO invalidBranchId() {
        return new AddHouseObjectResponseDTO(1, "Неверный филиал", null, null);
    }
    public static AddHouseObjectResponseDTO invalidAccountNumber() {
        return new AddHouseObjectResponseDTO(1, "Неверный лицевой счет", null, null);
    }

    public static AddHouseObjectResponseDTO invalidTitle() {
        return new AddHouseObjectResponseDTO(1, "Неверное название объекта", null, null);
    }
}
