package jpabook.jpashop_practice.domain.item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Movie extends Item {

    private String director;
    private String actor;
}
