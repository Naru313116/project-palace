package pl.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.rest.webservices.restfulwebservices.model.HelloWorldBean;

@RestController
public class HelloWorldController {

    @GetMapping(path = "/hello-world")
    String helloWorld() {
        return "Hello world";
    }

    @GetMapping(path = "/hello-world-bean")
    HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello world");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(name);
    }
}
