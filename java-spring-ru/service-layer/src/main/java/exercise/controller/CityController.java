package exercise.controller;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;


@RestController
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCity(@PathVariable long id) throws Exception {
        return weatherService.getWeatherData(id);
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCities(@RequestParam(required = false) String name) throws IOException {
        return weatherService.getWeatherDataList(name);
    }
    // END
}

