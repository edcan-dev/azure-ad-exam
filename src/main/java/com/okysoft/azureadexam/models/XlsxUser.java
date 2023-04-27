package com.okysoft.azureadexam.models;


// POJO de Usuario
public class XlsxUser {

    private String firstName;
    private String lastName;
    private String email;
    private String fullName;
    private String userName;
    private String password;
    private String activeDirectoryUserId;


    @Override
    public String toString() {
        return "XlsxUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public XlsxUser() {
    }

    public XlsxUser(String firstName, String lastName, String email, String fullName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.fullName = fullName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActiveDirectoryUserId() {
        return activeDirectoryUserId;
    }

    public void setActiveDirectoryUserId(String activeDirectoryUserId) {
        this.activeDirectoryUserId = activeDirectoryUserId;
    }
}
