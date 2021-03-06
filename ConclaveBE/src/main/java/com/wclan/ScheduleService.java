package com.wclan;

import com.wclan.exception.ResourceAlreadyExistsException;
import com.wclan.exception.ResourceNotFoundException;
import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class. Deals with all the "business logic"
 * ... I don't really know what that means either
 */
@Service
public class ScheduleService {
    public Schedule saveSchedule(Schedule schedule) throws ResourceAlreadyExistsException {
        if (!scheduleRepository.existsScheduleByName(schedule.getName()))
            return scheduleRepository.save(schedule);
        throw new ResourceAlreadyExistsException("Schedule already exists with name: " + schedule.getName());
    }

    public Schedule updateSchedule(Schedule schedule) throws ResourceNotFoundException {
        if (scheduleRepository.existsById(schedule.getId()))
            return scheduleRepository.save(schedule);
        throw new ResourceNotFoundException("Schedule doesn't exist.");
    }

    public Schedule getScheduleById(Long id) throws ResourceNotFoundException {
       return scheduleRepository.getScheduleById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find schedule with id="+id));
    }

    public Schedule getScheduleByName(String name) throws ResourceNotFoundException {
        return scheduleRepository.getScheduleByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find schedule with name="+name));
    }

    public void removeScheduleByName(String name) throws ResourceNotFoundException {
        Long result = scheduleRepository.removeScheduleByName(name);
        if (result < 1)
            throw new ResourceNotFoundException("Schedule doesn't exist.");
    }


    private final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
}
