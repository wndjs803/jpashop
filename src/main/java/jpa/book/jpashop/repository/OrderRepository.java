package jpa.book.jpashop.repository;

import jpa.book.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch){
        // 추후에 Querydsl로
        return em.createQuery("select o from Order o join o.member m"
                + " where 0.status =:status "
                + " and m.name like :name", Order.class)
                .setParameter("name", orderSearch.getMemberName())
                .setParameter("status", orderSearch.getOrderStatus())
                .setMaxResults(1000) // 최대 1000건
                .getResultList();
    }
}
