package ru.gasworkers.dev.api.consultation.CodeByOrder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeByOrderConsultationResponse {
    private Integer status;
    private String message;
    private DataDto data;

    @lombok.Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataDto {
        @JsonProperty("conference_code")
        private String conferenceCode;
        @JsonProperty("agora_token")
        private String agoraToken;
        @JsonProperty("agora_token_client")
        private String agoraTokenClient;
        @JsonProperty("agora_channel_name")
        private String agoraChannelName;
        @JsonProperty("call_started_at")
        private String callStartedAt;
        private Client client;
        private Master master;
        private String status;
        private String type;
        @JsonProperty("chat_id")
        private Integer chatId;
        @JsonProperty("consultation_passed")
        private String consultationPassed;

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Client {
            private Integer id;
            private String avatar;
            @JsonProperty("full_name")
            private String fullName;
        }

        @lombok.Data
        @NoArgsConstructor
        @AllArgsConstructor
        @JsonInclude(JsonInclude.Include.NON_NULL)
        public static class Master {
            private Integer id;
            private String avatar;
            @JsonProperty("full_name")
            private String fullName;
        }
    }
}
