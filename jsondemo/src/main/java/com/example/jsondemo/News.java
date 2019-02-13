package com.example.jsondemo;

import java.util.List;

public class News {

    private Long id;
    private String title;
    private String text;
    private List<Author> authors;

    public News(){

    }

    public News(final Long id, final String title, final String text, final List<Author> authors) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(final List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", authors=" + authors +
                '}';
    }
}
