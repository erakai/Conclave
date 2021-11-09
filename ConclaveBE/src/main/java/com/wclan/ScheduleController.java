package com.wclan;


import com.wclan.model.Schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Placeholder code for fetching existing schedules.
 * Currently navigating to /api/schedule?name=[name] will create a new Schedule(name)
 *
 * @author Eric but he didn't put the javadoc in so he's bad
 * @version Nov 7, 2021
 */
@RestController
public class ScheduleController {

    @GetMapping("/api/schedule")
    public Schedule getSchedule(@RequestParam(value = "name", defaultValue = "(No name)") String name) {
        return new Schedule(name);
    }

}
