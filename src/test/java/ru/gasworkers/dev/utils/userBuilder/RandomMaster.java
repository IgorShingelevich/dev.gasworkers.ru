package ru.gasworkers.dev.utils.userBuilder;
import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class RandomMaster {

    private String name;
    private String surname;
    private String patronymicName;
    private String gender;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String sinceDate;
    private  String password = "1234";
    private String confirmationCode = "222222";
    private String passportSeries;
    private String passportNumber;
    private String passportIssuedDate;
    private String passportIssuedBy = "ОВД Одинцовского района г. Одинцово";
    private String registrationAddress = "Московская обл, Одинцово г, г.о. Одинцовский, ул Маршала Неделина, д. 6А"; 
    private String registrationAddressNumber;
    private String bik = "044525225";
    private String checkingAccount;
    private String masterIDValidTillNextYearDate;
    private File masterIDPhoto;
    private File taxpayerCertificatePhoto;
    private File masterPhoto;
    private String speciality = "Слесарь";
    private String skills = "Слесарь с навыками";
    private File certificatePhoto;
    private String workAddress = "улица Северная 5 Одинцово";
    private String workAddressNumber;

    private String workRadius = "10";
    private String videoPrice = "3000";


    public RandomMaster() {
        Faker faker = new Faker(new Locale("ru"));
        String
            emailPrefixMock = "gwtest",
            emailMasterDomainMock = "master.002",
            phoneMasterPrefixMock = "7002";
        String prefixDateTime =  LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM"))+ "_" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"))+"_";

        this.name = faker.name().firstName();
        this.surname = faker.name().lastName();
        this.patronymicName = "Автотестович";
        this.gender = null;
        this.fullName = this.surname.toString() + " " + this.name.toString() + " " + this.patronymicName.toString();
        Transliterator cyrillicToLatin = Transliterator.getInstance("Cyrillic-Latin");
        String latinSurname = cyrillicToLatin.transliterate(surname);

        String email = emailPrefixMock + prefixDateTime + latinSurname.toLowerCase() + "@" + emailMasterDomainMock;
// TODO  different  random email domains for test
        this.email = email;
        this.phoneNumber =phoneMasterPrefixMock + faker.regexify("[0-9]{7}");
//        this.phoneNumber = faker.phoneNumber().subscriberNumber(11).replaceFirst("^[^7]", "7");
        this.sinceDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        this.passportSeries = faker.regexify("[0-9]{4}");
        this.passportNumber = faker.regexify("[0-9]{6}");
        this.passportIssuedDate = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        this.checkingAccount = faker.regexify("[0-9]{20}");
        this.registrationAddressNumber = faker.regexify("[0-9]{1,3}");
        this.masterIDValidTillNextYearDate = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        this.workAddressNumber = faker.regexify("[0-9]{1,3}");

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
    public String getWorkAddressNumber() {
        return workAddressNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phoneNumber;
    }
    public String getConfirmationCode() {
        return confirmationCode;
    }
    public String getPassportSeries() {
        return passportSeries;
    }
    public String getPassportNumber() {
        return passportNumber;
    }
    public String getPassportIssuedDate() {
        return passportIssuedDate;
    }
    public String getPassportIssuedBy() {
        return passportIssuedBy;
    }
    public String getRegistrationAddress() {
        return registrationAddress;
    }
    public String getRegistrationAddressNumber() {
        return registrationAddressNumber;
    }
    public String getBik() {
        return bik;
    }
    public String getCheckingAccount() {
        return checkingAccount;
    }
    public String getMasterIDValidTillNextYearDate() {
        return masterIDValidTillNextYearDate;
    }
    public File getMasterIDPhoto() {
        return masterIDPhoto;
    }
    public File getTaxpayerCertificatePhoto() {
        return taxpayerCertificatePhoto;
    }
    public File getMasterPhoto() {
        return masterPhoto;
    }
    public String getSpeciality() {
        return speciality;
    }
    public String getSkills() {
        return skills;
    }
    public File getCertificatePhoto() {
        return certificatePhoto;
    }
    public String getWorkAddress() {
        return workAddress;
    }

    public String getWorkRadius() {
        return workRadius;
    }
    public String getVideoPrice() {
        return videoPrice;
    }



}


