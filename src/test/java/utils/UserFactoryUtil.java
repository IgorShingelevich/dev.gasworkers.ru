package utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class UserFactoryUtil {

    /*

    String
        name,
        patronymic,
        surname,
        email,
        password,
        address;

    Integer
        phoneNumber,
        smsCode;

    ChromeDriver
        driver;

    //make a builder




    public static User createClient(String name, String patronymic, String surname, String password, Integer phoneNumber, String address, ChromeDriver driver) {
        User user = new User(name, patronymic, surname, password, phoneNumber, address, driver);
        user.setRole(User.Role.CLIENT);
        return user;
    }

    public static User createDispatcher(String name, String patronymic, String surname, String password, Integer phoneNumber, String address, ChromeDriver driver) {
        User user = new User(name, patronymic, surname, password, phoneNumber, address, driver);
        user.setRole(User.Role.DISPATCHER);
        return user;
    }

    public static User createMaster(String name, String patronymic, String surname, String password, Integer phoneNumber, String address, ChromeDriver driver) {
        User user = new User(name, patronymic, surname, password, phoneNumber, address, driver);
        user.setRole(User.Role.MASTER);
        return user;
    }

    WebDriver driver = new ChromeDriver();
    User client = UserFactory.createClient(name = "John", "", "Doe", "password", "1234567890", "123 Main St", driver);
}

    public static List<User> createUsers(List<Map<String, String>> userData, WebDriver driver) {
        List<User> users = new ArrayList<>();
        for (Map<String, String> data : userData) {
            String name = data.get("name");
            String patronymic = data.get("patronymic");
            String surname = data.get("surname");
            String password = data.get("password");
            String phoneNumber = data.get("phoneNumber");
            String address = data.get("address");
            String role = data.get("role");
            User user = new User(name, patronymic, surname, password, phoneNumber, address, driver);
            if (role.equals("CLIENT")) {
                user.setRole(User.Role.CLIENT);
            } else if (role.equals("DISPATCHER")) {
                user.setRole(User.Role.DISPATCHER);
            } else if (role.equals("MASTER")) {
                user.setRole(User.Role.MASTER);
            }
            users.add(user);
        }
        return users;
    }
}

List<Map<String, String>> userData = readUserDataFromTable();
        WebDriver driver = new ChromeDriver();
        List<User> users = UserFactory.createUsers(userData, driver);
*/

}
