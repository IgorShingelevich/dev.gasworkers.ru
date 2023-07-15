package ru.gasworkers.dev.tests.api.auth.registration.through.checkAndFinish;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.check.CheckThroughRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum CheckThroughRegistrationPositiveCase {
    BG_MAINTENANCE("BG Maintenance"),
    BG_REPAIR("BG Repair"),
    BG_VIDEO("BG Video");

    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomThroughClient();


    public StartThroughRegistrationRequestDto getStartDto() {
        StartThroughRegistrationRequestDto startDto = complexDto.toStartThroughRegistration();
        switch (this) {
            case BG_MAINTENANCE:
                return complexDto.toStartThroughRegistration()
                        .setOrderType("maintenance");
            case BG_REPAIR:
                return complexDto.toStartThroughRegistration()
                        .setOrderType("repair");
            case BG_VIDEO:
                return complexDto.toStartThroughRegistration()
                        .setOrderType("consultation");
            default:
                return startDto;
        }
    }

    public CheckThroughRegistrationRequestDto getCheckDto() {
        CheckThroughRegistrationRequestDto checkDto = complexDto.toCheckThroughRegistration();
        switch (this) {
            case BG_MAINTENANCE:
            case BG_REPAIR:
            case BG_VIDEO:
                return checkDto;
            default:
                throw new EnumNotSupportedException(this);

        }

    }

    @Override
    public String toString() {
        return description;
    }
}
