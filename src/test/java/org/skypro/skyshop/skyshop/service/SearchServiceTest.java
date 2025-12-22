package org.skypro.skyshop.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.skyshop.model.product.Product;
import org.skypro.skyshop.skyshop.model.product.SimpleProduct;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void searchWhenNoProductsInStorage() {
        when(storageService.getAllProduct()).thenReturn(List.of());

        Collection<SearchResult> result = searchService.search("Ноутбук");

        assertTrue(result.isEmpty());
        verify(storageService).getAllProduct();
    }


    @Test
    void searchWhenProductsExistButNoMatch() {
        List<Product> products = List.of(
                new SimpleProduct("Телефон", 25590, UUID.randomUUID()),
                new SimpleProduct("Планшет", 34299,UUID.randomUUID())
        );
        when(storageService.getAllProduct()).thenReturn(products);

        Collection<SearchResult> result = searchService.search("Ноутбук");

        assertTrue(result.isEmpty());
        verify(storageService).getAllProduct();
    }

    @Test
    void searchWhenMatchingProductExists() {
        List<Product> products = List.of(
                new SimpleProduct("Игровой ноутбук", 65990,UUID.randomUUID()),
                new SimpleProduct("Ноутбук", 32000,UUID.randomUUID()),
                new SimpleProduct("Смартфон", 35490, UUID.randomUUID())
        );
        when(storageService.getAllProduct()).thenReturn(products);

        Collection<SearchResult> result = searchService.search("laptop");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(p -> p.getName().contains("gaming")));
        assertTrue(result.stream().anyMatch(p -> p.getName().contains("office")));
        verify(storageService).getAllProduct();
    }

    @Test
    void searchWithEmptyQuery() {
        when(storageService.getAllProduct()).thenReturn(List.of(new SimpleProduct("test", 100,UUID.randomUUID())));

        Collection<SearchResult> result = searchService.search("");

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }
}
