package ru.gasworkers.dev.api.users.client.house.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.gasworkers.dev.extension.user.client.WithHouse;

import java.math.BigInteger;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class HousesRequestDto {
    @JsonProperty("address_id")
    private Integer addressId;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("branch_id")
    private Integer branchId;
    @JsonProperty("account_number")
    private BigInteger accountNumber;
    private Integer[] photos;
    private String title;

    public static HousesRequestDto newInstance(Integer addressId, Integer companyId, Integer branchId, BigInteger accountNumber, Integer[] photos, String title) {
        return HousesRequestDto.builder()
                .addressId(addressId)
                .companyId(companyId)
                .branchId(branchId)
                .accountNumber(accountNumber)
                .photos(photos)
                .title(title)
                .build();
    }

    public static HousesRequestDto newInstance(WithHouse annotation) {
        return newInstance(
                annotation.addressId(),
                annotation.companyId(),
                null,
                null,
                new Integer[]{},
                annotation.title());
    }

}
