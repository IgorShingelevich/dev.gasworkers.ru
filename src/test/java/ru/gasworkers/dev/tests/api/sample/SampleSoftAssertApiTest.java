package ru.gasworkers.dev.tests.api.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.tests.api.BaseApiTest;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleSoftAssertApiTest extends BaseApiTest {
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

    @Test
    @DisplayName("sample api test 3 - positive")
    void positiveApiTest3() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    @DisplayName("sample api test 4 - positive")
    void positiveApiTest4() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    @DisplayName("sample api test 5 - negative")
    void negativeApiTest() {
        assertThat(1).isEqualTo(2);
    }

    @Test
    @DisplayName("sample api test 6 - negative")
    void negativeApiTest2() {
        assertThat(1).isEqualTo(2);
    }

    @Test
    @DisplayName("sample api test 7 - negative")
    void negativeApiTest3() {
        assertThat(1).isEqualTo(2);
    }

}
