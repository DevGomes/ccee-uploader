package io.gomes.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class AgenteWrapDTO {
    private List<AgenteDTO> agente;
    private String versao;
}
