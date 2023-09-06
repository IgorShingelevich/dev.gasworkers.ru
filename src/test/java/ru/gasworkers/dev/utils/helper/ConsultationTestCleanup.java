package ru.gasworkers.dev.utils.helper;


public class ConsultationTestCleanup {

    public static void cleanup() {
        UserIdCollector collector = UserIdCollector.getInstance();
        for (int userId : collector.getUserIds()) {
            deleteUserById(userId);
        }
    }

    private static void deleteUserById(int id) {
        // Replace with actual logic to delete user by ID
    }
}
