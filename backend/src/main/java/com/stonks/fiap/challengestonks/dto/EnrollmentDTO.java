package com.stonks.fiap.challengestonks.dto;

import com.stonks.fiap.challengestonks.entities.Enrollment;

import java.io.Serializable;
import java.time.Instant;

public class EnrollmentDTO implements Serializable {

    private Long userId;
    private Long courseId;
    private Instant enrollMoment;
    private Instant refundMoment;

    public EnrollmentDTO() {

    }

    public EnrollmentDTO(Long userId, Long courseId, Instant enrollMoment, Instant refundMoment) {
        this.userId = userId;
        this.courseId = courseId;
        this.enrollMoment = enrollMoment;
        this.refundMoment = refundMoment;
    }

    public EnrollmentDTO(Enrollment entity) {
        userId = entity.getUser().getId();
        courseId = entity.getCourse().getId();
        enrollMoment = entity.getEnrollMoment();
        refundMoment = entity.getRefundMoment();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Instant getEnrollMoment() {
        return enrollMoment;
    }

    public void setEnrollMoment(Instant enrollMoment) {
        this.enrollMoment = enrollMoment;
    }

    public Instant getRefundMoment() {
        return refundMoment;
    }

    public void setRefundMoment(Instant refundMoment) {
        this.refundMoment = refundMoment;
    }
}
