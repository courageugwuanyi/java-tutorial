package com.cloudtruss.peopledb_web.web.controller;

import com.cloudtruss.peopledb_web.biz.model.Person;
import com.cloudtruss.peopledb_web.biz.service.PersonService;
import com.cloudtruss.peopledb_web.data.FileStorageRepository;
import com.cloudtruss.peopledb_web.data.PersonRepository;
import com.cloudtruss.peopledb_web.exception.StorageException;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

/**
 * The @Controller annotation is a specialization of the @Component annotation.
 * It is used to define a controller class in Spring MVC (Model-View-Controller) applications.
 * Controllers are responsible for handling HTTP requests, processing user input, interacting
 * with the service layer, and returning appropriate views or responses.
 */
@Controller
@RequestMapping("/people")
@Log4j2
public class PeopleController {

    public static final String DISPO = """
            attachment; filename="%s"
            """;
    public static final String PEOPLE = "people";
    private PersonRepository personRepository;
    private FileStorageRepository fileStorageRepository;
    private PersonService personService;

    // I don't want PeopleController to exist if there is no valid PersonRepository
    public PeopleController(PersonRepository personRepository,
                            FileStorageRepository fileStorageRepository,
                            PersonService personService) {
        this.personRepository = personRepository;
        this.fileStorageRepository = fileStorageRepository;
        this.personService = personService;
    }

    @ModelAttribute("people")
    public Page<Person> getPeople(@PageableDefault(size = 10) Pageable page) {
        // whatever is returned will be stored in the Model with the key or variable name called "people".
        return personService.findAll(page);
    }

    @ModelAttribute
    public Person getPerson() {
        Person person = new Person();
        return person;
    }

    // Handler Method. Handles GET request. The handler method should be very lean and doing only one thing
    @GetMapping
    public String showPeoplePage(Model model) {
        return "people"; // Matches people.html in templates folder
    }

    @GetMapping("/images/{resource}")
    public ResponseEntity<Resource> getResource(@PathVariable String resource) { // /{resource} is the requested parameter from the url
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, format(DISPO, resource))
                .body(fileStorageRepository.findByName(resource));
    }

    @PostMapping
    public String savePerson(Model model, @Valid Person person, Errors errors, @RequestParam("photoFileName") MultipartFile photoFIle) {
        // @Valid is validating the data, Errors captures any error msg
        // @MultipartFile allows us to work with files
        // Model is injected into this method to help add attributes to the Model
        log.info("Saving person: " + person);
        log.info("PhotoFileName: " + photoFIle.getOriginalFilename());
        log.info("Errors: " + errors.getAllErrors());
        if (errors.hasErrors()) {return "people";}
        try {
            personService.save(person, photoFIle.getInputStream());
            return "redirect:people";
        } catch (IOException e) {
            model.addAttribute("errorMsg", "System is unable to accept photo files at this time");
            return PEOPLE;
        }
    }

    // Error handler incase no error was caught
    @ExceptionHandler(StorageException.class)
    public String handleException(Exception e) {
        return "error";
    }

    @PostMapping(params = "action=delete")
    public String deletePerson(@RequestParam Optional<List<Long>> selections) {
        log.info("Deleting person: " + selections);
        if (selections.isPresent()) {
            personService.deleteAllById(selections.get());
        }
        return "redirect:people";
    }

    @PostMapping(params = "action=edit")
    public String editPerson(@RequestParam Optional<List<Long>> selections, Model model) {
        if (selections.isPresent()) {
            Optional<Person> person = personRepository.findById(selections.get().getFirst());
            model.addAttribute("person", person);
        }
        return "people";
    }

    @PostMapping(params = "action=import")
    public String importCSV(@RequestParam("csvFile") MultipartFile csvFile) {
        log.info("Importing CSV file: " + csvFile.getOriginalFilename());
        log.info("File size: " + csvFile.getSize());
        try {
            personService.importCSV(csvFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:people";
    }
}
