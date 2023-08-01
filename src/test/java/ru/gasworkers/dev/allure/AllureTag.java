package ru.gasworkers.dev.allure;

public final class AllureTag {

    public static final String POSITIVE = "positive",
            NEGATIVE = "negative",
            COMBINATORIAL = "combinatorial";

    public static final String REGRESSION = "regression",
            SMOKE = "smoke";
    //user roles
    public static final String CLIENT = "client",
            MASTER = "master",
            SELF_EMPLOYED = "self-employed",
            SELF_EMPLOYED_IP = "self-employed-ip";

    // target
    public static final String
            API = "api",
            WEB = "web";

    // order types
    public static final String
            MAINTENANCE = "maintenance",
            REPAIR = "repair",
            CONSULTATION = "consultation";

    //processes
    public static final String
            ADMINISTRATION = "administration",
            REGISTRATION = "registration",
            AUTHORISATION = "authorisation",
            BG_REGISTRATION = "bg-registration",
            PAYMENT = "payment",
            ACCOUNT = "account",
            ORDERS = "orders",
            HOUSE = "house";
    //samples
    public static final String SAMPLE_WEB_TEST = "sample-web-test",
            SAMPLE_API_TEST = "sample-api-test";
}
