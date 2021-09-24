package com.stonks.fiap.challengestonks.dto;

import java.util.ArrayList;
import java.util.List;

public class TeacherDTO extends UserDTO {
    private Long yearsOfExperience;

    private List<CourseDTO> courses = new ArrayList<>();

    public Long getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Long yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }
}
