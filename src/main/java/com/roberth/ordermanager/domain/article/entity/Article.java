package com.roberth.ordermanager.domain.article.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uniqueCode;

    @Column
    private String name;

    @Column
    private Float price;

}
