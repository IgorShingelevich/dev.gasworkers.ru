package ru.gasworkers.dev.api.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResponsePOJO {

    private Data data;

    // Getter and Setter methods

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private int id;
        private String firstName;
        private String lastName;
        private String middleName;
        private String fullName;
        private String email;
        private String login;
        private String emailVerifiedAt;
        private String joinedAt;
        private String registeredAt;
        private long phone;
        private Contacts contacts;
        private String type;
        private Passport passport;
        private String avatar;
        private String diploma;
        private String selfEmployedCertificate;
        private String selfEmployedCertificateExpired;
        private boolean isFullProfile;
        private boolean isHalfFullProfile;
        private String specialization;
        private String skills;
        private Integer countObjects;
        private Integer countOrders;
        private boolean requisitesFilled;
        private boolean bankDetailsFilled;
        private boolean rateFilled;
        private boolean priceListFilled;
        private boolean distributionCompanyAgreementFilled;
        private boolean equipmentsFilled;
        private boolean contactsFilled;
        private boolean profileCompleted;
        private long createdAt;
        private String company;
        private String service;
        private String companyAsDispatcher;
        private Integer serviceId;
        private Integer distributionCompanyId;
        private boolean onVerification;
        private String verifiedAt;
        private String bank;
        private String accountNumber;
        private int firstAccept;
        private String secondAccept;
        private String country;
        private String postAddress;
        private String employmentStatus;
        private String[] brands;
        private String[] certificates;
        private String insuranceStartedAt;
        private String rating;
        private int reviewsCount;
        private int completedOrdersCount;
        private boolean acceptTerms;
        private int newMessagesCount;
        private int userNotificationsCount;
        private boolean pushNotificationsEnable;
        private boolean emailNotificationsEnable;
        private boolean smsNotificationsEnable;
        private boolean readyForConsultation;
        private boolean canConsulting;
        private boolean isHaveContract;
        private boolean isIp;
        private String inn;
        private String activeRole;
        private int consultationPrice;
        private Integer livingAddressId;
        private String livingAddress;
        private String workDistance;
        private Guide[] guides;
        private LastReview lastReview;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(String emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public String getJoinedAt() {
            return joinedAt;
        }

        public void setJoinedAt(String joinedAt) {
            this.joinedAt = joinedAt;
        }

        public String getRegisteredAt() {
            return registeredAt;
        }

        public void setRegisteredAt(String registeredAt) {
            this.registeredAt = registeredAt;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

        public Contacts getContacts() {
            return contacts;
        }

        public void setContacts(Contacts contacts) {
            this.contacts = contacts;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Passport getPassport() {
            return passport;
        }

        public void setPassport(Passport passport) {
            this.passport = passport;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDiploma() {
            return diploma;
        }

        public void setDiploma(String diploma) {
            this.diploma = diploma;
        }

        public String getSelfEmployedCertificate() {
            return selfEmployedCertificate;
        }

        public void setSelfEmployedCertificate(String selfEmployedCertificate) {
            this.selfEmployedCertificate = selfEmployedCertificate;
        }

        public String getSelfEmployedCertificateExpired() {
            return selfEmployedCertificateExpired;
        }

        public void setSelfEmployedCertificateExpired(String selfEmployedCertificateExpired) {
            this.selfEmployedCertificateExpired = selfEmployedCertificateExpired;
        }

        public boolean isFullProfile() {
            return isFullProfile;
        }

        public void setFullProfile(boolean fullProfile) {
            isFullProfile = fullProfile;
        }

        public boolean isHalfFullProfile() {
            return isHalfFullProfile;
        }

        public void setHalfFullProfile(boolean halfFullProfile) {
            isHalfFullProfile = halfFullProfile;
        }

        public String getSpecialization() {
            return specialization;
        }

        public void setSpecialization(String specialization) {
            this.specialization = specialization;
        }

        public String getSkills() {
            return skills;
        }

        public void setSkills(String skills) {
            this.skills = skills;
        }

        public Integer getCountObjects() {
            return countObjects;
        }

        public void setCountObjects(Integer countObjects) {
            this.countObjects = countObjects;
        }

        public Integer getCountOrders() {
            return countOrders;
        }

        public void setCountOrders(Integer countOrders) {
            this.countOrders = countOrders;
        }

        public boolean isRequisitesFilled() {
            return requisitesFilled;
        }

        public void setRequisitesFilled(boolean requisitesFilled) {
            this.requisitesFilled = requisitesFilled;
        }

        public boolean isBankDetailsFilled() {
            return bankDetailsFilled;
        }

        public void setBankDetailsFilled(boolean bankDetailsFilled) {
            this.bankDetailsFilled = bankDetailsFilled;
        }

        public boolean isRateFilled() {
            return rateFilled;
        }

        public void setRateFilled(boolean rateFilled) {
            this.rateFilled = rateFilled;
        }

        public boolean isPriceListFilled() {
            return priceListFilled;
        }

        public void setPriceListFilled(boolean priceListFilled) {
            this.priceListFilled = priceListFilled;
        }

        public boolean isDistributionCompanyAgreementFilled() {
            return distributionCompanyAgreementFilled;
        }

        public void setDistributionCompanyAgreementFilled(boolean distributionCompanyAgreementFilled) {
            this.distributionCompanyAgreementFilled = distributionCompanyAgreementFilled;
        }

        public boolean isEquipmentsFilled() {
            return equipmentsFilled;
        }

        public void setEquipmentsFilled(boolean equipmentsFilled) {
            this.equipmentsFilled = equipmentsFilled;
        }

        public boolean isContactsFilled() {
            return contactsFilled;
        }

        public void setContactsFilled(boolean contactsFilled) {
            this.contactsFilled = contactsFilled;
        }

        public boolean isProfileCompleted() {
            return profileCompleted;
        }

        public void setProfileCompleted(boolean profileCompleted) {
            this.profileCompleted = profileCompleted;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public String getCompanyAsDispatcher() {
            return companyAsDispatcher;
        }

        public void setCompanyAsDispatcher(String companyAsDispatcher) {
            this.companyAsDispatcher = companyAsDispatcher;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
        }

        public Integer getDistributionCompanyId() {
            return distributionCompanyId;
        }

        public void setDistributionCompanyId(Integer distributionCompanyId) {
            this.distributionCompanyId = distributionCompanyId;
        }

        public boolean isOnVerification() {
            return onVerification;
        }

        public void setOnVerification(boolean onVerification) {
            this.onVerification = onVerification;
        }

        public String getVerifiedAt() {
            return verifiedAt;
        }

        public void setVerifiedAt(String verifiedAt) {
            this.verifiedAt = verifiedAt;
        }

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        public int getFirstAccept() {
            return firstAccept;
        }

        public void setFirstAccept(int firstAccept) {
            this.firstAccept = firstAccept;
        }

        public String getSecondAccept() {
            return secondAccept;
        }

        public void setSecondAccept(String secondAccept) {
            this.secondAccept = secondAccept;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPostAddress() {
            return postAddress;
        }

        public void setPostAddress(String postAddress) {
            this.postAddress = postAddress;
        }

        public String getEmploymentStatus() {
            return employmentStatus;
        }

        public void setEmploymentStatus(String employmentStatus) {
            this.employmentStatus = employmentStatus;
        }

        public String[] getBrands() {
            return brands;
        }

        public void setBrands(String[] brands) {
            this.brands = brands;
        }

        public String[] getCertificates() {
            return certificates;
        }

        public void setCertificates(String[] certificates) {
            this.certificates = certificates;
        }

        public String getInsuranceStartedAt() {
            return insuranceStartedAt;
        }

        public void setInsuranceStartedAt(String insuranceStartedAt) {
            this.insuranceStartedAt = insuranceStartedAt;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public int getReviewsCount() {
            return reviewsCount;
        }

        public void setReviewsCount(int reviewsCount) {
            this.reviewsCount = reviewsCount;
        }

        public int getCompletedOrdersCount() {
            return completedOrdersCount;
        }

        public void setCompletedOrdersCount(int completedOrdersCount) {
            this.completedOrdersCount = completedOrdersCount;
        }

        public boolean isAcceptTerms() {
            return acceptTerms;
        }

        public void setAcceptTerms(boolean acceptTerms) {
            this.acceptTerms = acceptTerms;
        }

        public int getNewMessagesCount() {
            return newMessagesCount;
        }

        public void setNewMessagesCount(int newMessagesCount) {
            this.newMessagesCount = newMessagesCount;
        }

        public int getUserNotificationsCount() {
            return userNotificationsCount;
        }

        public void setUserNotificationsCount(int userNotificationsCount) {
            this.userNotificationsCount = userNotificationsCount;
        }

        public boolean isPushNotificationsEnable() {
            return pushNotificationsEnable;
        }

        public void setPushNotificationsEnable(boolean pushNotificationsEnable) {
            this.pushNotificationsEnable = pushNotificationsEnable;
        }

        public boolean isEmailNotificationsEnable() {
            return emailNotificationsEnable;
        }

        public void setEmailNotificationsEnable(boolean emailNotificationsEnable) {
            this.emailNotificationsEnable = emailNotificationsEnable;
        }

        public boolean isSmsNotificationsEnable() {
            return smsNotificationsEnable;
        }

        public void setSmsNotificationsEnable(boolean smsNotificationsEnable) {
            this.smsNotificationsEnable = smsNotificationsEnable;
        }

        public boolean isReadyForConsultation() {
            return readyForConsultation;
        }

        public void setReadyForConsultation(boolean readyForConsultation) {
            this.readyForConsultation = readyForConsultation;
        }

        public boolean isCanConsulting() {
            return canConsulting;
        }

        public void setCanConsulting(boolean canConsulting) {
            this.canConsulting = canConsulting;
        }

        public boolean isHaveContract() {
            return isHaveContract;
        }

        public void setHaveContract(boolean haveContract) {
            isHaveContract = haveContract;
        }

        public boolean isIp() {
            return isIp;
        }

        public void setIp(boolean ip) {
            isIp = ip;
        }

        public String getInn() {
            return inn;
        }

        public void setInn(String inn) {
            this.inn = inn;
        }

        public String getActiveRole() {
            return activeRole;
        }

        public void setActiveRole(String activeRole) {
            this.activeRole = activeRole;
        }

        public int getConsultationPrice() {
            return consultationPrice;
        }

        public void setConsultationPrice(int consultationPrice) {
            this.consultationPrice = consultationPrice;
        }

        public Integer getLivingAddressId() {
            return livingAddressId;
        }

        public void setLivingAddressId(Integer livingAddressId) {
            this.livingAddressId = livingAddressId;
        }

        public String getLivingAddress() {
            return livingAddress;
        }

        public void setLivingAddress(String livingAddress) {
            this.livingAddress = livingAddress;
        }

        public String getWorkDistance() {
            return workDistance;
        }

        public void setWorkDistance(String workDistance) {
            this.workDistance = workDistance;
        }

        public Guide[] getGuides() {
            return guides;
        }

        public void setGuides(Guide[] guides) {
            this.guides = guides;
        }

        public LastReview getLastReview() {
            return lastReview;
        }

        public void setLastReview(LastReview lastReview) {
            this.lastReview = lastReview;
        }
    }

    public static class Contacts {
        private String facetime;
        private String whatsapp;
        private String skype;
        private String telegram;

        // Getter and Setter methods

        public String getFacetime() {
            return facetime;
        }

        public void setFacetime(String facetime) {
            this.facetime = facetime;
        }

        public String getWhatsapp() {
            return whatsapp;
        }

        public void setWhatsapp(String whatsapp) {
            this.whatsapp = whatsapp;
        }

        public String getSkype() {
            return skype;
        }

        public void setSkype(String skype) {
            this.skype = skype;
        }

        public String getTelegram() {
            return telegram;
        }

        public void setTelegram(String telegram) {
            this.telegram = telegram;
        }
    }

    public static class Passport {
        private int id;
        private String series;
        private String number;
        private long issuedDate;
        private String issuedBy;
        private String registrationAddress;
        private long createdAt;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSeries() {
            return series;
        }

        public void setSeries(String series) {
            this.series = series;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public long getIssuedDate() {
            return issuedDate;
        }

        public void setIssuedDate(long issuedDate) {
            this.issuedDate = issuedDate;
        }

        public String getIssuedBy() {
            return issuedBy;
        }

        public void setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
        }

        public String getRegistrationAddress() {
            return registrationAddress;
        }

        public void setRegistrationAddress(String registrationAddress) {
            this.registrationAddress = registrationAddress;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(long createdAt) {
            this.createdAt = createdAt;
        }
    }

    public static class Guide {
        private int id;
        private String type;
        private boolean needStart;
        private boolean finished;
        private int screen;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isNeedStart() {
            return needStart;
        }

        public void setNeedStart(boolean needStart) {
            this.needStart = needStart;
        }

        public boolean isFinished() {
            return finished;
        }

        public void setFinished(boolean finished) {
            this.finished = finished;
        }

        public int getScreen() {
            return screen;
        }

        public void setScreen(int screen) {
            this.screen = screen;
        }
    }

    public static class LastReview {
        private int id;
        private String text;
        private Creator creator;
        private int value;
        private String createdAt;
        private Order order;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Creator getCreator() {
            return creator;
        }

        public void setCreator(Creator creator) {
            this.creator = creator;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }

    public static class Creator {
        private String fullName;
        private String rating;
        private int countReviews;

        // Getter and Setter methods

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public int getCountReviews() {
            return countReviews;
        }

        public void setCountReviews(int countReviews) {
            this.countReviews = countReviews;
        }
    }

    public static class Order {
        private int id;
        private String type;
        private Equipment[] equipments;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Equipment[] getEquipments() {
            return equipments;
        }

        public void setEquipments(Equipment[] equipments) {
            this.equipments = equipments;
        }
    }

    public static class Equipment {
        private int id;
        private String title;

        // Getter and Setter methods

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}




