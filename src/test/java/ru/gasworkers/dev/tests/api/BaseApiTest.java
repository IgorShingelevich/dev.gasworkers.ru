package ru.gasworkers.dev.tests.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.skyscreamer.jsonassert.JSONAssert;
import ru.gasworkers.dev.api.registration.dto.registration.regular.start.StartRegistrationResponseDto;
import ru.gasworkers.dev.tests.BaseTest;

import java.io.IOException;

public abstract class BaseApiTest extends BaseTest {

    public final ObjectMapper objectMapper = new ObjectMapper();


    public void assertResponse(Object expectedResponse, Object actualResponse) throws IOException {
        String expectedJson = objectMapper.writeValueAsString(expectedResponse);
        String actualJson = objectMapper.writeValueAsString(actualResponse);

        JSONAssert.assertEquals(expectedJson, actualJson, false);
    }

}
