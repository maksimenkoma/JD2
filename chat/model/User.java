package by.it_academy.jd2.m_jd2_88_22.chat.model;

import by.it_academy.jd2.m_jd2_88_22.demo.model.FlightFilter;

import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

public class User {


    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate dateBirth;

    public User(String login, String password, String firstName, String lastName, String middleName, LocalDate dateBirth) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateBirth = dateBirth;
    }

    public User(String login, String password, String firstName, String lastName, LocalDate dateBirth) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }

    public User() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(middleName, user.middleName) && Objects.equals(dateBirth, user.dateBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, firstName, lastName, middleName, dateBirth);
    }

    @Override
    public String toString() {
        return "User{" +
               "login='" + login + '\'' +
               ", password='" + password + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", middleName='" + middleName + '\'' +
               ", dateBirth=" + dateBirth +
               '}';
    }

    public static class Builder{

        private String login;
        private String password;
        private String firstName;
        private String lastName;
        private String middleName;
        private LocalDate dateBirth;

        private Builder() {
        }

        public static Builder builder() {

            return new Builder();
        }

        public Builder setLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;

        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setDateBirth(LocalDate dateBirth) {
            this.dateBirth = dateBirth;
            return this;
        }

        public User build(){

            return new User(login,password,firstName,lastName,middleName,dateBirth);
        }


    }




}
