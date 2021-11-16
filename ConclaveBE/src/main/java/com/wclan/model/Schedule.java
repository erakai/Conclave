package com.wclan.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * TODO: Kian implement the actual class
 *
 * @author Eric
 * @version Nov 07, 2021
 */
@Entity
@Table(name="SCHEDULE")
public class Schedule {

    @Column(name="id")
    @GeneratedValue
    @Id
    private Long id;

    @Column(name="name")
    private String name;

    public Schedule(String name) {
        this.name = name;
    }

    public Schedule() {
        this("Undefined");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(name, schedule.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}