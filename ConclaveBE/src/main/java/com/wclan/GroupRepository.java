package com.wclan;

import com.wclan.model.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Similar to ScheduleRepository. Defines CRUD functions for Group objects
 */
@CrossOrigin("*")
public interface GroupRepository extends CrudRepository<Group, Long> {
}
