package com.wclan;

import com.wclan.model.Schedule;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

/**
 * Repository definition. Required to make a REST app work due to the methods it comes with.
 *
 * Spring automatically implements all the functions declared in this interface.
 *
 * TODO add more functions as needed
 *
 * @author Eric but he didn't put the javadoc in so he's bad
 * @version Nov 7, 2021
 */
@CrossOrigin("*")
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    boolean existsScheduleByName(String name);

    Optional<Schedule> getScheduleById(Long id);

    Optional<Schedule> getScheduleByName(String name);

    Long removeScheduleByName(String name); // can only use Long return type...

}