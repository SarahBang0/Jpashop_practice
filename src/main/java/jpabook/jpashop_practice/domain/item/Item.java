package jpabook.jpashop_practice.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop_practice.domain.Category;
import jpabook.jpashop_practice.exception.NotEnoughStockExcpetion;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();


    //==비즈니스 로직==//
    // 상품 추가 시 stockQuantity 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;

    }

    // 상품 삭제 시 stockQuantity 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0) {
            throw new NotEnoughStockExcpetion("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
