package ru.gasworkers.dev.api.users.fspBankList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FspBankListResponseDto {

    public int status;
    public String message;
    public ArrayList<DataDto> data;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DataDto {
        public int id;
        public String identifier;
        public String title;
        public String logo;
        @JsonProperty("package")
        public String dataPackage;
    }

}
