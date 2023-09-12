package io.gomes.rest.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CompraDTO {
    private List<BigDecimal> valor;
}
