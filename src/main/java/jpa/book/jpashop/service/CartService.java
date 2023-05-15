package jpa.book.jpashop.service;

import jpa.book.jpashop.domain.*;
import jpa.book.jpashop.domain.Item.Item;
import jpa.book.jpashop.repository.CartRepository;
import jpa.book.jpashop.repository.ItemRepository;
import jpa.book.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    @Transactional
    public Long add(Long memberId, Long itemId, int count){
        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);
        Cart cart = cartRepository.findByMember(member);

        // 장바구니 상품 생성
        CartItem cartItem = CartItem.createCartItem(item, item.getPrice(), count);

        cartRepository.saveCartItem(cartItem);

        // 장바구니 상품 추가
        cart.addCartItem(cartItem);

        return cart.getId();
    }

}
