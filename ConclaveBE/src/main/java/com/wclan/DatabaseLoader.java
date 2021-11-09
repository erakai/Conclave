package com.wclan;

import com.wclan.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Component annotation means spring will automatically see the class. Implementing CLR means that after the beans
 * are created it will run and provide pre-loaded data.
 *
 * @author Eric but he didn't put the javadoc in so he's bad
 * @version Nov 7, 2021
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final ScheduleRepository repository;

    @Autowired
    public DatabaseLoader(ScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Schedule("Eric"));
        this.repository.save(new Schedule("Kai"));
        this.repository.save(new Schedule("Kian"));
    }
}