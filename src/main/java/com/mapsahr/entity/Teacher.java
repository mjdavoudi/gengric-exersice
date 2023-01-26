package com.mapsahr.entity;

public class Teacher extends Person{
    private Integer teacherId;

    public Teacher(String firstName, String lastName, int age, int teacherId) {
        super(firstName, lastName, age);
        this.teacherId = teacherId;
    }

    public Integer getTeacherId() {
        return this.teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}
