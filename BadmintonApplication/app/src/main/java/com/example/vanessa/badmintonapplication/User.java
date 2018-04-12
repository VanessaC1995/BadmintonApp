package com.example.vanessa.badmintonapplication;

/**
 * Created by Vanessa on 05/04/2018.
 */

public class User {
    public String id;
    public String username;
    public String name;
    public String password;

    public User(String id, String username, String name, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
}
