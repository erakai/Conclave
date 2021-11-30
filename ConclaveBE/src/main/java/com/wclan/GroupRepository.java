package com.wclan;

import com.wclan.model.Group;
import org.springframework.data.repository.CrudRepository;

/**
 * Similar to ScheduleRepository. Defines CRUD functions for Group objects
 */
public interface GroupRepository extends CrudRepository<Group, Long> {
}
