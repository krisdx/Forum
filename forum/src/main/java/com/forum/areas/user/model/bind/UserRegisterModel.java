package com.forum.areas.user.model.bind;

import com.forum.areas.user.validation.ConfirmPassword;
import com.forum.areas.user.validation.Password;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ConfirmPassword
public class UserRegisterModel implements Serializable {

    @Size(min = 5, max = 20, message = "Password must be between 5 and 20 symbols long.")
    private String username;

    @NotBlank(message = "Full name cannot be empty.")
    @Pattern(regexp = "^([A-ZА-Я][a-zа-я]+)(\\s*[A-ZА-Я][a-zа-я]+)+$", message = "Invalid Full name format.")
    private String fullName;

    @Pattern(regexp = "^[a-z][a-z.0-9]*@[a-z]+\\.[a-z]+(\\.[a-z]+)*$", message = "Invalid email format.")
    private String email;

    private char gender;

    @Password
    private String password;

    @NotBlank(message = "Confirm password cannot be empty.")
    private String confirmPassword;

    public UserRegisterModel() {
    }

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}