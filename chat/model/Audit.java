package by.it_academy.jd2.m_jd2_88_22.chat.model;

import java.time.LocalDateTime;

public class Audit {
    private Long id;
    private String text;
    private String author;
    private User user;
    private LocalDateTime dt_create;

    public Audit() {
    }

    public Audit(String text, String author, LocalDateTime dt_create) {
        this.text = text;
        this.author = author;
        this.dt_create = dt_create;
    }

    public Audit(Long id, String text, String author, User user, LocalDateTime dt_create) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.user = user;
        this.dt_create = dt_create;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }
}
