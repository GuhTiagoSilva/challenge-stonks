package com.stonks.fiap.challengestonks.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_teacher")
public class Teacher extends User {

    private String yearsOfExperience;

    @ManyToMany
    @JoinTable(name = "tb_teacher_course", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<>();

    public String getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(String yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
