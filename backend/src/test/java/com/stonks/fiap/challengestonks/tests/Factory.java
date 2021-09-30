package com.stonks.fiap.challengestonks.tests;

import com.stonks.fiap.challengestonks.dto.CourseDTO;
import com.stonks.fiap.challengestonks.dto.StudentDTO;
import com.stonks.fiap.challengestonks.dto.TeacherDTO;
import com.stonks.fiap.challengestonks.dto.UserDTO;
import com.stonks.fiap.challengestonks.entities.*;

import java.time.Instant;

public class Factory {

    public static User createUser() {
        User user = new User(1L, "Henrique", "Lima", Instant.now(), "henrique@gmail.com", "12345678", new Role(1L, null));
        return user;
    }

    public static UserDTO createUserDTO() {
        UserDTO dto = new UserDTO(createUser());
        return dto;
    }

    public static Teacher createTeacher() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setPassword("12345678");
        teacher.setFirstName("Mario");
        teacher.setLastName("Silva");
        teacher.setYearsOfExperience("3 ANOS");
        teacher.setRole(new Role(1L, null));
        return teacher;
    }

    public static TeacherDTO createTeacherDTO() {
        TeacherDTO dto = new TeacherDTO(createTeacher());
        return dto;
    }

    public static Student createStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setPassword("12345678");
        student.setFirstName("Mario");
        student.setLastName("Silva");
        student.setRegistrationCode("33333");
        student.setRole(new Role(1L, null));
        return student;
    }

    public static StudentDTO createStudentDTO() {
        StudentDTO dto = new StudentDTO(createStudent());
        return dto;
    }

    public static Course createCourse() {
        Course course = new Course(1L, "Java", "Learn Java", 3);
        return course;
    }

    public static CourseDTO createCourseDTO() {
        CourseDTO dto = new CourseDTO(createCourse());
        return dto;
    }

}
