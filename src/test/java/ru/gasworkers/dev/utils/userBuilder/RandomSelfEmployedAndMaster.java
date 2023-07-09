package ru.gasworkers.dev.utils.userBuilder;

import com.github.javafaker.Faker;
import com.ibm.icu.text.Transliterator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@Getter
@Setter
@AllArgsConstructor
public class RandomSelfEmployedAndMaster {
    private String name;
    private String surname;
    private String middleName;
    private String gender;
    private String fullName;
    private String email;
    private String phone;
    private String sinceDate;
    private String password = "1234";
    private String confirmationCode = "222222";
    private String passportSeries;
    private String passportNumber;
    private String passportIssuedDatePicker;
    private String passportIssuedBy = "ОВД Одинцовского района г. Одинцово";
    private String registrationAddress = "Московская обл, Одинцово г, г.о. Одинцовский, ул Маршала Неделина, д. 6А";
    private String registrationAddressNumber;
    private String bik = "044525225";
    private String bicResult = "ПАО Сбербанк";
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
    private String code = "222222";
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


    public RandomSelfEmployedAndMaster() {
        Faker faker = new Faker(new Locale("ru"));
        String
                emailPrefixMock = "gwtest",
                emailMasterDomainMock = "master.002",
                phoneMasterPrefixMock = "7002";
        String prefixDateTime = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMM")) + "_" + LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm")) + "_";

        this.name = faker.name().firstName();
        this.surname = faker.name().lastName();
        this.middleName = "Мастерович";
        this.gender = null;
        this.fullName = this.surname + " " + this.name + " " + this.middleName;
        Transliterator cyrillicToLatin = Transliterator.getInstance("Cyrillic-Latin");
        String latinSurname = cyrillicToLatin.transliterate(surname);

        String email = emailPrefixMock + prefixDateTime + latinSurname.toLowerCase() + "@" + emailMasterDomainMock;
// TODO  different  random email domains for test
        this.email = email;
        this.phone = phoneMasterPrefixMock + faker.regexify("[0-9]{7}");
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
        this.avatarRandomPhotoFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\person\\test_person" + faker.random().nextInt(1, 9) + ".jpg");
        this.masterIDFile = new File("src/test/resources/uploadFiles/diploma/test_diploma" + faker.random().nextInt(1, 3) + ".jpg");
//                this.masterIDFile = new File("D:\\GW\\src\\test\\resources\\uploadFiles\\diploma\\test_diploma" + faker.random().nextInt(1,3) + ".jpg");
        this.equipmentCertificateFile = new File("src/test/resources/uploadFiles/diploma/test_diploma" + faker.random().nextInt(1, 3) + ".jpg");
        this.taxpayerCertificateFile = new File("src/test/resources/uploadFiles/diploma/test_diploma" + faker.random().nextInt(1, 3) + ".jpg");
        this.equipmentRandomPhotoFile = new File("src/test/resources/uploadFiles/equipment/test_equipment" + faker.random().nextInt(1, 4) + ".jpg");
        this.equipmentVideoFile = new File("src/test/resources/uploadFiles/video/test_equipment_video1.mp4");
        this.pdfFile = new File("src/test/resources/uploadFiles/otherFormats/test_pdf.pdf");
        this.xlsxFile = new File("src/test/resources/uploadFiles/otherFormats/test_xlsx.xlsx");
        this.rarFile = new File("src/test/resources/uploadFiles/otherFormats/test_rar.rar");
//todo  change all the paths to relative
    }
}


