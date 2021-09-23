package com.stonks.fiap.challengestonks.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_student")
public class Student extends User {
    private String registrationCode;

    @ManyToMany
    @JoinTable(name = "tb_students_course", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
