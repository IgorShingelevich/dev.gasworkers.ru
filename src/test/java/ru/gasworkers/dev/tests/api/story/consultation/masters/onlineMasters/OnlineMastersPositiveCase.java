package ru.gasworkers.dev.tests.api.story.consultation.masters.onlineMasters;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.consultation.masters.onlineMasters.dto.OnlineMastersRequestDto;
import ru.gasworkers.dev.api.orders.create.dto.CreateOrderRequestDto;
import ru.gasworkers.dev.api.users.client.house.equipment.addEquipment.dto.AddEquipmentRequestDto;
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

 public CreateOrderRequestDto getCreateOrdersDto() {
     return CreateOrderRequestDto.builder()
          .type("consultation")
          .build();
 }

 public OnlineMastersRequestDto getOnlineMastersDto(Integer orderId) {
  if (this == OnlineMastersPositiveCase.ONLINE_MASTER_WITH_BOILER) {
   return OnlineMastersRequestDto.builder()
           .orderId(orderId)
           .search("rating")
           .build();
  }
  throw new EnumNotSupportedException(this);

 }
}
