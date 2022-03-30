package by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "message_table")
public class MessageHibernate {

    private Long id;
    private String recipient;
    private String messages;
    private LocalDateTime dateTime;
    private String fk_message_from;


    public MessageHibernate() {
    }

    public MessageHibernate(String recipient, String messages, LocalDateTime dateTime, String fk_message_from) {

        this.recipient = recipient;
        this.messages = messages;
        this.dateTime = dateTime;
        this.fk_message_from = fk_message_from;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }



    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getFk_message_from() {
        return fk_message_from;
    }

    public void setFk_message_from(String fk_message_from) {
        this.fk_message_from = fk_message_from;
    }
}
