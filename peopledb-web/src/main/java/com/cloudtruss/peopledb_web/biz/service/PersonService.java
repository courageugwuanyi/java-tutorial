package com.cloudtruss.peopledb_web.biz.service;

import com.cloudtruss.peopledb_web.biz.model.Person;
import com.cloudtruss.peopledb_web.data.FileStorageRepository;
import com.cloudtruss.peopledb_web.data.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Set;
import java.util.zip.ZipInputStream;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final FileStorageRepository fileStorageRepository;

    public PersonService(PersonRepository personRepository, FileStorageRepository fileStorageRepository) {
        this.personRepository = personRepository;
        this.fileStorageRepository = fileStorageRepository;
    }

    @Transactional
    public Person save(Person person, InputStream photoStream) {
        Person savedPerson = personRepository.save(person);
        fileStorageRepository.save(person.getPhotoFileName(), photoStream);
        return savedPerson;
    }

    public Optional<Person> findById(Long aLong) {
        return personRepository.findById(aLong);
    }

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    public Page<Person> findAll(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public void deleteAllById(Iterable<Long> ids) {
//        Iterable<Person> peopleToDelete = personRepository.findAllById(ids);
//        Stream<Person> peopleStream = StreamSupport.stream(peopleToDelete.spliterator(), false);
//        Set<String> photoFileNames = peopleStream.map(Person::getPhotoFileName).collect(toSet());
        Set<String> photoFileNames = personRepository.findFileNamesByIds(ids);
        personRepository.deleteAllById(ids);
        fileStorageRepository.deleteAllByName(photoFileNames);
    }

    public void deleteById(Long aLong) {
        personRepository.deleteById(aLong);
    }

    public void importCSV(InputStream csvFileStream) {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(csvFileStream); // read the binary zip data
            zipInputStream.getNextEntry(); // read the next entry
            InputStreamReader inputStreamReader = new InputStreamReader(zipInputStream); // read character data after unzipping the stream of bytes
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader); // read lines at a time
            bufferedReader.lines()
                    .skip(1)
                    .limit(50)
                    .map(Person::parse)
                    .forEach(personRepository::save);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
