package br.com.specification.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "age")
    private Integer age;

    @Column(nullable = false, name = "birthdate")
    private LocalDate birthDate;

    public UserEntity() {
    }

    public UserEntity(String name, String email, Integer age, LocalDate birthDate) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }
}