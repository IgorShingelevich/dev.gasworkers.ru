package utils;
import com.github.javafaker.Faker;

import static org.apache.commons.lang3.RegExUtils.replaceFirst;

public class UserRandom {

    private String name;
    private String surname;
    private String patronicName;
    private String email;
    private String phoneNumber;
    private final String password = "123456";

    public UserRandom() {
        Faker faker = new Faker();
        this.name = faker.name().firstName();
        this.surname = faker.name().lastName();
        this.patronicName = faker.name().nameWithMiddle();
        this.email = faker.internet().emailAddress();
        this.phoneNumber = faker.phoneNumber().subscriberNumber(11).replaceFirst("^[^7]", "7");
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronicName() {
        return patronicName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }


}
