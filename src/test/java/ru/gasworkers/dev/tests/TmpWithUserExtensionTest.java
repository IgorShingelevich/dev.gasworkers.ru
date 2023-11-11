package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.client.WithClient;
import ru.gasworkers.dev.pages.context.ClientPages;

import static ru.gasworkers.dev.model.UserRole.CLIENT;

public class TmpWithUserExtensionTest {

    @Browser(role = CLIENT)
    ClientPages clientPages;

    @Disabled
    @Test
    void test(@WithClient User client1,
              @WithClient User client2,
              @WithClient User client3,
              @WithClient User client4) {
        clientPages.getLoginPage().open()
                .login(client1.getEmail(), client1.getPassword());
    }

}
