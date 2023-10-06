package ru.gasworkers.dev.api.companies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompaniesSearchExecutorRequestDto {
    String query;
}
