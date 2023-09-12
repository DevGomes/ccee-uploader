package io.gomes.rest.controller;

import io.gomes.domain.entity.Regiao;
import io.gomes.rest.dto.AgenteInfoArquivoDTO;
import io.gomes.services.AgenteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/agentes")
public class AgenteController {

    private AgenteService agenteService;

    public AgenteController(AgenteService agenteService) {
        this.agenteService = agenteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody AgenteInfoArquivoDTO agenteDTO) {
        agenteService.salvar(agenteDTO);
    }

    @GetMapping("/{regiao}")
    public List<Regiao> getDadosConsolidadosPorRegiao(@PathVariable String regiao) throws Throwable {
        return agenteService.getDadosConsolidadosPelaSigla(regiao.toUpperCase());
    }
}
