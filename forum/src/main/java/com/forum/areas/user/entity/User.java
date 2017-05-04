package com.forum.areas.user.entity;

import com.forum.areas.question.entity.Question;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@SuppressWarnings("ALL")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Basic
    private String password;

    @Basic
    private String email;

    @Basic
    private char gender;

    @Basic
    private String bio;

    @Basic
    private String language;

    @Column(name = "register_date")
    private LocalDate registerDate;

    private Boolean isAccountNotExpired;
    private Boolean isAccountNotLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_questions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> authorities;

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNotExpired;
    }

    public void setAccountNotExpired(Boolean accountNotExpired) {
        this.isAccountNotExpired = accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNotLocked;
    }

    public void setAccountNotLocked(Boolean accountNotLocked) {
        this.isAccountNotLocked = accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public LocalDate getRegisterDate() {
        return this.registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Set<Question> getQuestions() {
        if (this.questions == null) {
            this.setQuestions(new HashSet<>());
        }

        return this.questions;
    }

    protected void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public Set<Role> getAuthorities() {
        if (this.authorities == null) {
            this.setAuthorities(new HashSet<>());
        }

        return this.authorities;
    }

    protected void setAuthorities(Set<Role> authorities) {
        if (this.authorities == null) {
            this.authorities = new HashSet<>();
        }

        this.authorities = authorities;
    }

    public void addQuestion(Question question) {
        this.getQuestions().add(question);
    }
}