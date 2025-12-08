package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    public Set<Searchable> catalog;
    private int currentSize = 0;


    public SearchEngine(int capacity) {
        this.catalog = new HashSet<Searchable>();
    }

    public void add(Searchable item) {
        catalog.add(item);
    }

    public Searchable[] search(String query) {

        if (query == null || query.trim().isEmpty()) {
            return new Searchable[0];
        }

        String lowerQuery = query.trim().toLowerCase();

        List<Searchable> results = catalog.stream()
                .filter(item -> item.getSearchTerm().contains(lowerQuery))
                .collect(Collectors.toList());
        results.sort(Comparator.comparing(Searchable::getName));


        return results.stream()
                .limit(5)
                .toArray(Searchable[]::new);
    }

    private int countOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SearchEngine that = (SearchEngine) o;
        return currentSize == that.currentSize && Objects.equals(catalog, that.catalog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catalog, currentSize);
    }
}

