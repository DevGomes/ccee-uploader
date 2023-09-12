package io.gomes.domain.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Getter
@Setter
public abstract class EntityDefault {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

}
