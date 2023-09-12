package io.gomes.domain.repository;

import io.gomes.domain.entity.Geracao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeracaoRepository extends JpaRepository<Geracao, Integer> {
}
