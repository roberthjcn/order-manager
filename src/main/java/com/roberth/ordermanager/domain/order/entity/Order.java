package com.roberth.ordermanager.domain.order.entity;

import com.roberth.ordermanager.domain.article.entity.Article;
import com.roberth.ordermanager.domain.client.entity.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "order_articles",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;

    @Column
    private Date date;

    @Column
    private String uniqueCode;
}
