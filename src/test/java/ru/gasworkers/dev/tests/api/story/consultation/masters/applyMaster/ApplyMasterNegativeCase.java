package ru.gasworkers.dev.tests.api.story.consultation.masters.applyMaster;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.users.client.house.ClientHousesBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.HousesRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum ApplyMasterNegativeCase {
    /*.orderId(orderId)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)*/

    APPLY_MASTER_MISSING_ORDER_ID("Missing order id"),
    APPLY_MASTER_MISSING_TIMETABLE_ID("Missing timetable id"),
    APPLY_MASTER_MISSING_DESCRIPTION("Missing description"),
    APPLY_MASTER_MISSING_NOW("Missing now"),
    APPLY_MASTER_MISSING_ALL("Missing all"),
    APPLY_MASTER_INVALID_ORDER_ID("Invalid order id"),
    APPLY_MASTER_INVALID_TIMETABLE_ID("Invalid timetable id"),
    APPLY_MASTER_INVALID_DESCRIPTION("Invalid description"),
    APPLY_MASTER_INVALID_NOW("Invalid now"),
    APPLY_MASTER_INVALID_ALL("Invalid all"),
    APPLY_MASTER_WRONG_ORDER_ID_ALREADY_USED("Wrong order id"), // order id is already used by another user 6695
    APPLY_MASTER_WRONG_TIMETABLE_ID("Wrong timetable id"), // timetable id is already used by another user 106930
    APPLY_MASTER_WRONG_DESCRIPTION_EXCEED_MAX_LENGTH("Wrong description exceeded max length"), // description is more than  symbols
    APPLY_MASTER_OUT_OF_RANGE_ORDER_ID("Out of range order id"),  // not exist in the db yet
    APPLY_MASTER_OUT_OF_RANGE_TIMETABLE_ID("Out of range timetable id"); // add more cases for timetable id  to check previous and next days

    private final String description;

    public HousesRequestDto getAddObjectDto() {
        return ClientHousesBuilder.addDefaultHouseRequestDto();
    }

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    public CreateOrderRequestDto getCreateOrdersDto() {
        return CreateOrderRequestDto.builder()
                .type("consultation")
                .build();
    }

    public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
        return OnlineMastersRequestDto.builder()
                .orderId(orderId)
                .search("rating")
                .build();
    }

    public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        return PickMasterRequestDto.builder()
                .orderId(orderId)
                .online(true)
                .build();
    }

    public IsStartedRequestDto getIsStartedDto(Integer orderId) {
        return IsStartedRequestDto.builder()
                .orderId(orderId)
                .mainFilter("rating")
                .build();
    }

    public ApplyMasterRequestDto getApplyMasterDto(Integer orderId, Integer timetableId) {
        switch (this) {
            case APPLY_MASTER_MISSING_ORDER_ID:
                return ApplyMasterRequestDto.builder()
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_MISSING_TIMETABLE_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_MISSING_DESCRIPTION:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .now(true)
                        .build();
            case APPLY_MASTER_MISSING_NOW:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("test description")
                        .build();
            case APPLY_MASTER_MISSING_ALL:
                return ApplyMasterRequestDto.builder()
                        .build();
            case APPLY_MASTER_INVALID_ORDER_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(-1)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_INVALID_TIMETABLE_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(-1)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_INVALID_DESCRIPTION:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("")
                        .now(true)
                        .build();
            case APPLY_MASTER_INVALID_NOW:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(null)
                        .build();
            case APPLY_MASTER_INVALID_ALL:
                return ApplyMasterRequestDto.builder()
                        .orderId(-1)
                        .timetableId(-1)
                        .description("")
                        .now(null)
                        .build();
            case APPLY_MASTER_WRONG_ORDER_ID_ALREADY_USED:
                return ApplyMasterRequestDto.builder()
                        .orderId(6695)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_WRONG_TIMETABLE_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(111111)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_WRONG_DESCRIPTION_EXCEED_MAX_LENGTH:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                        .now(true)
                        .build();
            case APPLY_MASTER_OUT_OF_RANGE_ORDER_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(111111)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
            case APPLY_MASTER_OUT_OF_RANGE_TIMETABLE_ID:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(222222)
                        .description("test description")
                        .now(true)
                        .build();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }

}
