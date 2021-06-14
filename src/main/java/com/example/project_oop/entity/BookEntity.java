package com.example.project_oop.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name  ="book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "book_title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "price")
    private String price;

    @Column(name = "category_id")
    private Integer categoryId;
}
