package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 99;

    public FixPriceProduct(String name, UUID id) {
        super(name, id);
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return name + ": Фиксированная цена " + FIXED_PRICE;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}