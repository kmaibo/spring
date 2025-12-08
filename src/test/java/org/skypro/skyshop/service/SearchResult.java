package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;


public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public static SearchResult fromSearchable(Searchable item) {
        String name = (item instanceof Product)
                ? ((Product) item).getName()
                : ((Article) item).getTitle();

        String contentType = (item instanceof Product) ? "product" : "article";

        return new SearchResult(item.getId().toString(), name, contentType);
    }
}

