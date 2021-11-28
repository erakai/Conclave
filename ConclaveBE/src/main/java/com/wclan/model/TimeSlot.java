package com.wclan.model;

import javax.persistence.*;

@Entity
@Table(name = "TIMESLOT")
public class TimeSlot implements Comparable<TimeSlot> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="schedule_id", nullable = false)
    private Schedule schedule;

    @Column(name = "date")
    private long date;

    @Column(name = "rating")
    private int rating;

    public TimeSlot(Schedule schedule, long date, int rating) {
        this.schedule = schedule;
        this.date = date;
        this.rating = rating;
    }

    public TimeSlot(Schedule schedule) {
        this.schedule = schedule;
    }

    public TimeSlot() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int compareTo(TimeSlot other) {
        long diff = this.date - other.date;
        if (diff>0) return 1;
        else if (diff<0) return -1;
        return 0;
    }

}
