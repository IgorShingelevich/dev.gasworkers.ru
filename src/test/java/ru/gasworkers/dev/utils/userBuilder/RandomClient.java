package ru.gasworkers.dev.utils.userBuilder;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class RandomClient {

    private String name;
    private String surname;
    private String middleName;
    private String gender;
    private String fullName;
    private String email;
    private String phone;
    private String sinceTodayDate;
    private String password = "1234";
    private String confirmationCode = "111111";
    private String description = "Не работает -  описание проблемы";
    private String objectAddress = "Московская обл, Одинцово г, г.о. Одинцовский, ул Маршала Неделина, д. 6А";
    private File avatarRandomPhotoFile;
    private File equipmentRandomPhotoFile;
    private File equipmentVideoFile;
    private File pdfFile;
    private File xlsxFile;
    private File rarFile;

    public RandomClient() {
        Faker faker = new Faker(new Locale("ru"));
        String emailPrefixMock = "gwtest";
        String emailClientDomainMock = "client.001";
        String phoneClientPrefixMock = "7001";
        String prefixDateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM")) + "_" +
                LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")) + "_";

        this.name = faker.name().firstName();
        this.surname = faker.name().lastName();
        this.middleName = "Автотестович";
        this.gender = "male";
        this.fullName = this.surname + " " + this.name + " " + this.middleName;
        Transliterator cyrillicToLatin = Transliterator.getInstance("Cyrillic-Latin");
        String latinSurname = cyrillicToLatin.transliterate(surname);
        this.avatarRandomPhotoFile = new File("src/test/resources/uploadFiles/person/test_person" +
                faker.random().nextInt(1, 9) + ".jpg");
        this.equipmentRandomPhotoFile = new File("src/test/resources/uploadFiles/equipment/test_equipment" +
                faker.random().nextInt(1, 4) + ".jpg");
        this.equipmentVideoFile = new File("src/test/resources/uploadFiles/video/test_equipment_video1.mp4");
        this.pdfFile = new File("src/test/resources/uploadFiles/otherFormats/test_pdf.pdf");
        this.xlsxFile = new File("src/test/resources/uploadFiles/otherFormats/test_xlsx.xlsx");
        this.rarFile = new File("src/test/resources/uploadFiles/otherFormats/test_rar.rar");

        String email = emailPrefixMock + prefixDateTime + latinSurname.toLowerCase() + "@" + emailClientDomainMock;
        this.email = email;
        this.phone = phoneClientPrefixMock + faker.regexify("[0-9]{7}");
        this.sinceTodayDate = LocalDate.now().format(DateTimeFormatter.ofPattern("d MMMM yyyy"));
        this.objectAddress = objectAddress;
    }
}
