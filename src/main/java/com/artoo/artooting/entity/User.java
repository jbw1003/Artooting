package com.artoo.artooting.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    //@NotBlank(message = "Sex is required")
    private String sex; // Male, Female, Other, Prefer not to say

    //@NotBlank(message = "Phone number is required")
    //@Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    //@Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number")
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToMany(mappedBy = "attendees", fetch = FetchType.LAZY)
    private Set<Party> parties = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    // Constructors, Getters, and Setters

    public User() {
    }

    // getters and setters

    public Long getId() {
        return id;
    }
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
