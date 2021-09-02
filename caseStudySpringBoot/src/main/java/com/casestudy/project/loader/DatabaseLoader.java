package com.casestudy.project.loader;

import com.casestudy.project.entitymodels.UserEntity;
import com.casestudy.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public DatabaseLoader(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new UserEntity("Kelly Kapowski", "baysidecheer@aol.com", "i<3screech", "123 Bayside St",
                        "Apt 3", "Beverly Hills", "CA", "USA", "90210", "555-9021"));
        this.repository.save(new UserEntity("カカロット", "songoku@hotmail.com", "songohan", "439 East District",
                null, "Mount Paozu", null, "Nippon", "100-0011", "555-5555"
        ));
    }
}
