package ru.gasworkers.dev.tests.api.user.client.house.equipment;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.house.addEquipment.AddEquipmentBuilder;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.api.users.client.house.addEquipment.dto.AddEquipmentResponseDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum AddEquipmentNegativeCase {
    /*Request fields for test cases
    * @JsonProperty("brand_id")
    private Integer brandId;

    @JsonProperty("model_id")
    private Integer modelId;

    @JsonProperty("custom_brand")
    private String customBrand;

    @JsonProperty("custom_model")
    private String customModel;

    @JsonProperty("type_id")
    private Integer typeId;

    @JsonProperty("all_works")
    private Boolean allWorks;

    private Double power;
    private String[] photos;
    private String[] videos;*/


    ADD_EQUIPMENT_MISSING_ALL_FIELDS("Add equipment with missing all fields( check negative commented cases)", AddEquipmentResponseDto.missingAllFieldsResponse()),
    //    ADD_EQUIPMENT_MISSING_BRAND_ID("Add equipment with missing brandId", AddEquipmentResponseDto.missingBrandIdResponse()),
//    ADD_EQUIPMENT_MISSING_MODEL_ID("Add equipment with missing modelId", AddEquipmentResponseDto.missingModelIdResponse()),
//    ADD_EQUIPMENT_MISSING_CUSTOM_BRAND("Add equipment with missing customBrand", AddEquipmentResponseDto.missingCustomBrandResponse()),
    ADD_EQUIPMENT_MISSING_CUSTOM_MODEL("Add equipment with missing custom model( 200 fix  doc and test)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_EMPTY_CUSTOM_BRAND("Add equipment with empty custom brand ( 200 fix  doc and test) ", AddEquipmentResponseDto.successResponse()),
    //    ADD_EQUIPMENT_MISSING_TYPE_ID("Add equipment with missing typeId", AddEquipmentResponseDto.missingTypeIdResponse()),
    //    ADD_EQUIPMENT_MISSING_POWER("Add equipment with missing power", AddEquipmentResponseDto.missingPowerResponse()),
    //    ADD_EQUIPMENT_MISSING_PHOTOS("Add equipment with missing photos", AddEquipmentResponseDto.missingPhotosResponse()),
    //    ADD_EQUIPMENT_MISSING_VIDEOS("Add equipment with missing videos", AddEquipmentResponseDto.missingVideosResponse()),
    ADD_EQUIPMENT_MISSING_PHOTOS_AND_VIDEOS("Add equipment with missing photos and videos( now 200 need to specify doc  or fix api)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_MISSING_ALL_WORKS("Add equipment with missing allWorks value", AddEquipmentResponseDto.missingAllWorksResponse()),
    ADD_EQUIPMENT_MISSING_TYPE("Add equipment with missing type( now 200 need to specify doc  or fix api)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_MISSING_MODEL("Add equipment with missing model( 500 )", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_BOILER_MISSING_POWER("Add equipment boiler with missing power( now 200 need to specify doc  or fix api)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_OVEN_BEON_WITH_POWER("Add equipment oven with power( now 200 need to specify doc  or fix api)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_WRONG_MODEL("Add equipment with wrong model", AddEquipmentResponseDto.wrongModelIdResponse()),
    ADD_EQUIPMENT_MISSING_BRAND("Add equipment with missing brand ( now 200 need to specify doc  or fix api)", AddEquipmentResponseDto.successResponse()),
    ADD_EQUIPMENT_INVALID_PHOTO("Add equipment with invalid photo(how to handle - Поле photos.0 должно быть числом) ", AddEquipmentResponseDto.invalidPhotoResponse()),
    ADD_EQUIPMENT_INVALID_VIDEO("Add equipment with invalid video ( videos.0  )", AddEquipmentResponseDto.invalidVideoResponse());

    private final String description;
    private final AddEquipmentResponseDto expectedResponse;

    public AddEquipmentResponseDto getExpectedResponse() {
        return expectedResponse;
    }

    public AddEquipmentRequestDto getAddEquipmentDto() {
        AddEquipmentRequestDto addBoilerDto = AddEquipmentBuilder.boilerViessmanRequest();
        AddEquipmentRequestDto addOvenDto = AddEquipmentBuilder.owenBeonRequest();
        switch (this) {
            case ADD_EQUIPMENT_BOILER_MISSING_POWER:
                return addBoilerDto.setPower(null);
            case ADD_EQUIPMENT_OVEN_BEON_WITH_POWER:
                return addOvenDto.setPower(1.0);
            case ADD_EQUIPMENT_MISSING_MODEL:
                return addBoilerDto.setModelId(null);
            case ADD_EQUIPMENT_WRONG_MODEL:
                return addBoilerDto.setModelId(999999);
            case ADD_EQUIPMENT_MISSING_BRAND:
                return addBoilerDto.setBrandId(null);
            case ADD_EQUIPMENT_MISSING_TYPE:
                return addBoilerDto.setTypeId(null);
            case ADD_EQUIPMENT_INVALID_PHOTO:
                return addBoilerDto.setPhotos(new String[]{"invalid_photo.jpg"});
            case ADD_EQUIPMENT_EMPTY_CUSTOM_BRAND:
                return addBoilerDto.setCustomBrand("");
            case ADD_EQUIPMENT_MISSING_CUSTOM_MODEL:
                return addBoilerDto.setCustomModel(null);
            case ADD_EQUIPMENT_MISSING_ALL_WORKS:
                return addBoilerDto.setAllWorks(null);
            case ADD_EQUIPMENT_MISSING_PHOTOS_AND_VIDEOS:
                return addBoilerDto.setPhotos(null).setVideos(null);
            case ADD_EQUIPMENT_INVALID_VIDEO:
                return addBoilerDto.setVideos(new String[]{"invalid_video.mp4"});
            case ADD_EQUIPMENT_MISSING_ALL_FIELDS:
                return new AddEquipmentRequestDto();
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
