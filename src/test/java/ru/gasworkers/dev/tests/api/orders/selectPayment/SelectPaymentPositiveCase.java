package ru.gasworkers.dev.tests.api.orders.selectPayment;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentRequestDto;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum SelectPaymentPositiveCase {
    PAY_FPS("Оплата fps - система быстрых платежей"),
    PAY_CARD("Оплата card - оплата картой");
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

    public PickMasterRequestDto getPickMasterDto(Integer orderId) {
        return PickMasterRequestDto.builder()
                .orderId(orderId)
                .online(true)
                .build();
    }

    public ApplyMasterRequestDto getApplyMasterDto(Integer orderId, Integer timetableId) {
        switch (this) {
            case PAY_CARD:
            case PAY_FPS:
                return ApplyMasterRequestDto.builder()
                        .orderId(orderId)
                        .timetableId(timetableId)
                        .description("test description")
                        .now(true)
                        .build();
        }
        throw new EnumNotSupportedException(this);
    }

    public SelectPaymentRequestDto getSelectPaymentDto(Integer orderId, Integer receiptId) {
        switch (this) {
            case PAY_CARD:
                return SelectPaymentRequestDto.builder()
                        .orderId(orderId)
                        .receiptId(receiptId)
                        .type("card")
                        .build();
            case PAY_FPS:
                return SelectPaymentRequestDto.builder()
                        .orderId(orderId)
                        .receiptId(receiptId)
                        .type("fps")
                        .build();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }
}
