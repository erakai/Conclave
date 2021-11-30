package com.wclan;


import com.wclan.model.Group;
import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * RESTful controller for accessing, adding, removing, and modifying schedules.
 *
 * Each method returns a ResponseEntity which represents an HTTP response for the client,
 * such as 200 Ok and 404 Not Found. Use -v (verbose) flag on cURL to see these codes
 *
 * Would probably be helpful to use a program like Postman to test all these functions.
 *
 * For Postman purposes, OpenAPI can be viewed at localhost:8080/swagger-ui.html (UI version) or
 * localhost:8080/v3/api-docs (JSON version)
 *
 * @author Eric
 * @version Nov 7, 2021
 */

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000"})
public class APIController {

    /**
     * example cURL command to modify the name of a schedule with a certain id:
     * curl -X PUT -d name=Eric http://localhost:8080/api/schedules/{insert_id_here}
     *
     * TODO maybe turn this into a PATCH request? Im not extremely confident on how to do this
     *
     * Modify a schedule by passing in variables to be replaced.
     * @param groupId the id of the group this schedule is in
     * @param scheduleId the id of the schedule
     * @param name (optional) the new name of the schedule
     * @param timeSlotsString (optional) the new timeslots of the schedule
     * @return 200 Ok
     */
    @PutMapping(path="/groups/{gId}/schedules/{sId}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable(value="gId") Long groupId,
            @PathVariable(value="sId") Long scheduleId,
            @RequestParam(value="name", required=false) String name,
            @RequestParam(value="timeSlotsString", required=false) String timeSlotsString) {
        // maybe do the modifying part on the service layer?
        Schedule schedule = apiService.findScheduleByIdAndGroup_Id(scheduleId, groupId);
        if (name != null)
            schedule.setName(name);
        if (timeSlotsString != null)
            schedule.setTimeSlotsString(timeSlotsString);
        Schedule updatedSchedule = apiService.updateSchedule(schedule);
        return ResponseEntity.ok().body(updatedSchedule);
    }

    /**
     * Attempt to retrieve a schedule from the repository by id.
     * @param groupId the id of the group this schedule is in
     * @param scheduleId the id of the schedule
     * @return 200 Ok containing the schedule contents if found.
     */
    @GetMapping("/groups/{gId}/schedules/{sId}")
    public ResponseEntity<Schedule> findScheduleByIdAndGroup_Id(
            @PathVariable(value="gId") Long groupId,
            @PathVariable(value="sId") Long scheduleId) {
        Schedule schedule = apiService.findScheduleByIdAndGroup_Id(scheduleId, groupId);
        return ResponseEntity.ok().body(schedule);
    }

    /**
     * Example cURL command to remove a schedule with a certain id:
     * curl -X DELETE localhost:8080/api/schedules/1
     *
     * Removes schedule from the repository if it exists.
     * @param groupId the id of the group this schedule is in
     * @param scheduleId the id of the schedule
     * @return 200 Ok if successfully removed, otherwise 404 Not Found
     */
    @DeleteMapping("/groups/{gId}/schedules/{sId}")
    @Transactional // this annotation is needed for this method to work, not really sure why (:
    public ResponseEntity<Void> deleteScheduleByIdAndGroup_Id(
            @PathVariable(value="gId") Long groupId,
            @PathVariable(value="sId") Long scheduleId) {
        apiService.deleteScheduleByIdAndGroup_Id(scheduleId, groupId);
        return ResponseEntity.ok().build();
    }

    // do we need to delete the corresponding schedules too or is that already handled?
    @DeleteMapping("/groups/{id}")
    @Transactional
    public ResponseEntity<Void> deleteGroupById(@PathVariable Long id) {
        apiService.deleteGroupById(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Adds a new schedule to the repository via POST request. Schedules must be added with a
     * corresponding group.
     * @param groupId the id of the group of schedules
     * @param name the name of the schedule owner
     * @param timeSlotsString the string representation of the time slots of the schedule
     * @return 201 Created if not a duplicate, else 303 See Other directing to existing schedule.
     */
    @PostMapping("/groups/{gId}/schedules")
    public ResponseEntity<Schedule> addScheduleToGroup(
            @PathVariable(value="gId") Long groupId,
            @RequestParam(value="name") String name,
            @RequestParam(value="timeSlotsString") String timeSlotsString) {
        Group group = apiService.findGroupById(groupId);
        Schedule schedule = new Schedule(name, timeSlotsString, group);
        Schedule addedSchedule = apiService.addSchedule(schedule);
        // I dont think ur supposed to do this but idk
        URI location = URI.create("api/groups/" + groupId + "/schedules/" + addedSchedule.getId());
        return ResponseEntity.created(location).body(addedSchedule);
    }

    @PostMapping("/groups")
    public ResponseEntity<Group> addGroup(@RequestParam(value="name") String name) {
        Group group = new Group(name);
        Group addedGroup = apiService.addGroup(group);
        URI location = URI.create("api/groups/"+addedGroup.getId());
        return ResponseEntity.created(location).body(group);
    }

    @GetMapping("/groups/{gId}/schedules")
    public ResponseEntity<Iterable<Schedule>> findSchedulesByGroup_Id(@PathVariable(value="gId") Long groupId) {
        Iterable<Schedule> schedules = apiService.findSchedulesByGroup_Id(groupId);
        return ResponseEntity.ok().body(schedules);
    }

    @GetMapping("/groups")
    public ResponseEntity<Iterable<Group>> findAllGroups() {
        Iterable<Group> groups = apiService.findAllGroups();
        return ResponseEntity.ok().body(groups);
    }

    // defines the service that the controller will use. Spring automatically calls the constructor.
    private final APIService apiService;
    @Autowired
    public APIController(APIService apiService) {
        this.apiService = apiService;
    }
}
