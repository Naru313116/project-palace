package pl.rest.webservices.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.rest.webservices.restfulwebservices.model.FilteringBean;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        //MappingJackson
        FilteringBean filteringBean = new FilteringBean("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteringBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ExcludeField2", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {

        List<FilteringBean> filteringBeanList = Arrays.asList(
                new FilteringBean("value1", "value2", "value3"),
                new FilteringBean("value4", "value5", "value6"));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filteringBeanList);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.serializeAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("ExcludeField2", filter);
        mappingJacksonValue.setFilters(filters);


        return mappingJacksonValue;
    }
}
