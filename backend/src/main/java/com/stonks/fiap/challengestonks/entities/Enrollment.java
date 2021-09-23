package com.stonks.fiap.challengestonks.entities;

import com.stonks.fiap.challengestonks.entities.pk.EnrollmentPk;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_enrollment")
public class Enrollment implements Serializable {

    @EmbeddedId
    private EnrollmentPk id = new EnrollmentPk();

    private Instant enrollMoment;
    private Instant refundMoment;

    public Enrollment() {

    }

    public Enrollment(User user, Course course, Instant enrollMoment, Instant refundMoment) {
        this.id.setUser(user);
        this.id.setCourse(course);
        this.enrollMoment = enrollMoment;
        this.refundMoment = refundMoment;
    }

    public User getUser() {
        return this.id.getUser();
    }

    public void setUser(User user) {
        this.id.setUser(user);
    }

    public Course getCourse() {
        return this.id.getCourse();
    }

    public void setCourse(Course course) {
        this.id.setCourse(course);
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
