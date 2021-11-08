package com.wclan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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