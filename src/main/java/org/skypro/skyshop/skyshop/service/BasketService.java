package org.skypro.skyshop.skyshop.service;

import org.skypro.skyshop.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID id) {
        Optional<Product> product = storageService.getProductById(id);
        if (product.isEmpty()) {
            throw new IllegalArgumentException("Продукт не найден");
        }
        basket.addBasket(id);
    }

    public UserBasket getUserBasket() {
        return new UserBasket(
                basket.getBasket().entrySet().stream()
                        .map(entry -> {
                            Product product = storageService.getProductById(entry.getKey())
                                    .orElseThrow(() -> new IllegalArgumentException(
                                            "Продукт не найден в хранилище: " + entry.getKey()
                                    ));
                            return new BasketItem(product, entry.getValue());
                        })
                        .collect(Collectors.toList())
        );
    }


}
