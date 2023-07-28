//package ru.gasworkers.dev.tests.api.story.repair;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import ru.gasworkers.dev.api.auth.user.UserResponseDto;
//import ru.gasworkers.dev.api.orders.actions.dto.OrdersActionsRequestDto;
//import ru.gasworkers.dev.api.orders.actions.dto.OrdersSaveActionsRequestDto;
//import ru.gasworkers.dev.api.orders.approveDate.OrdersApproveDateRequestDto;
//import ru.gasworkers.dev.api.orders.checkList.SaveCheckListRequestDto;
//import ru.gasworkers.dev.api.orders.createAct.OrdersCreateActRequestDto;
//import ru.gasworkers.dev.api.orders.id.OrdersIdResponseDto;
//import ru.gasworkers.dev.api.orders.info.dto.OrdersInfoResponseDto;
//import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersMaterialValuesRequestDto;
//import ru.gasworkers.dev.api.orders.materialValues.dto.OrdersSaveMaterialValuesRequestDto;
//import ru.gasworkers.dev.api.orders.sign.dto.OrdersSendSignRequestDto;
//import ru.gasworkers.dev.api.orders.sign.dto.OrdersSignRequestDto;
//import ru.gasworkers.dev.api.users.client.lastOrderInfo.LastOrderInfoResponseDto;
//
//import java.io.File;
//import java.io.IOException;
//
//public enum StateTransitionRepair {
//
//    PUBLISHED {
//
//        @Override
//        public UserResponseDto getPublishedClient() throws IOException {
//            UserResponseDto userResponse = new UserResponseDto();
//
//            // Populate id
//            userResponse.setId(123);
//
//            // Populate email
//            userResponse.setEmail("test@email.com");
//
//            // Populate guides
//            UserGuideDto guide1 = new UserGuideDto();
//            guide1.setId(456);
//            userResponse.setGuides(Arrays.asList(guide1));
//
//            // Populate phone
//            userResponse.setPhone("+79997776655");
//
//            // Populate notifications count
//            userResponse.setNotificationsCount(5);
//
//            return userResponse;
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getPublishedLastOrderInfo() throws IOException {
//            LastOrderInfoResponseDto lastOrderResponse = new LastOrderInfoResponseDto();
//
//            // Populate order id
//            lastOrderResponse.setId(789);
//
//            // Populate order number
//            lastOrderResponse.setNumber("ORDER-1234");
//
//            // Populate client object id
//            ClientDto client = new ClientDto();
//            client.setId(123);
//            lastOrderResponse.setClientObject(client);
//
//            // Populate equipments
//            EquipmentDto equipment = new EquipmentDto();
//            equipment.setId(456);
//            lastOrderResponse.setEquipments(Arrays.asList(equipment));
//
//            // Populate client object equipments
//            client.setEquipments(Arrays.asList(equipment));
//
//            return lastOrderResponse;
//        }
//
//    },
//
//    HAS_OFFER {
//
//        @Override
//        public LastOrderInfoResponseDto getHasOfferLastOrderInfo() throws IOException {
//            LastOrderInfoResponseDto lastOrderResponse = new LastOrderInfoResponseDto();
//
//            // Populate order id
//            lastOrderResponse.setId(789);
//
//            // Populate order number
//            lastOrderResponse.setNumber("ORDER-1234");
//
//            // Populate client object id
//            ClientDto client = new ClientDto();
//            client.setId(123);
//            lastOrderResponse.setClientObject(client);
//
//            // Populate equipments
//            EquipmentDto equipment = new EquipmentDto();
//            equipment.setId(456);
//            lastOrderResponse.setEquipments(Arrays.asList(equipment));
//
//            // Populate client object equipments
//            client.setEquipments(Arrays.asList(equipment));
//
//            // Populate active offers count
//            client.setActiveOffersCount(2);
//
//            return lastOrderResponse;
//        }
//
//    },
//
//    BEFORE_PAYMENT {
//
//        @Override
//        public LastOrderInfoResponseDto getBeforePaymentLastOrderInfo() throws IOException {
//            // Implementation similar to getHasOfferLastOrderInfo
//
//        }
//
//        @Override
//        public OrdersInfoResponseDto getBeforePaymentOrderInfo() throws IOException {
//            OrdersInfoResponseDto orderInfoResponse = new OrdersInfoResponseDto();
//
//            // Populate order id
//            orderInfoResponse.setId(789);
//
//            // Populate order number
//            orderInfoResponse.setNumber("ORDER-1234");
//
//            // Populate client object
//            ClientDto client = new ClientDto();
//            client.setId(123);
//            orderInfoResponse.setClientObject(client);
//
//            // Populate equipments
//            EquipmentDto equipment = new EquipmentDto();
//            equipment.setId(456);
//            orderInfoResponse.setEquipments(Arrays.asList(equipment));
//
//            // Populate offer
//            OfferDto offer = new OfferDto();
//            offer.setId(135);
//            orderInfoResponse.setOffer(offer);
//
//            // Populate possible offer id
//            orderInfoResponse.setPossibleOfferId(246);
//
//            // Populate receipts
//            ReceiptDto receipt = new ReceiptDto();
//            receipt.setId(789);
//            orderInfoResponse.setReceipts(Arrays.asList(receipt));
//
//            return orderInfoResponse;
//        }
//
//    },
//
//    DATE_SELECTING {
//
//        @Override
//        public OrdersInfoResponseDto getDateSelectingOrderInfo() throws IOException {
//            // Implementation similar to getBeforePaymentOrderInfo
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getDateSelectingLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getDateSelectingOrderId() throws IOException {
//            OrdersIdResponseDto orderIdResponse = new OrdersIdResponseDto();
//
//            // Populate order id
//            orderIdResponse.setId(789);
//
//            // Populate order number
//            orderIdResponse.setNumber("ORDER-1234");
//
//            // Populate client object
//            ClientDto client = new ClientDto();
//            client.setId(123);
//            orderIdResponse.setClientObject(client);
//
//            // Populate client email
//            UserDto user = new UserDto();
//            user.setEmail("test@email.com");
//            orderIdResponse.setClient(user);
//
//            // Populate equipments
//            EquipmentDto equipment = new EquipmentDto();
//            equipment.setId(456);
//            orderIdResponse.setEquipments(Arrays.asList(equipment));
//
//            // Populate offers
//            OfferDto offer = new OfferDto();
//            offer.setId(135);
//            orderIdResponse.setOffers(Arrays.asList(offer));
//
//            // Populate offer
//            orderIdResponse.setOffer(offer);
//
//            // Populate possible offer id
//            orderIdResponse.setPossibleOfferId(246);
//
//            // Populate receipts
//            ReceiptDto receipt = new ReceiptDto();
//            receipt.setId(789);
//            orderIdResponse.setReceipts(Arrays.asList(receipt));
//
//            // Populate selected date
//            orderIdResponse.setSelectedDate(LocalDate.of(2023, 2, 15));
//
//            return orderIdResponse;
//        }
//
//    },
//
//    WAIT_MASTER {
//
//        @Override
//        public OrdersIdResponseDto getWaitMasterOrderIdAsDispatcher() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getWaitMasterLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getWaitMasterOrderIdAsClient() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//        @Override
//        public OrdersIdResponseDto getWaitMasterOrderIdAsMaster() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//    },
//
//    MASTER_START_WORKING {
//
//        @Override
//        public OrdersIdResponseDto getMasterStartWorkingOrderIdAsMaster() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getMasterStartWorkingLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//    },
//
//    MATERIAL_INVOICE_ISSUED {
//
//        @Override
//        public OrdersIdResponseDto getMaterialInvoiceIssuedOrderIdAsMaster() throws IOException {
//            OrdersIdResponseDto orderIdResponse = new OrdersIdResponseDto();
//
//            // Populate order id
//            orderIdResponse.setId(789);
//
//            // Populate order number
//            orderIdResponse.setNumber("ORDER-1234");
//
//            // Populate receipts
//            ReceiptDto receipt1 = new ReceiptDto();
//            receipt1.setId(135);
//
//            ReceiptDto receipt2 = new ReceiptDto();
//            receipt2.setId(246);
//
//            orderIdResponse.setReceipts(Arrays.asList(receipt1, receipt2));
//
//            // Continue populating other fields similar to previous methods
//
//            return orderIdResponse;
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getMaterialInvoiceIssuedLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//    },
//
//    MATERIAL_INVOICE_PAID {
//
//        @Override
//        public LastOrderInfoResponseDto getMaterialInvoicePaidLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getMaterialInvoicePaidOrderIdAsMaster() throws IOException {
//            // Implementation similar to getMaterialInvoiceIssuedOrderIdAsMaster
//        }
//
//    },
//
//    ACTIONS_INVOICE_ISSUED {
//
//        @Override
//        public OrdersIdResponseDto getActionsInvoiceIssuedOrderIdAsMaster() throws IOException {
//            // Implementation similar to getMaterialInvoiceIssuedOrderIdAsMaster
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getActionsInvoiceIssuedLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getActionsInvoiceIssuedOrderIdAsClient() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//    },
//
//    ACTIONS_INVOICE_PAID {
//
//        @Override
//        public LastOrderInfoResponseDto getActionsInvoicePaidLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getActionsInvoicePaidOrderIdAsMaster() throws IOException {
//            // Implementation similar to getMaterialInvoicePaidOrderIdAsMaster
//        }
//
//        @Override
//        public OrdersIdResponseDto getActionsInvoicePaidOrderIdAsClient() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//    },
//
//    ACT_SIGNED_MASTER {
//
//        @Override
//        public OrdersIdResponseDto getActSignedMasterOrderIdAsMaster() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getActSignedMasterLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getActSignedMasterOrderIdAsClient() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//    },
//
//    ACT_SIGNED_CLIENT {
//
//        @Override
//        public OrdersIdResponseDto getActSignedClientOrderIdAsMaster() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//        @Override
//        public LastOrderInfoResponseDto getActSignedClientLastOrderInfo() throws IOException {
//            // Implementation similar to getPublishedLastOrderInfo
//        }
//
//        @Override
//        public OrdersIdResponseDto getActSignedClientOrderIdAsClient() throws IOException {
//            // Implementation similar to getDateSelectingOrderId
//        }
//
//    };
//
//    abstract UserResponseDto getPublishedClient() throws IOException;
//
//    abstract LastOrderInfoResponseDto getPublishedLastOrderInfo() throws IOException;
//
//    abstract LastOrderInfoResponseDto getHasOfferLastOrderInfo() throws IOException;
//
//    abstract LastOrderInfoResponseDto getBeforePaymentLastOrderInfo() throws IOException;
//
//    abstract OrdersInfoResponseDto getBeforePaymentOrderInfo() throws IOException;
//
//    abstract OrdersInfoResponseDto getDateSelectingOrderInfo() throws IOException;
//
//    abstract LastOrderInfoResponseDto getDateSelectingLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getDateSelectingOrderId() throws IOException;
//
//    abstract OrdersIdResponseDto getWaitMasterOrderIdAsDispatcher() throws IOException;
//
//    abstract LastOrderInfoResponseDto getWaitMasterLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getWaitMasterOrderIdAsClient() throws IOException;
//
//    abstract OrdersIdResponseDto getWaitMasterOrderIdAsMaster() throws IOException;
//
//    abstract OrdersIdResponseDto getMasterStartWorkingOrderIdAsMaster() throws IOException;
//
//    abstract LastOrderInfoResponseDto getMasterStartWorkingLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getMaterialInvoiceIssuedOrderIdAsMaster() throws IOException;
//
//    abstract LastOrderInfoResponseDto getMaterialInvoiceIssuedLastOrderInfo() throws IOException;
//
//    abstract LastOrderInfoResponseDto getMaterialInvoicePaidLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getMaterialInvoicePaidOrderIdAsMaster() throws IOException;
//
//    abstract OrdersIdResponseDto getActionsInvoiceIssuedOrderIdAsMaster() throws IOException;
//
//    abstract LastOrderInfoResponseDto getActionsInvoiceIssuedLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getActionsInvoiceIssuedOrderIdAsClient() throws IOException;
//
//    abstract LastOrderInfoResponseDto getActionsInvoicePaidLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getActionsInvoicePaidOrderIdAsMaster() throws IOException;
//
//    abstract OrdersIdResponseDto getActionsInvoicePaidOrderIdAsClient() throws IOException;
//
//    abstract OrdersIdResponseDto getActSignedMasterOrderIdAsMaster() throws IOException;
//
//    abstract LastOrderInfoResponseDto getActSignedMasterLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getActSignedMasterOrderIdAsClient() throws IOException;
//
//    abstract OrdersIdResponseDto getActSignedClientOrderIdAsMaster() throws IOException;
//
//    abstract LastOrderInfoResponseDto getActSignedClientLastOrderInfo() throws IOException;
//
//    abstract OrdersIdResponseDto getActSignedClientOrderIdAsClient() throws IOException;
//
//}