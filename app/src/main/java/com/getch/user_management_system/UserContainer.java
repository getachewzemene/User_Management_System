package com.getch.user_management_system;
public class UserContainer {
    private String fulname,username,email,password,phone,gender;

    public UserContainer(String fulname, String username, String email,String password, String phone, String gender) {
        this.fulname = fulname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
    }

    public String getFulname() {
        return fulname;
    }

    public void setFulname(String fulname) {
        this.fulname = fulname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
