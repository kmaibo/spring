package org.skypro.skyshop.skyshop.controller;

import org.skypro.skyshop.skyshop.model.article.Article;
import org.skypro.skyshop.skyshop.model.product.Product;
import org.skypro.skyshop.skyshop.service.SearchResult;
import org.skypro.skyshop.skyshop.service.SearchService;
import org.skypro.skyshop.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController

public class ShopController {

    private final StorageService storageService;
    private  final SearchService searchService;


    public ShopController(StorageService storageService,SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }


    @GetMapping("/products")
    public Collection<Product> getAllProduct() {
        return storageService.getAllProduct();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticle() {
        return storageService.getAllArticle();
    }

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam(required = false) String pattern) {
        return (List<SearchResult>) searchService.search(pattern);
    }

}