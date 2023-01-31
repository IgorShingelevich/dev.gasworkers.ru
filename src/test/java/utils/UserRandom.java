package utils;
import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static utils.RandomUtil.*;


public class UserRandom {

    private String name;
    private String surname;
    private String patronymicName;
    private String gender;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String sinceDate;
    private  String password = "123456";

    public  UserRandom() {
        Faker faker = new Faker(new Locale("ru"));
        String emailPrefixMock = "gwtest";
        String prefixDateTime =  LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"))+ "_" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"))+"_";


        this.name = faker.name().firstName();
                //fakerRelativeName();
        this.surname = faker.name().lastName();
                //fakerRelativeSurname();
        this.patronymicName = "Автотестович";
                //relativeFakerPatronymic();
        this.gender = null;
                //fakerRelativeGender();
        this.fullName = this.surname.toString() + " " + this.name.toString() + " " + this.patronymicName.toString();
        Transliterator cyrillicToLatin = Transliterator.getInstance("Cyrillic-Latin");
        String latinSurname = cyrillicToLatin.transliterate(surname);



        String email = emailPrefixMock + prefixDateTime + latinSurname.toLowerCase() + "@rambler.ru";
// TODO  different  random email domains for test
        this.email = email;
        this.phoneNumber =faker.regexify("7777[0-9]{7}");
//        this.phoneNumber = faker.phoneNumber().subscriberNumber(11).replaceFirst("^[^7]", "7");
        this.sinceDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public String getGender(){
        return gender;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSinceDate() {
        return sinceDate;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phoneNumber;
    }



}


