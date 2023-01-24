package tests.registration;

import extension.browser.Browser;
import model.Role;
import pages.context.ClientPages;
import pages.context.DispatcherPages;
import pages.context.MasterPages;
import utils.RandomUtil;
import utils.User;
import utils.UserRandom;

public class RegistrationTest {

    @Browser(role = Role.CLIENT, browserSize = "800x1000", browserPosition = "0x0")
    ClientPages clientPages;

    @Browser(role = Role.DISPATCHER, browserSize = "800x1000", browserPosition = "850x0")
    DispatcherPages dispatcherPages;

    @Browser(role = Role.MASTER, browserSize = "800x1000", browserPosition = "1700x0")
    MasterPages masterPages;

    UserRandom client = new UserRandom();
    UserRandom dispatcher = new UserRandom();
    UserRandom master = new UserRandom();












}
