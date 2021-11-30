package com.wclan.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Class representing a group of people and their schedules.
 */
@Entity
@Table(name="GROUPS")
public class Group {

    // can add more columns such as tasks/items needed... etc. later

    @Column(name="group_id")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="group_name")
    private String eventName;

    @OneToMany(targetEntity=Schedule.class, mappedBy="group")
    private List<Schedule> schedules;

    public Group(String eventName, List<Schedule> schedules) {
        this.eventName = eventName;
        this.schedules = schedules;
    }

    public Group(String groupName) {
        this(groupName, new ArrayList<>());
    }

    public Group() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String groupName) {
        this.eventName = groupName;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(id, group.id) && Objects.equals(eventName, group.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventName);
    }
}
