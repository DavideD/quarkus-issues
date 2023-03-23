package org.acme;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static jakarta.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "authors")
public class Author {
    private static final int MAX_NAME_LENGTH = 10;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "authorIds")
    @GenericGenerator(name = "authorIds", strategy = "org.acme.AuthorIdGenerator")
    private Integer id;

    @NotNull
    @Size(max = MAX_NAME_LENGTH)
    private String name;

    @JoinColumn(name = "author")
    @OneToMany(cascade = PERSIST, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public List<Book> getBooks() {
        return books;
    }
}
