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
@Table(name="SCHEDULES")
public class Schedule {

    @Column(name="schedule_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="group_id", nullable=false)
    private Group group;

    @Column(name="name")
    private String name;

    @Column(name="time_slots")
    private String timeSlotsString;
    private transient List<TimeSlot> timeSlots;

    /**
     * Constructor for the schedule class.
     * @param name the name of the owner of the schedule.
     * @param timeSlotsString the string representation of the 15-minute time slots in the schedule.
     * @param group the group this schedule is assigned to.
     */
    public Schedule(String name, String timeSlotsString, Group group) {
        this.name = name;
        this.timeSlotsString = timeSlotsString;
        this.group = group;
    }

    //temporary
    public Schedule(String name) {
        this(name, "", null);
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

    public String getTimeSlotsString() {
        return timeSlotsString;
    }

    public void setTimeSlotsString(String timeSlotString) {
        this.timeSlotsString = timeSlotString;
    }

    // can't name it as "getTimeSlots" or Spring will get angry
    public List<TimeSlot> timeSlots() {
        if (this.timeSlots == null)
            this.timeSlots = TimeSlot.timeSlotsFromString(this.timeSlotsString);
        return timeSlots;
    }
}