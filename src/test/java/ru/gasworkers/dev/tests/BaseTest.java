package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.gasworkers.dev.extension.browser.ScreenshotExtension;
import ru.gasworkers.dev.utils.helper.UserIdCollector;

import java.util.List;
import java.util.function.Consumer;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {

    public void assertAll(List<Consumer<SoftAssert>> executables) {
        SoftAssert softAssert = new SoftAssert();
        executables.forEach(executable -> softAssert.assertAll(executable));
        softAssert.assertAll();
    }

    @AfterAll
    public static void cleanupAfterAllTests() {
        UserIdCollector collector = UserIdCollector.getInstance();
        List<Integer> userIds = collector.getUserIds();

        if (userIds == null || userIds.isEmpty()) {
            System.out.println("No users to delete");
            return;
        }

        for (int userId : userIds) {
            deleteUserById(userId);
        }
        System.out.println(userIds.size() + " users are deleted");
        userIds.clear(); // Clear the list after cleanup
    }

    private static void deleteUserById(int id) {
        // Replace with actual logic to delete user by ID
    }
}
