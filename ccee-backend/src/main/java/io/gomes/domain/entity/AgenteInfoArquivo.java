package io.gomes.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AgenteInfoArquivo extends EntityDefault {

    @OneToOne
    private AgenteWrap agenteWrap;
    private String arquivo;
}
