package ru.gasworkers.dev.tests.api.auth.registration.through.start;

import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.auth.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.auth.registration.through.dto.start.StartThroughRegistrationRequestDto;
import ru.gasworkers.dev.exception.EnumNotSupportedException;

@AllArgsConstructor
enum StartThroughRegistrationPositiveCase {
    BG_MAINTENANCE("BG Maintenance"),
    BG_REPAIR("BG Repair"),
    BG_VIDEO("BG Video");

    private final String description;
    private final ComplexRegistrationRequestDto complexDto = ComplexRegistrationFactory.defaultRandomThroughClient();

    public StartThroughRegistrationRequestDto getStartDto() {
        switch (this) {
            case BG_MAINTENANCE:
                return complexDto.toStartThroughRegistration()
                        .setType("maintenance");
            case BG_REPAIR:
                return complexDto.toStartThroughRegistration()
                        .setType("repair");
            case BG_VIDEO:
                return complexDto.toStartThroughRegistration()
                        .setType("consultation");
            default:
                throw new EnumNotSupportedException(this);
        }
    }

    @Override
    public String toString() {
        return description;
    }
}
