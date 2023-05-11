package ru.gasworkers.dev.api.pojo;

import ru.gasworkers.dev.model.Role;
import ru.gasworkers.dev.model.apiModel.UserType;
import ru.gasworkers.dev.utils.userBuilder.RandomClient;

public class RegularStartRegistrationRequestPOJO {
    private String type ;
    private String email ;
    private  String phone;
    private  Boolean phone_send ;


    public RegularStartRegistrationRequestPOJO(Builder builder) {
        this.type = builder.type;
        this.email = builder.email;
        this.phone = builder.phone;
        this.phone_send = builder.phone_send;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getPhoneSend() {
        return phone_send;
    }

    public static class Builder {
        private String type;
        private String email;
        private String phone;
        private Boolean phone_send;

        public Builder() {
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder phoneSend(Boolean phone_send) {
            this.phone_send = phone_send;
            return this;
        }

        public RegularStartRegistrationRequestPOJO build() {
            return new RegularStartRegistrationRequestPOJO(this);
        }
    }
}
