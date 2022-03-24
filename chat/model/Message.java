package by.it_academy.jd2.m_jd2_88_22.chat.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Message {


    private String sender;
    private String message;
    private LocalDateTime dateTime;


    public Message(String recipient, String message, LocalDateTime dateTime) {
        this.sender = recipient;
        this.message = message;
        this.dateTime = dateTime;
    }

    public Message() {
    }

    public String getRecipient() {
        return sender;
    }

    public void setRecipient(String recipient) {
        this.sender = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(sender, message1.sender) && Objects.equals(message, message1.message) && Objects.equals(dateTime, message1.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, message, dateTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yy ");
        return
                sender +
                " - " + message +
                " "+dateTime.format(dtf);
    }
}
