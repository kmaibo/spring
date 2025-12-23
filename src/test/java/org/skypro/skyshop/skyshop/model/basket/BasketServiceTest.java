package org.skypro.skyshop.skyshop.model.basket;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.skyshop.service.BasketService;
import org.skypro.skyshop.skyshop.service.StorageService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;


class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;
    private SimpleProduct bread;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addNonExistingProduct_throwsException() {
        Mockito.when(storageService.getProductById(UUID.randomUUID())).
                thenThrow(new NoSuchProductException("Не найден"));

        Assertions.assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(UUID.randomUUID()));

    }

    @Test
    void addExistingProduct_callsBasketAdd() {
        UUID productId = UUID.randomUUID();
        Mockito.when(storageService.getProductById(productId))
                .thenReturn(bread);

        basketService.addProductToBasket(productId);

        Mockito.verify(productBasket).addBasket(productId);

    }

    @Test
    void getUserBasket_emptyBasket_returnsEmptyUserBasket() {
        Mockito.when(productBasket.getBasket())
                .thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    void getUserBasket_withProducts_returnsFilledBasket() {
        UUID productId = UUID.randomUUID();

        SimpleProduct bread = new SimpleProduct("Хлеб", 50,UUID.randomUUID());

        Mockito.when(productBasket.getBasket())
                .thenReturn(Map.of(productId, 2));

        Mockito.when(storageService.getProductById(productId))
                .thenReturn(bread);

        UserBasket userBasket = basketService.getUserBasket();

        Assertions.assertEquals(1, userBasket.getItems().size());
        Assertions.assertEquals(2, userBasket.getItems().get(0).quantity());
    }
}