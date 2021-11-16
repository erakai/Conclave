package com.wclan.model;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** TimeSlot
 * A subclass for schedule, each
 * object representing every (30) minute block
 * that is available in the 24 hr day
 *
 * @author Kian
 * @version Nov 09, 2021
 **/
@Entity
public class TimeSlot {
    private @Id @GeneratedValue Long key; //No clue what this does
    private boolean reserved; //whether this specific time slot is open or reserved
    private final int numMinutes; //the number of minutes out of a day that a timeslot is

    public TimeSlot(boolean reserved) {
        this.reserved = reserved;
        numMinutes = 30; //initialized as 30 minutes
    }

    public TimeSlot() {
        this(false);
    }

    //accessors and mutators
    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public int getNumMinutes() {
        return numMinutes;
    }
}
