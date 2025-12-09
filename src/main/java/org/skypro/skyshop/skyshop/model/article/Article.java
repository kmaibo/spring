package org.skypro.skyshop.skyshop.model.article;




import org.skypro.skyshop.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable, Comparable<Article> {
    private final String title;
    private final String text;
    private final UUID id;

    public Article(String title, String text, UUID id) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(Article other) {
        int lengthDiff = Integer.compare(other.title.length(), this.title.length());
        if (lengthDiff != 0) {
            return lengthDiff;
        }
        return this.title.compareTo(other.title);
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return title + " " + text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
