package org.skypro.skyshop.model.product;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name,id);
        if (price <= 0) {
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        this.price = price;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }
    @Override
    public String toString() {
        return name + ": " + getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SimpleProduct that = (SimpleProduct) o;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}