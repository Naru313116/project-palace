package pl.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rest.webservices.restfulwebservices.model.Name;
import pl.rest.webservices.restfulwebservices.model.PersonV1;
import pl.rest.webservices.restfulwebservices.model.PersonV2;

@RestController
public class VersioningPersonController {
    @GetMapping("/v1/person")
    public PersonV1 getFirstOfPerson() {
        return new PersonV1("Bon Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
