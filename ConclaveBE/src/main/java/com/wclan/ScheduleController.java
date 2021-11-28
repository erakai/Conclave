package com.wclan;


import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * RESTful controller for accessing, adding, removing, and modifying schedules.
 *
 * Each method returns a ResponseEntity which represents an HTTP response for the client,
 * such as 200 Ok and 404 Not Found. Use -v (verbose) flag on cURL to see these codes
 *
 * Would probably be helpful to use a program like Postman to test all these functions.
 *
 * @author Eric
 * @version Nov 7, 2021
 */

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
public class ScheduleController {

    @GetMapping("/schedules")
    public ResponseEntity<Iterable<Schedule>> getAllSchedules() {
        return ResponseEntity.ok().body(scheduleService.getAllSchedules());
    }

    /**
     * example cURL command to modify the name of a schedule with a certain id:
     * curl -X PUT -d name=Eric http://localhost:8080/api/schedules/{insert_id_here}
     *
     * TODO maybe turn this into a PATCH request? Im not extremely confident on how to do this
     *
     * Modify a schedule by passing in variables to be replaced.
     * @param id the schedule to be modified
     * @param name the new name of the schedule
     * @return 200 Ok
     */
    @PutMapping(path="/schedules/{id}")
    public ResponseEntity<Schedule> updateSchedule(@PathVariable(value = "id") Long id,
                                                   @RequestParam(required = false) String name) {
        Schedule schedule = scheduleService.getScheduleById(id);
        if (name != null)
            schedule.setName(name);
        Schedule updatedSchedule = scheduleService.updateSchedule(schedule);
        return ResponseEntity.ok().body(updatedSchedule);
    }

    /**
     * Attempt to retrieve a schedule from the repository by id.
     * @param id the id of the schedule
     * @return 200 Ok containing the schedule contents if found.
     */
    @GetMapping("/schedules/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable(value = "id") Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok().body(schedule);
    }

    /**
     * Example cURL command to add a schedule with a certain name:
     * curl -X POST -d name=Eric http://localhost:8080/schedules
     *
     * Adds a new schedule to the repository via POST request.
     * @param name the name of the schedule owner
     * @return 201 Created if not a duplicate, else 303 See Other directing to existing schedule.
     */
    @PostMapping("/schedules")
    public ResponseEntity<Schedule> addNewSchedule(@RequestParam(value="name") String name) {
        Schedule schedule = new Schedule(name);
        Schedule addedSchedule = scheduleService.saveSchedule(schedule);
        URI location = URI.create("api/schedule/"+addedSchedule.getId()); // I dont think ur supposed to do this but idk
        return ResponseEntity.created(location).body(addedSchedule);
    }

    /**
     * Example cURL command to remove a schedule with a certain name:
     * curl -X DELETE -d name=Eric http://localhost:8080/schedule
     *
     * Removes schedule from the repository if it exists.
     * @param name the name of the schedule owner
     * @return 200 Ok if successfully removed, otherwise 404 Not Found
     */
    @DeleteMapping("/schedule")
    @Transactional // this annotation is needed for this method to work, not really sure why (:
    public ResponseEntity<Schedule> removeSchedule(@RequestParam(value="name") String name) {
        scheduleService.removeScheduleByName(name);
        return ResponseEntity.ok().build();
    }

    private final ScheduleService scheduleService;
    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
