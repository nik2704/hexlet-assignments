package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPeople() throws JsonProcessingException {
        String query = "SELECT * FROM person";
        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> ls = jdbc.queryForList(query);
        return ResponseEntity.ok(mapper.writeValueAsString(ls));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getSinglePeopson(@PathVariable Long id) throws JsonProcessingException {
        String query = "SELECT * FROM person WHERE id = " + id;
        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, Object>> ls = jdbc.queryForList(query);
        return ResponseEntity.ok(mapper.writeValueAsString(ls));
    }
    // END
}
