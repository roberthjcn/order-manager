package com.roberth.ordermanager.domain.article.service;

import com.roberth.ordermanager.domain.article.entity.Article;
import com.roberth.ordermanager.domain.article.repository.ArticleRepository;
import com.roberth.ordermanager.generic.service.GenericService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService extends GenericService<Article, Long> {
    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        super(articleRepository);
    }

    @Override
    public Article update(Long id, Article article) throws BadRequestException {
        Optional<Article> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist an article with ID: " + id);
        }
        article.setId(id);
        return repository.save(article);
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        Optional<Article> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist an article with ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<Article> getAllByIds(List<Long> articleIds) {
        return repository.findAllById(articleIds);
    }
}
