package jpabook.jpashop_practice.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop_practice.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 상품 등록
    public void save(Item item) {
        if(item.getId() == null) { //아직 id 셋팅 안된 상태. id는 EntityManager를 통해 persist 되고 세팅됨.
            em.persist(item);
        } else {
            em.merge(item); // 만약 저장되어 있다면 새로운 내용으로 업데이트
        }
    }

    // 상품 목록 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    // 상품 단일 조회
    public Item findOne(Long itemId) {
        return em.find(Item.class, itemId);
    }

    // 상품 수정
}
