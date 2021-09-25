package com.stonks.fiap.challengestonks.dto;

import com.stonks.fiap.challengestonks.entities.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends UserDTO {
    private Integer yearsOfExperience;
    private List<CourseDTO> courses = new ArrayList<>();

    public TeacherDTO() {

    }

    public TeacherDTO(Teacher entity) {
        super(entity);
        yearsOfExperience = entity.getYearsOfExperience();
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }
}
