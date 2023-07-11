package ru.gasworkers.dev.api.users.companies.masters.dto;

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
public class CompaniesMastersListResponse {
    private ArrayList<DataDto> data;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataDto {
        private Integer id;
        @JsonProperty("active_role")
        private Object activeRole;
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("last_name")
        private String lastName;
        private String rating;
        @JsonProperty("consultation_price")
        private Integer consultationPrice;
        private String skills;
        private String avatar;
        private Company company;
        @JsonProperty("employment_status")
        private String employmentStatus;
        private ArrayList<Brand> brands;
        @JsonProperty("reviews_count")
        private Integer reviewsCount;
        @JsonProperty("completed_orders_count")
        private Integer completedOrdersCount;
        @JsonProperty("created_at")
        private Integer createdAt;

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Company {
            private Integer id;
            private String title;
            @JsonProperty("short_title")
            private String shortTitle;
            private String inn;
            private String kpp;
            private String ogrn;
            private String okved;
            private String address;
            @JsonProperty("register_date")
            private Integer registerDate;
            @JsonProperty("is_ip")
            private Boolean isIp;
            private Object site;
            private String logo;
            private String card;
            private String description;
            private Boolean specialized;
            @JsonProperty("ministry_of_energy_agreement_exist")
            private Boolean ministryOfEnergyAgreementExist;
            private String rating;
            @JsonProperty("gen_dir")
            private String genDir;
            @JsonProperty("gen_dir_np")
            private String genDirNp;
            @JsonProperty("vdgo_contract_charter")
            private String vdgoContractCharter;
            @JsonProperty("is_self_employed_owner")
            private Boolean isSelfEmployedOwner;
            @JsonProperty("first_accept")
            private Integer firstAccept;
        }

        @Data
        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Brand {
            private Integer id;
            private String title;
            @JsonProperty("is_foreign")
            private Boolean isForeign;
            @JsonProperty("is_custom")
            private Boolean isCustom;
            @JsonProperty("is_show_hidden")
            private Boolean isShowHidden;
            private String logo;
            @JsonProperty("created_at")
            private Integer createdAt;
            private ArrayList<Type> types;

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Type {
                private Integer id;
                @JsonProperty("sort_order")
                private Integer sortOrder;
                private String title;
                @JsonProperty("additional_parameter")
                private String additionalParameter;
                private Object note;
                @JsonProperty("power_required")
                private Boolean powerRequired;
                @JsonProperty("included_in_price_list")
                private Boolean includedInPriceList;
                @JsonProperty("is_show_hidden")
                private Boolean isShowHidden;
            }
        }
    }
}
