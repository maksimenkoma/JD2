package by.it_academy.jd2.m_jd2_88_22.chat.model.hibernate;


import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "user_table")
public class UserHibernate {


    @Id
    private String login;

    private String password;
    private String firstName;
    private String lastName;
    private String middleName;

    @Column(name = "datebirth")
    private LocalDate dateBirth;


    @Column(columnDefinition = "serial")
    @Generated(value = GenerationTime.INSERT)
    private Long id;

    public UserHibernate() {
    }

    public UserHibernate(String login, String password, String firstName, String lastName, String middleName, LocalDate dateBirth) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateBirth = dateBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }


}
