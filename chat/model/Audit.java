package by.it_academy.jd2.m_jd2_88_22.chat.model;

import java.time.LocalDateTime;

public class Audit {

    private Long id;
    private String text;
    private User author;
    private LocalDateTime dt_create;

    public Audit() {
    }

    public Audit(String text, User author, LocalDateTime dt_create) {
        this.text = text;
        this.author = author;
        this.dt_create = dt_create;
    }

    public Audit(Long id, String text, User author, LocalDateTime dt_create) {
        this.id = id;
        this.text = text;
        this.author = author;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }
}
