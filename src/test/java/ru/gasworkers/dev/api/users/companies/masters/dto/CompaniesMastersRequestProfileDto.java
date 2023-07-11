package ru.gasworkers.dev.api.users.companies.masters.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CompaniesMastersRequestProfileDto {
//  add master  https://api.dev.gasworkers.ru/docs#servisnye-kompanii-POSTapi-v1-companies-masters

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    private String email;
    private String phone;
    private String login;
    private String password;
    private String gender;

}
