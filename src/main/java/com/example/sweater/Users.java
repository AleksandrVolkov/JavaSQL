package com.example.sweater;

public class Users {
    private long id;
    private String fName;
    private String lName;
    private String login;

    public Users(long id, String fName, String lName, String login) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}
