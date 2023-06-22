package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.users.client.object.AddHouseObjectBuilder;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectRequestDTO;
import ru.gasworkers.dev.api.users.client.object.addObject.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.exception.EnumNotSupportedException;


@AllArgsConstructor
public enum AddHouseObjectNegativeCase {
    ADD_HOUSE_OBJECT_MISSING_ADDRESS_ID(
            "Добавление объекта с отсутствующим addressId", AddHouseObjectResponseDTO.missingAddressId()),
    ADD_HOUSE_OBJECT_MISSING_COMPANY_ID(
            "Добавление объекта с отсутствующим companyId", AddHouseObjectResponseDTO.missingCompanyId()),
    ADD_HOUSE_OBJECT_MISSING_BRANCH_ID(
            "Добавление объекта с отсутствующим branchId", AddHouseObjectResponseDTO.missingBranchId()),
    ADD_HOUSE_OBJECT_MISSING_ACCOUNT_NUMBER(
            "Добавление объекта с отсутствующим accountNumber", AddHouseObjectResponseDTO.missingAccountNumber()),
    ADD_HOUSE_OBJECT_MISSING_TITLE(
            "Добавление объекта с отсутствующим title", AddHouseObjectResponseDTO.missingTitle()),
    ADD_HOUSE_OBJECT_INVALID_ADDRESS_ID(
            "Добавление объекта с невалидным addressId", AddHouseObjectResponseDTO.invalidAddressId()),
    ADD_HOUSE_OBJECT_INVALID_COMPANY_ID(
            "Добавление объекта с невалидным companyId", AddHouseObjectResponseDTO.invalidCompanyId()),
    ADD_HOUSE_OBJECT_INVALID_BRANCH_ID(
            "Добавление объекта с невалидным branchId", AddHouseObjectResponseDTO.invalidBranchId()),
    ADD_HOUSE_OBJECT_INVALID_ACCOUNT_NUMBER(
            "Добавление объекта с невалидным accountNumber", AddHouseObjectResponseDTO.invalidAccountNumber()),
    ADD_HOUSE_OBJECT_INVALID_TITLE(
            "Добавление объекта с невалидным title", AddHouseObjectResponseDTO.invalidTitle());


    private final String description;
    private final AddHouseObjectResponseDTO expectedResponse;

    public AddHouseObjectResponseDTO getExpectedResponse() {
        return expectedResponse;
    }

    public AddHouseObjectRequestDTO getAddHouseObjectDto() {
        AddHouseObjectRequestDTO addHouseObjectDto = AddHouseObjectBuilder.addHouseObjectRequest();
        switch (this) {
            case ADD_HOUSE_OBJECT_MISSING_ADDRESS_ID:
                return addHouseObjectDto.setAddressId(null);
            case ADD_HOUSE_OBJECT_MISSING_COMPANY_ID:
                return addHouseObjectDto.setCompanyId(null);
            case ADD_HOUSE_OBJECT_MISSING_BRANCH_ID:
                return addHouseObjectDto.setBranchId(null);
            case ADD_HOUSE_OBJECT_MISSING_ACCOUNT_NUMBER:
                return addHouseObjectDto.setAccountNumber(null);
            case ADD_HOUSE_OBJECT_MISSING_TITLE:
                return addHouseObjectDto.setTitle(null);
            case ADD_HOUSE_OBJECT_INVALID_ADDRESS_ID:
                return addHouseObjectDto.setAddressId(0);
            case ADD_HOUSE_OBJECT_INVALID_COMPANY_ID:
                return addHouseObjectDto.setCompanyId(0);
            case ADD_HOUSE_OBJECT_INVALID_BRANCH_ID:
                return addHouseObjectDto.setBranchId(0);
            case ADD_HOUSE_OBJECT_INVALID_ACCOUNT_NUMBER:
                return addHouseObjectDto.setAccountNumber(0);
            case ADD_HOUSE_OBJECT_INVALID_TITLE:
                return addHouseObjectDto.setTitle(" ");
            default:
                throw new EnumNotSupportedException(this);
        }
    }


    @Override
    public String toString() {
        return description;
    }
}
