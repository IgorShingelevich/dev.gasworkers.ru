package ru.gasworkers.dev.api.consultation.masters.pickMaster.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PickMasterResponseDto {

    private DataDto data;
    private MasterDto master;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class DataDto {
        private Integer id;
        @JsonProperty("from_maintenance")
        private Boolean fromMaintenance;
        @JsonProperty("is_insurance_case")
        private Boolean isInsuranceCase;
        @JsonProperty("repair_order_id")
        @Nullable
        private Integer repairOrderId;
        @JsonProperty("maintenance_order_id")
        @Nullable
        private Integer maintenanceOrderId;
        @JsonProperty("timetable_id")
        private Integer timetableId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.ALWAYS) // Include null fields during serialization
    public static class MasterDto {
        private Integer id;
    }


}
