/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bhk
 */
public class User {
    private int id,points;
    private String username,email,photo,bio,password,status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    private boolean is_verified;

    public User(int id, int points, String username, String email, String photo, String bio, boolean is_verified,String Pwd) {
        this.id = id;
        this.points = points;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.bio = bio;
        this.is_verified = is_verified;
        this.password= Pwd;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
     public User(int points, String username, String email, String photo, String bio, boolean is_verified,String Pwd) {
        this.id = id;
        this.points = points;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.bio = bio;
        this.is_verified = is_verified;
        this.password= Pwd;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", points=" + points + ", username=" + username + ", email=" + email + ", photo=" + photo + ", bio=" + bio + ", is_verified=" + is_verified + ", Password= "+password+", Status="+status+ '}';
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    public User() {
    }
    public String idtoString ()
    {
        return String.valueOf(id);
    }
    public int getId() {
        return id;
    }


    public int getPoints() {
        return points;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoto() {
        return photo;
    }

    public String getBio() {
        return bio;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    
    
    
    
}
