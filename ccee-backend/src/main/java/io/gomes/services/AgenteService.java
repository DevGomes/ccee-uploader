package io.gomes.services;

import io.gomes.domain.entity.Regiao;
import io.gomes.rest.dto.AgenteInfoArquivoDTO;

import java.util.List;

public interface AgenteService {
    void salvar(AgenteInfoArquivoDTO agenteInfoArquivoDTO);
    List<Regiao> getDadosConsolidadosPelaSigla(String sigla);
}
