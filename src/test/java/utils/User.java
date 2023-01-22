package utils;

public class User {




     public String
        name,
        patronymic,
        surname,
        email,
        password,
        address;

    public Integer
        firstSmsCode,
        secondSmsCode,
        thirdSmsCode,
        orderNumber;
    public Long
        phoneNumber;



    //make a builder




    public User(String name, String patronymic, String surname, String email, String password, String address, Long phoneNumber) {
        this.name = name;
        this.patronymic = patronymic;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderNumber = null;
        this.firstSmsCode = null;
        this.secondSmsCode = null;
        this.thirdSmsCode = null;
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
