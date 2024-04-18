package edu.epam.fop.cascadetypes.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Integer id;
    @Column
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private Author author;

    public Book() {
    }

    public Book(Integer id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author id=" + author.getId() +
                ", author=" + author.getName() +
                '}';
    }
}