package pl.rest.webservices.restfulwebservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rest.webservices.restfulwebservices.model.FilteringBean;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public FilteringBean fitering() {
        return new FilteringBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<FilteringBean> fiteringList() {
        return Arrays.asList(
                new FilteringBean("value1", "value2", "value3"),
                new FilteringBean("value4", "value5", "value6"));
    }
}
