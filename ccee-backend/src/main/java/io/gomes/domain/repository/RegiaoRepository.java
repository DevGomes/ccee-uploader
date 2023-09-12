package io.gomes.domain.repository;

import io.gomes.domain.entity.Regiao;
import io.gomes.domain.enums.RegiaoSigla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RegiaoRepository extends JpaRepository<Regiao, Integer> {

    Optional<List<Regiao>> findBySigla(@Param("sigla") RegiaoSigla sigla);
}
