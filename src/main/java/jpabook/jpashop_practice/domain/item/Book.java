package jpabook.jpashop_practice.domain.item;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Book extends Item{

    private String author;
    private String isbn;
}
