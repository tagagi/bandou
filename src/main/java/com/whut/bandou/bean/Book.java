package com.whut.bandou.bean;

import javax.persistence.*;
import java.util.List;

@Entity 
@Table(name = "t_book")
public class Book {

    @Id     //主键标识
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ISBN;
    private String bookname;
    private String writer;
    private String publish;
    private double score;
    private int count;   //总评分人数

    @Basic(fetch = FetchType.LAZY)//懒加载
    @Lob
    private String description;

    @OneToMany(mappedBy = "book")
    private List<Score> scores;

    @OneToMany(mappedBy = "book")
    private List<Comment> comments;

    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", bookname='" + bookname + '\'' +
                ", writer='" + writer + '\'' +
                ", publish='" + publish + '\'' +
                ", score=" + score +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", scores=" + scores +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
