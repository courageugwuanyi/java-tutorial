package com.cloudtruss.peopledb_web.data;

import com.cloudtruss.peopledb_web.biz.model.Person;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@Component
public class PersonDataLoader implements ApplicationRunner {
    private PersonRepository personRepository;

    public PersonDataLoader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        if (personRepository.count() == 0) {
//            List<Person> people = List.of(new Person(null, "Pete", "Sam", "sam@pete.com", LocalDate.of(1950, 3, 13), new BigDecimal("50000")),
//                    new Person(null, "Sarah", "Smith", "sarah@smith.com", LocalDate.of(2001, 6, 16), new BigDecimal("55000")),
//                    new Person(null, "Mark", "Sam", "mark@sam.com", LocalDate.of(1997, 4, 8), new BigDecimal("30000")),
//                    new Person(null, "Hannah", "Andrew", "hannah@andrew.com", LocalDate.of(1993, 1, 9), new BigDecimal("49000")),
//                    new Person(null, "Akira", "Malik", "akira@malik.com", LocalDate.of(1999, 7, 5), new BigDecimal("24000")),
//                    new Person(null, "Jimi", "Lahm", "jimi@lahm.com", LocalDate.of(1985, 5, 23), new BigDecimal("25000"))
//            );
//
//            personRepository.saveAll(people);
//        }
    }
}
