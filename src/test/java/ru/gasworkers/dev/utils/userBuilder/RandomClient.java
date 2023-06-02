package ru.gasworkers.dev.utils.userBuilder;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class RandomClient {

    private String name;
    private String surname;
    private String patronymicName;
    private String gender;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String sinceTodayDate;
    private String password = "1234";
    private String confirmationCode = "111111";
    private String description = "Не работает -  описание проблемы";
    private String objectAddress = "Московская обл, Одинцово г, г.о. Одинцовский, ул Маршала Неделина, д. 6А";
    private File avatarRandomPhotoFile,
            equipmentRandomPhotoFile,
            equipmentVideoFile,
            pdfFile,
            xlsxFile,
            rarFile;


    public RandomClient() {
        Faker faker = new Faker(new Locale("ru"));
        String
                emailPrefixMock = "gwtest",
                emailClientDomainMock = "client.001",
                phoneClientPrefixMock = "7001";
        String prefixDateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM")) + "_" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")) + "_";


        this.name = faker.name().firstName();
        //fakerRelativeName();
        this.surname = faker.name().lastName();
        //fakerRelativeSurname();
        this.patronymicName = "Автотестович";
        //relativeFakerPatronymic();
        this.gender = "male";
        //fakerRelativeGender();
        this.fullName = this.surname.toString() + " " + this.name.toString() + " " + this.patronymicName.toString();
        Transliterator cyrillicToLatin = Transliterator.getInstance("Cyrillic-Latin");
        String latinSurname = cyrillicToLatin.transliterate(surname);
        this.avatarRandomPhotoFile = new File("src/test/resources/uploadFiles/person/test_person" + faker.random().nextInt(1, 9) + ".jpg");
        this.equipmentRandomPhotoFile = new File("src/test/resources/uploadFiles/equipment/test_equipment" + faker.random().nextInt(1, 4) + ".jpg");
        this.equipmentVideoFile = new File("src/test/resources/uploadFiles/video/test_equipment_video1.mp4");
        this.pdfFile = new File("src/test/resources/uploadFiles/otherFormats/test_pdf.pdf");
        this.xlsxFile = new File("src/test/resources/uploadFiles/otherFormats/test_xlsx.xlsx");
        this.rarFile = new File("src/test/resources/uploadFiles/otherFormats/test_rar.rar");


        String email = emailPrefixMock + prefixDateTime + latinSurname.toLowerCase() + "@" + emailClientDomainMock;
// TODO  different  random email domains for test
        this.email = email;
        this.phoneNumber = phoneClientPrefixMock + faker.regexify("[0-9]{7}");
//        this.phoneNumber = faker.phoneNumber().subscriberNumber(11).replaceFirst("^[^7]", "7");
        this.sinceTodayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        this.objectAddress = objectAddress;
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

    public String getGender() {
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

    public String getSinceTodayDate() {
        return sinceTodayDate;
    }

    public String getObjectAddress() {
        return objectAddress;
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

    public String getDescription() {
        return description;
    }

    public File getAvatarRandomPhotoFile() {
        System.out.println("Client_avatarRandomPhotoFile = " + avatarRandomPhotoFile);
        return avatarRandomPhotoFile;
    }

    public File getEquipmentRandomPhotoFile() {
        System.out.println("Client_equipmentRandomPhotoFile = " + equipmentRandomPhotoFile);
        return equipmentRandomPhotoFile;
    }

    public File getEquipmentVideoFile() {
        System.out.println("Client_equipmentVideoFile = " + equipmentVideoFile);
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

}


