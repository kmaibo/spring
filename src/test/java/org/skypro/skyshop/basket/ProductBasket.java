package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class ProductBasket {
    private HashMap<String, List<Product>> basket = new HashMap<>();

    public void addProduct(Product product) {
        String name = product.getName();
        basket.computeIfAbsent(name, k -> new ArrayList<>()).add(product);
    }


    public void printContent() {
//        for (List<Product> productList : basket.values()) {
//            for (Product product : productList) {
//                System.out.println(product);
//            }
//        }
        basket.values().stream().flatMap(Collection::stream).forEach(System.out::println);

    }


        public boolean removeProduct (Product product){
            String name = product.getName();
            List<Product> products = basket.get(name);
            if (products != null) {
                boolean removed = products.remove(product);
                if (removed && products.isEmpty()) {
                    basket.remove(name); // Удаляем ключ, если список пуст
                }
                return removed;
            }
            return false;
        }
    }