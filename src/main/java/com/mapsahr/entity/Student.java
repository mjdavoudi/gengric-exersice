package com.mapsahr.entity;

public class Student extends Person{
    private Integer studentId;

    public Student(String firstName, String lastName, int age, int studentId) {
        super(firstName, lastName, age);
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return this.studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
