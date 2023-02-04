package ru.gasworkers.dev.api;

public class SmsData {

    private static String phoneFrom;
    private String text;
    private String service;
    private String date;
    private String type;
    private Integer cost;
    private String accepted;
    private Integer passedSeconds;

    public SmsData(String phoneFrom, String text, String service, String date, String type, Integer cost, String accepted, Integer passedSeconds) {
        this.phoneFrom = phoneFrom;
        this.text = text;
        this.service = service;
        this.date = date;
        this.type = type;
        this.cost = cost;
        this.accepted = accepted;
        this.passedSeconds = passedSeconds;
    }

    public static String getPhoneFrom() {
        return phoneFrom;
    }

    public String getText() {
        return text;
    }

    public String getService() {
        return service;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Integer getCost() {
        return cost;
    }

    public String getAccepted() {
        return accepted;
    }

    public Integer getPassedSeconds() {
        return passedSeconds;
    }

    public static void main(String[] args) {
        System.out.println(getPhoneFrom());

    }

}
