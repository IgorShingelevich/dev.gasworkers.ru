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
    private String passportIssuedDatePicker;
    private String passportIssuedBy = "ОВД Одинцовского района г. Одинцово";
    private String registrationAddress = "Московская обл, Одинцово г, г.о. Одинцовский, ул Маршала Неделина, д. 6А"; 
    private String registrationAddressNumber;
    private String bik = "044525225";
    private String checkingAccount;
    private String masterIDValidTillNextYearDatePicker;
    private File masterIDPhoto;
    private File taxpayerCertificatePhoto;
    private File masterPhoto;
    private String speciality = "Слесарь";
    private String skills = "Слесарь с навыками";
    private File certificatePhoto;
    private String workAddress = "улица Северная 5 Одинцово";
    private String workAddressNumber;
    private String masterIDValidTillDatePicker;
    private String equipmentCertificateValidTillDatePicker;

    private String workRadius = "10";
    private String videoPrice = "3000";
    private File avatarRandomPhotoFile,
            equipmentRandomPhotoFile,
            equipmentVideoFile,
            pdfFile,
            xlsxFile,
            rarFile,
    masterIDFile,
    taxpayerCertificateFile,
    equipmentCertificateFile;


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
        this.passportIssuedDatePicker = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.checkingAccount = faker.regexify("[0-9]{20}");
        this.registrationAddressNumber = faker.regexify("[0-9]{1,3}");
        this.masterIDValidTillNextYearDatePicker = LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.masterIDValidTillDatePicker = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.equipmentCertificateValidTillDatePicker = LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.workAddressNumber = faker.regexify("[0-9]{1,3}");
        this.avatarRandomPhotoFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\person\\test_person" + faker.random().nextInt(1,9) + ".jpg");
        this.masterIDFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\diploma\\test_diploma" + faker.random().nextInt(1,3) + ".jpg");
        this.equipmentCertificateFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\diploma\\test_diploma" + faker.random().nextInt(1,3) + ".jpg");
        this.taxpayerCertificateFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\diploma\\test_diploma" + faker.random().nextInt(1,3) + ".jpg");
        this.equipmentRandomPhotoFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\equipment\\test_equipment" + faker.random().nextInt(1,4) + ".jpg");
        this.equipmentVideoFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\video\\test_equipment_video1.mp4");
        this.pdfFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\otherFormats\\test_pdf.pdf");
        this.xlsxFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\otherFormats\\test_xlsx.xlsx");
        this.rarFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\otherFormats\\test_rar.rar");

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
    public String getPassportIssuedDatePicker() {
        return passportIssuedDatePicker;
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
    public String getMasterIDValidTillNextYearDatePicker() {
        return masterIDValidTillNextYearDatePicker;
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

    public String getWorkAddress() {
        return workAddress;
    }

    public String getWorkRadius() {
        return workRadius;
    }
    public String getVideoPrice() {
        return videoPrice;
    }
    public String getMasterIDValidTillDatePicker() {
        return masterIDValidTillDatePicker;
    }
    public String getBoilerEquipmentCertificateValidTillDatePicker() {
        return equipmentCertificateValidTillDatePicker;
    }
    public File getAvatarRandomPhotoFile() {
        return avatarRandomPhotoFile;
    }
    public File getEquipmentRandomPhotoFile() {
        return equipmentRandomPhotoFile;
    }
    public File getEquipmentVideoFile() {
        return equipmentVideoFile;
    }
    public File getPdfFile() {
        return pdfFile;
    }
    public File getXlsxFile() {
        return xlsxFile;
    }
    public File getRarFile() {
        return rarFile;
    }
    public File getMasterIDFile() {
        return masterIDFile;
    }
    public File getTaxpayerCertificateFile() {
        return taxpayerCertificateFile;
    }
    public File getBoilerEquipmentCertificateFile() {
        return equipmentCertificateFile;
    }


}


