package com.wclan.model;

import com.wclan.exception.BadResourceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents a block of time in a schedule. Primarily used to assist in calculations.
 * @author Eric
 */
public class TimeSlot {

    /**
     * Schedules are stored as a string representation of timeslots.
     *
     * Currently, it looks like:
     * {start_date}-{rating1},{available1}-{rating2},{available2} ... etc
     */
    public static final String TIME_SLOT_DELIM = "-";
    public static final String TIME_SLOT_INFO_DELIM = ",";

    private static final long FIFTEEN_MINUTES_MILLIS = 1000*60*15;

    /**
     * Generates list of instantiated TimeSlot objects, mainly for calculation purposes
     * @param str the string representation of the time slots
     * @return list of instantiated time slots
     * @throws BadResourceException if str is improperly formatted
     */
    public static List<TimeSlot> timeSlotsFromString(String str) {
        List<TimeSlot> timeSlots = new ArrayList<>();
        try {
            Scanner timeSlotScanner = new Scanner(str).useDelimiter(TIME_SLOT_DELIM);
            long startDate = Long.parseLong(timeSlotScanner.next());

            while (timeSlotScanner.hasNext()) {
                String timeSlotStr = timeSlotScanner.next();
                Scanner infoScanner = new Scanner(timeSlotStr).useDelimiter(TIME_SLOT_INFO_DELIM);

                long date = startDate + FIFTEEN_MINUTES_MILLIS * timeSlots.size();
                int rating = Integer.parseInt(infoScanner.next());
                boolean available = Integer.parseInt(infoScanner.next()) == 1;
                timeSlots.add(new TimeSlot(date, rating, available));
            }
        } catch (Exception e) {
            throw new BadResourceException("Invalid timeslot string");
        }
        return timeSlots;
    }

    public static String timeSlotsToString(List<TimeSlot> timeSlots) {
        if (timeSlots.isEmpty()) return "";

        long startDate = timeSlots.get(0).getDate();
        StringBuilder str = new StringBuilder(startDate + TIME_SLOT_DELIM);
        for (TimeSlot timeSlot : timeSlots) {
            str.append(timeSlot.toString()).append(TIME_SLOT_DELIM);
        }
        return str.substring(0, str.length()-TIME_SLOT_DELIM.length()); // remove trailing delimiter
    }

    private long date;

    private int rating;

    private boolean available;

    /**
     * Constructs a new TimeSlot object.
     * @param date the date this TimeSlot represents, in milliseconds since the epoch
     * @param rating the user's rating of how well this timeslot works for them
     * @param available whether the slot is available or not
     */
    public TimeSlot(long date, int rating, boolean available) {
        this.date = date;
        this.rating = rating;
        this.available = available;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "" + rating + TIME_SLOT_INFO_DELIM + available;
    }

}
