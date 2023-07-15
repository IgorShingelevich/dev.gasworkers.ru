package ru.gasworkers.dev.tests.api.consultation.isStarted;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.isStarted.dto.IsStartedRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;

@AllArgsConstructor
enum IsStartedPositiveCase {
    IS_STARTED_POSITIVE_CASE(" Статус после  списка мастеров   ( поправить орфографию message: СТатус начала конференции");
    private final String description;

    public AddEquipmentRequestDto getAddEquipmentDto() {
        return AddEquipmentRequestDto.defaultBoilerEquipment();
    }

    public CreateOrdersRequestDto getCreateOrdersDto() {
        return CreateOrdersRequestDto.builder()
                .type("consultation")
                .build();
    }

    public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
        return OnlineMastersRequestDto.builder()
                .orderId(orderId)
                .search("rating")
                .build();
    }



    /*public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        return PickMasterRequestDto.builder()
                .orderId(orderId)
                .online(true)
                .build();
    }

    public ApplyMasterRequestDto getApplyMasterDto(Integer orderId, Integer timetableId) {
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
        }

    public SelectPaymentRequestDto getSelectPaymentDto(Integer orderId, Integer receiptId) {
                return SelectPaymentRequestDto.builder()
                        .orderId(orderId)
                        .receiptId(receiptId)
                        .type("card")
                        .build();
    }*/

    @Override
    public String toString() {
        return description;
    }

    public IsStartedRequestDto getIsStartedDto(Integer orderId) {
        return IsStartedRequestDto.builder()
                .orderId(orderId)
                .mainFilter("rating")
                .build();
    }
}
