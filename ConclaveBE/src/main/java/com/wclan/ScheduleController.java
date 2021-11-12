package com.wclan;


import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.web.bind.annotation.*;

/**
 * RESTful controller for accessing, adding, removing, and modifying schedules.
 *
 * @author Eric but he didn't put the javadoc in so he's bad
 * @version Nov 7, 2021
 */
@BasePathAwareController
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ScheduleController {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleController(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    /**
     * Attempt to retrieve a schedule from the repository by name.
     * @param name the name of the schedule owner
     * @return the schedule owned by (name), if it exists, otherwise null (for now).
     */
    @GetMapping("/schedule")
    public Schedule getSchedule(@RequestParam(value = "name", defaultValue = "Undefined") String name) {
        return scheduleRepository.getScheduleByName(name);
    }

    /**
     * Adds a new schedule to the repository.
     * @param name the name of the schedule owner
     * @return JSON response indicating success (or failure somehow)
     */
    @PostMapping("/addSchedule")
    public @ResponseBody String addNewSchedule(@RequestParam(value="name") String name) {
        Schedule schedule = new Schedule(name);
        scheduleRepository.save(schedule);
        return "Successfully added schedule with name " + name;
    }
}
