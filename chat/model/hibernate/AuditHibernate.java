package by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_users")

public class AuditHibernate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;


   @ManyToOne
   @JoinColumn(name = "author")
    private UserHibernate author;

    private LocalDateTime dt_create;

    public AuditHibernate() {
    }

    public AuditHibernate(Long id, String text, UserHibernate author, LocalDateTime dt_create) {
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

    public UserHibernate getAuthor() {
        return author;
    }

    public void setAuthor(UserHibernate author) {
        this.author = author;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }
}
