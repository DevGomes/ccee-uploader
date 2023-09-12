package io.gomes.services.impl;

import io.gomes.domain.entity.*;
import io.gomes.domain.enums.RegiaoSigla;
import io.gomes.domain.repository.*;
import io.gomes.rest.dto.AgenteInfoArquivoDTO;
import io.gomes.services.AgenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgenteServiceImpl implements AgenteService {

    private final AgenteInfoArquivoRepository agenteInfoArquivoRepository;
    private final AgenteWrapRepository agenteWrapRepository;
    private final AgenteRepository agenteRepository;
    private final RegiaoRepository regiaoRepository;


    @Override
    @Transactional
    public void salvar(AgenteInfoArquivoDTO agenteInfoArquivoDTO) {
        AgenteInfoArquivo agenteInfoArquivo = AgenteInfoArquivo.builder()
                .agenteWrap(AgenteWrap.builder()
                        .agente(
                                agenteInfoArquivoDTO.getAgentes().getAgente().stream().map(agenteDTO -> Agente.builder()
                                        .data(agenteDTO.getData())
                                        .codigo(agenteDTO.getCodigo())
                                        .regiao(
                                                agenteDTO.getRegiao().stream().map(regiaoDTO ->
                                                        Regiao.builder()
                                                                .sigla(RegiaoSigla.valueOf(regiaoDTO.getSigla()))
                                                                .geracoes(regiaoDTO.getGeracao().getValor().stream().map(geracaoDTO ->
                                                                        Geracao.builder().valor(geracaoDTO).build()).collect(Collectors.toList()))
                                                                .compras(regiaoDTO.getCompra().getValor().stream().map(compraDTO ->
                                                                        Compra.builder().valor(compraDTO).build()).collect(Collectors.toList()))
                                                                .build()
                                                ).collect(Collectors.toList())
                                        )
                                        .build()).collect(Collectors.toList())
                        )
                        .versao(agenteInfoArquivoDTO.getAgentes().getVersao())
                        .build())
                .arquivo(agenteInfoArquivoDTO.getArquivo())
                .build();

        agenteWrapRepository.save(agenteInfoArquivo.getAgenteWrap());
        agenteInfoArquivoRepository.save(agenteInfoArquivo);

        agenteInfoArquivo.getAgenteWrap().getAgente().stream().forEach(agente -> {
            agente.getRegiao().stream().forEach(regiao -> {
                regiaoRepository.save(regiao);
            });
            agenteRepository.save(agente);
            System.out.println("Código Agente Recebido: " + agente.getCodigo());
        });

    }

    @Override
    public List<Regiao> getDadosConsolidadosPelaSigla(String sigla) {
        try {
            return regiaoRepository.findBySigla(RegiaoSigla.valueOf(sigla)).orElse(Collections.emptyList());
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
