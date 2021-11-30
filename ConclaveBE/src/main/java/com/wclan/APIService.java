package com.wclan;

import com.wclan.exception.ResourceAlreadyExistsException;
import com.wclan.exception.ResourceNotFoundException;
import com.wclan.model.Group;
import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class. Deals with "business logic"
 */
@Service
public class APIService {

    public Schedule addSchedule(Schedule schedule) {
        if (schedule.getId() == null || !scheduleRepository.existsById(schedule.getId())) {
            return scheduleRepository.save(schedule);
        } else {
            throw new ResourceAlreadyExistsException("Schedule already exists with name: " + schedule.getName());
        }
    }

    public Schedule updateSchedule(Schedule schedule) {
        if (scheduleRepository.existsById(schedule.getId())) {
            return scheduleRepository.save(schedule);
        } else {
            throw new ResourceNotFoundException("Schedule doesn't exist.");
        }
    }

    public Iterable<Schedule> findSchedulesByGroup_Id(Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Group doesn't exist."));
        return group.getSchedules();
    }

    public Schedule findScheduleByIdAndGroup_Id(Long id, Long groupId) {
        return scheduleRepository.findScheduleByIdAndGroup_Id(id, groupId)
                .orElseThrow(() -> new ResourceNotFoundException("Can't find schedule in group"));
    }

    public void deleteScheduleByIdAndGroup_Id(Long id, Long groupId) {
        if (scheduleRepository.existsByIdAndGroup_Id(id, groupId)) {
            scheduleRepository.deleteByIdAndGroup_Id(id, groupId);
        } else {
            throw new ResourceNotFoundException("Schedule doesn't exist.");
        }
    }

    public Group addGroup(Group group) {
        if (group.getId() == null || !groupRepository.existsById(group.getId()))
            return groupRepository.save(group);
        throw new ResourceAlreadyExistsException("Group already exists");
    }

    public Group updateGroup(Group group) {
        if (groupRepository.existsById(group.getId()))
            return groupRepository.save(group);
        throw new ResourceNotFoundException("Group doesn't exist.");
    }

    public void deleteGroupById(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Group doesn't exist.");
        }
    }

    public Group findGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group doesn't exist."));
    }

    public Iterable<Group> findAllGroups() {
        return groupRepository.findAll();
    }

    private final GroupRepository groupRepository;
    private final ScheduleRepository scheduleRepository;
    @Autowired
    public APIService(GroupRepository groupRepository, ScheduleRepository scheduleRepository) {
        this.groupRepository = groupRepository;
        this.scheduleRepository = scheduleRepository;
    }
}
