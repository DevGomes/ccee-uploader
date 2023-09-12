package io.gomes.domain.entity;

import io.gomes.domain.enums.RegiaoSigla;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Regiao extends EntityDefault {

    @Enumerated(EnumType.STRING)
    private RegiaoSigla sigla;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "regiao_id")
    private List<Geracao> geracoes;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "regiao_id")
    private List<Compra> compras;
}
