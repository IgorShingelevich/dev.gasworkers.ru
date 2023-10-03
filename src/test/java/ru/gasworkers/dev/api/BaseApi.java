package ru.gasworkers.dev.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.BODY;

public abstract class BaseApi {

    protected final RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.dev.gasworkers.ru/api/v1")
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .addHeader("accept-language", "en-US,en;q=0.9,ru;q=0.8")
//            .log(LogDetail.BODY)
            .build()
            .log().ifValidationFails()
            .log().uri()
            .log().body();

    protected final ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(BODY)
            .build();

}

 /* protected void addTestWatcher(TestScenario scenario) {
        TestWatcher watchman = new TestWatcher() {

            @Override
            protected void failed(Throwable e, Description description) {
                switch (scenario) {
                    case CONSULTATION_MASTER_APPLIED:
                        consultationCancelRequest(e, description);
                        break;
                    case SCENARIO_2:
                        handleFailureScenario2(e, description);
                        break;
                    default:
                        // Handle any default behavior here
                        break;
                }
            }

            private void consultationCancelRequest(Throwable e, Description description) { // token)
                ConsultationCancelRequestApi consultationCancelRequestApi = new ConsultationCancelRequestApi();

            }

            private void handleFailureScenario2(Throwable e, Description description) {
                // This method will be called if the test fails or throws an exception
                // in Scenario 2
                // Perform your desired action here
            }

        };

        // Apply the rule to the current test class
        org.junit.rules.RuleChain.outerRule(watchman);
        // in testclass
        *//* @Before
    public void setup() {
        String token = "your_token_here";
        addTestWatcher(TestScenario.SCENARIO_1, token); // Apply Scenario 1 failure handling logic
    }*//*
    }*/

  /* protected enum TestScenario {
        CONSULTATION_MASTER_APPLIED,
        SCENARIO_2
    }*/
