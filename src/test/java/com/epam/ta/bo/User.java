package com.epam.ta.bo;

public class User {
    private String username;
    private String password;
    private String domain;

    public User(String username, String password, String domain){
        this.username = username;
        this.password = password;
        this.domain = domain;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

}
