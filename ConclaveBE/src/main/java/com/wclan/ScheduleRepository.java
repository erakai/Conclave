package com.wclan;

import com.wclan.model.Schedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Repository definition. Required to make a REST app work due to the methods it comes with.
 *
 * @author Eric but he didn't put the javadoc in so he's bad
 * @version Nov 7, 2021
 */
@CrossOrigin("*")
public interface ScheduleRepository extends CrudRepository<Schedule, Long> { }