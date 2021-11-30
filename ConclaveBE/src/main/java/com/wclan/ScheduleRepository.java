package com.wclan;

import com.wclan.model.Group;
import com.wclan.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

/**
 * Repository definition. Required to make a REST app work due to the methods it comes with.
 *
 * Spring automatically implements all the functions declared in this interface.
 *
 * TODO add more functions as needed
 *
 * @author Eric
 * @version Nov 7, 2021
 */
@CrossOrigin("*")
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    Optional<List<Schedule>> findSchedulesByGroup(Group group);

}