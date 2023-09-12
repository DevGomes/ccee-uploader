package io.gomes.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class AgenteDTO {
    private Integer codigo;
    private String data;
    private List<RegiaoDTO> regiao;
}
