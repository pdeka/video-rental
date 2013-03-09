package com.example.app.domain;


import javax.persistence.*;

@Entity
public class Movie {
    public static final Price CHILDRENS = new ChildrensPrice();
    public static final Price REGULAR = new RegularPrice();
    public static final Price NEW_RELEASE = new NewReleasePrice();

    @Id
    @Column(name = "AdviserID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    private Price price;

    public Movie() {
    }

    public Movie(String title, Price price) {
        this.title = title;
        setPrice(price);
    }

    public String getTitle() {
        return title;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }
}
