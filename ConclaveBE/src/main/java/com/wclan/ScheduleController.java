package com.wclan;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @GetMapping("/api/schedule")
    public Schedule getSchedule(@RequestParam(value = "name", defaultValue = "(No name)") String name) {
        return new Schedule(name);
    }

}
