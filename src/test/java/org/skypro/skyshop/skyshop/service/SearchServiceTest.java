package org.skypro.skyshop.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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
        when(storageService.getAllSearchable()).thenReturn(List.of());

        Collection<SearchResult> result = searchService.search("Ноутбук");

        assertTrue(result.isEmpty());
        verify(storageService).getAllSearchable();
    }


    @Test
    void searchWhenProductsExistButNoMatch() {
        List<Searchable> products = List.of(
                new SimpleProduct("Телефон", 25590, UUID.randomUUID()),
                new SimpleProduct("Планшет", 34299,UUID.randomUUID())
        );
        when(storageService.getAllSearchable()).thenReturn(products);

        Collection<SearchResult> result = searchService.search("Ноутбук");

        assertTrue(result.isEmpty());
        verify(storageService).getAllSearchable();
    }

    @Test
    void searchWhenMatchingProductExists() {
        List<Searchable> products = List.of(
                new SimpleProduct("Игровой ноутбук", 65990,UUID.randomUUID()),
                new SimpleProduct("Ноутбук", 32000,UUID.randomUUID()),
                new SimpleProduct("Смартфон", 35490, UUID.randomUUID())
        );
        when(storageService.getAllSearchable()).thenReturn(products);

        Collection<SearchResult> result = searchService.search("ноутбук");

        assertEquals(2, result.size());
        assertTrue(result.stream()
                .allMatch(r -> r.getName().toLowerCase().contains("ноутбук")));
        verify(storageService).getAllSearchable();
    }

    @Test
    void searchWithEmptyQuery() {
        when(storageService.getAllSearchable()).thenReturn(List.of(new SimpleProduct("test", 100,UUID.randomUUID())));

        Collection<SearchResult> result = searchService.search("");

        assertTrue(result.isEmpty());
        verify(storageService, never()).getAllSearchable();
    }

    @Test
    void searchWithNullQuery() {
        Collection<SearchResult> result = searchService.search(null);

        assertTrue(result.isEmpty());
        verify(storageService, never()).getAllSearchable();
    }
}
