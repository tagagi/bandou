package com.whut.bandou.bean;

import javax.persistence.*; 

@Entity
@Table(name = "t_score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float bookScore;

    @OneToOne
    private User user;

    @ManyToOne
    private Book book;


    public Score() {
    }

    public Score(Float bookScore, User user, Book book) {
        this.bookScore = bookScore;
        this.user = user;
        this.book = book;
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", bookScore=" + bookScore +
                ", user=" + user +
                ", book=" + book +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBookScore() {
        return bookScore;
    }

    public void setBookScore(Float bookScore) {
        this.bookScore = bookScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
