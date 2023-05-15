package jpa.book.jpashop.domain;

import jpa.book.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
public class CartItem {

    @Id @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name ="cart_id")
    private Cart cart;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name ="item_id")
    private Item item;

    private int orderPrice; // 주문 가격
    private int count; // 주문 개수


    //== 생성 매소드 ==//
    public static CartItem createCartItem(Item item, int orderPrice, int count){
        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setOrderPrice(orderPrice);
        cartItem.setCount(count);

        return cartItem;
    }
}
