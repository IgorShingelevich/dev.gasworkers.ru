package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.extension.user.client.WithEquipment;
import ru.gasworkers.dev.extension.user.client.WithHouse;
import ru.gasworkers.dev.extension.user.client.WithOrder;

import static ru.gasworkers.dev.extension.user.client.WithOrder.OrderType.MAINTENANCE;
import static ru.gasworkers.dev.extension.user.client.WithOrder.OrderType.REPAIR;

public class DelMe {


    @Disabled
    @Test
    void test(@WithClient(houses = @WithHouse(
            equipments = @WithEquipment,
            orders = {@WithOrder(MAINTENANCE), @WithOrder(REPAIR)}
    )) User client) {
        String a = "asf";

    }

}
