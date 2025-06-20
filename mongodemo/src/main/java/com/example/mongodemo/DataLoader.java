package com.example.mongodemo;

import com.example.mongodemo.domain.Person;
import com.example.mongodemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final Environment env;

    private final PersonRepository repo;

    @Override
    public void run(String... args) throws Exception {

        if(!"true".equals(env.getProperty("data.init", "false"))){
            System.out.println("DataLoader: Skipped");
            return;
        }
//        System.out.println("repo.count():" + repo.count());
//        if(repo.count() > 0)return;

        Random rand = new Random();
        IntStream.rangeClosed(1, 100)
                .mapToObj(i -> new Person(null, "User" + i, rand.nextInt(40) + 20))
                .forEach(repo::save);
        System.out.println("Dummy data inserted.");
    }
}
