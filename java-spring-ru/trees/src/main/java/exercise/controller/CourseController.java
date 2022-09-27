package exercise.controller;

import com.fasterxml.jackson.annotation.OptBoolean;
import exercise.model.Course;
import exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous") //, produces = MediaType.APPLICATION_JSON_VALUE
    public Iterable<Course> getPreviousCourses(@PathVariable long id) throws Exception {
        Course course = courseRepository.findById(id);

        if (course != null) {
            if (course.getPath() != null) {
                return Arrays.stream(course.getPath().split("\\."))
                        .map(couseId -> courseRepository.findById(Integer.parseInt(couseId)))
                        .collect(Collectors.toList());
            } else {
                return List.of();
            }
        }

        throw new Exception("Processing is not possible");
    }
    // END

}
