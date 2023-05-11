package ru.gasworkers.dev.tests.apiTest.administration;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.api.ApiTestConfig;
import ru.gasworkers.dev.api.administration.usersDeleteApi;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class usersDeleteApiTest {

    public final usersDeleteApi usersDeleteApi = new usersDeleteApi();

    @BeforeEach
    public void setUp() {
        ApiTestConfig.configureRestAssured();
    }

    public final RandomClient randomClient = new RandomClient();

    String sinceDate = randomClient.getSinceDate();
    // Parse sinceDate into a LocalDate object
    LocalDate localDate = LocalDate.parse(sinceDate, DateTimeFormatter.ofPattern("d MMMM yyyy"));

    // Convert LocalDate to an Instant representing the start of the day in the UTC time zone
    long epochSeconds = localDate.atStartOfDay(ZoneOffset.UTC).toEpochSecond();

    // Convert the number of seconds since the Unix epoch to an integer API timestamp
    int apiTimestamp = Math.toIntExact(epochSeconds);


    @Test
    @Owner("Igor Shingelevich")
    @Epic(AllureEpic.ADMINISTRATION)
    @Tags({@Tag(AllureTag.REGRESSION),  @Tag(AllureTag.ADMINISTRATION), @Tag(AllureTag.POSITIVE), @Tag(AllureTag.API)})
    @DisplayName("Массовое удаление пользователей Api")
    public void usersDeleteApiTest() {

        usersDeleteApi.deleteUsersFrom("phone", apiTimestamp);

    }


}
