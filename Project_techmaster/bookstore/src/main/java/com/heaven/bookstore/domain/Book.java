package com.heaven.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String publicationDate;
    private String language;
    private String category;
    private int numberOfPages;
    private String format;
    private int isbn;
    private double shippingWeight;
    private double listPrice;
    private double ourPrice;
    private boolean active = true;
    @Column(columnDefinition = "text")
    private String description;
    private int inStockNumber;

    @Transient
    private MultipartFile bookImage;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<BookToCartItem> bookToCartItemList;
}
