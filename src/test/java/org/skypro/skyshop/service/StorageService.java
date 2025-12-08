package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();

    public StorageService() {
        initTestData();
    }

    private void initTestData() {
        products.put(UUID.randomUUID(), new SimpleProduct("Ноутбук", 49590, UUID.randomUUID()));
        products.put(UUID.randomUUID(), new SimpleProduct("Смартфон", 31990, UUID.randomUUID()));

        articles.put(UUID.randomUUID(), new Article("Как выбрать ноутбук", "Советы по выбору процессора", UUID.randomUUID()));
    }

    public Collection<Product> getAllProduct() {
        return products.values();
    }

    public Collection<Article> getAllArticle() {
        return articles.values();
    }

    public Collection<Searchable> getAllSearchable() {
        List<Searchable> all = new ArrayList<>();
        all.addAll(products.values());
        all.addAll(articles.values());
        return all;
    }

}