package ru.gasworkers.dev.tests.api.sample;

import io.qameta.allure.Epic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.allure.AllureEpic;
import ru.gasworkers.dev.allure.AllureTag;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static org.assertj.core.api.Assertions.assertThat;

@Epic(AllureEpic.SAMPLE_TEST)
@Tag(AllureTag.SAMPLE_API_TEST)
public class SampleApiTest extends BaseApiTest {
    @Test
    @DisplayName("sample api test 1 - positive")
    void positiveApiTest() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    @DisplayName("sample api test 2 - positive")
    void positiveApiTest2() {
        assertThat(1).isEqualTo(1);
    }


}
