package jpabook.jpashop_practice.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop_practice.domain.Address;
import jpabook.jpashop_practice.domain.Member;
import jpabook.jpashop_practice.domain.Order;
import jpabook.jpashop_practice.domain.OrderStatus;
import jpabook.jpashop_practice.domain.item.Book;
import jpabook.jpashop_practice.exception.NotEnoughStockExcpetion;
import jpabook.jpashop_practice.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional(readOnly = true)
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    EntityManager em;

    @Test
    void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("바다", 15000, 10);
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문 시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다");
        assertEquals(15000 * 2, getOrder.getTotalPrice(), "주문 가격은 주문 * 수량이다");
        assertEquals(10-2, book.getStockQuantity(), "주문한 수량만큼 재고가 줄어야 한다");
    }

    @Test
    void 상품주문_재고수량초과() {
        Member member = createMember();
        Book book = createBook("바다", 15000, 10);

        assertThrows(NotEnoughStockExcpetion.class,
                ()-> orderService.order(member.getId(), book.getId(), 11));
    }

    @Test
    void 주문취소() {
        Member member = createMember();
        Book book = createBook("바다", 15000, 10);
        Long orderId = orderService.order(member.getId(), book.getId(), 2);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문취소 시 주문 상태는 CANCEL");
        assertEquals(10, book.getStockQuantity(), "주문취소 시 재고 수량은 되돌아온다");
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

}