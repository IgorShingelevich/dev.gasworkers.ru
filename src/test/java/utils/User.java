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
        smsCode;
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
        this.smsCode = null;
        this.phoneNumber = phoneNumber;
    }

    public Integer getCodeFromNewSMS() {
        try {
            String currentSmsCode = SMSActivateUtil.getLastSms(this.phoneNumber);
            this.smsCode = Integer.parseInt(currentSmsCode.substring(currentSmsCode.length() - 6));
            System.out.println("SMS code: " + this.smsCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.smsCode;

    }


}
