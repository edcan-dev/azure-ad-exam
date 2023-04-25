package com.okysoft.azureadexam.models;


// POJO de Usuario
public class XlsxUser {

    private String firstName;
    private String lastName;
    private Integer age;
    private String profession;
    private String grade;
    private String maritalStatus;
    private String city;
    private String jobTitle;

    public XlsxUser() {

    }

    public XlsxUser(String firstName, String lastName, Integer age, String profession, String grade, String maritalStatus, String city, String jobTitle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
        this.grade = grade;
        this.maritalStatus = maritalStatus;
        this.city = city;
        this.jobTitle = jobTitle;
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
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getMaritalStatus() {
        return maritalStatus;
    }
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "XlsxUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                ", grade='" + grade + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", city='" + city + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
