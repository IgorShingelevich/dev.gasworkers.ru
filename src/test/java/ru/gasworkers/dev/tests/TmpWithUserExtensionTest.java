package ru.gasworkers.dev.tests;

import org.junit.jupiter.api.Test;
import ru.gasworkers.dev.extension.browser.Browser;
import ru.gasworkers.dev.extension.user.User;
import ru.gasworkers.dev.extension.user.WithUser;
import ru.gasworkers.dev.pages.context.ClientPages;

import static ru.gasworkers.dev.model.Role.CLIENT;

public class TmpWithUserExtensionTest {

    @Browser(role = CLIENT)
    ClientPages clientPages;

    @Test
    void test(@WithUser User client1,
              @WithUser User client2,
              @WithUser User client3,
              @WithUser User client4) {
        clientPages.getLoginPage().open()
                .login(client1.getEmail(), client1.getPassword());
    }

}
