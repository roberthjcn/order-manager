package com.roberth.ordermanager.domain.order.service;

import com.roberth.ordermanager.domain.article.entity.Article;
import com.roberth.ordermanager.domain.article.service.ArticleService;
import com.roberth.ordermanager.domain.client.entity.Client;
import com.roberth.ordermanager.domain.client.service.ClientService;
import com.roberth.ordermanager.domain.order.dto.OrderCreateDto;
import com.roberth.ordermanager.domain.order.dto.OrderUpdateDto;
import com.roberth.ordermanager.domain.order.entity.Order;
import com.roberth.ordermanager.domain.order.repository.OrderRepository;
import com.roberth.ordermanager.generic.service.GenericService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService extends GenericService<Order, Long> {

    private final ClientService clientService;
    private final ArticleService articleService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ClientService clientService, ArticleService articleService) {
        super(orderRepository);
        this.clientService = clientService;
        this.articleService = articleService;
    }

    public Order save(OrderCreateDto orderCreateDto) throws BadRequestException {
        Client client = getClient(orderCreateDto.getClientId());
        List<Article> articles = getArticles(orderCreateDto.getArticleIds());

        Order order = new Order();
        order.setClient(client);
        order.setArticles(articles);
        order.setDate(new Date());
        order.setUniqueCode(generateOrderUniqueCode());

        return repository.save(order);
    }

    public Order update(OrderUpdateDto orderUpdateDto) throws BadRequestException {
        Optional<Order> optional = repository.findById(orderUpdateDto.getId());
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist an order with ID: " + orderUpdateDto.getId());
        }

        Client client = getClient(orderUpdateDto.getClientId());
        List<Article> articles = getArticles(orderUpdateDto.getArticleIds());

        Order order = optional.get();
        order.setClient(client);
        order.setArticles(articles);

        return repository.save(order);
    }

    @Override
    public void delete(Long id) throws BadRequestException {
        Optional<Order> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new BadRequestException("Does not exist an order with ID: " + id);
        }
        repository.deleteById(id);
    }

    private String generateOrderUniqueCode() {
        Order lastOrder = ((OrderRepository) repository).findLastOrder();
        if (lastOrder == null) {
            return "OC-000001";
        }
        String lastOrderCode = lastOrder.getUniqueCode().substring(3);
        int newOrderNumber = Integer.parseInt(lastOrderCode) + 1;
        return "OC-" + String.format("%06d", newOrderNumber);
    }

    private Client getClient(Long clientId) throws BadRequestException {
        Optional<Client> client = clientService.getById(clientId);
        if (client.isEmpty()) {
            throw new BadRequestException("Does not exist a client with ID: " + clientId);
        }
        return client.get();
    }

    private List<Article> getArticles(List<Long> articleIds) throws BadRequestException {
        List<Article> articles = articleService.getAllByIds(articleIds);
        if (articles.size() != articleIds.size()) {
            throw new BadRequestException("Some articles do not exist");
        }
        return articles;
    }

}
