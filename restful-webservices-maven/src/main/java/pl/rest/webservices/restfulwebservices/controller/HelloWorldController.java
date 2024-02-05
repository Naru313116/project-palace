package pl.rest.webservices.restfulwebservices.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.rest.webservices.restfulwebservices.model.HelloWorldBean;

import java.util.Locale;

@RestController
public class HelloWorldController {
    private final MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping(path = "/hello-world-internationalized")
        //en - Good Morning
        // pl - Dzień dobry
        // fr - Bonjour
    String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
