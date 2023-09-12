package io.gomes.domain.repository;

import io.gomes.domain.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository extends JpaRepository<Compra, Integer> {
}
