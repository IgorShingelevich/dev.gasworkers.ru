package ru.gasworkers.dev.api.users.client.house.getClientHouses.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL) // when is needed
@Accessors(chain = true)
public class GetClientHousesRequestDto {

}
