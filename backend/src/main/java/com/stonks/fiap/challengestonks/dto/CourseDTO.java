package com.stonks.fiap.challengestonks.dto;

import com.stonks.fiap.challengestonks.entities.Course;

import java.io.Serializable;

public class CourseDTO implements Serializable {

    private Long id;
    private String name;
    private String description;
    private Integer duration;

    public CourseDTO() {

    }

    public CourseDTO(Long id, String name, String description, Integer duration) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    public CourseDTO(Course entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        duration = entity.getDuration();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
