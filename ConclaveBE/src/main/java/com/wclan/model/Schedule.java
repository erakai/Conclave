package com.wclan.model;

import javax.persistence.*;
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
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="time_slots")
    private String timeSlotString;
    private transient List<TimeSlot> timeSlots;

    /**
     * Constructor for the schedule class.
     * @param name the name of the owner of the schedule.
     * @param timeSlotString the string representation of the 15-minute time slots in the schedule.
     */
    public Schedule(String name, String timeSlotString) {
        this.name = name;
        this.timeSlotString = timeSlotString;
    }

    //temporary
    public Schedule(String name) {
        this(name, "");
    }

    public Schedule() {
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

    public String getTimeSlotString() {
        return timeSlotString;
    }

    public void setTimeSlotString(String timeSlotString) {
        this.timeSlotString = timeSlotString;
    }

    // can't name it as "getTimeSlots" or Spring will get angry
    public List<TimeSlot> timeSlots() {
        if (this.timeSlots == null)
            this.timeSlots = TimeSlot.timeSlotsFromString(this.timeSlotString);
        return timeSlots;
    }
}