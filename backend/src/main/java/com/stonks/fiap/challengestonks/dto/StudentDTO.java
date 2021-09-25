package com.stonks.fiap.challengestonks.dto;

import com.stonks.fiap.challengestonks.entities.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDTO extends UserDTO {

    private String registrationCode;
    private List<CourseDTO> courses = new ArrayList<>();

    public StudentDTO() {

    }

    public StudentDTO(Student entity) {
        super(entity);
        registrationCode = entity.getRegistrationCode();
    }

    public String getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(String registrationCode) {
        this.registrationCode = registrationCode;
    }
}
