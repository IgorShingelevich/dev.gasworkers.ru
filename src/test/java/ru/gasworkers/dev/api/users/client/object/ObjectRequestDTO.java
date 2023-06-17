package ru.gasworkers.dev.api.users.client.object;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Accessors(chain = true)
public class ObjectRequestDTO {
    @JsonProperty("address_id")
    private Integer addressId;
    @JsonProperty("company_id")
    private Integer companyId;
    @JsonProperty("branch_id")
    private Integer branchId;
    @JsonProperty("account_number")
    private Integer accountNumber;
    private Integer[] photos;
    private String title;

    public static ObjectRequestDTO newInstance(Integer addressId, Integer companyId, Integer branchId, Integer accountNumber, Integer[] photos, String title) {
        return new ObjectRequestDTO(addressId, companyId, branchId, accountNumber, photos, title);
    }
}
