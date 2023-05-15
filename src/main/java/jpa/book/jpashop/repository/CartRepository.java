package jpa.book.jpashop.repository;

import jpa.book.jpashop.domain.Cart;
import jpa.book.jpashop.domain.CartItem;
import jpa.book.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@RequiredArgsConstructor
public class CartRepository {

    @PersistenceContext
    private final EntityManager em;

    public void save(Cart cart) {
        em.persist(cart);
    }

    public void saveCartItem(CartItem cartItem) {
        em.persist(cartItem);
    }

    public Cart findByMember(Member member) {
        return  em.createQuery("select c from Cart c where c.member = :member", Cart.class)
                .setParameter("member", member).getSingleResult();
    }
}
