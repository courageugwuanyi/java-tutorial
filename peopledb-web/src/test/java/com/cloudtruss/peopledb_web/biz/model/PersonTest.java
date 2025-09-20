package com.cloudtruss.peopledb_web.biz.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void canParsePerson() {
        String csvLine = ""; // I needed to extract a line of the String from the CSV file. I will do that later but trust me the test will pass when I do so
        Person person = Person.parse(csvLine);
        assertThat(person.getFirstName()).isEqualTo("");
    }
}