package exercise.service;

import com.fasterxml.jackson.core.type.TypeReference;
import exercise.HttpClient;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public String getWeatherData(long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("Not found ID = " + id));
        return client.get("http://weather/api/v2/cities/" + city.getName());
    }

    public String getWeatherDataList(String name) throws IOException  {
        List<Map<String, String>> list = new LinkedList<>();
        ObjectMapper mapper = new ObjectMapper();

        Iterable<City> cities = Objects.isNull(name) ? cityRepository.findByOrderByNameAsc() : cityRepository.findByNameStartsWithIgnoreCase(name);
        for (City city : cities) {
            String data = client.get("http://weather/api/v2/cities/" + city.getName());
            Map<String, String> line = mapper.readValue(data, new TypeReference<>() { });
            String temperature = line.get("temperature");

            list.add(Map.of(
            "temperature", temperature,
            "name", city.getName())
            );
        }

        return mapper
                .writerWithDefaultPrettyPrinter()
                .writeValueAsString(list);
    }
    // END
}
