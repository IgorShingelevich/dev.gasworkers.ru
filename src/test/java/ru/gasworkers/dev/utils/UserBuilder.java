package ru.gasworkers.dev.utils;

public class UserBuilder {

    public String
        name,
        patronymic,
        surname,
        fullName,
        sinceDate,
        email,
        password,
        address;

    public Integer firstSmsCode,
            secondSmsCode,
            thirdSmsCode,
            orderNumber;
    public Long phoneNumber;

    //make a builder

    public UserBuilder(String name, String patronymic, String surname, String sinceDate, String email, String password, String address, Long phoneNumber) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.fullName = surname + " " + name + " " + patronymic;
        this.sinceDate = sinceDate;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Integer firstCodeFromNewSMS() {
        try {
            String currentSms = SMSActivateUtil.getLastSms(this.phoneNumber);
            this.firstSmsCode = Integer.parseInt(currentSms.substring(currentSms.length() - 6));
            System.out.println("SMS code: " + this.firstSmsCode);
            System.out.println("currentSms: " + currentSms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.firstSmsCode;
    }

}
