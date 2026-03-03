package jpabook.jpashop_practice.exception;

public class NotEnoughStockExcpetion extends RuntimeException {
    public NotEnoughStockExcpetion(String message) {
        super(message);
    }
}
