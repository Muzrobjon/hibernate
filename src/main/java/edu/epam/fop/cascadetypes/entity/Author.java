package edu.epam.fop.cascadetypes.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    private Integer id;
    @Column
    private String name;
    @OneToMany(mappedBy = "author", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(Integer id, String name, List<Book> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(Book book) {
        if (!this.books.contains(book)) {
            this.books.add(book);
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}