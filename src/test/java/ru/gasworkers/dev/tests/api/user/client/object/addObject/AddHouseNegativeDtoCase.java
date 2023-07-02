package ru.gasworkers.dev.tests.api.user.client.object.addObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.gasworkers.dev.api.users.client.house.HouseBuilder;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectRequestDTO;
import ru.gasworkers.dev.api.users.client.house.dto.AddHouseObjectResponseDTO;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

import java.math.BigInteger;

@AllArgsConstructor
enum AddHouseNegativeDtoCase {
    MISSING_ALL_FIELDS("Добавление объекта с отсутствующими всеми полями",
            AddHouseObjectResponseDTO.missingAllFields()),
    MISSING_ADDRESS_ID("Добавление объекта с отсутствующим addressId",
            AddHouseObjectResponseDTO.missingAddressId()),
    MISSING_COMPANY_ID("Добавление объекта с отсутствующим companyId",
            AddHouseObjectResponseDTO.missingCompanyId()),
    INVALID_ADDRESS_ID("Добавление объекта с невалидным addressId",
            AddHouseObjectResponseDTO.invalidAddressId()),
    INVALID_COMPANY_ID("Добавление объекта с невалидным companyId",
            AddHouseObjectResponseDTO.invalidCompanyId()),
    MISSING_BRANCH_ID("Добавление объекта с отсутствующим branchId ( need to update doc and after - the test)",
            AddHouseObjectResponseDTO.missingBranchId()),
    MISSING_TITLE("Добавление объекта с отсутствующим title",
            AddHouseObjectResponseDTO.missingTitle()),
    INVALID_TITLE_LENGTH("Добавление объекта с невалидной длиной title",
            AddHouseObjectResponseDTO.invalidExceedSymbolsLimitTitle()),
    INVALID_EMPTY_STRING_TITLE("Добавление объекта с невалидным пустым title" +
            "(message: Поле Название обязательно для заполнения.) ",
            AddHouseObjectResponseDTO.invalidTitle()),
    MISSING_ACCOUNT_NUMBER("Добавление объекта с отсутствующим accountNumber " +
            "(need to update doc and after - the test)",
            AddHouseObjectResponseDTO.missingAccountNumber()),
    INVALID_BRANCH_ID("Добавление объекта с невалидным branchId", AddHouseObjectResponseDTO.invalidBranchId()),
    DUPLICATE_ACCOUNT_NUMBER("Добавление объекта с уже существующим accountNumber " +
            "( 200 account_number: 1.2345678901234567e+19, )",
            AddHouseObjectResponseDTO.invalidAccountNumber()),
    INVALID_ACCOUNT_NUMBER_LONG("Добавление объекта с невалидной длиной - длинный accountNumber " +
            "(200 \"account_number\": 1.2345678901234568e+89,)",
            AddHouseObjectResponseDTO.invalidAccountNumber()),
    INVALID_ACCOUNT_NUMBER_SHORT("Добавление объекта с невалидной длиной - короткий accountNumber " +
            "( 200 \"account_number\": 123,)",
            AddHouseObjectResponseDTO.invalidAccountNumber()),
    MISSING_ACCOUNT_NUMBER_AND_TITLE("Добавление объекта с отсутствующим accountNumber и title " +
            "( комбинация обязательных и необязательных полей) ",
            AddHouseObjectResponseDTO.missingTitle());

    private final String description;
    @Getter
    private final AddHouseObjectResponseDTO expectedResponse;

    public AddHouseObjectRequestDTO getInputDto() {
        AddHouseObjectRequestDTO addHouseObjectDto = HouseBuilder.addDefaultHouseRequestDto();
        switch (this) {
            case MISSING_ADDRESS_ID:
                return addHouseObjectDto.setAddressId(null);
            case MISSING_COMPANY_ID:
                return addHouseObjectDto.setCompanyId(null);
            case MISSING_BRANCH_ID:
                return addHouseObjectDto.setBranchId(null);
            case MISSING_ACCOUNT_NUMBER:
                return addHouseObjectDto.setAccountNumber(null);
            case MISSING_TITLE:
                return addHouseObjectDto.setTitle(null);
            case INVALID_ADDRESS_ID:
                return addHouseObjectDto.setAddressId(0);
            case INVALID_COMPANY_ID:
                return addHouseObjectDto.setCompanyId(0);
            case INVALID_BRANCH_ID:
                return addHouseObjectDto.setBranchId(0);
            case INVALID_EMPTY_STRING_TITLE:
                return addHouseObjectDto.setTitle("");
            case DUPLICATE_ACCOUNT_NUMBER:
                return addHouseObjectDto.setAccountNumber(new BigInteger("12345678901234567890"));
            case INVALID_ACCOUNT_NUMBER_SHORT:
                return addHouseObjectDto.setAccountNumber(new BigInteger("123"));
            case INVALID_ACCOUNT_NUMBER_LONG:
                return addHouseObjectDto.setAccountNumber(new BigInteger("123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890"));
            case INVALID_TITLE_LENGTH:
                return addHouseObjectDto.setTitle("more than 255 - Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
            case MISSING_ALL_FIELDS:
                return new AddHouseObjectRequestDTO();
            case MISSING_ACCOUNT_NUMBER_AND_TITLE:
                return addHouseObjectDto
                        .setAccountNumber(null)
                        .setTitle(null);
            default: throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }

}
