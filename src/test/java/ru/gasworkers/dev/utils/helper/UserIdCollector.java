package ru.gasworkers.dev.utils.helper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserIdCollector {
//    UserIdCollector.getInstance().addUserId(newUserId);

    private static UserIdCollector instance;
    private final List<Integer> userIds = new ArrayList<>();

    private UserIdCollector() {
    }

    public static UserIdCollector getInstance() {
        if (instance == null) {
            instance = new UserIdCollector();
        }
        return instance;
    }

    public void addUserId(int userId) {
        userIds.add(userId);
    }
}
