package com.wclan;

import com.wclan.exception.ResourceAlreadyExistsException;
import com.wclan.exception.ResourceNotFoundException;
import com.wclan.model.Group;
import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer class. Deals with all the "business logic"
 * ... I don't really know what that means either
 */
@Service
public class ScheduleService {
    public Schedule addSchedule(Schedule schedule) {
        if (!scheduleRepository.existsById(schedule.getId()))
            return scheduleRepository.save(schedule);
        throw new ResourceAlreadyExistsException("Schedule already exists with name: " + schedule.getName());
    }

    public Schedule updateSchedule(Schedule schedule) {
        if (scheduleRepository.existsById(schedule.getId()))
            return scheduleRepository.save(schedule);
        throw new ResourceNotFoundException("Schedule doesn't exist.");
    }

    public List<Schedule> findSchedulesByGroup(Group group) {
        return scheduleRepository.findSchedulesByGroup(group)
                .orElseThrow(() -> new ResourceNotFoundException("Group doesn't exist"));
    }

    public Schedule findScheduleById(Long id) {
       return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find schedule with id: " + id));
    }

    public void deleteScheduleById(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Schedule doesn't exist.");
        }
    }

    private final ScheduleRepository scheduleRepository;
    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }
}
