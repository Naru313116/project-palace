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

    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstOfPersonRequestParameter() {
        return new PersonV1("Bon Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=1")
    public PersonV1 getFirstOfPersonRequestHeader() {
        return new PersonV1("Bon Charlie");
    }

    @GetMapping(path = "/person", headers = "X-API-VERSION=2")
    public PersonV2 getSecondOfPersonRequestHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstOfPersonAcceptHeader() {
        return new PersonV1("Bon Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondOfPersonAcceptHeader() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
