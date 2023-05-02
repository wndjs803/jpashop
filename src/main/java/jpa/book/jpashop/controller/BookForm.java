package jpa.book.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    public Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
