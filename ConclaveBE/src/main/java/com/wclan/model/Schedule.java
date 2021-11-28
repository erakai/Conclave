package com.wclan.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Kian implement the actual class
 *
 * @author Eric
 * @version Nov 07, 2021
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule {

    @Column(name="schedule_id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "schedule")
    private List<TimeSlot> timeSlots;

    /**
     * Constructor for the Schedule class.
     * @param name the name of the owner of the schedule
     * @param start the date of the beginning of the range of the schedule.
     * @param end the date of the end of the range of the schedule.
     */
    public Schedule(String name, long start, long end) {
        this.name = name;
        this.timeSlots = populateTimeSlots(start, end);
    }

    //temporary
    public Schedule(String name) {
        this(name, 0, 0);
    }

    public Schedule() {
        this("Undefined", 0, 0);
    }


    /**
     * Populates time slot list. Time slots are instantiated in increments of 15 minutes. The list will always begin
     * at the same time or before the starting date, and finish at the same time or after the ending date.
     *
     * @param start the starting date range of the schedule
     * @param end the ending date range of the schedule
     */
    List<TimeSlot> populateTimeSlots(long start, long end) {
        final long fifteenMinutesMillis = 1000*60*15;
        List<TimeSlot> timeSlots = new ArrayList<>();

        // find closest 15 min time interval to the starting date (rounding down)
        // this is calculated in milliseconds since the Jan 1st 1970 epoch
        long currentTime = start - (start % fifteenMinutesMillis);

        // generate time slots in 15 minute intervals
        while (currentTime >= end) {
            TimeSlot timeSlot = new TimeSlot(this, currentTime, 0);
            timeSlots.add(timeSlot);
            currentTime += fifteenMinutesMillis;
        }
        return timeSlots;
    }

    public String getName() {
        return name;
    }

    public void setName(String scheduleName) {
        this.name = scheduleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

}