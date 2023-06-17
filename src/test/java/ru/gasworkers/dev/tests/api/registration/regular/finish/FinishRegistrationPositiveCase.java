package ru.gasworkers.dev.tests.api.registration.regular.finish;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationFactory;
import ru.gasworkers.dev.api.registration.regular.dto.ComplexRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.check.CheckRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.finish.FinishRegistrationRequestDto;
import ru.gasworkers.dev.api.registration.regular.dto.start.StartRegistrationRequestDto;

@AllArgsConstructor
enum FinishRegistrationPositiveCase {
    // CLIENT
    CLIENT_WITH_EMAIL(
            "Client with email"),
    CLIENT_WITH_PHONE(
            "Client with phone");


    private final String description;
    private final ComplexRegistrationRequestDto complex = ComplexRegistrationFactory.defaultRandomClient();

    /*public StartRegistrationRequestDto getStartDto() {
        return complex.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        return complex.toCheckRegistration();
    }
*/

    public StartRegistrationRequestDto getStartDto() {
        ComplexRegistrationRequestDto copyDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            copyDto = objectMapper.readValue(objectMapper.writeValueAsString(complex), ComplexRegistrationRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a deep copy of complexDto", e);
        }

        switch (this) {
            case CLIENT_WITH_EMAIL:
                // Modify copyDto if needed
                copyDto.setPhone(null);
                break;
            case CLIENT_WITH_PHONE:
                copyDto.setEmail(null);
                // Add other cases here if needed
            default:
                break;
        }
        return copyDto.toStartRegistration();
    }

    public CheckRegistrationRequestDto getCheckDto() {
        ComplexRegistrationRequestDto copyDto;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            copyDto = objectMapper.readValue(objectMapper.writeValueAsString(complex), ComplexRegistrationRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create a deep copy of complexDto", e);
        }

        switch (this) {
            case CLIENT_WITH_EMAIL:
                // Modify copyDto if needed
                copyDto.setPhone(null);
                break;
            case CLIENT_WITH_PHONE:
                copyDto.setEmail(null);
                // Add other cases here if needed
            default:
                break;
        }
        return copyDto.toCheckRegistration();
    }

    public FinishRegistrationRequestDto getFinishDto() {
        switch (this) {
            case CLIENT_WITH_EMAIL:
                return complex.toFinishRegistration();
            case CLIENT_WITH_PHONE:
                return complex.toFinishRegistration();
            default:
                throw new RuntimeException("Enum with type " + name() + " not supported");
        }
    }


    @Override
    public String toString() {
        return description;
    }

}
