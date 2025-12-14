package org.skypro.skyshop.skyshop.model.basket;

import org.skypro.skyshop.skyshop.model.product.Product;

    public record BasketItem(Product product, int quantity) {
    }

