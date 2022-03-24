package by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_users")
public class AuditHibernate {

    private Long id;
    private String text;
    private String author;
    private LocalDateTime dt_create;

    public AuditHibernate() {
    }

    public AuditHibernate(String text, String author, LocalDateTime dt_create) {

        this.text = text;
        this.author = author;
        this.dt_create = dt_create;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
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

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }
}
