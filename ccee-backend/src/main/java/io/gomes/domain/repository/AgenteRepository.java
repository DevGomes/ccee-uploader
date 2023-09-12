package io.gomes.domain.repository;

import io.gomes.domain.entity.Agente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenteRepository extends JpaRepository<Agente, Integer> {
}
