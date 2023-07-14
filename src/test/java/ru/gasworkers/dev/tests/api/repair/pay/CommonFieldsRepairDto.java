package ru.gasworkers.dev.tests.api.repair.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class CommonFieldsRepairDto {
    private String tokenClient;
    private String tokenDispatcher;
    private String tokenMaster;
    private Integer clientId;
    private String clientEmail;
    private Integer clientGuides0Id;
    private Long clientPhone;
    private Integer clientNotificationsCount;
    private Integer serviceId;
    private Integer dispatcherId;
    private Integer masterId;
    private Integer orderId;
    private Integer clientObjectId;
    private String orderNumber;
    private Integer equipmentsId;
    private Integer activeOffersCount;
    private Integer receiptId;
}
