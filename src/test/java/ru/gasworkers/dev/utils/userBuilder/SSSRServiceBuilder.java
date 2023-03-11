package ru.gasworkers.dev.utils.userBuilder;

public class SSSRServiceBuilder {
    public String
    //home page
    name = "ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ СЕРПУХОВСКИЙСТРОИТЕЛЬНЫЙСЕТЕВОЙРЕСУРС",
    shortName = "ООО СССР",
    sinceDate = "03/04/2001",
    inn = "5077028618",
    kpp = "503201001",
    ogrn = "1145043000520",
    mainOkved = "41.20",
    descriptionService,
    //contacts page
    country = "Россия",
    officialAddress = "143002, Московская область, Г. ОДИНЦОВО, Ш. МОЖАЙСКОЕ, Д. 8, ЭТ/ПОМ 3/3/9-5",
    mailAddress = "Россия, Московская область, Одинцово, Можайское шоссе, 3",

    password = "123456",
    phone = "+4(564)-584-5684",
    mail = "test_gw_service06@rambler.ru",
    site = "https://example.com";
    //todo add price range page, all docs, the rest info

    public SSSRServiceBuilder() {
        this.name = name;
        this.shortName = shortName;
        this.sinceDate = sinceDate;
        this.inn = inn;
        this.kpp = kpp;
        this.ogrn = ogrn;
        this.mainOkved = mainOkved;
        this.descriptionService = descriptionService;
        this.country = country;
        this.officialAddress = officialAddress;
        this.mailAddress = mailAddress;
        this.password = password;
        this.phone = phone;
        this.mail = mail;
        this.site = site;
    }

    public SSSRServiceBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public SSSRServiceBuilder setShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }
    public SSSRServiceBuilder setSinceDate(String sinceDate) {
        this.sinceDate = sinceDate;
        return this;
    }
    public SSSRServiceBuilder setInn(String inn) {
        this.inn = inn;
        return this;
    }
    public SSSRServiceBuilder setKpp(String kpp) {
        this.kpp = kpp;
        return this;
    }
    public SSSRServiceBuilder setOgrn(String ogrn) {
        this.ogrn = ogrn;
        return this;
    }
    public SSSRServiceBuilder setMainOkved(String mainOkved) {
        this.mainOkved = mainOkved;
        return this;
    }
    public SSSRServiceBuilder setDescriptionService(String descriptionService) {
        this.descriptionService = descriptionService;
        return this;
    }
    public SSSRServiceBuilder setCountry(String country) {
        this.country = country;
        return this;
    }
    public SSSRServiceBuilder setOfficialAddress(String officialAddress) {
        this.officialAddress = officialAddress;
        return this;
    }
    public SSSRServiceBuilder setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
        return this;
    }
    public SSSRServiceBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public SSSRServiceBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }
    public SSSRServiceBuilder setMail(String mail) {
        this.mail = mail;
        return this;
    }
    public SSSRServiceBuilder setSite(String site) {
        this.site = site;
        return this;
    }



}
