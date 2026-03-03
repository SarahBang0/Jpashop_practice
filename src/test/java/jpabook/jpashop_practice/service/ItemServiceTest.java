package jpabook.jpashop_practice.service;

import jpabook.jpashop_practice.domain.item.Book;
import jpabook.jpashop_practice.domain.item.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void 상품_등록_조회() {
        //given
        Book item = new Book();
        item.setName("녹나무의 파수꾼");
        itemService.saveItem(item);

        //when
        Item findItem = itemService.findOne(item.getId());

        //then
        Assertions.assertThat(findItem.getName()).isEqualTo(item.getName());
    }

}