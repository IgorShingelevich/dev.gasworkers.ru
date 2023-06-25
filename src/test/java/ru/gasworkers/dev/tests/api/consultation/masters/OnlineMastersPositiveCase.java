package ru.gasworkers.dev.tests.api.consultation.masters;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.masters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrdersRequestDto;
import ru.gasworkers.dev.api.users.client.equipment.dto.AddEquipmentRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
 enum OnlineMastersPositiveCase {
 ONLINE_MASTER_WITH_BOILER("Online master with boiler");
  private final String description;


 public AddEquipmentRequestDto getAddEquipmentDto() {
  return AddEquipmentRequestDto.defaultBoilerEquipment();


 }
 @Override
    public String toString() {
    return description;
    }

 public CreateOrdersRequestDto getCreateOrdersDto() {
     return CreateOrdersRequestDto.builder()
          .type("consultation")
          .build();
 }

 public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
  switch (this) {
   case ONLINE_MASTER_WITH_BOILER:
    return OnlineMastersRequestDto.builder()
            .orderId(orderId)
            .search("rating")
            .build();
   default:
    throw new EnumNotSupportedException(this);
  }

 }
}
