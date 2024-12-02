package com.purwadhika.mini_project.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id
    private int user_id;
    private String username;
    private String email;
    private  String password;
    private String referral_code;

    public int getId() {
        return user_id;
    }

    public void setId(int id) {
        this.user_id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReferal_code() {
        return referral_code;
    }

    public void setReferall_code(String referral_code) {
        this.referral_code = referral_code;
    }
    @Override
    public String toString() {
        return "Users{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", referall_code='" + referral_code + '\'' +
                '}';
    }
}
