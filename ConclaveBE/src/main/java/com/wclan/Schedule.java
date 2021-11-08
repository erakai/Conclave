package com.wclan;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Eric
 * @version Nov 07, 2021
 */

@Entity
public class Schedule {

    // TODO: Kian implement the actual class

    private @Id @GeneratedValue Long id;

    private String scheduleName;

    public Schedule(String name) {
        this.scheduleName = name;
    }

    // JPA wants a no-arg constructor for some reason
    public Schedule() {
        this("(No name)");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id) && Objects.equals(scheduleName, schedule.scheduleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduleName);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", name='" + scheduleName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}