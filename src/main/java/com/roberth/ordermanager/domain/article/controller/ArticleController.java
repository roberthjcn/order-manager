package com.roberth.ordermanager.domain.article.controller;

import com.roberth.ordermanager.domain.article.entity.Article;
import com.roberth.ordermanager.domain.article.service.ArticleService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAll() {
        return this.articleService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> getById(@PathVariable Long id) {
        return this.articleService.getById(id);
    }

    @PostMapping
    public Article create(@RequestBody Article article) {
        return this.articleService.save(article);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) throws BadRequestException {
        return articleService.update(id, article);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) throws BadRequestException {
        articleService.delete(id);
    }
}
