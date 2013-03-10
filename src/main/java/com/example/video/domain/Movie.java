package com.example.video.domain;


import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    public static final Price CHILDRENS = new ChildrensPrice();
    public static final Price REGULAR = new RegularPrice();
    public static final Price NEW_RELEASE = new NewReleasePrice();

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    public Movie() {
    }

    public Movie(String title, Price price) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
