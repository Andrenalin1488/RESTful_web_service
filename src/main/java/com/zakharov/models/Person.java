package com.zakharov.models;

import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.Constraint;
import javax.validation.constraints.*;

@Entity
@Table(name = "Person")
public class Person {

    public static final int MIN_AGE = 16;
    public static final int MAX_AGE = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name has wrong value")
    @Size(min = 2,max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotEmpty(message = "Surname has wrong value")
    @Size(min = 2,max = 30, message = "Surname should be between 2 and 30 characters")
    private String surname;


    @Column(name = "age", nullable = false)
    @Min(value = MIN_AGE, message = "Age should be grater than " + MIN_AGE)
    @Max(value = MAX_AGE, message = "Age must be less than you specified")
    private int age;

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty(message = "Email should not be empty ")
    @Email(message = "This is not email")
    private String email;

    public Person() {
    }

    public Person(int id,
                  @NotEmpty(message = "Name has wrong value")
                  @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name,
                  @NotEmpty(message = "Surname has wrong value")
                  @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters") String surname,
                  @Min(value = MIN_AGE, message = "Age should be grater than " + MIN_AGE)
                  @Max(value = MAX_AGE, message = "Age must be less than you specified") int age,
                  @NotEmpty(message = "Email should not be empty ")
                  @Email(message = "This is not email") String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
