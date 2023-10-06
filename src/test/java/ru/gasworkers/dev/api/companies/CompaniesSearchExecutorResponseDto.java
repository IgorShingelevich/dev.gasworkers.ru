package ru.gasworkers.dev.api.companies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesSearchExecutorResponseDto {

    public static CompaniesSearchExecutorResponseDto sssrResponse = CompaniesSearchExecutorResponseDto.builder()
            .data(List.of(DataDto.builder()
                    .id(39)
                    .type("company")
                    .name("ООО \"СССР\"")
                    .rating("5.00")
                    .avatar("https://gasworkers.storage.yandexcloud.net/files/gSyZMhRaO1hRyFAoidTw2dSdhb99Clk8aU3zLjYX.jpg")
                    .first_accept_price(3100)
                    .build()))
            .build();
    /*{
    "data": [
        {
            "id": 39,
            "type": "company",
            "name": "ООО \"СССР\"",
            "rating": "5.00",
            "avatar": "https://gasworkers.storage.yandexcloud.net/files/gSyZMhRaO1hRyFAoidTw2dSdhb99Clk8aU3zLjYX.jpg",
            "first_accept_price": 3100
        }
    ]
}*/
    private List<DataDto> data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataDto {
        private Integer id;
        private String type;
        private String name;
        private String rating;
        private String avatar;
        private Integer first_accept_price;
    }
}
