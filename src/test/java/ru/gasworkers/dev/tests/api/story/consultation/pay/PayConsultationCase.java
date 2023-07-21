package ru.gasworkers.dev.tests.api.story.consultation.pay;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.cancelRequest.dto.ConsultationCancelRequestRequestDto;
import ru.gasworkers.dev.api.consultation.masters.apply.dto.ApplyMasterRequestDto;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.consultation.masters.pickMaster.dto.PickMasterRequestDto;
import ru.gasworkers.dev.api.orders.selectPayment.dto.SelectPaymentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum PayConsultationCase {
    STANDARD_CASE("Client cancel payed consultation");
    private final String description;

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
                .type("fps")
                .build();
    }

    public ConsultationCancelRequestRequestDto getCancelRequestDto() {
        if (this == PayConsultationCase.STANDARD_CASE) {
            return new ConsultationCancelRequestRequestDto();
        }
        throw new EnumNotSupportedException(this);
    }

    @Override
    public String toString() {
        return description;
    }
}
