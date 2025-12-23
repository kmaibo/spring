package org.skypro.skyshop.skyshop.model.basket;


import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@SessionScope

public class ProductBasket {

    private final Map<UUID, Integer> basket = new ConcurrentHashMap<>();

        public void addBasket(UUID id) {
            basket.merge(id, 1, Integer::sum);
        }

    public Map<UUID, Integer> getBasket() {
        return Collections.unmodifiableMap(basket);
    }

}
