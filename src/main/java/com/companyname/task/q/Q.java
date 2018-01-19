package com.companyname.task.q;

/**
 * @author Igor Borshchev
 */
public class Q {
    private String author;
    private String text;

    public Q(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Quote{" + "author='" + author + '\'' + ", text='" + text + '\'' + '}';
    }
}