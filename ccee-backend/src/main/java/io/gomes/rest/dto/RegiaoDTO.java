package io.gomes.rest.dto;

import lombok.Data;


@Data
public class RegiaoDTO {
    private String sigla;
    private CompraDTO compra;
    private GeracaoDTO geracao;
}
